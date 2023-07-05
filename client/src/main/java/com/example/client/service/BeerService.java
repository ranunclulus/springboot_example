package com.example.client.service;

import com.example.client.client.BeerClient;
import com.example.client.client.BeerRestClient;
import com.example.client.client.BeerWebClient;
import com.example.client.dto.BeerGetDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BeerService {

    private final BeerClient client;

    public BeerService(BeerClient client) {
        this.client = client;
    }

    public void drickBeer() {
        log.info("order beer");
        // TODO API를 이용해 맥주 정보 받아오기
        BeerGetDto result = client.getBeer();
        log.info("{}는 맛있다", result.getName());
    }
}
