package com.srvnn.ProductApplication.datasourceconfig;

import javax.sql.DataSource;

import com.srvnn.ProductApplication.entity.Vehicle;
import com.srvnn.ProductApplication.repository.VehicleRepository;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.srvnn.ProductApplication.repository",
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryDataSourceConfig {

    @Primary
    @Bean
    public DataSource primaryDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/vehicleDB");
        dataSource.setUsername("newuser");
        dataSource.setPassword("newpassword");
        return dataSource;
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder, JpaProperties jpaProperties) {
        return builder
                .dataSource(primaryDataSource())
                .packages("com.srvnn.ProductApplication.entity")
                .persistenceUnit("primary")
                .properties(jpaProperties.getProperties())
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager primaryTransactionManager(EntityManagerFactory primaryEntityManagerFactory) {
        return new JpaTransactionManager(primaryEntityManagerFactory);
    }
}
