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

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class StudentServiceImplTest {
  
  @Mock
  private StudentRepository studentRepository;
  
  @InjectMocks
  private StudentServiceImpl studentService;
  
  @Test
  public void findAll() {
    Student student = new Student();
    student.setName("Walter");
    student.setGender("Masculino");
    student.setBirthdate(new Date());
    student.setTypeID("DNI");
    student.setNumberID("55556666");
    
    when(studentService.findAll()).thenReturn(Flux.just(student));
    Flux<Student> actua = studentService.findAll();
    assertResults(actua, student);
  }
  
  
  @Test
  public void findById_exist() {
    Student parent2 = new Student();
    parent2.setId("102");
    parent2.setName("Ramon");
    parent2.setGender("Masculino");
    parent2.setBirthdate(new Date());
    parent2.setTypeID("DNI");
    parent2.setNumberID("10210210");
    when(studentRepository.findById(parent2.getId())).thenReturn(Mono.just(parent2));
    Mono<Student> actual = studentRepository.findById(parent2.getId());
    assertResults(actual, parent2);
  }
  
  @Test
  public void findById_not_exist() {
    Student s = new Student();
    s.setId("101");
    s.setName("Tito");
    s.setGender("Masculino");
    s.setBirthdate(new Date());
    s.setTypeID("DNI");
    s.setNumberID("10110110");
    when(studentRepository.findById(s.getId())).thenReturn(Mono.empty());
    Mono<Student> actual = studentRepository.findById(s.getId());
    assertResults(actual);
  }
  
  
  @Test
  public void save() {
    Student s = new Student();
    s.setId("10");
    s.setName("Victor");
    s.setGender("Masculino");
    s.setBirthdate(new Date());
    s.setTypeID("DNI");
    s.setNumberID("44443333");
    s.setCreatedAt(new Date());
    when(studentRepository.save(s)).thenReturn(Mono.just(s));
    Mono<Student> actual = studentService.save(s);
    assertResults(actual, s);
  }
  
  
  @Test
  public void delete() {
    Student stud = new Student();
    stud.setId("10");
    stud.setName("Victor");
    stud.setGender("Masculino");
    stud.setBirthdate(new Date());
    stud.setTypeID("DNI");
    stud.setNumberID("44443333");
    stud.setCreatedAt(new Date());
    
    when(studentRepository.delete(stud)).thenReturn(Mono.empty());
  }
  
  @Test
  public void findBynNumberID() {
    Student s = new Student();
    s.setId("dekweowe");
    s.setName("cristohper");
    s.setGender("male");
    s.setBirthdate(new Date());
    s.setTypeID("dni");
    s.setNumberID("736723727");
    final String numberID = "736723727";
    when(studentRepository.findByNumberID(numberID)).thenReturn(Mono.just(s));
    Mono<Student> actual = studentService.findByNumberID(numberID);
    assertResults(actual,s);
  }

  @Test
  public void findByName() {
    Student s = new Student();
    s.setId("dekweowe");
    s.setName("cristohper");
    s.setGender("male");
    s.setBirthdate(new Date());
    s.setTypeID("dni");
    s.setNumberID("736723727");
    final String name = "736723727";
    when(studentRepository.findByName(name)).thenReturn(Flux.just(s));
    Flux<Student> actual = studentService.findByName(name);
    assertResults(actual,s);
  }
  
  private void assertResults(Publisher<Student> publisher, Student... expectedProducts) {
    StepVerifier
        .create(publisher)
        .expectNext(expectedProducts)
        .verifyComplete();
  }
}
