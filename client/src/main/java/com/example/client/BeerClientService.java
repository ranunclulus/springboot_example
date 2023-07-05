package com.example.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class BeerClientService {
    public void getBeer() {
        WebClient webClient = WebClient.builder().build();

        String url = "https://random-data-api.com/api/v2/beers";
        // 어떤 HTTP Method로 요청을 보낼지를 get() post() 메소드 순으로 결정
        // 만일 다른 메소드를 쓰고 싶다면 method() 사
        BeerGetDto response = webClient.get() // webClient.method(HttpMethod.GET)\
                .uri(url) // 요청 경로 설정
                .header("x-test", "header") // 요청 헤더 추가
                // GET 요청이라 Body 없이
                .retrieve() // 여기 전까지는 요청을 정의한 부분
                // 이제부터 정의하는 건 응답을 어떻게 처리할 것인지
                .bodyToMono(BeerGetDto.class) // 응답이 한 번 돌아올 것이며 String으로 받을 거임
                .block(); // 동기식으로 처리하겠다
        log.info(response.toString());
    }

    public void getBeers() {
        WebClient webClient = WebClient.builder().build();

        String url = "https://random-data-api.com/api/v2/beers?size=10";
        // 어떤 HTTP Method로 요청을 보낼지를 get() post() 메소드 순으로 결정
        // 만일 다른 메소드를 쓰고 싶다면 method() 사
        BeerGetDto[] response = webClient.get() // webClient.method(HttpMethod.GET)\
                .uri(url) // 요청 경로 설정
                .header("x-test", "header") // 요청 헤더 추가
                // GET 요청이라 Body 없이
                .retrieve() // 여기 전까지는 요청을 정의한 부분
                // 이제부터 정의하는 건 응답을 어떻게 처리할 것인지
                .bodyToMono(BeerGetDto[].class) // 응답이 한 번 돌아올 것이며 String으로 받을 거임
                .block(); // 동기식으로 처리하겠다
        log.info(response.toString());
    }

    public void postBear() {
        WebClient webClient = WebClient.builder().build();
        String url = "http://localhost:8081/give-me-beer";

        BeerPostDto dto = new BeerPostDto();
        MessageDto responseBody = webClient.post()
                .uri(url)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(MessageDto.class)
                .block();
        log.info(responseBody.toString());
    }
}
