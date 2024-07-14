package com.isoft.mtax.config;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration

public class JpaConfig {
   /* @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory sessionFactory() {
        if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("EntityManagerFactory is not a Hibernate EntityManagerFactory");
        }
        return entityManagerFactory.unwrap(SessionFactory.class);
    }*/
}
