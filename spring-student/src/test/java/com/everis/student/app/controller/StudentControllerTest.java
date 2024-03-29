package com.everis.student.app.controller;

import com.everis.student.app.bean.Student;
import com.everis.student.app.repository.StudentRepository;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * .
 * @author lriveras
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StudentControllerTest {

  /**
   * Injected AppContext.
   */
  @Autowired
  private ApplicationContext applicationContext;

  /**
   * .
   */
  @Autowired
  private StudentRepository studentRepository;
  
  /**
   * .
   */
  private WebTestClient client;
  
  /**
   * .
   */
  private List<Student> expectedStudents;

  /**
   * .
   */
  @BeforeEach
  private void setUp() {
    client = WebTestClient
      .bindToApplicationContext(applicationContext)
      .configureClient()
      .baseUrl("/api/v1.0")
      .build();

    final Flux<Student> initData = studentRepository.deleteAll()
        .thenMany(Flux.just(
        Student.builder().id("1").name("Andres").gender("Masculino")
        .birthdate(new Date()).typeID("DNI").numberID("74306050").build(),
        Student.builder().id("2").name("Rodrigo").gender("Masculino")
        .birthdate(new Date()).typeID("DNI").numberID("74306051").build())
        .flatMap(studentRepository::save))
        .thenMany(studentRepository.findAll());

    expectedStudents = initData.collectList().block();
  }

  /**
   * Test FindAll.
   */
  @Test
  private void findAll() {

    client.get().uri("/students").exchange()
      .expectStatus().isOk();
  }

  /**
   * Test Save.
   */
  @Test
  private void save() {
    final Student expectedStud = expectedStudents.get(0);
    client.post().uri("/students").body(Mono.just(expectedStud), Student.class).exchange()
      .expectStatus().isCreated();
  }

  /**
   * Test findByID.
   */
  @Test
  private void findById() {

    final String id = "1";
    client.get().uri("/students/{id}", id).exchange()
      .expectStatus().isOk();
  }

  /**
   * Test Update.
   */
  @Test
  private void update() {

    final Student expectedStud = expectedStudents.get(0);

    client.put().uri("/students/{id}", expectedStud.getId())
    .body(Mono.just(expectedStud), Student.class).exchange()
      .expectStatus().isCreated();
  }

  /**
   * Test Delete.
   */
  @Test
  private void delete() {

    final Student studDelete = expectedStudents.get(0);
    client.delete().uri("/students/{id}", studDelete.getId()).exchange()
      .expectStatus().isOk();
  }

  /**
   * FindByNumberID.
   */
  @Test
  private void findByNumberID() {

    final String numberID = "74306051";
    client.get().uri("/students/doc/{numberID}", numberID).exchange()
      .expectStatus().isFound();
  }

  /**
   * Test FindByName.
   */
  @Test
  private void findByName() {

    final String name = "Rodrigo";
    client.get().uri("/students/nombre/{name}", name).exchange()
      .expectStatus().isFound();
  }

  /**
   * Test FindByBirthDateBetween.
   */
  @Test
  private void findBybirthdateBetween() {

    final LocalDate birthdate = LocalDate.of(2018,03,02);
    final LocalDate birthdate1 = LocalDate.of(2019,03,02);
    client.get().uri("/students/date/{birthdate}/{birthdate1}", birthdate,birthdate1).exchange()
        .expectStatus().isOk()
        .expectBodyList(Student.class);

  }
}
