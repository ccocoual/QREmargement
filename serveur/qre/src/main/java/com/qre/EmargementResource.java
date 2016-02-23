package com.qre;

/**
 * Created by stagiaire on 20/01/2016.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import database.BDD_Authentication;
import database.BDD_Emargement;
import database.BDD_Etudiant;
import database.BDD_Signature;
import model.Authentication;
import model.Emargement;
import model.Signature;
import utils.Logger;
import utils.ResponseObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;


@Path("/{token}/emargements")
public class EmargementResource extends OptionsResource{

    @GET
    @Produces("application/json")
    public Response getAllEmargements(@PathParam("token") String token){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            int professeur_id = authentication.getProfesseur_id();

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            String json = gson.toJson(BDD_Emargement.getAll(professeur_id));
            return Response.status(Response.Status.OK).entity(json).build();

        } catch (SQLException e) {
            Logger.getInstance().err(e.getMessage());
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @GET
    @Path("/{emargement_id}")
    @Produces("application/json")
    public Response getEmargementById(@PathParam("token") String token,
                                      @PathParam("emargement_id") int emargement_id){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            int professeur_id = authentication.getProfesseur_id();

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            String json = gson.toJson(BDD_Emargement.getById(emargement_id, professeur_id));
            return Response.status(Response.Status.OK).entity(json).build();

        } catch (Exception e) {
            Logger.getInstance().err(e.getMessage());
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @GET
    @Path("/{emargement_id}/signatures")
    @Produces("application/json")
    public Response getSignaturesByEmargementById(@PathParam("token") String token,
                                                  @PathParam("emargement_id") int emargement_id){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            int professeur_id = authentication.getProfesseur_id();

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            String json = gson.toJson(BDD_Emargement.getSignatures(emargement_id, professeur_id));
            return Response.status(Response.Status.OK).entity(json).build();

        } catch (Exception e) {
            Logger.getInstance().err(e.getMessage());
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @PUT
    @Path("/{emargement_id}/signatures")
    @Produces("application/json")
    public Response updateSignatures(@PathParam("token") String token,
                                     @PathParam("emargement_id") int emargement_id,
                                     String data){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            int professeur_id = authentication.getProfesseur_id();

            // Leve une exception si acces non autorisé
            BDD_Emargement.getById(emargement_id, professeur_id);

            JsonObject jsonObject =  new JsonParser().parse(data).getAsJsonObject();
            int etudiant_id = jsonObject.get("etudiant_id").getAsInt();
            boolean signee = jsonObject.get("signee").getAsBoolean();

            Signature signature = BDD_Signature.getById(emargement_id, etudiant_id);

            Calendar cal = Calendar.getInstance(); // creates calendar
            cal.setTime(new java.util.Date()); // sets calendar time/date

            signature.setDate(new Timestamp(cal.getTime().getTime()));
            signature.setSignee(signee);

            // Leve une exception si acces non autorisé
            BDD_Signature.update(signature);

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            String json = gson.toJson(signature);
            return Response.status(Response.Status.OK).entity(json).build();

        } catch (Exception e) {
            Logger.getInstance().err(e.getMessage());
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response insertEmargement(@PathParam("token") String token,
                                   String data){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            int professeur_id = authentication.getProfesseur_id();

            Emargement emargement = new Gson().fromJson(data, Emargement.class);

            if(BDD_Emargement.insert(emargement, professeur_id)){
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                String json = gson.toJson(emargement);
                return Response.status(Response.Status.OK).entity(json).build();
            } else {
                String json = new ResponseObject("error", "nextURL",  "Etudiant inserting has failed").toJSON();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
            }
        } catch (Exception e) {
            Logger.getInstance().err(e.getMessage());
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateEtudiant(@PathParam("token") String token,
                                   @PathParam("id") int id,
                                   String data){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            int professeur_id = authentication.getProfesseur_id();

            Emargement emargement = new Gson().fromJson(data, Emargement.class);

            if(BDD_Emargement.update(emargement, professeur_id)){
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                String json = gson.toJson(emargement);
                return Response.status(Response.Status.OK).entity(json).build();
            } else {
                String json = new ResponseObject("error", "nextURL",  "Etudiant updating has failed").toJSON();
                return Response.status(Response.Status.NOT_FOUND).entity(json).build();
            }
        } catch (Exception e) {
            Logger.getInstance().err(e.getMessage());
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteEtudiant(@PathParam("token") String token,
                                   @PathParam("id") int id){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            int professeur_id = authentication.getProfesseur_id();

            if(BDD_Etudiant.delete(id)){
                String json = new ResponseObject("success", "nextURL",  "Etudiant has been deleted with succes").toJSON();
                return Response.status(Response.Status.OK).entity(json).build();
            } else {
                String json = new ResponseObject("error", "nextURL",  "Etudiant deleting has failed").toJSON();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
            }
        } catch (Exception e) {
            Logger.getInstance().err(e.getMessage());
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

}
