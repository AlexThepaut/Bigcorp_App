package com.training.spring.repository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.training.spring.repository", "com.training.spring.utils"})
public class DaoTestConfig {
}
