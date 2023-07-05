package com.example.client;

import com.example.client.dto.BeerGetDto;
import com.example.client.dto.BeerPostDto;
import com.example.client.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class BeerRestService {
    public void getBeerObject() {
        // RestTemplate:
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://random-data-api.com/api/v2/beers";

        /*
        // RestTemplate으로 GET 요청
        String responseBody = restTemplate.getForObject(url, String.class);
        log.info(responseBody);
         */

        // @RequestBody -> JSON -> DTO
        BeerGetDto responseBody
                = restTemplate.getForObject(url, BeerGetDto.class);
        log.info(responseBody.toString());
    }

    // STATUS LINE
    // RESPONSE HEADER
    // RESPONSE BODY
    // HTTP 응답 전체 확인
    public void getBeerEntity() {
        // RestTemplate: Spring에서 제공하는 기본 HTTP Client
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://random-data-api.com/api/v2/beers";

        // RestController
        ResponseEntity<BeerGetDto> response =
                restTemplate.getForEntity(url, BeerGetDto.class);
        log.info(response.getStatusCode().toString());
        log.info(response.getHeaders().toString());
        log.info(response.getBody().toString());
    }

    public void postBeerObject() {
        // RestTemplate: Spring에서 제공하는 기본 HTTP Client
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/give-me-beer";

        BeerPostDto beerPostDto = new BeerPostDto();

        // post 요청을 보낼 때 responseBody를 같이 전달해야 함
        MessageDto responseBody = restTemplate.postForObject(
                url, // 요청 url
                beerPostDto, // requestBody
                MessageDto.class); // 응답 해석 타입
        log.info(responseBody.toString());

        // 응답 Body 없이 응답하는 URL
        // 요청이 정상적으로 처리됐는데 서버에서 굳이 응답해 줄 게 없을 때 -> delete
        url = "http://localhost:8081/give-me-beer-204";
        ResponseEntity<Void> response = restTemplate.postForEntity(
                url,
                beerPostDto,
                Void.class); // null의 객체화된 클래스
        log.info(response.getStatusCode().toString());
    }
}
