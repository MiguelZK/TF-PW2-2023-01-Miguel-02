package ifrs.dev.web;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.smallrye.jwt.build.Jwt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ifrs.dev.util.Authentication;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.HashSet;

@QuarkusTest
public class UserWSTest {

    private String jwtTokenUserAdmin;
    private String jwtTokenGod;

    @BeforeEach
    void setup() {
        // Aqui você pode obter o token JWT do seu serviço de autenticação
        // e armazená-lo na variável "jwtToken" para uso nos testes
        // jwtToken = "seu-token-jwt";
        Authentication.saveUser();

        jwtTokenUserAdmin = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJ1cG4iOiJxdWFscXVlciIsImdyb3VwcyI6WyJVc2VyIiwiQWRtaW4iXSwiZnVsbF9uYW1lIjoiTWlndWVsIENhcmEgTGVnYWwiLCJPdXRybyB2YWxvciBxdWFscXVlciI6IlZhbG9yIHF1YWxxdWVyIiwiZXhwIjoxNjg4MzM2MTg0LCJpYXQiOjE2ODgzNDUxODQsImp0aSI6IjY4OTFkZTg4LWFiYTctNDBjMS05YjhkLTYzNDE1OGQyYzhmOCJ9.XP5ynzfsD-oLQLoV1LGa7zPVcr7UXt0cBZiPg1NMCJ37K5fp9m-BMn1UE1cBNIF6kWESvtSJrd2MaQn6Vc7sMKp_7kfapwxNuPuk2HKg_oxR-nfHauqMNJpyj8HAe7jYWVLbUOPyshTavzIpe8p3da83ECPzCROAWlECcP9sjEsJi52sc_Dc6v9j-t05JReU3Q2zlYXGphbHwjfdIjmjfVuJiN1jaNsBiLLDAOCyxHP7aM6Jhewv8-dZZuHG3S9QxlyPWG-40vd4kiRm_Cg4BL069sZV6mNCxEJQT03GLUEaZYfYtg_s-MLvGeQt_CZO0nEjjxhyRlvXugz4TJXErQ";
        /* jwtTokenUserAdmin = given()
                .contentType(ContentType.URLENC)
                .formParam("login", "admin")
                .formParam("senha", "senha123")
            .when()
                .post("http:localhost:8080/login")
            .then()
                // .statusCode(200)
                .extract()
                .response()
                .asString(); */
    }

    @Test
    void testSaveUser() {
        given()
                .contentType(ContentType.URLENC)
                .formParam("nome", "Nome do Usuario")
                .formParam("login", "nome_usuario")
                .formParam("senha", "senha123")
            .when()
                .post("/user/save")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("nome", equalTo("Nome do Usuario"))
                .body("login", equalTo("nome_usuario"));
    }

    @Test
    void testSaveUserInvalidInput() {
        given()
                .contentType(ContentType.URLENC)
                .formParam("nome", "")
                .formParam("login", "nome_usuario")
                .formParam("senha", "senha123")
            .when()
                .post("/user/save")
            .then()
                .statusCode(500);
    }

    @Test
    void testSaveUserExistingLogin() {
        // Primeiro, salva um usuário com o login "nome_usuario"
        given()
                .contentType(ContentType.URLENC)
                .formParam("nome", "Nome do Usuario")
                .formParam("login", "nome_usuario")
                .formParam("senha", "senha123")
                .when()
                .post("/user/save")
                .then()
                .statusCode(200);

        // Em seguida, tenta salvar outro usuário com o mesmo login
        given()
                .contentType(ContentType.URLENC)
                .formParam("nome", "Maria da Silva")
                .formParam("login", "nome_usuario")
                .formParam("senha", "senha123123")
                .when()
                .post("/user/save")
                .then()
                .statusCode(500);
    }

    @Test
    void testListUsers() {
        given()
            .header("Authorization", "Bearer " + jwtTokenUserAdmin)
        .when()
            .get("/user/list")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("$", hasSize(greaterThan(0)));
    }

    @Test
    void testGetUserById() {
        // Primeiro, salva um usuário e obtém o seu ID
        int userId = given()
            .contentType(ContentType.URLENC)
            .formParam("nome", "Nome do Usuario")
            .formParam("login", "nome_usuario")
            .formParam("senha", "senha123")
        .when()
            .post("/user/save")
        .then()
            .statusCode(200)
            .extract()
            .path("id");
        
        // Em seguida, busca o usuário pelo ID
        given()
            .header("Authorization", "Bearer " + jwtTokenUserAdmin)
            .when()
            .get("/user/list/{id}", userId)
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", equalTo(userId))
            .body("nome", equalTo("Nome do Usuario"))
            .body("login", equalTo("nome_usuario"));
        }
        
        @Test
        void testDeleteUser() {
            // Primeiro, salva um usuário e obtém o seu ID
            int userId = given()
            .contentType(ContentType.URLENC)
            .formParam("nome", "Nome do Usuario")
            .formParam("login", "nome_usuario")
                .formParam("senha", "senha123")
                .when()
                .post("/user/save")
                .then()
                .statusCode(200)
                .extract()
                .path("id");
                
                // Em seguida, exclui o usuário pelo ID
            given()
                .header("Authorization", "Bearer " + jwtTokenUserAdmin)
            .when()
                .delete("/user/delete/{id}", userId)
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(userId))
                .body("nome", equalTo("Nome do Usuario"))
                .body("login", equalTo("nome_usuario"));

        // Tenta buscar o usuário novamente e verifica se foi excluído
        given()
            .header("Authorization", "Bearer " + jwtTokenUserAdmin)
                .when()
                .get("/user/list/{id}", userId)
                .then()
                .statusCode(404);
    }

    @Test
    void testEditUser() {
        // Primeiro, salva um usuário e obtém o seu ID
        int userId = given()
                .contentType(ContentType.URLENC)
                .formParam("nome", "Nome do Usuario")
                .formParam("login", "nome_usuario")
                .formParam("senha", "senha123")
                .when()
                .post("/user/save")
                .then()
                .statusCode(200)
                .extract()
                .path("id");

        // Em seguida, edita o usuário
        given()
            .header("Authorization", "Bearer " + jwtTokenUserAdmin)
                .contentType(ContentType.URLENC)
                .formParam("id", userId)
                .formParam("nome", "Maria da Silva")
                .formParam("senha", "newsenha123")
                .when()
                .put("/user/edit")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(userId))
                .body("nome", equalTo("Maria da Silva"))
                .body("login", equalTo("nome_usuario"));
    }

    @Test
    void testFindByLogin() {
        // Primeiro, salva um usuário com o login "nome_usuario"
        given()
                .contentType(ContentType.URLENC)
                .formParam("nome", "Nome do Usuario")
                .formParam("login", "nome_usuario")
                .formParam("senha", "senha123")
                .when()
                .post("/user/save")
                .then()
                .statusCode(200);

        // Em seguida, busca o usuário pelo login
        given()
            // .header("Authorization", "Bearer " + jwtTokenUserAdmin)
                .contentType(ContentType.URLENC)
                .formParam("login", "nome_usuario")
                .formParam("senha", "senha123")
        .when()
                .post("/user/findbylogin")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("nome", equalTo("Nome do Usuario"))
                .body("login", equalTo("nome_usuario"));
    }

}
