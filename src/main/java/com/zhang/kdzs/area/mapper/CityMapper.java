package com.zhang.kdzs.area.mapper;

import com.zhang.kdzs.area.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/12 18:15
 * @description：
 * @modified By：
 */
@Mapper
public interface CityMapper {



    @Select("select * from city where code = #{id} limit 1")
    City getById(String id);

    @Select("select * from city where provincecode = #{id}")
    List<City> getCityByProvinceId(@Param("id") String id);
}
