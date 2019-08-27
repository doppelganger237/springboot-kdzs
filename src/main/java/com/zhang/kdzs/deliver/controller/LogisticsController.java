package com.zhang.kdzs.deliver.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhang.kdzs.area.service.AreaService;
import com.zhang.kdzs.common.Exception.GlobalExcaption;
import com.zhang.kdzs.common.dto.Result;
import com.zhang.kdzs.common.dto.StatusCode;
import com.zhang.kdzs.deliver.entity.LogisticsCompany;
import com.zhang.kdzs.deliver.entity.LogisticsJob;
import com.zhang.kdzs.deliver.entity.LogisticsResult;
import com.zhang.kdzs.deliver.entity.Volume;
import com.zhang.kdzs.deliver.serivce.DeliverService;
import com.zhang.kdzs.deliver.task.JobTaskManager;
import com.zhang.kdzs.system.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/12 12:48
 * @description：
 * @modified By：
 */
@Slf4j
@Controller
@RequestMapping(value = "/wuliu")
public class LogisticsController {


    @Autowired
    JobTaskManager manager;

    @Autowired
    DeliverService deliverService;

    @Autowired
    AreaService areaService;

    @GetMapping(value = "/add")
    public String add() {

        return "wuliu/add";
    }


    @GetMapping(value = "/task")
    public String task() {

        return "wuliu/task";
    }

    @GetMapping(value = "/info}")
    public String info2Task() {

        return "redirect:/wuliu/task";
    }

    @GetMapping(value = "/info/{id}")
    public String info(@PathVariable long id) {


        return "wuliu/info";
    }


    @PostMapping(value = "/info/{id}")
    @ResponseBody
    public JSONObject postInfo(@PathVariable long id) {

        JSONObject jsonObject = new JSONObject();
        LogisticsJob job = deliverService.getJobById(id);
        if (job == null) {
            throw new GlobalExcaption("任务不存在");
        }

        List<LogisticsResult> results = job.getResults();
        JSONArray array = new JSONArray();

        for (LogisticsResult r : results) {
            String companyName = null;

            try {
                companyName = deliverService.getCompanyById(r.getParentId()).getName();
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            JSONObject o = new JSONObject();
            o.put("name", companyName);
            o.put("subName", r.getName());
            o.put("price", r.getPrice());


            array.add(o);
        }

        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", array.size());
        jsonObject.put("data", array);

        log.info(jsonObject.toJSONString());
        return jsonObject;

    }


    /**
     * @Description: 我不知道为什么不能直接返回 非要用FastJson
     * @Param: [page, limit]
     * @return: java.lang.String
     * @date: 2019/8/17
     */
    @PostMapping(value = "/task")
    @ResponseBody
    public String taskPost(int page, int limit) {

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<LogisticsJob> jobs = deliverService.getJobsByUserIdTable(user.getId(), page, limit);


        jobs.forEach(job -> {
            job.setFrom(areaService.fromAreaCode(job.getFrom()).toString());
            job.setDestination(areaService.fromAreaCode(job.getDestination()).toString());
        });


        JSONArray array = JSONArray.parseArray(JSON.toJSONStringWithDateFormat(jobs, "yyyy-MM-dd HH:mm:ss"));
        for (int i = 0; i < array.size(); i++) {

            JSONObject o = array.getJSONObject(i);
            o.put("percent", manager.getJobFinishPercent(jobs.get(i)));
        }

        JSONObject jsonObject = new JSONObject();


        jsonObject.put("code", 0);
        jsonObject.put("msg", "");

        // 以后再优化 省点事
        jsonObject.put("count", deliverService.getJobsByUserId(user.getId()).size());
        jsonObject.put("data", array);

        // log.info(JSON.toJSONStringWithDateFormat(jobs,"yyyy-MM-dd HH:mm:ss"));
        // log.info(JSON.toJSONStringWithDateFormat(jsonObject, "yyyy-MM-dd HH:mm:ss"));
        log.info(JSON.toJSONString(jsonObject));
        return JSON.toJSONString(jsonObject);


    }


    @PostMapping(value = "/addPost")
    @ResponseBody
    public Result addPost(@RequestBody Map<String, String> map) {

        Result result = new Result();
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        LogisticsJob job = new LogisticsJob();

        try {
            job.setFrom(map.get("area"));
            job.setDestination(map.get("area_to"));
            job.setStartTime(new Date());
            job.setUserId(user.getId());
            job.setWeight(Double.parseDouble(map.get("weight")));
            job.setVolume(new Volume(map.get("length"), map.get("width"), map.get("height")));

            //  job.setVolume(Double.parseDouble(map.get("volume")));
            //骚的一批的stream
            job.setUseCompanyId(deliverService.getCompanies().stream().map(LogisticsCompany::getId).collect(Collectors.toList()));

            job.setStatus(0);
        } catch (Exception e) {
            throw new GlobalExcaption("提交的数据不正确或者处理参数失败");
        }

        log.info(job.toString());

        try {

            deliverService.addJob(job);
            result.setCode(StatusCode.SUCCESS);

            manager.submit(job);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(StatusCode.ERROR);

            //以后换成enum或者config消息
            result.setMessage("提交任务失败!");
        }

        return result;


    }

}
