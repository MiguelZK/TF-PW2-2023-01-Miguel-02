package ifrs.dev.web;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.smallrye.jwt.build.Jwt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.HashSet;

@QuarkusTest
public class UserWSTest {

    private String jwtTokenUserAdmin;
    private String jwtTokenGod;

    @BeforeEach
    public void setup() {
        // Aqui você pode obter o token JWT do seu serviço de autenticação
        // e armazená-lo na variável "jwtToken" para uso nos testes
        // jwtToken = "seu-token-jwt";
        jwtTokenUserAdmin = given()
                .contentType(ContentType.URLENC)
                .formParam("login", "nome_usuario")
                .formParam("senha", "senha123")
            .when()
                .post("/login")
            .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
    }

    @Test
    public void testSaveUser() {
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
    public void testSaveUserInvalidInput() {
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
    public void testSaveUserExistingLogin() {
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
                .formParam("nome", "Jane Smith")
                .formParam("login", "nome_usuario")
                .formParam("senha", "senha123123")
                .when()
                .post("/user/save")
                .then()
                .statusCode(500);
    }

    @Test
    public void testListUsers() {
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
    public void testGetUserById() {
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
        public void testDeleteUser() {
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
    public void testEditUser() {
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
                .formParam("nome", "Jane Smith")
                .formParam("senha", "newsenha123")
                .when()
                .put("/user/edit")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(userId))
                .body("nome", equalTo("Jane Smith"))
                .body("login", equalTo("nome_usuario"));
    }

    @Test
    public void testFindByLogin() {
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
            .header("Authorization", "Bearer " + jwtTokenUserAdmin)
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
