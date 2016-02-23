package com.qre;

import javax.annotation.security.PermitAll;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by stagiaire on 23/02/2016.
 */
public class OptionsResource {

    // Match root-resources
    @OPTIONS
    @PermitAll
    public Response options() {
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    // Match sub-resources
    @OPTIONS
    @Path("{path:.*}")
    @PermitAll
    public Response optionsAll(@PathParam("path") String path) {
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
