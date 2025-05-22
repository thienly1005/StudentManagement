package com.thien.StudentManagement.repository;

import com.thien.StudentManagement.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
  List<Student> findByNameContainingIgnoreCase(String name);
}
