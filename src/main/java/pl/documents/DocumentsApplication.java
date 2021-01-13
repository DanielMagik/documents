package pl.documents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.servlet.*;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Collections;

@SpringBootApplication
public class DocumentsApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(DocumentsApplication.class, args);
	}

	@Bean
	Validator validator()
	{
		return new LocalValidatorFactoryBean();
	}

	/*
	@Bean
	public FilterRegistrationBean filterRegistrationBean()
	{
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new JwtFilter());

		filterRegistrationBean.setUrlPatterns(Collections.singleton("/getiset"));
		return filterRegistrationBean;
	}
	 */

}
