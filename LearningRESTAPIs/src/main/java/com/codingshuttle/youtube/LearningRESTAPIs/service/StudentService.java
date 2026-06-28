package com.codingshuttle.youtube.LearningRESTAPIs.service;

import com.codingshuttle.youtube.LearningRESTAPIs.dto.AddStudentRequestDto;
import com.codingshuttle.youtube.LearningRESTAPIs.dto.StudentResponseDto;

import java.util.List;
import java.util.Map;

public interface StudentService {

    List<StudentResponseDto> getAllStudents();

    StudentResponseDto getStudentById(Long id);

    StudentResponseDto createNewStudent(AddStudentRequestDto addStudentRequestDto);

    void deleteStudentById(Long id);

    StudentResponseDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto);

    StudentResponseDto updatePartialStudent(Long id, Map<String, Object> updates);
}
