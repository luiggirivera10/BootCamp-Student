package com.everis.student.app.service.impl;

import com.everis.student.app.bean.Student;
import com.everis.student.app.repository.StudentRepository;
import com.everis.student.app.service.StudentService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentRepository studentRepository;

  @Override
  public Flux<Student> findAll() {
    return studentRepository.findAll();
  }

  @Override
  public Mono<Student> findById(String id) {
    return studentRepository.findById(id);
  }

  @Override
  public Mono<Student> save(Student student) {
    return studentRepository.save(student);
  }

  @Override
  public Mono<Void> delete(Student student) {
    return studentRepository.delete(student);
  }

  @Override
  public Flux<Student> findByName(String name) {
    return studentRepository.findByName(name);
  }

  @Override
  public Mono<Student> findByNumberID(String nummberID) {
    return studentRepository.findByNumberID(nummberID);
  }

  @Override
  public Flux<Student> findByBirthdateBetween(Date birthdate, Date birthdate1) {
    return studentRepository.findByBirthdateBetween(birthdate, birthdate1);
  }

  @Override
  public Mono<Student> obtenerPorName(String name) {
    return studentRepository.obtenerPorName(name);
  }

}
