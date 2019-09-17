package com.everis.student.app;

import com.everis.student.app.bean.Student;
import com.everis.student.app.repository.StudentRepository;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringStudentApplicationTests {

  @Autowired
  private WebTestClient client;

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
           List<Student> students = response.getResponseBody();
           students.forEach(s -> {
             System.out.println(s.getName() + " - " + s.getNumberID());
           });
           Assertions.assertThat(students.size() > 0).isTrue();
         });
  }

  @Test
public void findByIdTest() {
    Student student = service.obtenerPorName("Flor").block();

    client.get().uri("/api/v1.0/students/{id}", Collections.singletonMap("id", student.getId()))
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange()
        .expectStatus().isOk()
        .expectHeader()
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBody(Student.class).consumeWith(response -> {
          Student st = response.getResponseBody();
          Assertions.assertThat(st.getId()).isNotEmpty();
          Assertions.assertThat(st.getId().length() > 0).isTrue();
          Assertions.assertThat(st.getName()).isEqualTo("Flor");
        });
  }

  @Test
  public void newTest() {
    Student student = new Student("Martinox", "Masculino", new Date(),"DNI", "00000000");
    client.post().uri("/api/v1.0/students")
       .contentType(MediaType.APPLICATION_JSON_UTF8)
       .accept(MediaType.APPLICATION_JSON_UTF8)
       .body(Mono.just(student), Student.class)
       .exchange()
       .expectStatus().isOk()
       .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
       .expectBody()
       .jsonPath("$.id").isNotEmpty().jsonPath("$.name").isEqualTo("Martinox");
  }


  @Test
  public void updateTest() {
    Student student = service.obtenerPorName("Robert").block();
    Student studentEditado = new Student("Robertxxx", "Masculino", new Date(),"DNI", "20090806");
    client.put().uri("/api/v1.0/students/{id}",Collections.singletonMap("id", student.getId()))
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.APPLICATION_JSON_UTF8)
     .body(Mono.just(studentEditado), Student.class)
     .exchange()
     .expectStatus().isOk()
     .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
     .expectBody()
     .jsonPath("$.id").isNotEmpty()
     .jsonPath("$.name").isEqualTo("Robertxxx")
     .jsonPath("$.numberID").isEqualTo("20090806");
  }

  @Test
  public void findByNameTest() {
    Student student = service.obtenerPorName("Lucia").block();
    client.get()
        .uri("/api/v1.0/students/nombre/{name}",Collections.singletonMap("name", student.getName()))
        .exchange()
        .expectStatus().isOk()
        .expectBody().jsonPath("$.name").isEqualTo("Lucia");
  }

  @Test
  public void findByNumberIdTest() {
    Student student = service.obtenerPorName("Lucia").block();
    client.get()
        .uri("/api/v1.0/students/doc/{numberID}",
        Collections.singletonMap("numberID", student.getNumberID()))
        .exchange()
        .expectStatus().isOk()
        .expectBody().jsonPath("$.numberID").isEqualTo("20191010");
  }

  @Test
  public void deleteTest() {
    Student student = service.findByNumberID("00000001").block();
    client.delete()
        .uri("/api/v1.0/students/{id}",Collections.singletonMap("id", student.getId()))
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .isEmpty();
  }

  /* FALTA CORREGIR
  @Test
  public void buscarBetweenDate() {
    client.get()
        .uri("/api/v1.0/students/date/{birthdate}/{birthdate1}",
         Collections.singletonMap("birthdate",2006-10-10),
         Collections.singletonMap("birthdate1", 2008-10-10))
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8);
  }
  */

}
