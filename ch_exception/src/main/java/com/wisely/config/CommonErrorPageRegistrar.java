package com.wisely.config;

import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
//@Component
public class CommonErrorPageRegistrar implements ErrorPageRegistrar{
	@Override
	public void registerErrorPages(ErrorPageRegistry registry) {
		ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
		ErrorPage args = new ErrorPage(IllegalArgumentException.class, "/args.html");
		 // 可以配置多个, 用"," 隔开
		registry.addErrorPages(e404,args);
		
	}
}
