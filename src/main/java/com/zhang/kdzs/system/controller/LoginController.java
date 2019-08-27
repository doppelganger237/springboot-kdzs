package com.zhang.kdzs.system.controller;

import com.zhang.kdzs.common.dto.Result;
import com.zhang.kdzs.common.dto.StatusCode;
import com.zhang.kdzs.common.enums.AccountStatusEnums;
import com.zhang.kdzs.system.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @PostMapping("/login")
    public Result login(@Validated User user, BindingResult bindResult, boolean remember) {

        String username = user.getUsername();

        String password = user.getPassword();
        Result result = new Result(StatusCode.ERROR);
        System.out.println(username + password);
        //System.out.println(bindResult.hasErrors());
        if (bindResult.hasErrors()) {

            result.setMessage(bindResult.getFieldError().getDefaultMessage());
            return result;
        }

        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {

            subject.logout();
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, remember);

        Map<String, Serializable> map = new HashMap<String, Serializable>();

        try {
            subject.login(token);
            map.put("token", subject.getSession().getId());
            result.setCode(StatusCode.SUCCESS);
            //result.setData(map);
        } catch (UnknownAccountException e) {
            result.setMessage(AccountStatusEnums.ACCOUNT_UNKNOWN.getInfo());
        } catch (IncorrectCredentialsException e) {
            result.setMessage(AccountStatusEnums.ACCOUNT_ERROR.getInfo());
        } catch (LockedAccountException e) {
            result.setMessage(AccountStatusEnums.LOCED.getInfo());
        } catch (Exception e) {
            result.setCode(StatusCode.UKNOWN);
        }

        return result;

    }

    @GetMapping(value = "/logout")
    public Result logout() {

        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result(StatusCode.SUCCESS);
    }

}
