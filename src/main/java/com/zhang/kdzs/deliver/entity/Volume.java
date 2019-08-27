package com.zhang.kdzs.deliver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/26 10:20
 * @description：
 * @modified By：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Volume implements Serializable {

    private Double length;
    private Double width;
    private Double height;


    public Volume(String length,String width,String height) throws NumberFormatException{
        this.length = Double.parseDouble(length);
        this.width = Double.parseDouble(width);
        this.height=Double.parseDouble(height);
    }

    public Double calculateTotal(){
        return length*width*height;
    }




}
