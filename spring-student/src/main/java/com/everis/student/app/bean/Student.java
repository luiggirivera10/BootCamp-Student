package com.everis.student.app.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "students")
@Builder(toBuilder = true)
public class Student {
  @Id
 private String id;
  @NotEmpty(message = "No debe ser vacio!")
 private String name;
  @NotEmpty(message = "No debe ser vacio!")
 private String gender;
  @NotNull
  //@DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
 private Date birthdate;
  @NotEmpty(message = "No debe ser vacio!")
 private String typeID;
  @NotEmpty(message = "No debe ser vacio!")
  @Size(min = 8, max = 8)
 private String numberID;
  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
 private Date createdAt = new Date();
}
