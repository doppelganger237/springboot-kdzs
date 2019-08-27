package com.zhang.kdzs.area.controller;

import com.zhang.kdzs.area.service.AreaService;
import com.zhang.kdzs.common.dto.Result;
import com.zhang.kdzs.common.dto.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/12 18:36
 * @description：
 * @modified By：
 */
@RestController
@RequestMapping(value = "/area")
@Slf4j
public class AreaController {

    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/list")
    public Result list() {

        return StatusCode.success(areaService.getAllProvince());

    }

    @RequestMapping(value = "/getCityByProvinceId")
    public Result getCityByProvinceId(String id) {

        return StatusCode.success(areaService.getCityByProvinceId(id));
    }

    //   根据城市id获取区域数据后直接使用@ResponseBody装成json数据
    @RequestMapping(value = "/getAreaByCityId")
    public Result getAreaByCityId(String id) {

        return StatusCode.success(areaService.getAreaByCityId(id));
    }


}
