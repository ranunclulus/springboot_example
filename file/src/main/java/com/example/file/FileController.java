package com.example.file;

import com.example.file.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class FileController {
    @PostMapping(value = "/multipart",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto miltipart(
            @RequestParam("name") String name,
            @RequestParam("photo")MultipartFile multipartFile
            ) {
        ResponseDto response = new ResponseDto();
        response.setMessage("success");
        return response;
    }
}
