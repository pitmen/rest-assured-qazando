// Importa todas as classes necessárias para os testes

import io.restassured.RestAssured; // Biblioteca para automação de testes REST
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test; // Framework de testes JUnit 5

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

// Classe que contém os testes de API
public class BookingTest {

    BookingEndpoint bookingEndpoint = new BookingEndpoint();

    /*public String lerJson(String caminhoArquivo) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoArquivo)));
    }*/

    /*@Test
    public void consultaPageObjects() {
        bookingEndpoint.getAllBookings();
    }*/

    // Define um metodo de teste
    @Test
    public void testeGetBooking(){
        bookingEndpoint.getAllBookings();
    }

    //@Tag("cadastro")
    @Test
    public void testeGetBookingPorId() {
        bookingEndpoint.getBookingsById(598);
    }

    @Tag("smoke")
    @Test
    public void testePostBookings() throws IOException {
        bookingEndpoint.postBookings();
    }

}
