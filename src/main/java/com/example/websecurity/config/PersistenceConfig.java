package com.example.websecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "default-auditor")
@EnableJpaRepositories(basePackages = "com.example.websecurity.dao.repository")
@EnableTransactionManagement
public class PersistenceConfig {
}
