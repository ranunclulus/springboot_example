package com.example.contents;

import com.example.contents.dto.UserDto;
import com.example.contents.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    // createUser
    public UserDto createUser(UserDto dto) {
        // 회원가입 => 프로필 이미지가 아직 필요 없다
        if (repository.existsByUsername(dto.getUsername())) {
            throw new IllegalStateException();
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(dto.getId());
        userEntity.setUsername(dto.getUsername());
        userEntity.setEmail(dto.getEmail());
        userEntity.setPhone(dto.getPhone());
        userEntity.setBio(dto.getBio());
        userEntity.setAvatar(null);
        return UserDto.fromEntity(repository.save(userEntity));
    }

    // readUserByUsername
    public UserDto readUserByUsername(String username) {
        Optional<UserEntity> userEntity = repository.findByUsername(username);
        if (userEntity.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return UserDto.fromEntity(userEntity.get());
    }

    // updateUser
    public UserDto updateUser(Long id, UserDto dto) {
        // update user로 사용자 이름은 업데이트할 수 없음
        Optional<UserEntity> optionalUser = repository.findById(id);
        if (optionalUser.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        UserEntity userEntity = optionalUser.get();
        userEntity.setEmail(dto.getEmail());
        userEntity.setBio(dto.getBio());
        userEntity.setEmail(dto.getEmail());
        return UserDto.fromEntity(repository.save(userEntity));
    }

    // updateUserAvatar
    public UserDto updateUserAvatar(Long id, MultipartFile avatarImage) {
        // 2. 사용자가 프로필 이미지를 업로드한다.

        // TODO 유저 존재 확인
        Optional<UserEntity> optionalUser = repository.findById(id);
        if(optionalUser.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        // TODO 파일을 어디에 업로드할 건지 => /media/{userId}/profile.{파일확장자}
        String profileDir = String.format("media/%d/", id);
        try {
            Files.createDirectories(Path.of(profileDir));
        } catch (IOException exception) {
            log.error(exception.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
        }
        // TODO 확장자를 포함한 이미지 이름 만들기 (profile.{확장자})
        String originalFilename = avatarImage.getOriginalFilename();
        // profile.png -> [profile, png]s
        String[] filenameSplit = originalFilename.split("\\.");
        String extension = filenameSplit[filenameSplit.length - 1];
        String profileFilename = "profile." + extension;
        log.info(profileFilename);

        // TODO 폴더와 파일 경로를 포함한 이름 만들기
        String profilePath = profileDir + profileFilename;

        // TODO 업로드
        try {
            avatarImage.transferTo(Path.of(profilePath));
        } catch (IOException exception) {
            log.error(exception.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // TODO User Entity Update
        UserEntity userEntity = optionalUser.get();
        userEntity.setAvatar(String.format("/static/%d/%s", id, profileFilename));
        return UserDto.fromEntity(repository.save(userEntity));
    }
}
