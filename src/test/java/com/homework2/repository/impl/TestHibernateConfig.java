package com.homework2.repository.impl;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

import static org.hibernate.cfg.JdbcSettings.SHOW_SQL;

@TestConfiguration
public class TestHibernateConfig {

    @Value("${hibernate.show_sql}")
    private String showSql = "true";
    @Bean
    public static PostgreSQLContainer<?> postgreSQLContainer() {
        PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:15")
                .withDatabaseName("testdb")
                .withUsername("testuser")
                .withPassword("testpass");
        postgresContainer.start();
        return postgresContainer;
    }

    @Bean
    public DataSource dataSource() {
        PostgreSQLContainer<?> postgresContainer = postgreSQLContainer();
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(postgresContainer.getJdbcUrl());
        dataSource.setUsername(postgresContainer.getUsername());
        dataSource.setPassword(postgresContainer.getPassword());
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.homework2.models"); // Укажите пакет, где находятся ваши модели
        sessionFactory.getHibernateProperties().put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        sessionFactory.getHibernateProperties().put("hibernate.hbm2ddl.auto", "update");
        sessionFactory.getHibernateProperties().put(SHOW_SQL, showSql);
        return sessionFactory;
    }
}
