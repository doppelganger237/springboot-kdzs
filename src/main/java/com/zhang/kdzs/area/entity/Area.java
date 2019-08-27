package com.zhang.kdzs.area.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/12 18:08
 * @description：
 * @modified By：
 */
@Data
public class Area implements Serializable {

    private Integer id;

    private String code;

    private String name;

    private String citycode;


}
