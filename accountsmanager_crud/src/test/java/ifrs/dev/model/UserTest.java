package ifrs.dev.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testSetAndGetNome() {
        // Arrange
        User user = new User();
        String nome = "Nome do Usuário";

        // Act
        user.setNome(nome);
        String retrievedNome = user.getNome();

        // Assert
        assertEquals(nome, retrievedNome);
    }

    @Test
    public void testSetAndGetLogin() {
        // Arrange
        User user = new User();
        String login = "nome_do_usuario";

        // Act
        user.setLogin(login);
        String retrievedLogin = user.getLogin();

        // Assert
        assertEquals(login, retrievedLogin);
    }

    @Test
    public void testSetAndGetSenha() {
        // Arrange
        User user = new User();
        String senha = "senha123";

        // Act
        user.setSenha(senha);
        String retrievedSenha = user.getSenha();

        // Assert
        assertEquals(senha, retrievedSenha);
    }

    @Test
    public void testAddConta() {
        // Arrange
        User user = new User();
        Conta conta = new Conta();

        // Act
        user.addConta(conta);
        List<Conta> contas = user.getContas();

        // Assert
        assertTrue(contas.contains(conta));
    }
}
