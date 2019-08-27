package com.zhang.kdzs.deliver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/10 22:09
 * @description：物流公司
 * @modified By：
 */
@Data
@AllArgsConstructor
public class LogisticsCompany implements Serializable {


    // 物流公司ID
    private long id;

    // 物流公司名称
    private String name;




}
