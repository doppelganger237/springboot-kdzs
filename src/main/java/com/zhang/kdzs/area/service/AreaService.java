package com.zhang.kdzs.area.service;

import com.zhang.kdzs.area.entity.Adress;
import com.zhang.kdzs.area.entity.Area;
import com.zhang.kdzs.area.entity.City;
import com.zhang.kdzs.area.entity.Province;
import com.zhang.kdzs.area.mapper.AreaMapper;
import com.zhang.kdzs.area.mapper.CityMapper;
import com.zhang.kdzs.area.mapper.ProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/12 18:18
 * @description：
 * @modified By：
 */
@Service
public class AreaService {


    @Autowired
    private ProvinceMapper provinceMapper;

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private AreaMapper areaMapper;


    public List<Province> getAllProvince() {
        return provinceMapper.list();
    }

    public List<City> getCityByProvinceId(String id) {
        return cityMapper.getCityByProvinceId(id);
    }

    public List<Area> getAreaByCityId(String id) {
        return areaMapper.getAreaByCityId(id);
    }

    public Adress fromAreaCode(String areaCode){


        Area area = areaMapper.getById(areaCode);

        City city = cityMapper.getById(area.getCitycode());

        Province province = provinceMapper.getById(city.getProvincecode());

        Adress adress = new Adress(province,city,area);

        return  adress;



    }

  /*  public void submit(Map<String,String>map){


    }*/

    /*public Adress getAdressFromString(Map<String,String>map){



    }*/
}
