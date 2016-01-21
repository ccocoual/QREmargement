package com.qre;

/**
 * Created by stagiaire on 20/01/2016.
 */

import com.google.gson.Gson;
import database.BDD_Matiere;
import database.Database;
import model.Matiere;
import utils.ResponseObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;


@Path("/admin/matieres")
public class MatiereResource {

    @GET
    @Produces("application/json")
    public Response getAllMatieres(){
        try {
            Connection connection = Database.getDbCon().conn;
            String json = new Gson().toJson(BDD_Matiere.getAll(connection));
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (SQLException e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getMatiereById(@PathParam("id") int id){
        try {
            Connection connection = Database.getDbCon().conn;
            Matiere matiere = BDD_Matiere.getById(connection, id);
            if (matiere == null){
                String json = new ResponseObject("error", "NEXTURL", "Etudiant with id:"+id+" not found").toJSON();
                return Response.status(Response.Status.NOT_FOUND).entity(json).build();
            }
            String json = new Gson().toJson(matiere);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (SQLException e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

}
