package com.Test.unziper.system;

/**
 * Created by olmer on 15.04.17.
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;


@Configuration
@ComponentScan(basePackages = {"sentinel", "com.bic.zakupkiload"})
@EnableJpaRepositories(
        basePackages = {"sentinel"},
        entityManagerFactoryRef = "systemEntityManagerFactory",
        transactionManagerRef = "systemTransactionManager")
@PropertySource(value = "classpath:sentinel.conf")
@EnableTransactionManagement
public class BaseConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${jdbc.driverClass}")
    private String driverClass;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUserName;
    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Bean(name = "systemDataSource")
    public DataSource getSystemDataSource() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUserName);
        dataSource.setPassword(jdbcPassword);
        return dataSource;
    }


    @Bean(name = "systemTransactionManager")
    public PlatformTransactionManager getTransactionManager() throws Exception {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(getEntityManagerFactory().getObject());
        return txManager;
    }


    @Bean(name = "systemEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() throws Exception {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(false);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(
                "experimental",
                "sentinel",
                "dataservices");

//Устанавливаемисточник

        factory.setDataSource(getSystemDataSource());

//Устанавливаемполитикуименованияколонок

/*
        Properties jpaProperties = new Properties();

        jpaProperties.setProperty("hibernate.physical_naming_strategy", "sentinel.system.ImprovedPhysicalNamingStrategy");

        factory.setJpaProperties(jpaProperties);
*/

        return factory;
    }
}
