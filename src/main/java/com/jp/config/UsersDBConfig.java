package com.jp.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.jp.users"},
		entityManagerFactoryRef = "usersLocalSessionFactoryBean",
		transactionManagerRef = "usersPlatformTransactionManager")
public class UsersDBConfig {
	
	@ConfigurationProperties(prefix = "spring.datasource.usersdb")
	@Bean
	public DataSource usersDataSource() {
		return DataSourceBuilder.create()
					.type(HikariDataSource.class)
						.build();
	}
	
	@Bean
	public LocalSessionFactoryBean usersLocalSessionFactoryBean() {
		
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("hibernate.hbm2ddl.auto","create-drop");
		
		localSessionFactoryBean.setDataSource(usersDataSource());
		localSessionFactoryBean.setHibernateProperties(properties);
		localSessionFactoryBean.setPackagesToScan("com.jp.users");
		return localSessionFactoryBean;
		
	}
	
	@Bean
	public PlatformTransactionManager usersPlatformTransactionManager() {
		return new JpaTransactionManager(usersLocalSessionFactoryBean().getObject());
	}

}
