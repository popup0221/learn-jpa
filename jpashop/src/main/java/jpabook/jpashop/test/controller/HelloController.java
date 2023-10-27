package jpabook.jpashop.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HelloController {
	
	@GetMapping("hello")
	public String hello(Model model) {
		
		/**
		 * SLF4J는 Simple Logging Facade for Java의 약자로 라이브러리 인터페이스 역할이며,
		 * Logback이 실질적인 로깅 라이브러리 구현체로, SLF4J는 Logback를 사용하기 쉽게 포장하는 역할
		 */
		log.info("home controller");
		
		model.addAttribute("data", "hello!!!");
		
		/**
		 * 스프링부트 thymeleaf viewName 매칭
		 * 스프링부트에서 thymeleaf 사용 시 viewResolver가 template이하 경로의 viewName으로 자동셋팅
		 * resources:templates/+{viewName}+.html
		 */	
		return "hello";
	}
}
