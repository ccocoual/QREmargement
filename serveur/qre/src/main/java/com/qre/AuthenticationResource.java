package com.qre;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.BDD_Authentication;
import database.BDD_Professeur;
import model.Professeur;
import utils.ResponseObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.security.SecureRandom;

@Path("/authentication")
public class AuthenticationResource {

    @POST
    @Consumes("application/json")
    public Response authenticateUser(String data) {

        System.out.println("json:"+data.toString());
        JsonObject jobj = new Gson().fromJson(data, JsonObject.class);

        System.out.println("json:"+jobj.toString());
        String login = jobj.get("login").toString();
        String password = jobj.get("password").toString();

        System.out.println("login:"+login);
        System.out.println("password:"+password);
        try {

            // Authenticate the user using the credentials provided
            Professeur professeur = authenticate(login, password);

            // Issue a token
            String token = generateToken();


            if(BDD_Authentication.insertOrReplaceToken( token, professeur.getId()))
                return Response.ok(token).build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();

        } catch (Exception e) {String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.NOT_FOUND).entity(json).build();
        }
    }

    private Professeur authenticate(String login, String password) throws Exception {

        if(login == null || login.isEmpty() || password == null || password.isEmpty())
            throw new Exception("Login and Password are not acceptable");


        Professeur professeur = BDD_Professeur.checkAuth(login, password);

        if (professeur == null)
            throw new Exception("Login and password don't match");

        return professeur;
    }

    private String generateToken() {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }
}