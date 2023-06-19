package ifrs.dev.web;

import java.util.List;

import ifrs.dev.model.Conta;
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

@Path("/conta")
@Transactional
public class ContaWS {

    @POST
    @Path("/save")
    @PermitAll
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Conta save(@FormParam("banco") String banco, @FormParam("numeroConta") Long numeroConta, @FormParam("agencia") Long agencia) {
        Conta conta = new Conta();
        conta.setBanco(banco);
        conta.setNumeroConta(numeroConta);
        conta.setAgencia(agencia);
        // 2 - O método do Panache `persist` possibilita persistir um objeto.
        conta.persist();
        return conta;
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Conta> list() {
        // 3 - O método `listAll` recupera todos os objetos da classe Conta.
        return Conta.listAll();
    }

    @GET
    @Path("/list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Conta list(@PathParam("id") Long id) {
        // 4 - O método do Panache `findById` recupera um objeto da classe Conta.
        return Conta.findById(id);
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Conta delete(@PathParam("id") Long id) {
        Conta u = Conta.findById(id);
        u.delete();
        return u;
    }

    @PUT
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Conta edit(@FormParam("id") Long id, @FormParam("nome") String banco, @FormParam("senha") Long agencia, @FormParam("senha") Long numeroConta) {
        Conta u = Conta.findById(id);
        u.setBanco(banco);
        u.setAgencia(agencia);
        u.setNumeroConta(numeroConta);
        return u;
    }

    @POST
    @Path("/findbylogin")
    @Produces(MediaType.APPLICATION_JSON)
    public Conta findByLogin(@FormParam("login") String login) {
        // 4 - O método do Panache `findById` recupera um objeto da classe Conta.
        return Conta.find("login", login).firstResult();
    }

}