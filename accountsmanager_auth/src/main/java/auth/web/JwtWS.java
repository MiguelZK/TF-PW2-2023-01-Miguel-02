package auth.web;

import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/auth")
public class JwtWS {

    @GET
    @Path("/jwt/{email}") // ver um identificador para o usuário - que pode ser hash
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String generate(@PathParam("email") String email) {
        return Jwt.issuer("stringParaValidarJWT")
            .upn(email)
            .groups(new HashSet<>(Arrays.asList("User", "Admin"))) // Não será usado neste projeto - mas dá pra deixar assim por enquanto (é interessante pelo menos 1 role)
            .claim(Claims.full_name, "Miguel Cara Legal")
            .claim("Outro valor qualquer", "Valor qualquer")
            .sign();
    }

}
