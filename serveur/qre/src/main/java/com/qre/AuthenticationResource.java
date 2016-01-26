package com.qre;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import database.BDD_Authentication;
import database.BDD_Professeur;
import model.Professeur;
import utils.Logger;
import utils.ResponseObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.security.SecureRandom;

@Path("/authentication")
public class AuthenticationResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateProfesseur(String data) {

        JsonObject jsonObject =  new JsonParser().parse(data).getAsJsonObject();

        String login = jsonObject.get("login").getAsString();
        String password = jsonObject.get("password").getAsString();

        try {

            // Authenticate the user using the credentials provided
            if(login == null || login.isEmpty() || password == null || password.isEmpty())
                throw new Exception("Login and Password are not acceptable");

            Professeur professeur = BDD_Professeur.checkAuth(login, password);

            if (professeur == null)
                throw new Exception("Login and password don't match");

            String token = new BigInteger(130, new SecureRandom()).toString(32);

            if(BDD_Authentication.insertOrReplaceToken(token, professeur.getId()))
                return Response.ok(token).build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();

        } catch (Exception e) {
            Logger.getInstance().info(e.getMessage());
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.NOT_FOUND).entity(json).build();
        }

    }

}