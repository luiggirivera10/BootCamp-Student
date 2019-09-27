package com.everis.student.app.service;

import static org.mockito.Mockito.when;

import com.everis.student.app.bean.Student;
import com.everis.student.app.repository.StudentRepository;
import com.everis.student.app.service.impl.StudentServiceImpl;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.reactivestreams.Publisher;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * .
 * @author lriveras
 *
 */
@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentServiceImplTest {

  /**
   * Inject StudentRepository.
   */
  @Mock
  private StudentRepository studentRepository;

  /**
   * Inject StudentServiceImpl.
   */
  @InjectMocks
  private StudentServiceImpl studentService;

  /**
   * finaAll.
   */
  @Test
  public void findallserviceimpltest() {
    final Student student = new Student();
    student.setName("Abelardo");
    student.setGender("Femenino");
    student.setBirthdate(new Date());
    student.setTypeID("CARNET");
    student.setNumberID("55556666");
    
    when(studentService.findAll()).thenReturn(Flux.just(student));
    final Flux<Student> actua = studentService.findAll();
    assertResults(actua, student);
  }

  /**
   * findById-Exist.
   */
  @Test
  public void findByidexistserviceimpltest() {
    final Student student2 = new Student();
    student2.setId("102");
    student2.setName("Ramon");
    student2.setGender("Masculino");
    student2.setBirthdate(new Date());
    student2.setTypeID("CARNET");
    student2.setNumberID("10210210");
    when(studentRepository.findById(student2.getId())).thenReturn(Mono.just(student2));
    final Mono<Student> actual = studentRepository.findById(student2.getId());
    assertResults(actual, student2);
  }

  /**
   * findById-not-Exist.
   */
  @Test
  public void findByIdnotexistServiceImplTest() {
    final Student student = new Student();
    student.setId("101");
    student.setName("Tito");
    student.setGender("Masculino");
    student.setBirthdate(new Date());
    student.setTypeID("DNI");
    student.setNumberID("10110110");
    when(studentRepository.findById(student.getId())).thenReturn(Mono.empty());
    final Mono<Student> actual = studentRepository.findById(student.getId());
    assertResults(actual);
  }
  
  /**
   * save.
   */
  @Test
  public void saveServiceImplTest() {
    final Student student = new Student();
    student.setId("10");
    student.setName("Victor");
    student.setGender("Masculino");
    student.setBirthdate(new Date());
    student.setTypeID("DNI");
    student.setNumberID("44443333");
    student.setCreatedAt(new Date());
    when(studentRepository.save(student)).thenReturn(Mono.just(student));
    final Mono<Student> actual = studentService.save(student);
    assertResults(actual, student);
  }
  
  /**
   * deleteTest.
   */
  @Test
  public void deleteServiceImplTest() {
    final Student stud = new Student();
    stud.setId("10");
    stud.setName("Victor");
    stud.setGender("Masculino");
    stud.setBirthdate(new Date());
    stud.setTypeID("DNI");
    stud.setNumberID("44443333");
    stud.setCreatedAt(new Date());
    
    when(studentRepository.delete(stud)).thenReturn(Mono.empty());
  }

  /**
   * findByNumberID.
   */
  @Test
  public void findBynnumberidserviceimplTest() {
    final Student student = new Student();
    student.setId("dekweowe");
    student.setName("cristohper");
    student.setGender("male");
    student.setBirthdate(new Date());
    student.setTypeID("dni");
    student.setNumberID("736723720");
    final String numberID = "736723720";
    when(studentRepository.findByNumberID(numberID)).thenReturn(Mono.just(student));
    final Mono<Student> actual = studentService.findByNumberID(numberID);
    assertResults(actual,student);
  }

  /**
   * findByName.
   */
  @Test
  public void findByNameServiceImplTest() {
    final Student student = new Student();
    student.setId("dekweowe");
    student.setName("cristohper");
    student.setGender("male");
    student.setBirthdate(new Date());
    student.setTypeID("dni");
    student.setNumberID("736723727");
    final String name = "736723727";
    when(studentRepository.findByName(name)).thenReturn(Flux.just(student));
    final Flux<Student> actual = studentService.findByName(name);
    assertResults(actual,student);
  }

  /**
   * assertResults.
   */
  private void assertResults(final Publisher<Student> publisher, Student... expectedProducts) {
    StepVerifier
        .create(publisher)
        .expectNext(expectedProducts)
        .verifyComplete();
  }
}
