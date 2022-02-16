package com.mybatis.demo;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@PropertySource("classpath:/application.yml")
@MapperScan(basePackages =  "com.mybatis.demo.biz2.mapper", sqlSessionFactoryRef = "h2SqlSessionFactory")
public class H2DataSourceConfig {

	@Bean(name = "h2DataSource")
	@ConfigurationProperties(prefix = "spring.datasource-h2")
	public DataSource h2DataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("schema.sql").addScript("data.sql").build();
	}
	
	@Bean(name = "h2SqlSessionFactory")
	public SqlSessionFactory h2SqlSessionFactory(@Qualifier("h2DataSource") 
	DataSource h2DataSource, ApplicationContext applicationContext) throws Exception{
		
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(h2DataSource);
		sqlSessionFactoryBean.setTypeAliasesPackage("com.mybatis.demo.biz2.**.domain");
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/h2Database/*.xml"));
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
		return sqlSessionFactoryBean.getObject();

	}
	
	public SqlSessionTemplate h2SqlSessionTemplate(@Qualifier("h2SqlSessionFactory") SqlSessionFactory h2SqlSessionFactory) {
		return new SqlSessionTemplate(h2SqlSessionFactory);
	}
}
