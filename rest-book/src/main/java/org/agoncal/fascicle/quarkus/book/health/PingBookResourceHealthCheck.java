package org.agoncal.fascicle.quarkus.book.health;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.agoncal.fascicle.quarkus.book.recurso.libro.BookResource;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

//import javax.enterprise.context.ApplicationScoped;
//import javax.inject.Inject;

@Liveness
@ApplicationScoped
public class PingBookResourceHealthCheck implements HealthCheck {
  @Inject
  BookResource bookResource;

  @Override
  public HealthCheckResponse call() {
    bookResource.ping();
    return HealthCheckResponse.named("Ping Book REST Endpoint").up().build();
  }

}
