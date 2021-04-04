package com.priscilasanfer.algafood;

import com.priscilasanfer.algafood.domain.model.Cozinha;
import com.priscilasanfer.algafood.domain.repository.CozinhaRepository;
import com.priscilasanfer.algafood.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.priscilasanfer.algafood.util.ReadFileAsString.readFileAsString;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {

    private static final String SRC_TEST_RESOURCES_FILES_REQUEST = "src/test/resources/json/cozinha";
    private static final int COZINHA_ID_INEXISTENTE = 100;

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    private int quantidadeCozinhasCadastradas;

    private Cozinha cozinhaAmericana;

    @Before
    public void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";

        databaseCleaner.clearTables();
        prepararDados();
    }

    @Test
    public void deveRetornarStatus200QuandoConsultarCozinhas() {
        given()
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarQuantidadeCorretaDeCozinhasQuandoConsultarCozinhas() {
        given()
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .body("", hasSize(quantidadeCozinhasCadastradas))
                .body("nome", hasItems("Indiana", "Brasileira"));
    }

    @Test
    public void deveRetornarStatus201QuandoCadastrarCozinha() {
        String requestBody = readFileAsString(SRC_TEST_RESOURCES_FILES_REQUEST + "/cadastrarCozinhaRequest.json");

        given()
                .body(requestBody)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarRespostaEStatusCorretoQuandoConsultarCozinhaExistente(){
        given()
                .pathParam("cozinhaId", cozinhaAmericana.getId())
                .accept(ContentType.JSON)
            .when()
                .get("/{cozinhaId}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo(cozinhaAmericana.getNome()));
    }

    @Test
    public void deveRetornarStatus404QuandoConsultarCozinhaInexistente(){
        given()
                .pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
                .accept(ContentType.JSON)
            .when()
                .get("/{cozinhaId}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepararDados(){
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Brasileira");
        cozinhaRepository.save(cozinha1);

        cozinhaAmericana = new Cozinha();
        cozinhaAmericana.setNome("Indiana");
        cozinhaRepository.save(cozinhaAmericana);

        quantidadeCozinhasCadastradas = (int) cozinhaRepository.count();
    }
}
