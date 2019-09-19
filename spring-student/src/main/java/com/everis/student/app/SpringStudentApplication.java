package com.everis.student.app;

import com.everis.student.app.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;


/**
 * .
 * @author lriveras
 *
 */
@SpringBootApplication
@Import(SwaggerConfiguration.class)
public class SpringStudentApplication {

  /**
 * Main.
 */
  public static void main(String[] args) {
    SpringApplication.run(SpringStudentApplication.class, args);
  }

  public void addResourceHandlers(ResourceHandlerRegistry regisry) {
    regisry.addResourceHandler("swagger-ui.html")
      .addResourceLocations("classpath:/META-INF/resources/");
  }

}
