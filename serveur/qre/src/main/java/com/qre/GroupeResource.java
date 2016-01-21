package com.qre;

/**
 * Created by stagiaire on 20/01/2016.
 */

import com.google.gson.Gson;
import database.BDD_Authentication;
import database.BDD_Etudiant;
import database.BDD_Groupe;
import model.Authentication;
import model.Etudiant;
import model.Groupe;
import utils.ResponseObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;


@Path("/{token}/groupes")
public class GroupeResource {

    @GET
    @Produces("application/json")
    public Response getAllGroupes(@PathParam("token") String token){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            String json = new Gson().toJson(BDD_Groupe.getAll());
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (SQLException e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getGroupeById(@PathParam("token") String token,
                                  @PathParam("id") int id){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            Groupe groupe = BDD_Groupe.getById(id);
            if (groupe == null){
                String json = new ResponseObject("error", "NEXTURL", "Classe with id:"+id+" not found").toJSON();
                return Response.status(Response.Status.NOT_FOUND).entity(json).build();
            }
            String json = new Gson().toJson(groupe);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (SQLException e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @GET
    @Path("/{id}/etudiants")
    @Produces("application/json")
    public Response getEtudiantsByGroupe(@PathParam("token") String token,
                                         @PathParam("id") int id){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            ArrayList<Etudiant> etudiants = BDD_Etudiant.getByGroupeId(id);
            String json = new Gson().toJson(etudiants);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (SQLException e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response insertGroupe(@PathParam("token") String token,
                                 String data){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            Groupe groupe = new Gson().fromJson(data, Groupe.class);

            if(BDD_Groupe.insert(groupe)){
                String json = new Gson().toJson(groupe);
                return Response.status(Response.Status.OK).entity(json).build();
            } else {
                String json = new ResponseObject("error", "nextURL",  "Groupe inserting has failed").toJSON();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
            }
        } catch (Exception e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateGroupe(@PathParam("token") String token,
                                 @PathParam("id") int id,
                                 String data){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            Groupe groupe = new Gson().fromJson(data, Groupe.class);

            if(BDD_Groupe.update(groupe)){
                String json = new Gson().toJson(groupe);
                return Response.status(Response.Status.OK).entity(json).build();
            } else {
                String json = new ResponseObject("error", "nextURL",  "Groupe updating has failed").toJSON();
                return Response.status(Response.Status.NOT_FOUND).entity(json).build();
            }
        } catch (Exception e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteGroupe(@PathParam("token") String token,
                                 @PathParam("id") int id){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            if(BDD_Groupe.delete(id)){
                String json = new ResponseObject("success", "nextURL",  "Groupe has been deleted with succes").toJSON();
                return Response.status(Response.Status.OK).entity(json).build();
            } else {
                String json = new ResponseObject("error", "nextURL",  "Groupe deleting has failed").toJSON();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
            }
        } catch (Exception e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

}
