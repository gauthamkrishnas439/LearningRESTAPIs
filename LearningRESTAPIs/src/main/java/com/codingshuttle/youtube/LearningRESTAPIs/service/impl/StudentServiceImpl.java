package com.codingshuttle.youtube.LearningRESTAPIs.service.impl;

import com.codingshuttle.youtube.LearningRESTAPIs.dto.AddStudentRequestDto;
import com.codingshuttle.youtube.LearningRESTAPIs.dto.StudentResponseDto;
import com.codingshuttle.youtube.LearningRESTAPIs.entity.Student;
import com.codingshuttle.youtube.LearningRESTAPIs.repository.StudentRepository;
import com.codingshuttle.youtube.LearningRESTAPIs.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentResponseDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students
                .stream()
//                .map(student -> new StudentDto(student.getId(), student.getName(), student.getEmail()))
                //instead of writing the above commented out line we can map the object using model mapping
                .map(student -> modelMapper.map(student, StudentResponseDto.class))
                .toList();
    }

    @Override
    public StudentResponseDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with id: "+ id));
        return modelMapper.map(student, StudentResponseDto.class);
    }

    @Override
    public StudentResponseDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        Student newStudent = modelMapper.map(addStudentRequestDto, Student.class);
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student, StudentResponseDto.class);
    }

    @Override
    public void deleteStudentById(Long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("Student does not exist by ID: "+id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentResponseDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with id: "+ id));
        modelMapper.map(addStudentRequestDto, student);

        student = studentRepository.save(student);
        return modelMapper.map(student, StudentResponseDto.class);
    }

    @Override
    public StudentResponseDto updatePartialStudent(Long id, Map<String, Object> updates) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: "+ id));

        updates.forEach((field, value) -> {
            switch(field) {
                case "name":
                    student.setName((String) value);
                    break;
                case "email":
                    student.setEmail((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Field is not supported");
            }
        });

        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentResponseDto.class);
    }
}
