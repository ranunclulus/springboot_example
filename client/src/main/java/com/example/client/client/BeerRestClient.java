package com.example.client.client;

import com.example.client.dto.BeerGetDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
// 그냥 평범한 빈 객체
public class BeerRestClient implements BeerClient {
    public BeerGetDto getBeer() {
        // BearRestService에 있는 코드
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://random-data-api.com/api/v2/beers";

        // @RequestBody -> JSON -> DTO
        return restTemplate.getForObject(url, BeerGetDto.class);
    }
}
