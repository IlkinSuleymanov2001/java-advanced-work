package com.example.demo.config;


import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbc.JdbcLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@Configuration
//@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT1H")
public class SchedulerConfig {

    
    @Bean
    public LockProvider lockProvider(DataSource dataSource) {
            return new JdbcLockProvider(dataSource);
    }
}
