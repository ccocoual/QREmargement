package com.qre;

import database.BDD_Classe;
import database.Database;
import model.Classe;

import javax.ws.rs.*;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/classe")
public class REST_Classe {

    @GET
    @Path("/all")
    @Produces("application/json")
    public Object getAll() {
        Database db = Database.getDbCon();
        try {
            return BDD_Classe.getAll(db.conn);
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
