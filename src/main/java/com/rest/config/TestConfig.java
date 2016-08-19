package com.rest.config;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

public class TestConfig {

	@Configuration
	@EnableTransactionManagement
	@EnableJpaRepositories(basePackages="com.rest.dao.first",
			entityManagerFactoryRef = "firstEntityManagerFactory", transactionManagerRef = "firstTransactionManager")
	public static class DnbbJdbcConfig {
		
		@Primary
	    @Bean
	    @ConfigurationProperties(prefix="datasource.first")
	    public DataSource dataSource() {
	        return DataSourceBuilder.create().build();
	    }
		
		@Primary
	    @Bean(name = "firstEntityManagerFactory")
		public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
	    	return builder
	                .dataSource(dataSource())
	                .packages("com.rest.dao.first")
	                .persistenceUnit("first")
	                .build();
	    }
		
		@Primary
		@Bean(name = "firstTransactionManager")
		PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
			return new JpaTransactionManager(entityManagerFactory(builder).getObject());
		}
		
	}
	
	@Configuration
	@EnableTransactionManagement
	@EnableJpaRepositories(basePackages="com.rest.dao.second",
		entityManagerFactoryRef = "secondEntityManagerFactory", transactionManagerRef = "secondTransactionManager")
	public static class SmsJdbcConfig {
		
		@Bean
		@ConfigurationProperties(prefix="datasource.second")
		public DataSource dataSource() {
			return DataSourceBuilder.create().build();
		}
		
		@Bean(name = "secondEntityManagerFactory")
		@PersistenceContext(unitName = "second")
		public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
			return builder
					.dataSource(dataSource())
					.packages("com.rest.dao.second")
					.persistenceUnit("second")
					.build();
		}
		
		@Bean(name = "secondTransactionManager")
		PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
			return new JpaTransactionManager(entityManagerFactory(builder).getObject());
		}
	}

}
