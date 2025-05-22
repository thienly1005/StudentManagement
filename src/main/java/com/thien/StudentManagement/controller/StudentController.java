package com.thien.StudentManagement.controller;

import com.thien.StudentManagement.entity.Student;
import com.thien.StudentManagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students")
public class StudentController {
  @Autowired
  private StudentService studentService;

  // Hiển thị danh sách sinh viên
  @GetMapping
  public String listStudents(Model model) {
    model.addAttribute("students", studentService.getAllStudents());
    model.addAttribute("student", new Student());
    return "students";
  }

  // Hiển thị form thêm sinh viên
  @GetMapping("/new")
  public String createStudentForm(Model model) {
    model.addAttribute("student", new Student());
    return "create_student";
  }

  // Lưu sinh viên mới
  @PostMapping
  public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      return "create_student";
    }
    studentService.saveStudent(student);
    redirectAttributes.addFlashAttribute("message", "Student added successfully!");
    return "redirect:/students";
  }

  // Hiển thị form chỉnh sửa sinh viên
  @GetMapping("/edit/{id}")
  public String editStudentForm(@PathVariable Long id, Model model) {
    model.addAttribute("student", studentService.getStudentById(id).orElse(null));
    return "edit_student";
  }

  // Cập nhật sinh viên
  @PostMapping("/{id}")
  public String updateStudent(@PathVariable Long id, @Valid @ModelAttribute("student") Student student, BindingResult result, RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      return "edit_student";
    }
    Student existingStudent = studentService.getStudentById(id).orElse(null);
    if (existingStudent != null) {
      existingStudent.setName(student.getName());
      existingStudent.setEmail(student.getEmail());
      existingStudent.setGrade(student.getGrade());
      studentService.saveStudent(existingStudent);
      redirectAttributes.addFlashAttribute("message", "Student updated successfully!");
    }
    return "redirect:/students";
  }

  // Xóa sinh viên
  @GetMapping("/delete/{id}")
  public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    studentService.deleteStudent(id);
    redirectAttributes.addFlashAttribute("message", "Student deleted successfully!");
    return "redirect:/students";
  }
  @GetMapping("/search")
  public String searchStudents(@RequestParam("keyword") String keyword, Model model) {
    model.addAttribute("students", studentService.searchStudents(keyword));
    return "students";
  }
}
