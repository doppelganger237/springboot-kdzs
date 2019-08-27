package com.zhang.kdzs.deliver.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/16 8:43
 * @description：
 * @modified By：
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class LogisticsResult implements Serializable {


    // JobId
    Long id;

    Long jobId;

    // 名:ex.顺丰次日达
    String name;

    // ex.顺丰物流
    long parentId;


    Double price;

    public LogisticsResult(Long jobId,String name,long parentId,Double price){
        this.jobId=jobId;
        this.name=name;
        this.parentId =parentId;
        this.price=price;

    }
}
