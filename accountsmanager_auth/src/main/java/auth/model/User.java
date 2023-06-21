package auth.model;

public class User {
    private Long id;
    private String nome;
    // private String login;
    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

/*     public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return this.login;
    } */

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return this.senha;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", nome=" + nome + ", /* login=" + login + ",  */senha=" + senha + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        /* result = prime * result + ((login == null) ? 0 : login.hashCode()); */
        result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
/*        if (login == null) {
            if (other.login != null)
                return false;
         } else if (!login.equals(other.login))
            return false; */
        if (senha == null) {
            if (other.senha != null)
                return false;
        } else if (!senha.equals(other.senha))
            return false;
        return true;
    }

    
}
