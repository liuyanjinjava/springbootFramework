package com.futao.springbootdemo.foundation.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

//@Configuration
//@EnableTransactionManagement

/**
 * @author futao
 * Created on ${date}.
 */
public class HibernateConfiguration {

    @Resource
    private DataSource dataSource;

    @Value("${spring.jpa.entitymanager.packagesToScan}")
    private String packageToScan;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String dialect;

    @Value("${spring.jpa.show-sql}")
    private Boolean showSql;

//    @Value("${spring.jpa.hibernate.hbm2ddl.auto}")
//    private String hb2ddlAuto;
//
//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setPackagesToScan();
//        sessionFactory.setPackagesToScan(packageToScan);
//        Properties hibernateProperties = new Properties();
//        hibernateProperties.put("hibernate.dialect", dialect);
//        hibernateProperties.put("hibernate.show_sql", showSql);
//        hibernateProperties.put("hibernate.hbm2ddl.auto", hb2ddlAuto);
//        sessionFactory.setHibernateProperties(hibernateProperties);
//        return sessionFactory;
//    }

    /**
     * 事物管理器配置
     *
     * @return
     */
//    @Bean
    public PlatformTransactionManager transactionManage() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
