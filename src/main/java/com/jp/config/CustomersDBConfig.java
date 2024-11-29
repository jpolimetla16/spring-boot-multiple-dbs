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
@EnableJpaRepositories(basePackages = {"com.jp.customers"},
		entityManagerFactoryRef = "customersLocalSessionFactoryBean",
		transactionManagerRef = "customersPlatformTransactionManager")
public class CustomersDBConfig {
	
	@ConfigurationProperties(prefix = "spring.datasource.customersdb")
	@Bean
	public DataSource customersDataSource() {
		return DataSourceBuilder.create()
					.type(HikariDataSource.class)
						.build();
	}
	
	@Bean
	public LocalSessionFactoryBean customersLocalSessionFactoryBean() {
		
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("hibernate.hbm2ddl.auto","create-drop");
		
		localSessionFactoryBean.setDataSource(customersDataSource());
		localSessionFactoryBean.setHibernateProperties(properties);
		localSessionFactoryBean.setPackagesToScan("com.jp.customers");
		return localSessionFactoryBean;
		
	}
	
	@Bean
	public PlatformTransactionManager customersPlatformTransactionManager() {
		return new JpaTransactionManager(customersLocalSessionFactoryBean().getObject());
	}

}
