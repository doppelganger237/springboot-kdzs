package com.zhang.kdzs.system.entity.system;

import lombok.Data;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/8 21:45
 * @description：
 * @modified By：
 */

@Data
public class SystemLog {

    private Long id;

    private String username;

    private String operation;

    private Long time;

    private String method;

    private String params;

    private String ip;
}
