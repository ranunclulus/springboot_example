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
import java.nio.file.Path;

@Slf4j
@RestController
public class FileController {
    @PostMapping(value = "/multipart",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto miltipart(
            @RequestParam("name") String name,
            @RequestParam("photo")MultipartFile multipartFile
            ) throws IOException {
        /*// 컴퓨터에서 파일 경로를 관리하기 위해 만들어진 인터페이스
        Path uploadTo = Path.of("filename.png");
        multipartFile.transferTo(uploadTo);
*/
        // 넘겨 받은 파일을 바이트 어레이로 사용할 수 있음
        File file = new File("./filename.png");
        try (OutputStream outputStream = new FileOutputStream(file)){
            byte[] fileBytes = multipartFile.getBytes();
            // 여기에서 byte[]를 활용
            outputStream.write(fileBytes);
        }
        ResponseDto response = new ResponseDto();
        response.setMessage("success");
        return response;
    }
}
