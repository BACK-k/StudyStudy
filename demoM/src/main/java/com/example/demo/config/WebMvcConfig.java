package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//** WebMvcConfigurer
//=> 스프링의 자동설정에 원하는 설정을 추가 설정할수있는 메서드들을 제공하는 인터페이스. 
//=> 스프링부트 컨트롤러 매핑메서드에서는 "/" 무시됨 -> addViewControllers 메서드로 해결  
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// WebMvcConfigurer.super.addViewControllers(registry);

		// => viewName 을 사용하는 경우
		// '/'요청이 들어왔을 때 'home'요청으로 들어가게함
		// "forward:/WEB-INF/views/home.jsp"_serverTime출력안됨 , "redirect:/home"
		// => 단, @RestController 설정한 RTestController 에
		// @GetMapping("/") 정의한 매핑메서드가 있으면 우선적용됨 (충돌은 없음)
		registry.addViewController("/").setViewName("redirect:/home");
	} // addViewControllers

} // class
