package ifrs.dev.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User extends PanacheEntity {
    
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Login é obrigatório")
    @Column(unique = true)
    private String login;

    @NotBlank(message = "Senha é obrigatória")
    @JsonIgnore
    private String senha;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private List<Conta> contas;
    
/*     @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private HashSet<String> roles;

    public HashSet<String> getRoles() {
        return roles;
    }

    public void setRoles(HashSet<String> roles) {
        this.roles = roles;
    } */

    public User() {
        this.contas = new LinkedList<>();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

     public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return this.login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return this.senha;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public void addConta(Conta conta) {
        this.contas.add(conta);
    }
}
