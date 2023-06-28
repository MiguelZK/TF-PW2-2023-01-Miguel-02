package ifrs.dev.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import ifrs.dev.model.User;
import jakarta.annotation.security.PermitAll;
// import io.quarkus.oidc.token.propagation.AccessToken;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

// @AccessToken
@RegisterRestClient(baseUri = "http://localhost:8080/login")
public interface TokenClient {
    
    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String generate(@FormParam("login") String login, @FormParam("senha") String senha);
}