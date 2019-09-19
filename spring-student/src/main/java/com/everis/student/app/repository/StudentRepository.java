package com.everis.student.app.repository;

import com.everis.student.app.bean.Student;
import java.util.Date;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * .
 * @author lriveras
 *
 */
public interface StudentRepository extends ReactiveMongoRepository<Student, String> {

  /**
 * findByName.
 */
  Flux<Student> findByName(String name);

  /**
 * findByNumberID.
 */
  Mono<Student> findByNumberID(String numberID);

  /**
 * findByBirthdateBetween.
 */
  Flux<Student> findByBirthdateBetween(Date birthdate,Date birthdate1);
 
  /**
 * Solo para TEST.
 */
  @Query("{ 'name': ?0 }")
  Mono<Student> findName(String name);
}
