package com.everis.student.app.service.impl;

import com.everis.student.app.bean.Student;
import com.everis.student.app.repository.StudentRepository;
import com.everis.student.app.service.StudentService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * .
 * @author lriveras
 *
 */
@Service
public class StudentServiceImpl implements StudentService {

  /**
 * Injec StudentRepository.
 */
  @Autowired
  private StudentRepository studentRepository;

  /**
 * findAll.
 */
  @Override
  public Flux<Student> findAll() {
    return studentRepository.findAll();
  }

  /**
 * findById.
 */
  @Override
  public Mono<Student> findById(final String id) {
    return studentRepository.findById(id);
  }

  /**
 * save.
 */
  @Override
  public Mono<Student> save(final Student student) {
    return studentRepository.save(student);
  }

  /**
 * delete.
 */
  @Override
  public Mono<Void> delete(final Student student) {
    return studentRepository.delete(student);
  }

  /**
 * findByName.
 */
  @Override
  public Flux<Student> findByName(final String name) {
    return studentRepository.findByName(name);
  }

  /**
 * findByNumberID.
 */
  @Override
  public Mono<Student> findByNumberID(final String nummberID) {
    return studentRepository.findByNumberID(nummberID);
  }

  /**
 * findByBirthdateBetween.
 */
  @Override
  public Flux<Student> findByBirthdateBetween(final Date birthdate, final Date birthdate1) {
    return studentRepository.findByBirthdateBetween(birthdate, birthdate1);
  }

  /**
 * Solo para TEST.
 */
  @Override
  public Mono<Student> obtenerPorName(final String name) {
    return studentRepository.findName(name);
  }
}
