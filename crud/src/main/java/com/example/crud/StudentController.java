package com.example.crud;

import com.example.crud.model.StudentDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {
    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/create-view")
    public String createView() {
        return "create";
    }
    @PostMapping("/create")
    public String create(
            @RequestParam("name") String name,
            @RequestParam("email") String email) {
        System.out.println(name);
        System.out.println(email);
        StudentDto newStudent = studentService.createStudent(name, email);
        System.out.println(newStudent.toString());
        return "redirect:/create-view";
    }

    @GetMapping("/home")
    public String readAll(Model model) {
        model.addAttribute(
                "studentList",
                studentService.readStudentAll());
        return "home";
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long id, Model model) {
        model.addAttribute(
                "student",
                studentService.readStudent(id)
        );
        return "read";
    }

    @GetMapping("/{id}/update-view")
    public String updateView(@PathVariable("id") Long id, Model model){
        model.addAttribute(
                "student",
                studentService.readStudent(id));
        return "update";
    }

    @PostMapping("/{id}/update")
    public String update(
            @PathVariable("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("email") String email
    ) {
        StudentDto studentDto = studentService.updateStudent(id, name, email);
        return "redirect:/{id}";
    }
}
