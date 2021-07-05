package com.project.four.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.project.four")
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public PlatformTransactionManager getTransactionManager(DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }
}