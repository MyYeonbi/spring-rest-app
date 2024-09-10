package org.project.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.project.backend.repository")
@PropertySource("classpath:application.properties")  // 프로퍼티 파일 명시
public class JpaConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       @Value("${spring.jpa.hibernate.ddl-auto}") String ddlAuto,
                                                                       @Value("${spring.jpa.properties.hibernate.dialect}") String dialect) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("org.project.backend.model");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // Hibernate 설정 추가
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", ddlAuto);
        properties.setProperty("hibernate.dialect", dialect);
        em.setJpaProperties(properties);

        return em;
    }

    @Bean
    public DataSource dataSource(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password,
            @Value("${spring.datasource.driver-class-name}") String driverClassName) {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
