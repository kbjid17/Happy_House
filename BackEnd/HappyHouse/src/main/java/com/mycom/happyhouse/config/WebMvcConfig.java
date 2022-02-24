package com.mycom.happyhouse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mycom.happyhouse.common.LoginInterceptor;

@Configuration // to many request, 원하는 곳으로 제대로 이동을 안한다 -> interceptor 의심, 주석처리해보고 테스트
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
		.addPathPatterns("/**")
		//.excludePathPatterns("/login/**", "/register/**"); // static folder // jsp 사용할 경우
		//.excludePathPatterns("/", "/index.html", "/login.html", "/register.html") // single page web
		.excludePathPatterns("/") // main
//		.excludePathPatterns("/index.html") // main
//		.excludePathPatterns("/css/**") // css
//		.excludePathPatterns("/img/**") // 이미지
//		.excludePathPatterns("/js/**") // js 파일들
		.excludePathPatterns("/users") // 회원가입
		.excludePathPatterns("//users/login") // 로그인
		.excludePathPatterns("/codes/**"); // 공통 코드 : 회원 종류
	}
	
//	@Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("/static/login.html");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    }
}
