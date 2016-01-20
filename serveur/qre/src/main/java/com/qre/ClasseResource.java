package com.qre;

import com.google.gson.Gson;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import database.*;
import model.Classe;
import model.Emargement;
import model.Etudiant;
import model.Signature;
import utils.ResponseObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.net.ConnectException;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


@Path("/classe")
public class ClasseResource {

    @GET
    @Path("/all")
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

}
