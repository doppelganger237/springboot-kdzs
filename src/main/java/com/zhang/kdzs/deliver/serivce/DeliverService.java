package com.zhang.kdzs.deliver.serivce;

import com.zhang.kdzs.deliver.entity.LogisticsCompany;
import com.zhang.kdzs.deliver.entity.LogisticsJob;
import com.zhang.kdzs.deliver.entity.LogisticsResult;

import java.util.List;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/10 22:21
 * @description： 每次加方法要改三个类 累死我了
 * @modified By：
 */


public interface DeliverService {


    void addJob(LogisticsJob logisticsJob);

    void removeJob(long id);

    LogisticsJob getJobById(long id);

   // void updateJobWithResults(LogisticsJob logisticsJob);

    void updateJob(LogisticsJob job);

    List<LogisticsJob> getJobsByUserId(long id);


    List<LogisticsJob> getJobsByUserIdTable(long id,int page,int limit);


    LogisticsCompany getCompanyById(long id);

    List<LogisticsCompany> getCompanies();


    void addResult(LogisticsResult result);
    void addResultList(List<LogisticsResult> results) ;

  //void saveResults(List<LogisticsResult> results);
}
