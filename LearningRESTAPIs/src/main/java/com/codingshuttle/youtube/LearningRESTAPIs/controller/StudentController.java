package com.codingshuttle.youtube.LearningRESTAPIs.controller;

import com.codingshuttle.youtube.LearningRESTAPIs.dto.AddStudentRequestDto;
import com.codingshuttle.youtube.LearningRESTAPIs.dto.StudentResponseDto;
import com.codingshuttle.youtube.LearningRESTAPIs.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    public final StudentService studentService;

//    @GetMapping("/student")
//    public StudentDto getStudent(){
//        return new StudentDto(5L, "Dinesh", "dinesh@gmail.com");
//    }

//    @GetMapping("/students")
    @GetMapping
    public List<StudentResponseDto> getAllStudents(){
        return studentService.getAllStudents();
    }

//    @GetMapping("/students/{id}/{nama}")
//    public String getStudentById(@PathVariable Long id,@PathVariable String nama){
//        return "Path Variable is "+id+" and name is "+nama;
//    }

//    @GetMapping("students/{id}")
    @GetMapping("/{id}")
    public StudentResponseDto getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> createNewStudent(@RequestBody @Valid AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(addStudentRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable Long id, @RequestBody AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.ok(studentService.updateStudent(id, addStudentRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updatePartialStudent(@PathVariable Long id,
                                                                   @RequestBody Map<String, Object> updates){
        return ResponseEntity.ok(studentService.updatePartialStudent(id, updates));
    }

}










