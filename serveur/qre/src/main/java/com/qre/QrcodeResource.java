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

    final String cookie_name = "QREmargement";

    @GET
    @Path("/scan/{token_generated}")
    public Response scanQRE(@CookieParam(value = cookie_name) String cookie_num_etu, @PathParam("token_generated") String token_generated){
        if(cookie_num_etu != null && !cookie_num_etu.isEmpty()){
            try {
                Etudiant etudiant = BDD_Etudiant.getByNumEtu(cookie_num_etu);
                if(etudiant == null){
                    String json = new ResponseObject("error", "NEXTURL", "Etudiant with num_etu:"+cookie_num_etu+" not found").toJSON();
                    return Response.status(Response.Status.NOT_FOUND).entity(json).build();
                }

                Emargement emargement = BDD_Emargement.getByURL(token_generated);
                if(emargement == null){
                    String json = new ResponseObject("error", "NEXTURL", "Emargement with url_generated:"+token_generated+" not found").toJSON();
                    return Response.status(Response.Status.NOT_FOUND).entity(json).build();
                }

                Groupe groupe = BDD_Groupe.getById(etudiant.getGroupe_id());
                if(groupe == null){
                    String json = new ResponseObject("error", "NEXTURL", "Groupe of etudiant  with num_etu:"+cookie_num_etu+" not found").toJSON();
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
                }

                if(!BDD_Emargement.containsGroupe(emargement.getId(), groupe.getId())){
                    String json = new ResponseObject("error", "NEXTURL", "Etudiant's groupe is not found in Emargement").toJSON();
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
                }

                Signature signature = BDD_Signature.getById(emargement.getId(), etudiant.getId());
                if(signature == null){
                    String json = new ResponseObject("error", "NEXTURL", "Signature with emargement_id:"+emargement.getId()+" and etudiant_id:"+etudiant.getId()+"not found").toJSON();
                    return Response.status(Response.Status.NOT_FOUND).entity(json).build();
                }

                if(signature.isSignee()){
                    String json = new ResponseObject("error", "NEXTURL", "Emargement already saved").toJSON();
                    return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
                }

                /* START TRAITEMENT */
                Calendar cal = Calendar.getInstance(); // creates calendar
                cal.setTime(new java.util.Date()); // sets calendar time/date
                cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
                Timestamp date_signature = new Timestamp(cal.getTime().getTime());

                signature.setSignee(true);
                signature.setDate(date_signature);
                /* END TRAITEMENT*/

                if (BDD_Signature.update(signature)){
                    String json = new ResponseObject("success", "NEXTURL", "Signature has been updated with success").toJSON();
                    return Response.status(Response.Status.OK).entity(json).build();
                } else {
                    String json = new ResponseObject("error", "NEXTURL", "Updating signature with emargement_id:"+emargement.getId()+" and etudiant_id:"+etudiant.getId()+"has failed").toJSON();
                    return Response.status(Response.Status.NOT_FOUND).entity(json).build();
                }
            } catch (SQLException e) {
                String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
            }

        }

        String json = new ResponseObject("cookie_not_found", "NEXTURL", "No cookie found").toJSON();
        return Response.status(Response.Status.NOT_FOUND).entity(json).build();
    }

    @POST
    @Path("/authentication_etudiant")
    @Consumes("application/json")
    public Response AuthEtudiant(String data){
        try {
            JsonObject jobj = new Gson().fromJson(data, JsonObject.class);
            String login = jobj.get("login").toString();
            if(login == null || login.isEmpty()){
                String json = new ResponseObject("error", "NEXTURL", "Login not acceptable").toJSON();
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(json).build();
            }

            String password = jobj.get("password").toString();
            if(password == null || password.isEmpty()){
                String json = new ResponseObject("error", "NEXTURL", "Password not acceptable").toJSON();
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(json).build();
            }


            Etudiant etudiant = BDD_Etudiant.checkAuth(login, password);
            if (etudiant == null){
                String json = new ResponseObject("error", "NEXTURL", "Login and password don't match").toJSON();
                return Response.status(Response.Status.NOT_FOUND).entity(json).build();
            }

            // NEW COOKIE
            return Response.ok().cookie(new NewCookie(cookie_name, etudiant.getNum_etu())).build();

        } catch (SQLException e) {
            Logger.getInstance().err(e.getMessage());
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        } catch (ParseException e) {
            Logger.getInstance().err(e.getMessage());
            String json = new ResponseObject("error", "nextURL",  e.getMessage()).toJSON();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(json).build();
        }
    }

    @GET
    @Path("/getCookie")
    public Response getCookie(@CookieParam(value = cookie_name) String cookie_num_etu){
        System.out.println(cookie_num_etu);
        if(cookie_num_etu != null && !cookie_num_etu.isEmpty()){
            String json = new ResponseObject("success", "NEXTURL", cookie_num_etu).toJSON();
            return Response.status(Response.Status.OK).entity(json).build();
        } else {
            String json = new ResponseObject("error", "NEXTURL", "No cookie found").toJSON();
            return Response.status(Response.Status.NOT_FOUND).entity(json).build();
        }

    }

    @GET
    @Path("/setCookie")
    public Response setCookie(){
        return Response.ok().cookie(new NewCookie(cookie_name, "15000001")).build();
    }


}
