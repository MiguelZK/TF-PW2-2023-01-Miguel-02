package auth.web;

import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import auth.client.UserClient;
import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/auth")
public class JwtWS {

    @Inject
    @RestClient
    UserClient user;

    @GET
    @Path("/jwt/{login}") // ver um identificador para o usuário - que pode ser hash
    @PermitAll
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String generate(@PathParam("login") String login) {
        return Jwt.issuer("stringParaValidarJWT")
            .upn(login)
            .groups(new HashSet<>(Arrays.asList("User", "Admin"))) // Não será usado neste projeto - mas dá pra deixar assim por enquanto (é interessante pelo menos 1 role)
            .claim(Claims.full_name, "Miguel Cara Legal")
            .claim("Outro valor qualquer", "Valor qualquer")
            .sign();
    }

}
