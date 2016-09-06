package com.rest.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
public class TestConfig {
	
	@Primary
    @Bean
    @ConfigurationProperties(prefix="datasource.first")
    public DataSource firstDataSource() {
        return DataSourceBuilder.create().build();
    }
	
	@Primary
    @Bean(name = "firstEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setPersistenceUnitName("first");
		entityManagerFactoryBean.setDataSource(firstDataSource());
		entityManagerFactoryBean.setPackagesToScan("com.rest.dao.first");
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		Properties properties = new Properties();
	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	    properties.setProperty("hibernate.hbm2ddl.auto", "update");
	    entityManagerFactoryBean.setJpaProperties(properties);
		entityManagerFactoryBean.afterPropertiesSet();
	    return entityManagerFactoryBean;
    }
	
	@Primary
	@Bean(name = "firstTransactionManager")
	public PlatformTransactionManager firstTransactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(firstEntityManagerFactory().getObject());
	}

	@Configuration
	@EnableTransactionManagement
	@EnableJpaRepositories(basePackages="com.rest.dao.first",
			entityManagerFactoryRef = "firstEntityManagerFactory", transactionManagerRef = "firstTransactionManager")
	public static class FirstJdbcConfig {
		
	}
	
	@Bean
	@ConfigurationProperties(prefix="datasource.second")
	public DataSource secondDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "secondEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setPersistenceUnitName("second");
		entityManagerFactoryBean.setDataSource(secondDataSource());
		entityManagerFactoryBean.setPackagesToScan("com.rest.dao.second");
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		Properties properties = new Properties();
	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	    properties.setProperty("hibernate.hbm2ddl.auto", "update");
	    entityManagerFactoryBean.setJpaProperties(properties);
		entityManagerFactoryBean.afterPropertiesSet();
	    return entityManagerFactoryBean;
	}
	
	@Bean(name = "secondTransactionManager")
	public PlatformTransactionManager secondTransactionManager() {
		return new JpaTransactionManager(secondEntityManagerFactory().getObject());
	}
	
	@Configuration
	@EnableTransactionManagement
	@EnableJpaRepositories(basePackages="com.rest.dao.second",   
		entityManagerFactoryRef = "secondEntityManagerFactory", transactionManagerRef = "secondTransactionManager")
	public static class SecondJdbcConfig {
		
	}

}
