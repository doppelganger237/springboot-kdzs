package com.zhang.kdzs.deliver.mapper;

import com.zhang.kdzs.deliver.entity.LogisticsCompany;
import com.zhang.kdzs.deliver.entity.LogisticsJob;
import com.zhang.kdzs.deliver.entity.LogisticsResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/11 18:40
 * @description：
 * @modified By：
 */
@Mapper
public interface LogisticsMapper {


    int addJob(LogisticsJob logisticsJob);

    void deleteJobById(long logisticsJob);

    LogisticsJob getJobById(long id);


    List<LogisticsJob> getJobsByUserId(long id);

    List<LogisticsJob> getJobsByUserIdTable(long id, int page, int limit);


    LogisticsCompany getCompanyById(@Param("id") long id);

    List<LogisticsCompany> getCompanies();

    int addResult(LogisticsResult result);

    void addResultList(List<LogisticsResult> results);

    int updateJob(LogisticsJob job);

}
