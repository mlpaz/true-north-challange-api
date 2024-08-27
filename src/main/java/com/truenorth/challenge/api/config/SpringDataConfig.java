package com.truenorth.challenge.api.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.truenorth.challenge.api.adapter.persistence")
@EntityScan(basePackages = "com.truenorth.challenge..api.model")
public class SpringDataConfig {
}