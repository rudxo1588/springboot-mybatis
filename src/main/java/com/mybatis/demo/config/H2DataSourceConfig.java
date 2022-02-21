package com.mybatis.demo.config;

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
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
* @Configuration는 해당 클래스가 Configuration임을 나타냄 
*/
@Configuration
/**
* @MapperScan은 basePackages나 value로 해당 위치에 있는 파일을 매퍼로 인식시키게 하며
*  sqlSessionFactoryRef는 myBatis에서 복수의 DataSource를 활용하기 위해 사용한다.
*/
@MapperScan(basePackages =  "com.mybatis.demo.biz2.mapper", sqlSessionFactoryRef = "h2SqlSessionFactory")
public class H2DataSourceConfig {

	/**
	* @Bean은 직접 제어가 불가능한 외부 라이브러리등을 Bean으로 만들때 사용.
	*/
	@Bean(name = "h2DataSource")
	/**
	* @ConfigurationProperties는 classpath에 있는 application.yml 또는 properties 파일의 값을 사용할때 사용.
	*/
	@ConfigurationProperties(prefix = "spring.datasource-h2")
	public DataSource h2DataSource() {
		// h2사용시 addScript를 통해 schema.sql, data.sql을 선언해주면 서버 기동시 해당 파일에 있는 스크립트들을 읽어온다.
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("schema.sql").addScript("data.sql").build();
	}
	
	@Bean(name = "h2SqlSessionFactory")
	/**
	 * @Qualifier는 특정한 객체를 찾기위한 이름을 지정한다.
	 */
	public SqlSessionFactory h2SqlSessionFactory(@Qualifier("h2DataSource") 
	DataSource h2DataSource, ApplicationContext applicationContext) throws Exception{
		
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(h2DataSource);
		sqlSessionFactoryBean.setTypeAliasesPackage("com.mybatis.demo.biz2.**.domain");
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/h2Database/*.xml"));
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
		return sqlSessionFactoryBean.getObject();

	}
	
	@Bean(name = "h2SqlSessionTemplate")
	public SqlSessionTemplate h2SqlSessionTemplate(@Qualifier("h2SqlSessionFactory") SqlSessionFactory h2SqlSessionFactory) {
		return new SqlSessionTemplate(h2SqlSessionFactory);
	}
}
