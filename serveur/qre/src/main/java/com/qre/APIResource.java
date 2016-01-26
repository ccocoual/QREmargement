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
import java.sql.Timestamp;
import java.util.ArrayList;

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
    public String logs() throws FileNotFoundException {

        Document doc = null;
        try {
            ArrayList<Logger.Log> logs = Logger.getInstance().getLastLogs();

            File file = new File(getClass().getClassLoader().getResource("logs.html").getFile());

            doc = Jsoup.parse(file, "UTF-8");
            Element tbody = doc.getElementById("logs");


            for (Logger.Log log : logs) {
                Element tr = tbody.appendElement("tr").addClass(log.getType_log());

                Element tdType = tr.appendElement("td").addClass("type").text(log.getType_log());
                Element tdDate = tr.appendElement("td").addClass("date").text(log.getDate().toString());
                Element tdMessage = tr.appendElement("td").addClass("message").text(log.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc.html();
    }

    @GET
    @Path("/models")
    @Produces({MediaType.TEXT_HTML})
    public InputStream model() throws FileNotFoundException {
        return new FileInputStream(new File(getClass().getClassLoader().getResource("models.html").getFile()));
    }
}
