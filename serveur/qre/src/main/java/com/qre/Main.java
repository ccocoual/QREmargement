package com.qre;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import utils.CORSResponseFilter;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
public class Main {

    // Base URI the Grizzly HTTP server will listen on
    public static String BASE_URI = "http://148.60.11.185:8080/";
    public static String LOCALHOST_URI = "http://localhost:8080/";
    public static boolean PRODUCTION = true;

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.qre package
        final ResourceConfig rc = new ResourceConfig().packages("com.qre");
        rc.register(CORSResponseFilter.class);

        if(!PRODUCTION){
            BASE_URI = LOCALHOST_URI;
        }
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if(!PRODUCTION){
            BASE_URI = LOCALHOST_URI;
        }

        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}

