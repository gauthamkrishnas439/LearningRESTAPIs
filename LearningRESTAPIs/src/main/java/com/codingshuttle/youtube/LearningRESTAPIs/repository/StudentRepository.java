package com.codingshuttle.youtube.LearningRESTAPIs.repository;

import com.codingshuttle.youtube.LearningRESTAPIs.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
