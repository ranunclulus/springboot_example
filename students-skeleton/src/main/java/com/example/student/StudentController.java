package com.example.student;

import com.example.student.dto.StudentDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("studentList", service.readStudentAll());
        return "home";
    }

    // create.html 응답
    @GetMapping("/create-view")
    public String createView() {
        return "create";
    }

    // 새로운 StudentEntity 생성 후 상세보기 페이지로
    @PostMapping("/create")
    public String create(StudentDto dto) {
        StudentDto newDto = service.createStudent(dto);
        // Post Redirect Get Pattern
        // View Resolver가 리다이렉트 String을 읽어서 경로를 저장해 주기 때문에 students/도 넣어 줘야 함
        return("redirect: /students/" + newDto.getId());
    }

    // id에 해당하는 StudentEntity의 read.html 응답
    @GetMapping("/{id}")
    public String read() {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    // id에 해당하는 StudentEntity의 update.html 응답
    @GetMapping("/{id}/update-view")
    public String updateView(){
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    // id에 해당하는 StudentEntity 수정 후 상세보기 페이지로
    @PostMapping("/{id}/update")
    public String update() {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    // id에 해당하는 StudentEntity delete.html
    @GetMapping("/{id}/delete-view")
    public String deleteView() {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    // id에 해당하는 StudentEntity 삭제 후 홈페이지로
    @PostMapping("/{id}/delete")
    public String delete() {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }
}
