package com.zhang.kdzs.common.enums;

import lombok.Getter;
import lombok.Setter;

public enum AccountStatusEnums {
	SUCCESS( "操作成功"), 
	ACCOUNT_UNKNOWN( "账户不存在"), 
	ACCOUNT_ERROR( "用户名或密码错误"), 
	ALREADY_LOGIN("已经登录"),
	LOCED("错误次数过多,已锁定"),
	CODE_ERROR( "验证码错误"),
	SYSTEM_ERROR( "系统错误"), 
	PARAM_ERROR( "参数错误"), 
	PARAM_REPEAT( "参数已存在"), 
	PERMISSION_ERROR( "没有操作权限"),
	OTHER( "其他错误");


	@Getter
	@Setter
	private String info;

	AccountStatusEnums( String info) {
		this.info = info;
	}
}
