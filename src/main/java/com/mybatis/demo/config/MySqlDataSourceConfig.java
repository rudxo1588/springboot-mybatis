package com.mybatis.demo.config;

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

/**
* @Configuration는 해당 클래스가 Configuration임을 나타냄
*/
@Configuration
/**
* @MapperScan은 basePackages나 value로 해당 위치에 있는 파일을 매퍼로 인식시키게 하며
*  sqlSessionFactoryRef는 myBatis에서 복수의 DataSource를 활용하기 위해 사용한다.
*/
@MapperScan(basePackages = "com.mybatis.demo.faq.mapper", sqlSessionFactoryRef = "mySqlSessionFactory")
public class MySqlDataSourceConfig {

	/**
	 * @Primary는 기본 DB를 지정해줄때 사용한다. 해당 어노테이션 사용시 매퍼스캔이 따로 필요하지않다.
	 *
	 */
	@Primary
	/**
	* @Bean은 직접 제어가 불가능한 외부 라이브러리등을 Bean으로 만들때 사용.
	*/
	@Bean(name ="mySqlDataSource")
	/**
	* @ConfigurationProperties는 classpath에 있는 application.yml 또는 properties 파일의 값을 사용할때 사용.
	*/
	@ConfigurationProperties(prefix = "spring.datasource-mysql")
	// DataSource란 WAS내에 있는 모든 Connection을 관리하는 Spring boot에 내장되어있는 Connection pool이다.
	public DataSource mySqlDataSource() {
		return DataSourceBuilder.create().build();	// yml 파일에서 spring.datasource-mysql 의 데이터 베이스를 사용하여 빌드 하겠다.
	}

	@Primary
	@Bean(name ="mySqlSessionFactory")
	/**
	 * @Qualifier는 특정한 객체를 찾기위한 이름을 지정한다.
	 */
	// SqlSessionFactory는 데이터베이스와의 연결과 SQL의 실행에 대한 모든 것을 가진 가장 중요한 객체이다.
	public SqlSessionFactory mySqlSessionFactory(@Qualifier("mySqlDataSource")
	DataSource mySqlDataSource , ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(mySqlDataSource); // 해당 메소드에서 빌드한 데이터베이스를 세션으로 등록
		sqlSessionFactoryBean.setTypeAliasesPackage("com.mybatis.demo.faq.**.domain");	// xml파일에서 타입 지정시 해당 경로는 따로 작성하지 않아도 사용가능
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/faq/*.xml"));	// xml매퍼파일의 위치를 찾기위해 사용
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/user/*.xml"));	// xml매퍼파일의 위치를 찾기위해 사용
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));	// mybatis를 설정해준 config파일을 사용.

		return sqlSessionFactoryBean.getObject();
	}

	@Primary
	@Bean(name = "mysqlSessionTemplate")
	/**
	 * 마이바티스 스프링 연동모듈의 핵심이며 쓰레드에 안전하고 여러개의 DAO나 매퍼에서 공유할수 있다.
	 * 생성자 인자로 SqlSessionFactory를 사용해서 생성할수 있다.
	 */
	public SqlSessionTemplate mysqlSessionTemplate(@Qualifier("mySqlSessionFactory") SqlSessionFactory mysqlSessionFactory) {
		return new SqlSessionTemplate(mysqlSessionFactory);
	}
}
