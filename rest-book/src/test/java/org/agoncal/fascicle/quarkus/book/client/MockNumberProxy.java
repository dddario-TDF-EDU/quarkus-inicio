package org.agoncal.fascicle.quarkus.book.client;


import io.quarkus.test.Mock;
import jakarta.enterprise.context.ApplicationScoped;
import org.agoncal.fascicle.quarkus.book.servicio.numberResources.IsbnNumbers;
import org.agoncal.fascicle.quarkus.book.servicio.numberResources.NumberProxy;
import org.eclipse.microprofile.rest.client.inject.RestClient;

//import javax.enterprise.context.ApplicationScoped;

// tag::adocSnippet[]
@Mock
@ApplicationScoped
@RestClient
public class MockNumberProxy implements NumberProxy {

  @Override
  public IsbnNumbers generateIsbnNumbers() {
    IsbnNumbers isbnNumbers = new IsbnNumbers();
    isbnNumbers.isbn13 = "Dummy Isbn 13";
    isbnNumbers.isbn10 = "Dummy Isbn 10";
    return isbnNumbers;
  }


}
