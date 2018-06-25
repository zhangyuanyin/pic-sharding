package com.gome;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * <dt>程序启动类（内嵌tomcat）</dt>
 * @author yyzhang
 * @since 2018年2月1日16:30:54
 */
@SpringBootApplication
@MapperScan("com.gome.dao")
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
