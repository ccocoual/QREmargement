package com.qre;

/**
 * Created by stagiaire on 21/01/2016.
 */

import com.google.gson.Gson;
import utils.EncrypteString;
import utils.Log;
import utils.ResponseObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.security.NoSuchAlgorithmException;

@Path("/api")
public class APIResource {

    @GET
    @Produces({MediaType.TEXT_HTML})
    public InputStream api() throws FileNotFoundException {
        return new FileInputStream(new File(getClass().getClassLoader().getResource("api.html").getFile()));
    }

    @GET
    @Path("/logs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream logs() throws FileNotFoundException {
        return new FileInputStream(new File(getClass().getClassLoader().getResource("logs.html").getFile()));
    }

    @GET
    @Path("/testLogs")
    public void test() throws IOException {
        Log.getInstance().info("Une information");
        Log.getInstance().warn("Un warning");
        Log.getInstance().err("Une erreur");
    }
}
