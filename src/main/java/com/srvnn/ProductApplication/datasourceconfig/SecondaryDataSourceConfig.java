package com.srvnn.ProductApplication.datasourceconfig;

import com.srvnn.ProductApplication.entity.Product;
import com.srvnn.ProductApplication.repository.ProductRepository;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.srvnn.ProductApplication.repository",
        entityManagerFactoryRef = "secondaryEntityManagerFactory",
        transactionManagerRef = "secondaryTransactionManager"
)
public class SecondaryDataSourceConfig {

    @Bean
    public DataSource secondaryDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/productDB");
        dataSource.setUsername("newuser");
        dataSource.setPassword("newpassword");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(EntityManagerFactoryBuilder builder, JpaProperties jpaProperties) {
        return builder
                .dataSource(secondaryDataSource())
                .packages("com.srvnn.ProductApplication.entity")  // Correct package for entities
                .persistenceUnit("secondary")
                .properties(jpaProperties.getProperties())
                .build();
    }

    @Bean
    public PlatformTransactionManager secondaryTransactionManager(EntityManagerFactory secondaryEntityManagerFactory) {
        return new JpaTransactionManager(secondaryEntityManagerFactory);
    }
}
