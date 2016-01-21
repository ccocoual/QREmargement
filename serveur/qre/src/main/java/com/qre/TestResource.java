package com.qre;

/**
 * Created by stagiaire on 21/01/2016.
 */

import com.google.gson.Gson;
import utils.EncrypteString;
import utils.ResponseObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;

@Path("/test")
public class TestResource {

    @GET
    @Path("/{id}")
    public Response test(@PathParam("id") String id){
        String json = null;
        try {
            json = new Gson().toJson(EncrypteString.encode(id));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.OK).entity(json).build();

    }
}
