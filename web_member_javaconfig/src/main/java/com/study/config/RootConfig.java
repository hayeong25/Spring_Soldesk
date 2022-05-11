package com.study.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// root-context.xml

@Configuration
@ComponentScan("com.study.service") // <context:component-scan base-package="com.study.service" />
@MapperScan("com.study.mapper") // <mybatis-spring:scan base-package="com.study.mapper"/>
public class RootConfig {
	
	@Bean // <bean> 객체
	public DataSource dataSource() {
		// <bean id="hikariConfig" class="com.zaxxer.hikari.HikariConfig">
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		hikariConfig.setUsername("javadb");
		hikariConfig.setPassword("12345");
		
		// <bean id="ds" class="com.zaxxer.hikari.HikariDataSource" destroy-method="close">
		HikariDataSource datasource = new HikariDataSource(hikariConfig);
		return datasource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		// <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		return sqlSessionFactoryBean.getObject();
	}
}