package com.zhang.kdzs.common.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/17 20:56
 * @description：
 * @modified By：
 */

@Data
@AllArgsConstructor
public class GlobalExcaption extends  RuntimeException {

    private String msg;
}
