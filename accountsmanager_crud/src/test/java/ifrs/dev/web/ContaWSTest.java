package ifrs.dev.web;

import ifrs.dev.model.Conta;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;

@QuarkusTest
public class ContaWSTest {

    @Test
    public void testSave() {
        given()
            .formParam("banco", "Banco X")
            .formParam("numeroConta", 123456789L)
            .formParam("agencia", 9876L)
        .when()
            .post("/conta/save")
        .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body("banco", equalTo("Banco X"))
            .body("numeroConta", equalTo(123456789))
            .body("agencia", equalTo(9876));
    }

    @Test
    public void testList() {
        given()
        .when()
            .get("/conta/list")
        .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body("", hasSize(greaterThan(0))); // Verifica se a lista não está vazia
    }

    @Test
    public void testListById() {
        given()
            .pathParam("id", 1) // Defina o ID da conta existente
        .when()
            .get("/conta/list/{id}")
        .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body("banco", equalTo("Banco Y"))
            .body("numeroConta", equalTo(987654321))
            .body("agencia", equalTo(1234));
    }

    @Test
    public void testDelete() {
        given()
            .pathParam("id", 1) // Defina o ID da conta existente
        .when()
            .delete("/conta/delete/{id}")
        .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body("banco", equalTo("Banco Z"))
            .body("numeroConta", equalTo(111222333))
            .body("agencia", equalTo(4444));
    }

    @Test
    public void testEdit() {
        given()
            .formParam("id", 1) // Defina o ID da conta existente
            .formParam("banco", "Banco W")
            .formParam("agencia", 8888L)
            .formParam("numeroConta", 555666777L)
        .when()
            .put("/conta/edit")
        .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body("banco", equalTo("Banco W"))
            .body("numeroConta", equalTo(555666777))
            .body("agencia", equalTo(8888));
    }

    @Test
    public void testFindByLogin() {
        given()
            .formParam("login", "john.doe")
        .when()
            .post("/conta/findbylogin")
        .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body("banco", equalTo("Banco W"))
            .body("numeroConta", equalTo(555666777))
            .body("agencia", equalTo(8888));
    }
}
