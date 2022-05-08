package com.programacion.rest;

import com.programacion.db.Empleado;
import com.programacion.db.servicios.ServicioEmpleadoImpl;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/empleados")
@ApplicationScoped
public class RestEmpleado {
    @Inject
    ServicioEmpleadoImpl servicio;

    @GET
    @Path("/{id}") @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(
            responseCode = "200",
            description = "empleado encontrada exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Empleado findById (@PathParam("id") Integer id){
        Empleado p = servicio.findById(id);
        if (p==null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return p;
    }

    @GET @Produces(MediaType.APPLICATION_JSON)
    public Response findAll (){
        return Response.ok(servicio.findAll()).build();
    }

    @POST@Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "insertar",
            summary = "crear un nuevo empleado",
            description = "Creando nuevo empleado"
    )
    @APIResponse(
            responseCode = "201",
            description = "empleado creado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response insertar(Empleado p){
        servicio.create(p);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE  @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @APIResponse(
            responseCode = "204",
            description = "empleado eliminado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response eliminar(@PathParam("id") Integer id){
        servicio.delete(id);
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @APIResponse(
            responseCode = "201",
            description = "empleado modificado exitosamente",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response update(@PathParam("id") Integer id, Empleado per){
        servicio.update(id, per);
        return Response.status(Response.Status.OK).build();
    }
}
