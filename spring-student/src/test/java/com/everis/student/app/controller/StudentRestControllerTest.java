package com.everis.student.app.controller;

import com.everis.student.app.bean.Student;
import com.everis.student.app.repository.StudentRepository;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

/**
 * .
 * @author lriveras
 *
 */
@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentRestControllerTest {

  /**
 * InjectWebTestClient.
 */
  @Autowired
  private WebTestClient client;

  /**
 * Log.
 */
  private static final Logger log = LoggerFactory.getLogger(StudentRestControllerTest.class);
  /**
 * Inject SetudentRepository.
 */
  @Autowired
  private StudentRepository service;

  @Test
  public void findAllTest() {
    client.get().uri("/api/v1.0/students")
         .accept(MediaType.APPLICATION_JSON_UTF8)
         .exchange().expectStatus().isOk()
         .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
         .expectBodyList(Student.class)
         .consumeWith(response -> {
           final List<Student> students = response.getResponseBody();
           students.forEach(s -> {
             System.out.println(s.getName() + " - " + s.getNumberID());
           });
           Assertions.assertThat(students.size() > 0).isTrue();
         });
  }

  /**
 * findbyId.
 */
  @Test
public void findByIdTest() {
    final Student student = service.findName("Flor").block();
    if (student != null) {
      client.get().uri("/api/v1.0/students/{id}", Collections.singletonMap("id", student.getId()))
          .accept(MediaType.APPLICATION_JSON_UTF8)
          .exchange()
          .expectStatus().isOk()
          .expectHeader()
          .contentType(MediaType.APPLICATION_JSON_UTF8)
          .expectBody(Student.class).consumeWith(response -> {
            final Student studnt = response.getResponseBody();
            Assertions.assertThat(studnt.getId()).isNotEmpty();
            Assertions.assertThat(studnt.getId().length() > 0).isTrue();
            Assertions.assertThat(studnt.getName()).isEqualTo("Flor");
          });
    } else {
      log.error("No se encontraron datos!");
    }
  }

  /**
 * newTest.
 */
  @Test
  public void newTest() {
    final Student student = new Student("Martino", "Masculinox", new Date(),"DNI", "00000000");
    if (student != null) {
      client.post().uri("/api/v1.0/students")
       .contentType(MediaType.APPLICATION_JSON_UTF8)
       .accept(MediaType.APPLICATION_JSON_UTF8)
       .body(Mono.just(student), Student.class)
       .exchange()
       .expectStatus().isCreated()
       .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
       .expectBody()
       .jsonPath("$.id").isNotEmpty().jsonPath("$.name").isEqualTo("Martinox");
    }
  }

  /**
   * updateTest.
   */
  @Test
  public void updateTest() {
    final Student student = service.findName("Robertxxx").block();
    final Student studentEditado = new Student("Robert", "Masculino", 
          new Date(),"DNI", "20090806");
    if (student != null) {
      client.put().uri("/api/v1.0/students/{id}",
        Collections.singletonMap("id", student.getId()))
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.APPLICATION_JSON_UTF8)
     .body(Mono.just(studentEditado), Student.class)
     .exchange()
     .expectStatus().isCreated()
     .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
     .expectBody()
     .jsonPath("$.id").isNotEmpty()
     .jsonPath("$.name").isEqualTo("Robert")
     .jsonPath("$.numberID").isEqualTo("20090806");
    } else {
      log.error("No se encontraron datos!");
    }

  }

  /**
   * findByNameTest.
   */
  @Test
  public void findByNameTest() {
    final Student student = service.findName("Lucia").block();
    if (student != null) {
      client.get()
        .uri("/api/v1.0/students/nombre/{name}",Collections.singletonMap("name", student.getName()))
        .exchange()
        .expectStatus().isFound()
        .expectBody().jsonPath("$.name").isEqualTo("Lucia");
    } else {
      log.error("No se encontraron datos!");
    }
  }

  /**
   * findByNumberIdTest.
   */
  @Test
  public void findByNumberIdTest() {
    final Student student = service.findName("Lucia").block();
    if (student != null) {
      client.get()
        .uri("/api/v1.0/students/doc/{numberID}",
        Collections.singletonMap("numberID", student.getNumberID()))
        .exchange()
        .expectStatus().isFound()
        .expectBody().jsonPath("$.numberID").isEqualTo("20191010");
    } else {
      log.error("No se encontraron datos!");
    }
  }

  /**
   * deleteTest.
   */
  @Test
  public void deleteTest() {
    final Student student = service.findByNumberID("00000001").block();
    if (student != null) {
      client.delete()
        .uri("/api/v1.0/students/{id}",Collections.singletonMap("id", student.getId()))
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .isEmpty();
    } else {
      log.error("No se encontraron datos!");
    }
  }
}
