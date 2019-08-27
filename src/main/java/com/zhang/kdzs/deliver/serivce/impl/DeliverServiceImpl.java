package com.zhang.kdzs.deliver.serivce.impl;

import com.zhang.kdzs.deliver.entity.LogisticsCompany;
import com.zhang.kdzs.deliver.entity.LogisticsJob;
import com.zhang.kdzs.deliver.entity.LogisticsResult;
import com.zhang.kdzs.deliver.mapper.LogisticsMapper;
import com.zhang.kdzs.deliver.serivce.DeliverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/11 18:39
 * @description：
 * @modified By：
 */
@Service
@Slf4j
public class DeliverServiceImpl implements DeliverService {

    @Autowired
    private LogisticsMapper logisticsMapper;


    @Override
    public void addJob(LogisticsJob logisticsJob) {

        logisticsMapper.addJob(logisticsJob);
    }


    @Override
    public void removeJob(long id) {

        logisticsMapper.deleteJobById(id);
    }

    @Override
    public LogisticsJob getJobById(long id) {

        return logisticsMapper.getJobById(id);
    }

 /*   @Override
    public void updateJobWithResults(LogisticsJob logisticsJob) {
        logisticsMapper.updateJob(logisticsJob);
        logisticsMapper.saveResults(logisticsJob.getResults());
    }
*/
    @Override
    public void updateJob(LogisticsJob job) {

        logisticsMapper.updateJob(job);
    }

    @Override
    public List<LogisticsJob> getJobsByUserId(long id) {

        return logisticsMapper.getJobsByUserId(id);
    }


    @Override
    public List<LogisticsJob> getJobsByUserIdTable(long id, int page, int limit) {

        page = (page - 1) * limit;


        return logisticsMapper.getJobsByUserIdTable(id, page, limit);
    }


    @Override
    public LogisticsCompany getCompanyById(long id) {

        return logisticsMapper.getCompanyById(id);
    }

    @Override
    public List<LogisticsCompany> getCompanies() {
        return logisticsMapper.getCompanies();
    }

    @Override
    public void addResult(LogisticsResult result) {
        logisticsMapper.addResult(result);
    }

    @Override
    public void addResultList(List<LogisticsResult> results) {
        logisticsMapper.addResultList(results);
    }

   /* @Override
    public void saveResults(List<LogisticsResult> results) {

        logisticsMapper.saveResults(results);
    }*/


}
