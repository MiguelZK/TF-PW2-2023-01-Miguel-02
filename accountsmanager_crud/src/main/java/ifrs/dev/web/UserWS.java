package ifrs.dev.web;

import java.util.List;

import ifrs.dev.model.User;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.annotation.security.PermitAll;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/user")
@Transactional
public class UserWS {

    @POST
    @Path("/save")
    @PermitAll
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public User save(@FormParam("nome") String nome, /* @FormParam("login") String login, */
            @FormParam("senha") String senha) {
        try {
            User user = new User();
            user.setNome(nome);
            // user.setLogin(login);
            user.setSenha(senha);
            // 2 - O método do Panache `persist` possibilita persistir um objeto.
            user.persist();
            return user;
        } catch (RuntimeException e) {
            if (nome == null || nome.isEmpty() || /* login == null || login.isEmpty() || */ senha == null
                    || senha.isEmpty()) {
                System.out.println("Nome não pode ser nulo ou vazio");
            }/*  else if (findByLogin(login) != null) {
                System.out.println("Login já existe");
            } */
            return null;
        } catch (Exception e) {
            System.out.println("Erro ao salvar");
            return null;
        }
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> list() {
        // 3 - O método `listAll` recupera todos os objetos da classe User.
        return User.listAll();
    }

    @GET
    @Path("/list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User list(@PathParam("id") Long id) {
        // 4 - O método do Panache `findById` recupera um objeto da classe User.
        return PanacheEntityBase.findById(id);
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User delete(@PathParam("id") Long id) {
        User u = PanacheEntityBase.findById(id);
        u.delete();
        return u;
    }

    @PUT
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public User edit(@FormParam("id") Long id, @FormParam("nome") String nome, @FormParam("senha") String senha) {
        User u = PanacheEntityBase.findById(id);
        u.setNome(nome);
        u.setSenha(senha);
        return u;
    }

/*     @POST
    @Path("/findbylogin")
    @Produces(MediaType.APPLICATION_JSON)
    public User findByLogin(@FormParam("login") String login) {
        // 4 - O método do Panache `findById` recupera um objeto da classe User.
        return PanacheEntityBase.find("login", login).firstResult();
    } */

}