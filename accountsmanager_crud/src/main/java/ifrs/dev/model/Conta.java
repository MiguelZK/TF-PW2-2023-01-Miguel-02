package ifrs.dev.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Conta extends PanacheEntity {

    private Long idConta;
    private String banco;
    private Long numeroConta;
    private Long agencia;
    private Double saldo;
    private Boolean ativa;
    private Date dataAbertura;
    private Date dataEncerramento;
    private List<Transacao> transacoes;
    
    public Conta() {
    }

    public Conta(String banco, Long numeroConta, Long agencia, Double saldo, Boolean ativa, Date dataAbertura,
            Date dataEncerramento, List<Transacao> transacoes) {
        this.banco = banco;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.saldo = saldo;
        this.ativa = ativa;
        this.dataAbertura = dataAbertura;
        this.dataEncerramento = dataEncerramento;
        this.transacoes = transacoes;
    }

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Long getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Long getAgencia() {
        return agencia;
    }

    public void setAgencia(Long agencia) {
        this.agencia = agencia;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(Date dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idConta == null) ? 0 : idConta.hashCode());
        result = prime * result + ((banco == null) ? 0 : banco.hashCode());
        result = prime * result + ((numeroConta == null) ? 0 : numeroConta.hashCode());
        result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
        result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
        result = prime * result + ((ativa == null) ? 0 : ativa.hashCode());
        result = prime * result + ((dataAbertura == null) ? 0 : dataAbertura.hashCode());
        result = prime * result + ((dataEncerramento == null) ? 0 : dataEncerramento.hashCode());
        result = prime * result + ((transacoes == null) ? 0 : transacoes.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Conta other = (Conta) obj;
        if (idConta == null) {
            if (other.idConta != null)
                return false;
        } else if (!idConta.equals(other.idConta))
            return false;
        if (banco == null) {
            if (other.banco != null)
                return false;
        } else if (!banco.equals(other.banco))
            return false;
        if (numeroConta == null) {
            if (other.numeroConta != null)
                return false;
        } else if (!numeroConta.equals(other.numeroConta))
            return false;
        if (agencia == null) {
            if (other.agencia != null)
                return false;
        } else if (!agencia.equals(other.agencia))
            return false;
        if (saldo == null) {
            if (other.saldo != null)
                return false;
        } else if (!saldo.equals(other.saldo))
            return false;
        if (ativa == null) {
            if (other.ativa != null)
                return false;
        } else if (!ativa.equals(other.ativa))
            return false;
        if (dataAbertura == null) {
            if (other.dataAbertura != null)
                return false;
        } else if (!dataAbertura.equals(other.dataAbertura))
            return false;
        if (dataEncerramento == null) {
            if (other.dataEncerramento != null)
                return false;
        } else if (!dataEncerramento.equals(other.dataEncerramento))
            return false;
        if (transacoes == null) {
            if (other.transacoes != null)
                return false;
        } else if (!transacoes.equals(other.transacoes))
            return false;
        return true;
    }

    
}
