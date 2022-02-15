package com.mybatis.demo;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@MapperScan(basePackages = "com.mybatis.demo.biz.mapper", sqlSessionFactoryRef = "mySqlSessionFactory")
public class DataSourceConfig {

	@Primary 
	@Bean(name ="mySqlDataSource")
	@ConfigurationProperties(prefix = "spring.datasource-mysql")
	public DataSource mySqlDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Primary
	@Bean(name ="mySqlSessionFactory")
	public SqlSessionFactory mySqlSessionFactory(@Qualifier("mySqlDataSource")
	DataSource mySqlDataSource , ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(mySqlDataSource);
		sqlSessionFactoryBean.setTypeAliasesPackage("com.mybatis.demo.biz.**.domain");
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/*.xml"));
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
		
		return sqlSessionFactoryBean.getObject();
	}
	
	@Primary
	@Bean(name = "mysqlSessionTemplate")
	public SqlSessionTemplate mysqlSessionTemplate(@Qualifier("mySqlSessionFactory") SqlSessionFactory mysqlSessionFactory) {
		return new SqlSessionTemplate(mysqlSessionFactory);
	}
}
