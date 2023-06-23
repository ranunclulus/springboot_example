package com.example.file;

import com.example.file.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Slf4j
@RestController
public class FileController {
    @PostMapping(value = "/multipart",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto miltipart(
            @RequestParam("name") String name,
            @RequestParam("photo")MultipartFile multipartFile
            ) throws IOException {
        // 저장할 파일 경로 생성
        Files.createDirectories(Path.of("media"));
        // 저장할 파일 이름을 포함한 경로를 작성한다.

        LocalDateTime now = LocalDateTime.now();
        log.info(now.toString());
        Path uploadTo = Path.of(String.format("media/%s.png", now));
        multipartFile.transferTo(uploadTo);

        ResponseDto response = new ResponseDto();
        response.setMessage("success");
        return response;
    }
}
