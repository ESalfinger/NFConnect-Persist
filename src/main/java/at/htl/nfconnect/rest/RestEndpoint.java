package at.htl.nfconnect.rest;

import at.htl.nfconnect.business.EndpointFacade;
import at.htl.nfconnect.entities.Account;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("endpoint")
public class RestEndpoint {
    @Inject
    EndpointFacade facade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getAccounts() {
        return this.facade.getAccounts();
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logIn(JsonObject accountData) {
        return this.facade.logIn(accountData);
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(JsonObject accountData) {
        return this.facade.register(accountData);
    }

    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(JsonObject accountData) {
        return Response.ok().build();
    }
}
