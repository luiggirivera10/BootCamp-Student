package com.everis.student.app.service;

import com.everis.student.app.bean.Student;
import java.util.Date;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * .
 * @author lriveras.
 *
 */
public interface StudentService {

  /**
 * findAll.
 */
  Flux<Student> findAll();

  /**
 * findById.
 */
  Mono<Student> findById(String id);

  /**
 * save.
 */
  Mono<Student> save(Student student);

  /**
 * Delete.
 */
  Mono<Void> delete(Student student);

  /**
 * findByName.
 */
  Flux<Student> findByName(String name);

  /**
 * findByNumberID.
 */
  Mono<Student> findByNumberID(String nummberID);

  /**
 * findByBirthdateBetween.
 */
  Flux<Student> findByBirthdateBetween(Date birthdate, Date birthdate1);

  /**
 * Solo para TEST.
 */
  Mono<Student> obtenerPorName(String name);
}
