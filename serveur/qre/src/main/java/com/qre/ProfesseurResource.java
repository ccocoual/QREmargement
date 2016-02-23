package com.qre;

/**
 * Created by stagiaire on 20/01/2016.
 */

import com.google.gson.Gson;
import database.BDD_Authentication;
import database.BDD_Professeur;
import model.Authentication;
import model.Professeur;
import utils.Logger;
import utils.ResponseObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;


@Path("/{token}/professeur")
public class ProfesseurResource extends OptionsResource {

    @GET
    @Produces("application/json")
    public Response getProfesseur(@PathParam("token") String token){
        try {
            Authentication authentication = BDD_Authentication.isValidTokenAndUpdateIfTrue(token);
            if(authentication == null){
                String json = new ResponseObject("error", "nextURL", "Token invalid or expired").toJSON();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
            }

            String json = new Gson().toJson(BDD_Professeur.getById(authentication.getProfesseur_id()));
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (SQLException e) {
            Logger.getInstance().err(e.getMessage());
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

}
