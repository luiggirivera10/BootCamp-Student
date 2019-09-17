package com.everis.student.app.service;

import com.everis.student.app.bean.Student;
import java.util.Date;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
  public Flux<Student> findAll();

  public Mono<Student> findById(String id);

  public Mono<Student> save(Student student);

  public Mono<Void> delete(Student student);

  public Flux<Student> findByName(String name);

  public Mono<Student> findByNumberID(String nummberID);

  public Flux<Student> findByBirthdateBetween(Date birthdate, Date birthdate1);

  public Mono<Student> obtenerPorName(String name);
}
