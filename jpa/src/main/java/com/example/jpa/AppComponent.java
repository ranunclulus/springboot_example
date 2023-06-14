package com.example.jpa;

import org.springframework.stereotype.Component;

@Component
public class AppComponent {
    private final AppConfigData configData;
    public AppComponent(AppConfigData configData){
        this.configData = configData;
    }
    public void useApi() {

    }
}
