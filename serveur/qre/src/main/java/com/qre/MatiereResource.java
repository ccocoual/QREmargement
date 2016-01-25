package com.qre;

/**
 * Created by stagiaire on 20/01/2016.
 */

import com.google.gson.Gson;
import database.BDD_Authentication;
import database.BDD_Groupe;
import database.BDD_Matiere;
import database.Database;
import model.Authentication;
import model.Groupe;
import model.Matiere;
import utils.Log;
import utils.ResponseObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;


@Path("/{token}/matieres")
public class MatiereResource {

    @GET
    @Produces("application/json")
    public Response getAllMatieres(@PathParam("token") String token){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            int professeur_id = authentication.getProfesseur_id();

            String json = new Gson().toJson(BDD_Matiere.getAll());
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (SQLException e) {
            Log.getInstance().err(e.getMessage());
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getMatiereById(@PathParam("token") String token,
                                   @PathParam("id") int id){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            int professeur_id = authentication.getProfesseur_id();

            Matiere matiere = BDD_Matiere.getById(id);
            if (matiere == null){
                String json = new ResponseObject("error", "NEXTURL", "Etudiant with id:"+id+" not found").toJSON();
                return Response.status(Response.Status.NOT_FOUND).entity(json).build();
            }
            String json = new Gson().toJson(matiere);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (SQLException e) {
            Log.getInstance().err(e.getMessage());
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response insertMatiere(@PathParam("token") String token,
                                 String data){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            int professeur_id = authentication.getProfesseur_id();

            Matiere matiere = new Gson().fromJson(data, Matiere.class);

            if(BDD_Matiere.insert(matiere)){
                String json = new Gson().toJson(matiere);
                return Response.status(Response.Status.OK).entity(json).build();
            } else {
                String json = new ResponseObject("error", "nextURL",  "Groupe inserting has failed").toJSON();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
            }
        } catch (Exception e) {
            Log.getInstance().err(e.getMessage());
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateMatiere( @PathParam("token") String token,
                                   @PathParam("id") int id,
                                 String data){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            int professeur_id = authentication.getProfesseur_id();

            Matiere matiere = new Gson().fromJson(data, Matiere.class);

            if(BDD_Matiere.update(matiere)){
                String json = new Gson().toJson(matiere);
                return Response.status(Response.Status.OK).entity(json).build();
            } else {
                String json = new ResponseObject("error", "nextURL",  "Groupe updating has failed").toJSON();
                return Response.status(Response.Status.NOT_FOUND).entity(json).build();
            }
        } catch (Exception e) {
            Log.getInstance().err(e.getMessage());
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteMatiere(@PathParam("token") String token,
                                 @PathParam("id") int id){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            int professeur_id = authentication.getProfesseur_id();

            if(BDD_Matiere.delete(id)){
                String json = new ResponseObject("success", "nextURL",  "Groupe has been deleted with succes").toJSON();
                return Response.status(Response.Status.OK).entity(json).build();
            } else {
                String json = new ResponseObject("error", "nextURL",  "Groupe deleting has failed").toJSON();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
            }
        } catch (Exception e) {
            Log.getInstance().err(e.getMessage());
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

}
