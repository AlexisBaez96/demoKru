package com.programacion.rest;

import com.programacion.db.Empvac;
import com.programacion.db.servicios.ServicioEmpvacImpl;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/empvacs")
@ApplicationScoped
public class RestEmpvac {
    @Inject
    ServicioEmpvacImpl servicio;

    @GET
    @Path("/{id}") @Produces(MediaType.APPLICATION_JSON)
    public Response findById (@PathParam("id") Integer id){
        return Response.ok(servicio.findById(id)).build();
    }

    @GET @Produces(MediaType.APPLICATION_JSON)
    public Response findAll (){

        return Response.ok(servicio.findAll()).build();
    }

    @POST@Consumes(MediaType.APPLICATION_JSON)
    public Response insertar(Empvac p){
        servicio.create(p);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE  @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") Integer id){
        servicio.delete(id);
        return Response.status(Response.Status.ACCEPTED).build();
    }

//    @PUT
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response update(@PathParam("id") Integer id, Empvac per){
//        servicio.update(id, per);
//        return Response.status(Response.Status.OK).build();
//    }
}
