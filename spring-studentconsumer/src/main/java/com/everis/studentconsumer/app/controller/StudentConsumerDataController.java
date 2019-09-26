package com.everis.studentconsumer.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.everis.studentconsumer.app.service.StudentConsumerService;

@RestController
public class StudentConsumerDataController {

  /**
   * Injected CourseConsumerService.
   */
  StudentConsumerService studentService;

  /**
   * .
   */
  @GetMapping(value="/getStudentInfo")
  public String getStudents() {
    System.out.println("Making a call to tha student application");
    return studentService.callStudentApplication();
  }
}
