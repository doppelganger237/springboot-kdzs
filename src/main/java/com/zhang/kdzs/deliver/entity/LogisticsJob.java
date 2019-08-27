package com.zhang.kdzs.deliver.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/10 22:11
 * @description：查询最佳物流公司的任务
 * @modified By：
 */
@Data
public class LogisticsJob implements Serializable {


    // 任务ID
    private long id;


    // 用户ID
    private long userId;

    private Double weight;

    private Volume volume;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    // 处理状态 0处理中 1完成 2失败

    private int status;

    // 出发地
    private String from;

    // 到达地
    private String destination;



    // 处理物流 K:物流公司ID V:价格
    //private Map<String,Double> results;


    private List<Long> useCompanyId;

    private List<LogisticsResult> results;

}
