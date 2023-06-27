package auth.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import auth.model.User;
// import io.quarkus.oidc.token.propagation.AccessToken;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

// @AccessToken
@RegisterRestClient(baseUri = "http://localhost:8081/user")
public interface UserClient {
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/findbylogin")
    public User findByLogin(@FormParam("login") String login);
}