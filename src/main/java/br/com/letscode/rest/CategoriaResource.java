package br.com.letscode.rest;

import br.com.letscode.models.Categoria;
import br.com.letscode.services.CategoriaService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@ApplicationScoped
@Path("/categoria")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource {

    @Inject
    CategoriaService service;

    @PostConstruct
    @Transactional
    public void init() throws Exception {

        System.out.println("Chamou");

        Categoria categoria1 = new Categoria();
        categoria1.setCodigo(100);
        categoria1.setNome("Programador");

        Categoria categoria2 = new Categoria();
        categoria2.setCodigo(200);
        categoria2.setNome("Comerciante");

        service.createCategoria(categoria1);
        service.createCategoria(categoria2);

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
