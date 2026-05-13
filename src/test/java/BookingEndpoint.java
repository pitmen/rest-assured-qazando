import io.restassured.RestAssured;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class BookingEndpoint
{
    private final String BASE_URL = "https://restful-booker.herokuapp.com";

    public String lerJson(String caminhoArquivo) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoArquivo)));
    }

    public void getAllBookings(){
        RestAssured.baseURI = BASE_URL;

        given()
                .header("Accept", "*/*")
                .when()
                .get("/booking")
                .then()
                .statusCode(200)
                .log().all();
    }

    public void getBookingsById(int id){
        RestAssured.baseURI = BASE_URL;

        given()
                .header("Accept", "application/json")
                .when()
                .get("/booking/" + id)
                .then()
                .statusCode(200)
                .body("firstname", equalTo("Josh"))
                .body("lastname", equalTo("Allen"))
                .body("totalprice", equalTo(111))
                .body("depositpaid", is(true))
                .body("bookingdates.checkin", equalTo("2018-01-01"))
                .body("bookingdates.checkout", equalTo("2019-01-01"))
                .body("additionalneeds", equalTo("super bowls"));
    }

    public void postBookings() throws IOException {
        RestAssured.baseURI = BASE_URL;

        String jsonBody = lerJson("src/test/resources/payloads/reserva.json");

        given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .body("booking.firstname", equalTo("Pitter"))
                .body("booking.lastname", equalTo("Lacerda"))
                .body("booking.totalprice", equalTo(200))
                .body("booking.depositpaid", is(true))
                .body("booking.bookingdates.checkin", equalTo("2026-05-20"))
                .body("booking.bookingdates.checkout", equalTo("2026-05-27"))
                .body("booking.additionalneeds", equalTo("Preciso de um colchao extra infantil"));
    }
}
