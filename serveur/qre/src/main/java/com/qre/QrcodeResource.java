package com.qre;

import com.google.gson.Gson;
import database.BDD_Emargement;
import database.BDD_Etudiant;
import database.BDD_Signature;
import database.Database;
import model.Emargement;
import model.Etudiant;
import model.Signature;
import utils.ResponseObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;


@Path("/qrcode")
public class QrcodeResource {

    final String cookie_name = "QREmargement";

    @GET
    @Path("/scan/{url_generated}")
    @Produces({MediaType.APPLICATION_JSON})
    public String scanQRE(@PathParam("url_generated") String url_generated, @CookieParam(value = cookie_name) String cookie_num_etu) throws SQLException {
        if(cookie_num_etu != null && !cookie_num_etu.isEmpty() && url_generated != null && !url_generated.isEmpty()){
            Connection connection = Database.getDbCon().conn;

            Etudiant etudiant = BDD_Etudiant.getByNumEtu(connection, cookie_num_etu);
            if(etudiant == null){
                String status = "error";
                String nextURL = "NEXTURL";
                String message = "Etudiant with num_etu:"+cookie_num_etu+" not found";
                return new Gson().toJson(new ResponseObject(status, nextURL, message));
            }

            Emargement emargement = BDD_Emargement.getByURL(connection, url_generated);
            if(emargement == null){
                String status = "error";
                String nextURL = "NEXTURL";
                String message = "Emargement with url_generated:"+url_generated+" not found";
                return new Gson().toJson(new ResponseObject(status, nextURL, message));
            }

            Signature signature= BDD_Signature.getById(connection, emargement.getId(), etudiant.getId());
            if(signature == null){
                String status = "error";
                String nextURL = "NEXTURL";
                String message = "Signature with emargement_id:"+emargement.getId()+" and etudiant_id:"+etudiant.getId()+"not found";
                return new Gson().toJson(new ResponseObject(status, nextURL, message));
            }

            signature.setSignee(true);
            signature.setDate(new java.sql.Date(new java.util.Date().getTime()));

            if(BDD_Signature.update(connection, signature)){
                String status = "success";
                String nextURL = "NEXTURL";
                String message = "Signature has been updated with success";
                return new Gson().toJson(new ResponseObject(status, nextURL, message));
            } else {
                String status = "error";
                String nextURL = "NEXTURL";
                String message = "Updating signature with emargement_id:"+emargement.getId()+" and etudiant_id:"+etudiant.getId()+"has failed";
                return new Gson().toJson(new ResponseObject(status, nextURL, message));
            }
        }

        String status = "cookie_not_found";
        String nextURL = "NEXTURL";
        String message = "No cookie found";
        return new Gson().toJson(new ResponseObject(status, nextURL, message));
    }

    @GET
    @Path("/setCookie")
    public Response setCookie(){
        return Response.seeOther(URI.create("qrcode/getCookie"))
                .cookie(new NewCookie(cookie_name, "15003456"))
                .build();
    }

    @GET
    @Path("/getCookie")
    public String getCookie(@CookieParam(value = cookie_name) String cookie_num_etu){
        return new Gson().toJson(cookie_num_etu);
    }
}
