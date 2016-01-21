package com.qre;

import com.google.gson.Gson;
import database.BDD_Authentication;
import database.BDD_Classe;
import database.BDD_Etudiant;
import database.BDD_Groupe;
import model.Authentication;
import model.Classe;
import model.Etudiant;
import model.Groupe;
import utils.ResponseObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;


@Path("/{token}/classes")
public class ClasseResource {


    @GET
    @Produces("application/json")
    public Response getAllClasses(@PathParam("token") String token){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            String json = new Gson().toJson(BDD_Classe.getAll());
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (SQLException e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getClasseById(@PathParam("token") String token,
                                  @PathParam("id") int id){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            Classe classe = BDD_Classe.getById(id);
            if (classe == null){
                String json = new ResponseObject("error", "NEXTURL", "Classe with id:"+id+" not found").toJSON();
                return Response.status(Response.Status.NOT_FOUND).entity(json).build();
            }
            String json = new Gson().toJson(classe);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (SQLException e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @GET
    @Path("/{id}/groupes")
    @Produces("application/json")
    public Response getGroupesByClasse(@PathParam("token") String token,
                                       @PathParam("id") int id){
        try {

            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            ArrayList<Groupe> groupes = BDD_Groupe.getByClassId(id);
            String json = new Gson().toJson(groupes);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (SQLException e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @GET
    @Path("/{id}/groupes/{groupe_id}")
    @Produces("application/json")
    public Response getGroupeByClasse(@PathParam("token") String token,
                                      @PathParam("id") int id,
                                      @PathParam("groupe_id") int groupe_id){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            Groupe groupe = BDD_Groupe.getById(groupe_id);
            ArrayList<Groupe> groupes = BDD_Groupe.getByClassId(id);
            if(groupes.contains(groupe)){
                String json = new Gson().toJson(groupe);
                return Response.status(Response.Status.OK).entity(json).build();
            } else {
                String json = new ResponseObject("error", "nextURL",  "Groupe with id:"+groupe_id+" in Classe with id:"+id+" not found").toJSON();
                return Response.status(Response.Status.NOT_FOUND).entity(json).build();
            }
        } catch (SQLException e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @GET
    @Path("/{id}/groupes/{groupe_id}/etudiants")
    @Produces("application/json")
    public Response getEtudiantsByGroupeByClasse(@PathParam("token") String token,
                                                 @PathParam("id") int id,
                                                 @PathParam("groupe_id") int groupe_id){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            Groupe groupe = BDD_Groupe.getById(groupe_id);
            ArrayList<Groupe> groupes = BDD_Groupe.getByClassId(id);
            if(groupes.contains(groupe)){
                ArrayList<Etudiant> etudiants = BDD_Etudiant.getByGroupeId(groupe_id);
                String json = new Gson().toJson(etudiants);
                return Response.status(Response.Status.OK).entity(json).build();
            } else {
                String json = new ResponseObject("error", "nextURL",  "Groupe with id:"+groupe_id+" in Classe with id:"+id+" not found").toJSON();
                return Response.status(Response.Status.NOT_FOUND).entity(json).build();
            }
        } catch (SQLException e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @GET
    @Path("/{id}/groupes/{groupe_id}/etudiants/{etudiant_id}")
    @Produces("application/json")
    public Response getEtudiantsByGroupeByClasse(@PathParam("token") String token,
                                                 @PathParam("id") int id,
                                                 @PathParam("groupe_id") int groupe_id,
                                                 @PathParam("etudiant_id") int etudiant_id){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            ArrayList<Groupe> groupes = BDD_Groupe.getByClassId(id);
            Groupe groupe = BDD_Groupe.getById(groupe_id);
            if(groupes.contains(groupe)){
                ArrayList<Etudiant> etudiants = BDD_Etudiant.getByGroupeId(groupe_id);
                Etudiant etudiant = BDD_Etudiant.getById(etudiant_id);
                if(etudiants.contains(etudiant)){
                    String json = new Gson().toJson(etudiant);
                    return Response.status(Response.Status.OK).entity(json).build();
                } else {
                    String json = new ResponseObject("error", "nextURL",  "Etudiant with id:"+etudiant_id+" in Groupe with id:"+groupe_id+" in Classe with id:"+id+" not found").toJSON();
                    return Response.status(Response.Status.NOT_FOUND).entity(json).build();
                }
            } else {
                String json = new ResponseObject("error", "nextURL",  "Groupe with id:"+groupe_id+" in Classe with id:"+id+" not found").toJSON();
                return Response.status(Response.Status.NOT_FOUND).entity(json).build();
            }
        } catch (SQLException e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @GET
    @Path("/byemargementid/{id}")
    @Produces("application/json")
    public Response getClasseByEmargementId(@PathParam("token") String token,
                                            @PathParam("id") int id){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            ArrayList<Classe> classes = BDD_Classe.getByEmargementId(id);
            String json = new Gson().toJson(classes);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (SQLException e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @POST
    @Consumes("application/json")
    public Response insertClasse(@PathParam("token") String token,
                                 String data){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            Classe classe = new Gson().fromJson(data, Classe.class);

            if(BDD_Classe.insert(classe)){
                String json = new Gson().toJson(classe);
                return Response.status(Response.Status.OK).entity(json).build();
            } else {
                String json = new ResponseObject("error", "nextURL",  "Classe inserting has failed").toJSON();
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
    public Response updateClasse(@PathParam("token") String token,
                                 @PathParam("id") int id,
                                 String data){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            Classe classe = new Gson().fromJson(data, Classe.class);

            if(BDD_Classe.update(classe)){
                String json = new Gson().toJson(classe);
                return Response.status(Response.Status.OK).entity(json).build();
            } else {
                String json = new ResponseObject("error", "nextURL",  "Classe updating has failed").toJSON();
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
    public Response deleteClasse(@PathParam("token") String token,
                                 @PathParam("id") int id){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            if(BDD_Classe.delete(id)){
                String json = new ResponseObject("success", "nextURL",  "Classe has been deleted with succes").toJSON();
                return Response.status(Response.Status.OK).entity(json).build();
            } else {
                String json = new ResponseObject("error", "nextURL",  "Classe deleting has failed").toJSON();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
            }
        } catch (Exception e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

}
