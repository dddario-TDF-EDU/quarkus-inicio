package org.agoncal.fascicle.quarkus.book.health;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.agoncal.fascicle.quarkus.book.recurso.libro.LibroResource;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

//import javax.enterprise.context.ApplicationScoped;
//import javax.inject.Inject;

@Liveness
@ApplicationScoped
public class PingBookResourceHealthCheck implements HealthCheck {
  @Inject
  LibroResource libroResource;

  @Override
  public HealthCheckResponse call() {
    libroResource.ping();
    return HealthCheckResponse.named("Ping Book REST Endpoint").up().build();
  }

}
