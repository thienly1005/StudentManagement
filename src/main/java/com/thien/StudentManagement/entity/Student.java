package com.thien.StudentManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank(message = "Name is required")
  private String name;
  @Email(message = "Invalid email")
  private String email;
  @Min(value = 0, message = "Grade must be at least 0")
  @Max(value = 10, message = "Grade cannot exceed 10")
  private double grade;

  // Constructor mặc định
  public Student() {}

  // Constructor với tham số
  public Student(String name, String email, double grade) {
    this.name = name;
    this.email = email;
    this.grade = grade;
  }

  // Getter và Setter
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public double getGrade() { return grade; }
  public void setGrade(double grade) { this.grade = grade; }
}
