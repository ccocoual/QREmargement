package com.qre;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.*;
import model.Emargement;
import model.Etudiant;
import model.Groupe;
import model.Signature;
import utils.Logger;
import utils.ResponseObject;

import javax.ws.rs.*;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;


@Path("/qrcode")
public class QrcodeResource {

    @POST
    @Path("/authentication_etudiant")
    @Consumes("application/json")
    public Response AuthEtudiant(String data){
        Etudiant etudiant;
        try {
            JsonObject jobj = new Gson().fromJson(data, JsonObject.class);
            String login = jobj.get("email").toString();
            if(login == null || login.isEmpty()){
                String json = new ResponseObject("error", "NEXTURL", "Login not acceptable").toJSON();
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(json).build();
            }

            String password = jobj.get("num_etu").toString();
            if(password == null || password.isEmpty()){
                String json = new ResponseObject("error", "NEXTURL", "Password not acceptable").toJSON();
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(json).build();
            }

            etudiant = BDD_Etudiant.checkAuth(login, password);
            if (etudiant == null){
                String json = new ResponseObject("error", "NEXTURL", "Login and password don't match").toJSON();
                return Response.status(Response.Status.NOT_FOUND).entity(json).build();
            }

        } catch (SQLException e) {
            Logger.getInstance().err(e.getMessage());
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        } catch (ParseException e) {
            Logger.getInstance().err(e.getMessage());
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }

        String json = new Gson().toJson(etudiant);
        return Response.status(Response.Status.OK).entity(json).build();
    }

    @POST
    public Response signature(String data){
        try {
            JsonObject jobj = new Gson().fromJson(data, JsonObject.class);
            int emargement_id = jobj.get("emargement_id").getAsInt();
            int etudiant_id = jobj.get("etudiant_id").getAsInt();
            boolean presence = true;

            if(jobj.get("presence") != null){
                presence = jobj.get("presence").getAsBoolean();
            }

            Signature signature = BDD_Signature.getById(emargement_id, etudiant_id);

            signature.setSignee(presence);
            Calendar cal = Calendar.getInstance(); // creates calendar
            cal.setTime(new java.util.Date()); // sets calendar time/date
            signature.setDate(new Timestamp(cal.getTime().getTime()));


            String json = new Gson().toJson(BDD_Signature.update(signature));
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (SQLException e) {
            Logger.getInstance().err(e.getMessage());
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }


}
