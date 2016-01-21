package com.qre;

/**
 * Created by stagiaire on 20/01/2016.
 */

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.BDD_Etudiant;
import database.BDD_Groupe;
import database.Database;
import model.Etudiant;
import model.Groupe;
import utils.ResponseObject;

import javax.ws.rs.*;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;


@Path("/admin/etudiants")
public class EtudiantResource {

    final String cookie_name = "QREmargement";

    @GET
    @Produces("application/json")
    public Response getAllEtudiants(){
        try {
            Connection connection = Database.getDbCon().conn;
            String json = new Gson().toJson(BDD_Etudiant.getAll(connection));
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (SQLException e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getEtudiantById(@PathParam("id") int id){
        try {
            Connection connection = Database.getDbCon().conn;
            Etudiant etudiant = BDD_Etudiant.getById(connection, id);
            if (etudiant == null){
                String json = new ResponseObject("error", "NEXTURL", "Etudiant with id:"+id+" not found").toJSON();
                return Response.status(Response.Status.NOT_FOUND).entity(json).build();
            }
            String json = new Gson().toJson(etudiant);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (SQLException e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @GET
    @Path("/num_etu/{num_etu}")
    @Produces("application/json")
    public Response getEtudiantsByNumEtu(@PathParam("num_etu") String num_etu){
        try {
            Connection connection = Database.getDbCon().conn;
            Etudiant etudiant = BDD_Etudiant.getByNumEtu(connection, num_etu);
            if (etudiant == null){
                String json = new ResponseObject("error", "NEXTURL", "Etudiant with num_etu:"+num_etu+" not found").toJSON();
                return Response.status(Response.Status.NOT_FOUND).entity(json).build();
            }
            String json = new Gson().toJson(etudiant);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (SQLException e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }



    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response insertEtudiant(String data){
        try {
            Etudiant etudiant = new Gson().fromJson(data, Etudiant.class);
            Connection connection = Database.getDbCon().conn;
            if(BDD_Etudiant.insert(connection, etudiant)){
                String json = new Gson().toJson(etudiant);
                return Response.status(Response.Status.OK).entity(json).build();
            } else {
                String json = new ResponseObject("error", "nextURL",  "Etudiant inserting has failed").toJSON();
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
    public Response updateEtudiant(@PathParam("id") int id, String data){
        try {
            Etudiant etudiant = new Gson().fromJson(data, Etudiant.class);
            Connection connection = Database.getDbCon().conn;
            if(BDD_Etudiant.update(connection, etudiant)){
                String json = new Gson().toJson(etudiant);
                return Response.status(Response.Status.OK).entity(json).build();
            } else {
                String json = new ResponseObject("error", "nextURL",  "Etudiant updating has failed").toJSON();
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
    public Response deleteEtudiant(@PathParam("id") int id){
        try {
            Connection connection = Database.getDbCon().conn;
            if(BDD_Etudiant.delete(connection, id)){
                String json = new ResponseObject("success", "nextURL",  "Etudiant has been deleted with succes").toJSON();
                return Response.status(Response.Status.OK).entity(json).build();
            } else {
                String json = new ResponseObject("error", "nextURL",  "Etudiant deleting has failed").toJSON();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
            }
        } catch (Exception e) {
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

}
