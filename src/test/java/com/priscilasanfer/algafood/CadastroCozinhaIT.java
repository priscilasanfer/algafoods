package com.priscilasanfer.algafood;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CadastroCozinhaIT {

    @LocalServerPort
    private int port;

    @Test
    public void deveRetornarStatus200QuandoConsultarCozinhas() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        given()
                .basePath("/cozinhas")
                .port(port)
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.OK.value());
    }

}
