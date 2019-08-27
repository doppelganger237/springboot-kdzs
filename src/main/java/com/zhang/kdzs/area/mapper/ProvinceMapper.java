package com.zhang.kdzs.area.mapper;

import com.zhang.kdzs.area.entity.Province;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/12 18:11
 * @description：
 * @modified By：
 */
@Mapper
public interface ProvinceMapper {

    @Select("select * from province where code = #{id} limit 1")
    Province getById(String id);


    @Select("select * from province")
    List<Province> list();
}
