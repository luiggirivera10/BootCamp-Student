package com.everis.student.app.controller;

import com.everis.student.app.bean.Student;
import com.everis.student.app.service.StudentService;
import java.util.Date;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Servicio Rest.
 *
 */
@RestController
@RefreshScope
@RequestMapping("/api/v1.0")
public class StudentRestController {

  /**
 * Inject StudentService.
 */
  @Autowired
  private StudentService studentRep;
  /**
 * log.
 */
  private static final Logger log = LoggerFactory.getLogger(StudentRestController.class);

  /**
   * Servicio listar todo.
   */
  @GetMapping("/students")
 public Flux<Student> findAll() {
    final Flux<Student> students = studentRep.findAll()
                .map(student -> {
                  student.setName(student.getName());
                  return student;
                })
        .doOnNext(stu -> log.info(stu.getName()));
    return students;
  }
  /**

   * Servicio buscar por ID.

   */
  @GetMapping("/students/{id}")
 public Mono<Student> findById(@PathVariable final String id) {
    final Flux<Student> students = studentRep.findAll();
    final Mono<Student> student = students.filter(s -> s.getId().equals(id))
                        .next().doOnNext(stu -> log.info(stu.getName()));
    return student;
  }
  /**

   * Servicio para buscar por nombre devuelve una lista.

   */
  @GetMapping("/students/name/{name}")
  public Flux<Student> findByName(@PathVariable ("name") final String name) {
    return studentRep.findByName(name)
        .doOnNext(stu -> log.info(stu.getName()));
  }

  /**
 * .
 */
  @GetMapping("/students/nombre/{name}")
  public Mono<ResponseEntity<Student>> getByName(@PathVariable ("name") final String name) {
    return studentRep.obtenerPorName(name).doOnNext(stu -> log.info(stu.getName()))
              .map(student -> new ResponseEntity<>(student, HttpStatus.FOUND))
              .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**

   * Servicio para buscar por DNI.

   */
  @GetMapping("/students/doc/{numberID}")
  public Mono<ResponseEntity<Student>> 
      findByNumberID(@PathVariable ("numberID") final String numberID) {
    return studentRep.findByNumberID(numberID)
              .map(student -> new ResponseEntity<>(student, HttpStatus.FOUND))
              .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**

   * Servicio para crear.

   */
  @PostMapping("/students")
  public Mono<ResponseEntity<Student>> newStudent(@Valid @RequestBody final Student student) {
    return studentRep.save(student)
              .map(addStudent -> new ResponseEntity<>(addStudent, HttpStatus.CREATED))
              .defaultIfEmpty(new ResponseEntity<>(HttpStatus.CONFLICT));
  }

  /**

   * Servicio para modificar.

   */
  @PutMapping("/students/{id}")
    public Mono<ResponseEntity<Student>> updateStudent(@PathVariable(value = "id") final String id,
                                                   @Valid @RequestBody final Student student) {
    return studentRep.findById(id)
                .flatMap(existingStudent -> {
                  existingStudent.setName(student.getName());
                  existingStudent.setGender(student.getGender());
                  existingStudent.setBirthdate(student.getBirthdate());
                  existingStudent.setTypeID(student.getTypeID());
                  existingStudent.setNumberID(student.getNumberID());
                  return studentRep.save(existingStudent);
                })
                .map(updatedStudent -> new ResponseEntity<>(updatedStudent, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**

   * Servicio para eliminar.

   */
  @DeleteMapping("/students/{id}")
  public Mono<ResponseEntity<Void>> deleteStudent(@PathVariable(value = "id") final String id) {
    return studentRep.findById(id)
    .flatMap(existingStudent ->
 studentRep.delete(existingStudent)
 .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
 .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**

   * Servicion para buscar entre fechas.

   */
  @GetMapping("/students/date/{birthdate}/{birthdate1}")
  public Flux<Student> findByBirthdate(@PathVariable("birthdate")
      @DateTimeFormat(iso = ISO.DATE) final Date birthdate,@PathVariable("birthdate1")
      @DateTimeFormat(iso = ISO.DATE) final Date birthdate1) {
    return studentRep.findByBirthdateBetween(birthdate, birthdate1);
  }
}
