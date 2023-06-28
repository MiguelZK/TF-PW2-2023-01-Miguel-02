package ifrs.dev.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class ContaTest {

    @Test
    public void testSetAndGetBanco() {
        // Arrange
        Conta conta = new Conta();
        String banco = "Banco X";

        // Act
        conta.setBanco(banco);
        String retrievedBanco = conta.getBanco();

        // Assert
        assertEquals(banco, retrievedBanco);
    }

    @Test
    public void testSetAndGetNumeroConta() {
        // Arrange
        Conta conta = new Conta();
        Long numeroConta = 123456789L;

        // Act
        conta.setNumeroConta(numeroConta);
        Long retrievedNumeroConta = conta.getNumeroConta();

        // Assert
        assertEquals(numeroConta, retrievedNumeroConta);
    }

    @Test
    public void testSetAndGetAgencia() {
        // Arrange
        Conta conta = new Conta();
        Long agencia = 9876L;

        // Act
        conta.setAgencia(agencia);
        Long retrievedAgencia = conta.getAgencia();

        // Assert
        assertEquals(agencia, retrievedAgencia);
    }

    @Test
    public void testSetAndGetSaldo() {
        // Arrange
        Conta conta = new Conta();
        Double saldo = 1000.0;

        // Act
        conta.setSaldo(saldo);
        Double retrievedSaldo = conta.getSaldo();

        // Assert
        assertEquals(saldo, retrievedSaldo);
    }

    @Test
    public void testSetAndGetAtiva() {
        // Arrange
        Conta conta = new Conta();
        Boolean ativa = true;

        // Act
        conta.setAtiva(ativa);
        Boolean retrievedAtiva = conta.getAtiva();

        // Assert
        assertEquals(ativa, retrievedAtiva);
    }

    @Test
    public void testSetAndGetDatas() {
        // Arrange
        Conta conta = new Conta();
        Date dataAbertura = new Date();
        Date dataEncerramento = new Date();

        // Act
        conta.setDataAbertura(dataAbertura);
        conta.setDataEncerramento(dataEncerramento);
        Date retrievedDataAbertura = conta.getDataAbertura();
        Date retrievedDataEncerramento = conta.getDataEncerramento();

        // Assert
        assertEquals(dataAbertura, retrievedDataAbertura);
        assertEquals(dataEncerramento, retrievedDataEncerramento);
    }
}
