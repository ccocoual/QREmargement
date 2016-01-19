package com.qre;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/qrcode")
public class QrCode {

    final String cookie_name = "QREmargement";

    @GET
    @Path("{random_str}")
    public String myMethod(@PathParam("random_str") String random_str, @CookieParam(value = cookie_name) String cookie) {


        /*
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("user-agent", userAgentHeader);
        jsonObject.append("accessed", accessed);

        if (userAgent != null) {
            jsono.append("previous-agent", userAgent.replace("##semicolon##", ";").replace("##comma##", ","));
        } else {
            jsono.append("previous-agent", null);
        }

        jsono.append("last-accessed", lastAccessed);

        return Response.ok(jsono, MediaType.APPLICATION_JSON).cookie(agentCookie, lastAccessedCookie).build();
        */
        return "";
    }

    @GET
    @Path("/setCookie")
    public Response setCookie() {

        return Response.seeOther(URI.create("qrcode/getCookie"))
                .cookie(new NewCookie(cookie_name, "mjqghmkjezbkjDS"))
                .build();
    }

    @GET
    @Path("/getCookie")
    public String getCookie(@CookieParam(value = cookie_name) String localLastVisited){
        return localLastVisited;
    }
}
