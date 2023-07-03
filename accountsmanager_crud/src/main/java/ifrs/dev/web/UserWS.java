package ifrs.dev.web;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import ifrs.dev.model.User;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
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
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;

@Path("/user")
@Transactional
public class UserWS {

    @Inject
    JsonWebToken jwt;

    @POST
    @Path("/save")
    @PermitAll
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public User save(@FormParam("nome") String nome, @FormParam("login") String login,
            @FormParam("senha") String senha) {
        try {
            User user = new User();
            user.setNome(nome);
            user.setLogin(login);
            user.setSenha(senha);
            // 2 - O método do Panache `persist` possibilita persistir um objeto.
            user.persist();
            return user;
        } catch (RuntimeException e) {
            if (nome == null || nome.isEmpty() || login.isEmpty() || senha == null
                    || senha.isEmpty()) {
                throw new WebApplicationException("Nome n\u00E3o pode ser nulo ou vazio", 404);
            }  else if (findByLogin(login) != null) {
                throw new WebApplicationException("Login j\u00E1 existe", 404);
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar");
            throw new WebApplicationException(403);
        }
        return null;
    }

    @GET
    @Path("/list")
    @RolesAllowed({"Admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> list() {
        // jwt.claim(null) - Colocar verificação de que usuário existe e token não é desatualizado.
        // 3 - O método `listAll` recupera todos os objetos da classe User.
        return User.listAll();
    }

    @GET
    @Path("/list/{id}")
    @RolesAllowed({"Admin", "User"})
    @Produces(MediaType.APPLICATION_JSON)
    public User list(@PathParam("id") Long id) {
        // 4 - O método do Panache `findById` recupera um objeto da classe User.
        return User.findById(id);
    }

    @DELETE
    @Path("/delete/{id}")
    @RolesAllowed({"Admin", "User"})
    @Produces(MediaType.APPLICATION_JSON)
    public User delete(@PathParam("id") Long id) {
        User u = User.findById(id);
        u.delete();
        return u;
    }

    @PUT
    @Path("/edit")
    @RolesAllowed({"Admin"})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public User edit(@FormParam("id") Long id, @FormParam("nome") String nome, @FormParam("senha") String senha) {
        User u = User.findById(id);
        u.setNome(nome);
        u.setSenha(senha);
        return u;
    }

    @POST
    @Path("/findbylogin")
    @PermitAll
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public User findByLogin(@FormParam("login") String login, @FormParam("senha") String senha) {
        // 4 - O método do Panache `findById` recupera um objeto da classe User.
        return User.find("login = ?1 AND senha = ?2", login, senha).firstResult();
    }

    private User findByLogin(@FormParam("login") String login) {
        return User.find("login", login).firstResult();
    }

}