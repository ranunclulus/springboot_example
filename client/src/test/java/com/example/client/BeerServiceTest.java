package com.example.client;

import com.example.client.service.BeerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BeerServiceTest {
    @Autowired
    private BeerService service;

    @Test
    public void drickBeer() {
        service.drickBeer();
    }
}
