package com.example.demo;

import com.example.demo.config.FiltrosCors;
import jakarta.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class);
	}



	@Bean
	public FilterRegistrationBean<FiltrosCors> corsFilterRegistration() {
		FilterRegistrationBean<FiltrosCors> registrationBean = new FilterRegistrationBean<>(new FiltrosCors());
		registrationBean.setName("filtros CORS");
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return registrationBean;
	}

}
