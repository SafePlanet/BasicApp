package com.spi;

import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ComponentScan({"org.springframework.social.connect", "com.spi"})
public class ApplicationConfiguration {
	
//	@Bean(name = "messageSource")
//	public ReloadableResourceBundleMessageSource messageSource() {
//	  ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
//	  messageBundle.setBasename("classpath:validationMessages");
//	  messageBundle.setDefaultEncoding("UTF-8");
//	  return messageBundle;
//	}
	
//	@Bean
//    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
//        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
//        bean.setTemplateLoaderPath("/templates/");
//        return bean;
//    }
	
	

}
