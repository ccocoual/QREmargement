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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

@Path("/api")
public class APIResource {

    @GET
    @Produces({MediaType.TEXT_HTML})
    public InputStream viewHome() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("api.html").getFile());
        return new FileInputStream(file);
    }
}
