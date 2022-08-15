package br.com.letscode.rest;

import br.com.letscode.form.ClientForm;
import br.com.letscode.services.ClientService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/client")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

    @Inject
    ClientService service;

    @GET
    @Path("/list")
    public Response listClients() throws Exception  {
        return Response.status(Response.Status.OK).entity(service.listClients()).build();
    }

    @GET
    @Path("/{id}")
    public Response getClient(final @PathParam("id") long id) throws Exception  {
        return Response.status(Response.Status.OK).entity(service.getClient(id)).build();
    }

    @POST
    public Response createClient(@Valid ClientForm clientForm) throws Exception {
        return  Response.status(Response.Status.CREATED).entity(service.createClient(clientForm)).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateClient(final @PathParam("id") long id, @Valid ClientForm clientForm) throws Exception  {
        return Response.status(Response.Status.OK).entity(service.updateClient(id, clientForm)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteClient(final @PathParam("id") long id) throws Exception  {
        service.deleteClient(id);
        return Response.status(Response.Status.OK).build();
    }

}