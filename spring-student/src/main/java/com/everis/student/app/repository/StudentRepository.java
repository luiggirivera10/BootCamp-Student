package com.everis.student.app.repository;

import com.everis.student.app.bean.Student;
import java.util.Date;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepository extends ReactiveMongoRepository<Student, String> {
  Flux<Student> findByName(String name);

  Mono<Student> findByNumberID(String numberID);

  Flux<Student> findByBirthdateBetween(Date birthdate,Date birthdate1);
 
  @Query("{ 'name': ?0 }")
  public Mono<Student> obtenerPorName(String name);
}
