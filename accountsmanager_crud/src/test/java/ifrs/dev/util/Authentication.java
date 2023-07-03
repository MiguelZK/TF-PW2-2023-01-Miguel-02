package ifrs.dev.util;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Authentication {

    static String baseURL = "http://localhost:8080/login";
    static String baseURLSave = "http://localhost:8081/user/save";

    public static void saveUser() {
        /* String novoUsuarioAdm =  */given()
                .contentType(ContentType.URLENC)
                .formParam("nome", "admin")
                .formParam("login", "admin")
                .formParam("senha", "senha123")
        .when()
                .post(baseURLSave)
        .then()
                .statusCode(200)
/*                 .contentType(ContentType.JSON)
                .extract().response()
                .body().asString() */;
                
/*                 assertEquals("nome: admin", novoUsuarioAdm);
                assertEquals("login: admin", novoUsuarioAdm); */
    }

    public static String token() {
        String token =
        given()
                .contentType(ContentType.URLENC)
                .formParam("login", "admin")
                .formParam("senha", "senha123")
/*                 .contentType(ContentType.JSON)
                .body("""
                        {
                        "login": "admin",
                        "senha": "senha123"
                        }
                        """) */
        .when()
                .post(baseURL)
        .then()
                .extract().asString();
        return token;
    }
}
