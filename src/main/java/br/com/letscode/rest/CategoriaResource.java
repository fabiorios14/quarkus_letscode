package br.com.letscode.rest;

import br.com.letscode.models.Categoria;
import br.com.letscode.models.Client;
import br.com.letscode.services.CategoriaService;
import org.eclipse.microprofile.opentracing.Traced;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@RequestScoped
@Path("/categoria")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource {

    @Inject
    CategoriaService service;


    @PostConstruct
    @Transactional
    public void init() throws Exception {

        service.createCategoria(new Categoria(100, "Programador"));
        service.createCategoria(new Categoria(200, "Comerciante"));

    }

    @GET
    @Path("/list")
    public Response listCategorias() throws Exception  {
        return Response.status(Response.Status.OK).entity(service.listCategoria()).build();
    }

    @GET
    @Path("/{id}")
    public Response getCategoria(final @PathParam("id") long id) throws Exception  {
        return Response.status(Response.Status.OK).entity(service.getCategoria(id)).build();
    }

    @POST
    public Response createCategoria(@Valid Categoria categoria) throws Exception {
        return  Response.status(Response.Status.CREATED).entity(service.createCategoria(categoria)).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCategoria(final @PathParam("id") long id, @Valid Categoria categoria) throws Exception  {
        return Response.status(Response.Status.OK).entity(service.updateCategoria(id, categoria)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCategoria(final @PathParam("id") long id) throws Exception  {
        service.deleteCategoria(id);
        return Response.status(Response.Status.OK).build();
    }

}