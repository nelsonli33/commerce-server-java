package com.shopflix.platform;

import com.shopflix.core.repository.impl.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.shopflix")
@EnableJpaRepositories(basePackages = "com.shopflix", repositoryBaseClass = CustomJpaRepositoryImpl.class)
@EntityScan(basePackages = "com.shopflix")
@PropertySource("classpath:project.properties")
public class CoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }

}
