package com.zhang.kdzs.area.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/12 21:05
 * @description：
 * @modified By：
 */
@Data
@AllArgsConstructor
public class Adress implements Serializable {

    private Province province;

    private City city;

    private Area area;


    @Override
    public String toString() {

        return province.getName()+city.getName()+area.getName();
    }
}
