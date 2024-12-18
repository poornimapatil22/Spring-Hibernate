package com.xworkz.commoun_module.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan("com.xworkz.commoun_module")
@Slf4j

public class UserConfig {
    public UserConfig(){
        System.out.println("const of config");
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean bean=new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource());
        log.info("this is info of config");
        bean.setPackagesToScan("com.xworkz.commoun_module.entity");
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return bean;

    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource drivermngrdatasouce=new DriverManagerDataSource();
        drivermngrdatasouce.setDriverClassName("com.mysql.cj.jdbc.Driver");
        drivermngrdatasouce.setUsername("root");
        drivermngrdatasouce.setUrl("jdbc:mysql://localhost:3306/Xworkz_module_db");
        drivermngrdatasouce.setPassword("Poornima!22");
        return drivermngrdatasouce;
    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }



}
