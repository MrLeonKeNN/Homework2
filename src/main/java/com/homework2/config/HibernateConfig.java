package com.homework2.config;

import jakarta.persistence.EntityManagerFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@PropertySource("classpath:application.properties")
@Profile("default")
@EnableTransactionManagement
public class HibernateConfig {

    private static final String SCAN_TO = "com.homework2.models";
    @Value("${db.url}")
    private String url;

    @Value("${db.login}")
    private String login;

    @Value("${db.password}")
    private String password;

    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.show_sql}")
    private String showSql;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    @Value("${hibernate.cache.region.factory_class}")
    private String cacheFactory;

    @Value("${hibernate.cache.use_second_level_cache}")
    private String userCacheSecondLevel;

    @Value("${hibernate.hbm2ddl.import_files}")
    private String autoImport;


    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(getSessionFactory().getObject());
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(login);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setPackagesToScan(SCAN_TO);
        factoryBean.setHibernateProperties(hibernateProperties());
        factoryBean.setDataSource(dataSource());

        return factoryBean;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
//        properties.setProperty(HBM2DDL_AUTO,hbm2ddlAuto);
        properties.setProperty(DIALECT, dialect);
        properties.setProperty(SHOW_SQL, showSql);
        properties.setProperty(CACHE_REGION_FACTORY, cacheFactory);
        properties.setProperty(USE_SECOND_LEVEL_CACHE, userCacheSecondLevel);
        return properties;
    }
}
