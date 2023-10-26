package jpabook.jpashop.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	
	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data", "hello!!!");
		
		/**
		 * 스프링부트 thymeleaf viewName 매칭
		 * 스프링부트에서 thymeleaf 사용 시 viewResolver가 template이하 경로의 viewName으로 자동셋팅
		 * resources:templates/+{viewName}+.html
		 */	
		return "hello";
	}
}
