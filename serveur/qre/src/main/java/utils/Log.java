package utils;

import java.io.*;
import java.sql.Timestamp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * Created by stagiaire on 25/01/2016.
 */
public class Log {

    private Log(){ }

    /** Instance unique non préinitialisée */
    private static Log INSTANCE = null;

    /** Point d'accès pour l'instance unique du singleton */
    public static Log getInstance() {
        if (INSTANCE == null) INSTANCE = new Log();
        return INSTANCE;
    }

    public void info(String str) {
        write("INFO", str);
    }

    public void warn(String str){
        write("WARNING", str);
    }

    public void err(String str) {
        write("ERROR", str);
    }

    private void write(String typeLog, String str) {
        try {
            File file = new File(getClass().getClassLoader().getResource("logs.html").getFile());

            Document doc = Jsoup.parse(file, "UTF-8");
            Element tbody = doc.getElementById("logs");

            Element tr = tbody.appendElement("tr").addClass(typeLog);

            Element tdType = tr.appendElement("td").addClass("type").text(typeLog);
            Element tdDate = tr.appendElement("td").addClass("date").text(new Timestamp(new java.util.Date().getTime()).toString());
            Element tdMessage = tr.appendElement("td").addClass("message").text(str);

            BufferedWriter htmlWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            htmlWriter.write(doc.toString());
            htmlWriter.flush();
            htmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
