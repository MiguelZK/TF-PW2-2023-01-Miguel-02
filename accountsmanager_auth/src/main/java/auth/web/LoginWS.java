package auth.web;

import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import auth.client.UserClient;
import auth.model.User;
import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/login")
public class LoginWS {

    @Inject
    @RestClient
    UserClient user;

    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String generate(@FormParam("login") String login, @FormParam("senha") String senha) {
        User meuUser = user.findByLogin(login);
        if (meuUser != null &&  (meuUser.getSenha().equals(senha))) {
                return Jwt.issuer("stringParaValidarJWT")
                        .upn(login)
                        .groups(new HashSet<>(Arrays.asList("User", "Admin"))) // Não será usado neste projeto - mas dá
                                                                               // pra deixar assim por enquanto (é
                                                                               // interessante pelo menos 1 role)
                        .claim(Claims.full_name, "Miguel Cara Legal")
                        .claim("Outro valor qualquer", "Valor qualquer")
                        .sign();
            
        }
        return "false";
    }

}
