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
  public Flux<Student> findAll();

  /**
 * findById.
 */
  public Mono<Student> findById(String id);

  /**
 * save.
 */
  public Mono<Student> save(Student student);

  /**
 * Delete.
 */
  public Mono<Void> delete(Student student);

  /**
 * findByName.
 */
  public Flux<Student> findByName(String name);

  /**
 * findByNumberID.
 */
  public Mono<Student> findByNumberID(String nummberID);

  /**
 * findByBirthdateBetween.
 */
  public Flux<Student> findByBirthdateBetween(Date birthdate, Date birthdate1);

  /**
 * Solo para TEST.
 */
  public Mono<Student> obtenerPorName(String name);
}
