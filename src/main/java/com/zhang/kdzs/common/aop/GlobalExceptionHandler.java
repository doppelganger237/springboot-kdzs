package com.zhang.kdzs.common.aop;

import com.zhang.kdzs.common.Exception.GlobalExcaption;
import com.zhang.kdzs.common.dto.Result;
import com.zhang.kdzs.common.dto.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/8 20:25
 * @description： 统一异常处理, 暂时用不着, 登录产生的异常都被捕获了
 * @modified By：
 */


@ControllerAdvice
public class GlobalExceptionHandler {

  /*  @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String unAuth(AuthenticationException e) {

        return "/login";
    }*/

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exception(Exception e) {

      e.printStackTrace();

      return  new Result(StatusCode.ERROR,"系统错误",null);
    }

    @ExceptionHandler(value = GlobalExcaption.class)
    @ResponseBody
    public Result globalExcaption(GlobalExcaption e, HttpServletResponse response){
        e.printStackTrace();
        return  new Result(response.getStatus(),e.getMsg(),null);
    }
}


