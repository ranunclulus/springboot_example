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
    public JobTicket senrd(String filename) {
        // jobId 발행
        String jobId = UUID.randomUUID().toString();
        JobTicket jobTicket = new JobTicket(jobId);

        // jobPayload 생성 (Consumer에서 확인)
        JobPayload jobPayload = new JobPayload(
                jobId,
                filename,
                String.format("/media/user-uploads/raw/%s", filename)
        );

        // jobEntity로 작업 내역 입력 기록
        JobEntity jobEntity = new JobEntity();
        jobEntity.setJobId(jobId);
        jobEntity.setStatus("IDLE");
        jobRepository.save(jobEntity);

        // Message Broker에게 메시지 전달
        rabbitTemplate.convertAndSend(
                // Queue의 이름
                jobQueue.getName(),
                // 메시지로 보낼 문자열
                gson.toJson(jobPayload)
        );

        // 사용자에게 추후 확인용 JwbTicket 전달
        log.info("Send job: {}", jobTicket.getJobId());
        return jobTicket;
    }
}
