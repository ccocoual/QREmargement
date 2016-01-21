package com.qre;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.BDD_Authentication;
import database.BDD_Professeur;
import database.Database;
import model.Etudiant;
import model.Professeur;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;

@Path("/authentication")
public class AuthenticationResource {

    @POST
    @Consumes("application/json")
    public Response authenticateUser(String data) {

        JsonObject jobj = new Gson().fromJson(data, JsonObject.class);
        String login = jobj.get("login").toString();
        String password = jobj.get("password").toString();

        try {

            // Authenticate the user using the credentials provided
            Professeur professeur = authenticate(login, password);

            // Issue a token
            String token = generateToken();

            Connection connection = Database.getDbCon().conn;
            BDD_Authentication.insertOrUpdateToken(connection, token, professeur.getId());

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private Professeur authenticate(String login, String password) throws Exception {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
        if(login == null || login.isEmpty()){
            throw new Exception("Login is not acceptable");
        }
        if(password == null || password.isEmpty()){
            throw new Exception("Password is not acceptable");
        }

        Connection connection = Database.getDbCon().conn;
        Professeur professeur = BDD_Professeur.checkAuth(connection, login, password);
        if (professeur == null){
            throw new Exception("Login and password don't match");
        }

        return professeur;
    }

    private String generateToken() {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
         return new BigInteger(130, new SecureRandom()).toString(32);
    }
}