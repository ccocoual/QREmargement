package com.qre;

import com.google.gson.Gson;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import database.*;
import model.*;
import utils.ResponseObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


@Path("/admin/classes")
public class ClasseResource {

    @GET
    @Produces("application/json")
    public Response getAllClasses(){
        try {
            Connection connection = Database.getDbCon().conn;
            String json = new Gson().toJson(BDD_Classe.getAll(connection));
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (SQLException e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getClasseById(@PathParam("id") int id){
        try {
            Connection connection = Database.getDbCon().conn;
            Classe classe = BDD_Classe.getById(connection, id);
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
    public Response getGroupesByClasse(@PathParam("id") int id){
        try {
            Connection connection = Database.getDbCon().conn;
            ArrayList<Groupe> groupes = BDD_Groupe.getByClassId(connection, id);
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
    public Response getGroupeByClasse(@PathParam("id") int id,
                                      @PathParam("groupe_id") int groupe_id){
        try {
            Connection connection = Database.getDbCon().conn;
            Groupe groupe = BDD_Groupe.getById(connection, groupe_id);
            ArrayList<Groupe> groupes = BDD_Groupe.getByClassId(connection, id);
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
    public Response getEtudiantsByGroupeByClasse(@PathParam("id") int id,
                                                 @PathParam("groupe_id") int groupe_id){
        try {
            Connection connection = Database.getDbCon().conn;
            Groupe groupe = BDD_Groupe.getById(connection, groupe_id);
            ArrayList<Groupe> groupes = BDD_Groupe.getByClassId(connection, id);
            if(groupes.contains(groupe)){
                ArrayList<Etudiant> etudiants = BDD_Etudiant.getByGroupeId(connection, groupe_id);
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
    public Response getEtudiantsByGroupeByClasse(@PathParam("id") int id,
                                                 @PathParam("groupe_id") int groupe_id,
                                                 @PathParam("etudiant_id") int etudiant_id){
        try {
            Connection connection = Database.getDbCon().conn;
            ArrayList<Groupe> groupes = BDD_Groupe.getByClassId(connection, id);
            Groupe groupe = BDD_Groupe.getById(connection, groupe_id);
            if(groupes.contains(groupe)){
                ArrayList<Etudiant> etudiants = BDD_Etudiant.getByGroupeId(connection, groupe_id);
                Etudiant etudiant = BDD_Etudiant.getById(connection, etudiant_id);
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
    public Response getClasseByEmargementId(@PathParam("id") int id){
        try {
            Connection connection = Database.getDbCon().conn;
            ArrayList<Classe> classes = BDD_Classe.getByEmargementId(connection, id);
            String json = new Gson().toJson(classes);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (SQLException e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response insertClasse(String data){
        try {
            Classe classe = new Gson().fromJson(data, Classe.class);
            Connection connection = Database.getDbCon().conn;
            if(BDD_Classe.insert(connection, classe)){
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
    @Produces("application/json")
    public Response updateClasse(@PathParam("id") int id, String data){
        try {
            Classe classe = new Gson().fromJson(data, Classe.class);
            Connection connection = Database.getDbCon().conn;
            if(BDD_Classe.update(connection, classe)){
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
    @Produces("application/json")
    public Response deleteClasse(@PathParam("id") int id){
        try {
            Connection connection = Database.getDbCon().conn;
            if(BDD_Classe.delete(connection, id)){
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
