import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TesteBasico {

    // Define um metodo de teste
    @Test
    public void testeGetBooking(){
        // Configure a URL base para as requisições da API
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        //Configura e executa a requisição GET para o endpoint "/booking/"
        given() // Define as configurações da requisição (headers, parametros, etc.)
                .header("Accept", "*/*") // Adiciona o header accept
        .when() // Indica o inicio da execução
                .get("/booking")
        .then() // Define as validações da resposta
                .statusCode(200) //Verifica se o status code da reposta é 200 (OK)
                .log().all();
    }

}
