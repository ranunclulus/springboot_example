package com.example.consumer;

import com.example.consumer.dto.JobPayload;
import com.example.consumer.entity.JobEntity;
import com.example.consumer.entity.JobRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
// 이 클래스는 RabbitMQ의 Queue에 적재되는 메시지를 받아서 처리하기 위함
@RabbitListener
@RequiredArgsConstructor
public class ConsumerService {
    private final JobRepository jobRepository;
    private final Gson gson;

    @RabbitHandler
    // message에 Queue에 담겼던 문자열 전달
    public void receive(String message) throws InterruptedException {
        // 역직렬화
        JobPayload newJob = gson.fromJson(message, JobPayload.class);
        String jobId = newJob.getJobId();
        log.info("Received Job: {}", jobId);
        // Entity 검색
        Optional<JobEntity> optionalJob = jobRepository.findByJobId(jobId);
        // TODO 예외처리를 해 줘야 마땅하나 잠시 생략
        JobEntity jobEntity = optionalJob.get();
        // 요청을 처리 상태로 업데이트
        jobEntity.setStatus("PROCESSING");
        jobEntity = jobRepository.save(jobEntity);
        log.info("Start Processing Job: {}", jobId);
        // 처리하는데 시간이 걸린다고 치자
        TimeUnit.SECONDS.sleep(5);

        jobEntity.setStatus("DONE");
        jobEntity.setResultPath(
                String.format("/media/user-uploaded/processed/%s", newJob.getFilename())
        );
        jobRepository.save(jobEntity);
        log.info("Finished Job: {}", jobId);
    }
}