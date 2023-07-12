package com.example.producer;

import com.example.producer.dto.JobPayload;
import com.example.producer.dto.JobTicket;
import com.example.producer.jpa.JobEntity;
import com.example.producer.jpa.JobRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerServices {
    // RabbitMQ에 메시지를 적재하기 위한 서비스
    private final RabbitTemplate rabbitTemplate;
    private final JobRepository jobRepository;
    private final Queue jobQueue;
    private final Gson gson;

    // filename을 인자로 받고
    // filename을 바탕으로 JSON으로 직렬화된 작업 정보를
    // Queue에 적재한 뒤
    // 사용자에게 JobTicket을 반환하는 메소드
    public JobTicket send(String filename) {
        // jobId 발행
        String jobId = UUID.randomUUID().toString();
        JobTicket jobTicket = new JobTicket(jobId);

        // JobPayload 생성 (Consumer가 확인하는 데이터)
        JobPayload payload = new JobPayload(
                jobId,
                filename,
                String.format("/media/user-uploaded/raw/%s", filename)
        );

        // JobEntity로 작업 내역 입력 기록
        JobEntity jobEntity = new JobEntity();
        jobEntity.setJobId(jobId);
        jobEntity.setStatus("IDLE");
        jobRepository.save(jobEntity);

        // Message Broker에게 메세지 전달
        rabbitTemplate.convertAndSend(
                // 어떤 Queue에 적재할지에 대한 이름
                jobQueue.getName(),
                // 메세지로 보낼 문자열
                gson.toJson(payload)
        );
        // 사용자에게 추후 확인용 JobTicket 전달
        log.info("Sent Job: {}", jobTicket.getJobId());
        return jobTicket;
    }
}
