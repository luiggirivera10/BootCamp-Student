package com.everis.student.app.controller;

import com.everis.student.app.bean.Student;
import com.everis.student.app.repository.StudentRepository;
import java.util.Date;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/v1.0")
public class StudentRestController {

  @Autowired
  private StudentRepository studentRep;

  private static final Logger log = LoggerFactory.getLogger(StudentRestController.class);
  /**

   * .

   */
  @GetMapping("/students")
 public Flux<Student> findAll() {
    Flux<Student> students = studentRep.findAll()
                .map(student -> {
                  student.setName(student.getName().toUpperCase());
                  return student;
                })
        .doOnNext(stu -> log.info(stu.getName()));
    return students;
  }
  /**

   * .

   */
  @GetMapping("/students/{id}")
 public Mono<Student> show(@PathVariable String id) {
    Flux<Student> students = studentRep.findAll();
    //tenemos un mono y con el next nos retorna el primero
    Mono<Student> student = students.filter(s -> s.getId().equals(id))
                        .next().doOnNext(stu -> log.info(stu.getName()));
    return student;
  }

  @GetMapping("/students/name/{name}")
 public Flux<Student> findByName(@PathVariable ("name") String name) {
    return studentRep.findByName(name);
  }

  @GetMapping("/students/nombre/{name}")
  public Mono<Student> obtenerByName(@PathVariable ("name") String name) {
    return studentRep.obtenerPorName(name);
  }

  @GetMapping("/students/doc/{numberID}")
 public Mono<Student> findByNumberID(@PathVariable ("numberID") String numberID) {
    return studentRep.findByNumberID(numberID);
  }

  @PostMapping("/students")
  public Mono<Student> newStudent(@RequestBody Student student) {
    return studentRep.save(student);
  }

  /**

   * .

   */
  @PutMapping("/students/{id}")
    public Mono<ResponseEntity<Student>> updateStudent(@PathVariable(value = "id") String id,
                                                   @Valid @RequestBody Student student) {
    return studentRep.findById(id)
                .flatMap(existingStudent -> {
                  existingStudent.setName(student.getName());
                  existingStudent.setGender(student.getGender());
                  existingStudent.setBirthdate(student.getBirthdate());
                  existingStudent.setTypeID(student.getTypeID());
                  existingStudent.setNumberID(student.getNumberID());
                  return studentRep.save(existingStudent);
                })
                .map(updatedStudent -> new ResponseEntity<>(updatedStudent, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
  /**

   * .

   */
  @DeleteMapping("/students/{id}")
  public Mono<ResponseEntity<Void>> deleteStudent(@PathVariable(value = "id") String id) {
    return studentRep.findById(id)
    .flatMap(existingStudent ->
 studentRep.delete(existingStudent)
 .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
 .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**

   * .

   */
  @GetMapping("/students/date/{birthdate}/{birthdate1}")
  public Flux<Student> findByBirthdate(@PathVariable("birthdate")
      @DateTimeFormat(iso = ISO.DATE) Date birthdate,@PathVariable("birthdate1")
      @DateTimeFormat(iso = ISO.DATE) Date birthdate1) {
    return studentRep.findByBirthdateBetween(birthdate, birthdate1);
  }
}
