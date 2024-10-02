package org.agoncal.fascicle.quarkus.book.health;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.agoncal.fascicle.quarkus.book.servicio.BookService;

import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;

//import javax.enterprise.context.ApplicationScoped;
//import javax.inject.Inject;


@Readiness
@ApplicationScoped
public class DatabaseConnectionHealthCheck implements HealthCheck {
  @Inject
  BookService bookService;

  @Override
  public HealthCheckResponse call() {
    HealthCheckResponseBuilder responseBuilder = HealthCheckResponse
      .named("Book Datasource connection health check");
    try {
      //List<BookDTO> books = bookService.findAllBooks();
      //responseBuilder.withData("Number of books in the database", books.size()).up();
    } catch (IllegalStateException e) {
      responseBuilder.down();
    }
    return responseBuilder.build();
  }


}
