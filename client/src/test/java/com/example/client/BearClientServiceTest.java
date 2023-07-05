package com.example.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BearClientServiceTest {
    @Autowired
    private BeerClientService service;

    @Test
    public void getBeerTest() {
        service.getBeer();
    }

    @Test
    public void postBeerTest() {
        service.postBear();
    }
}
