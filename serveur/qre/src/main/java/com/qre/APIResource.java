package com.qre;

/**
 * Created by stagiaire on 21/01/2016.
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import utils.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/api")
public class APIResource {

    @GET
    @Produces({MediaType.TEXT_HTML})
    public InputStream api(){
        try {
            return new FileInputStream(new File(getClass().getClassLoader().getResource("api.html").getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/logs")
    @Produces({MediaType.TEXT_HTML})
    public String logs(){

        Document doc = null;
        try {
            ArrayList<Logger.Log> logs = Logger.getInstance().getLastLogs();

            File file = new File(getClass().getClassLoader().getResource("logs.html").getFile());

            doc = Jsoup.parse(file, "UTF-8");
            Element tbody = doc.getElementById("logs");


            for (Logger.Log log : logs) {
                Element tr = tbody.appendElement("tr").addClass(log.getType_log());

                tr.appendElement("td").addClass("type").text(log.getType_log());
                tr.appendElement("td").addClass("date").text(log.getDate().toString());
                tr.appendElement("td").addClass("message").text(log.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (doc != null) {
            return doc.html();
        }

        return null;
    }

    @GET
    @Path("/models")
    @Produces({MediaType.TEXT_HTML})
    public InputStream model(){
        try {
            return new FileInputStream(new File(getClass().getClassLoader().getResource("models.html").getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
