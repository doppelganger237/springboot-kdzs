package com.zhang.kdzs.deliver.task;

import com.zhang.kdzs.deliver.entity.LogisticsJob;
import com.zhang.kdzs.deliver.entity.LogisticsResult;

import java.util.List;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/12 9:51
 * @description：
 * @modified By：
 */
public interface IWuLiuTask {

     long getID();
     List<LogisticsResult>  handleResult(LogisticsJob job,List<Object> objects);
}
