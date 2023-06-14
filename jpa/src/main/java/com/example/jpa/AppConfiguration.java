package com.example.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public AppConfigData connectionUrl() {
        // 이 메소드의 결과를 Bean 객체로 등록
        if(true) {
            return new AppConfigData("main-url");
        }
        else return new AppConfigData("backup-data");
    }
}
