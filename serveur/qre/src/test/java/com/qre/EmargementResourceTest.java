package com.qre;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.ResponseObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmargementResourceTest {

    private HttpServer server;
    private WebTarget target;



    @Before
    public void setUp() {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);

    }

    @After
    public void tearDown() {
        server.stop();
    }

    @Test
    public void GroupeTest() {
        /* INITIALISATION */
        Timestamp date;
        String token_unlimited, url_generated, type_cours, response;
        Classe classe;
        Groupe groupe;
        Matiere matiere;
        Etudiant etudiant1;
        Etudiant etudiant2;
        Emargement emargement;
        int nb_emargements_before_insert, nb_emargements_after_insert, nb_emargements_after_put, nb_emargements_after_delete, professeur_id;

        token_unlimited = "test";
        url_generated = "url_generated_test";
        type_cours = "CM";
        professeur_id = 1;

        emargement = new Emargement();

        matiere = new Matiere();
        matiere.setLibelle("testMatiere");
        response = target.path(token_unlimited+"/matieres").request().post(Entity.entity(matiere, MediaType.APPLICATION_JSON_TYPE)).readEntity(String.class);
        matiere = new Gson().fromJson(response, Matiere.class);

        classe = new Classe();
        classe.setLibelle("testClasse");
        response = target.path(token_unlimited+"/classes").request().post(Entity.entity(classe, MediaType.APPLICATION_JSON_TYPE)).readEntity(String.class);
        classe = new Gson().fromJson(response, Classe.class);

        groupe = new Groupe();
        groupe.setLibelle("testGroupe");
        groupe.setClasse_id(classe.getId());
        response = target.path(token_unlimited+"/groupes").request().post(Entity.entity(groupe, MediaType.APPLICATION_JSON_TYPE)).readEntity(String.class);
        groupe = new Gson().fromJson(response, Groupe.class);

        etudiant1 = new Etudiant();
        etudiant1.setNom("nom1_test");
        etudiant1.setPrenom("prenom1_test");
        etudiant1.setEmail("nom1_test.prenom1_test@email.fr");
        etudiant1.setNum_etu("15009876");
        etudiant1.setGroupe_id(groupe.getId());
        etudiant1.setClasse_id(classe.getId());
        response = target.path(token_unlimited+"/etudiants").request().post(Entity.entity(etudiant1, MediaType.APPLICATION_JSON_TYPE)).readEntity(String.class);
        etudiant1 = new Gson().fromJson(response, Etudiant.class);

        etudiant2 = new Etudiant();
        etudiant2.setNom("nom2_test");
        etudiant2.setPrenom("prenom2_test");
        etudiant2.setEmail("nom2_test.prenom2_test@email.fr");
        etudiant2.setNum_etu("15006789");
        etudiant2.setGroupe_id(groupe.getId());
        etudiant2.setClasse_id(classe.getId());
        response = target.path(token_unlimited+"/etudiants").request().post(Entity.entity(etudiant2, MediaType.APPLICATION_JSON_TYPE)).readEntity(String.class);
        etudiant2 = new Gson().fromJson(response, Etudiant.class);


        /* ****************** */

        /* GET ALL */
        response = target.path(token_unlimited + "/emargements").request().get(String.class);
        nb_emargements_before_insert = new Gson().fromJson(response, Emargement[].class).length;
        /* ****************** */

        /* INSERT */
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new java.util.Date()); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
        date = new Timestamp(cal.getTime().getTime());

        emargement.setDate(date);
        emargement.setType_cours(type_cours);
        emargement.setMatiere_id(matiere.getId());
        emargement.setProfesseur_id(professeur_id);
        emargement.addGroupe(groupe);

        // verifie que l'id est bien null
        assertEquals(0, emargement.getId());
        response = target.path(token_unlimited+"/emargements").request().post(Entity.entity(emargement, MediaType.APPLICATION_JSON_TYPE)).readEntity(String.class);
        emargement = new Gson().fromJson(response, Emargement.class);
        // verifie que l'id est different de null
        assertTrue(emargement.getId() != 0);
        // verifie que les champs correspondent
        assertEquals(type_cours, emargement.getType_cours());
        assertEquals(professeur_id,emargement.getProfesseur_id());
        response = target.path(token_unlimited+"/emargements").request().get(String.class);
        nb_emargements_after_insert = new Gson().fromJson(response, Etudiant[].class).length;
        // verifie qu'on a une ecriture supplémentaire dans la bdd
        assertEquals(nb_emargements_before_insert+1, nb_emargements_after_insert);
        /* ****************** */

        /* GET BY ID */
        int old_id = emargement.getId();
        response = target.path(token_unlimited+"/emargements/"+emargement.getId()).request().get(String.class);
        emargement = new Gson().fromJson(response, Emargement.class);
        // verifie que les id correspondent
        assertEquals(old_id, emargement.getId());
        /* ****************** */

        /* PUT */

        Groupe groupe_bis = new Groupe();
        groupe_bis.setLibelle("testGroupe_bis");
        groupe_bis.setClasse_id(classe.getId());
        response = target.path(token_unlimited+"/groupes").request().post(Entity.entity(groupe_bis, MediaType.APPLICATION_JSON_TYPE)).readEntity(String.class);
        groupe_bis = new Gson().fromJson(response, Groupe.class);

        Etudiant etudiant1_bis = new Etudiant();
        etudiant1_bis.setNom("nom1_bis");
        etudiant1_bis.setPrenom("prenom1_bis");
        etudiant1_bis.setEmail("nom1_bis.prenom1_bis@email.fr");
        etudiant1_bis.setNum_etu("15006576");
        etudiant1_bis.setGroupe_id(groupe_bis.getId());
        etudiant1_bis.setClasse_id(classe.getId());
        response = target.path(token_unlimited+"/etudiants").request().post(Entity.entity(etudiant1_bis, MediaType.APPLICATION_JSON_TYPE)).readEntity(String.class);
        etudiant1_bis = new Gson().fromJson(response, Etudiant.class);

        Etudiant etudiant2_bis = new Etudiant();
        etudiant2_bis.setNom("nom2_bis");
        etudiant2_bis.setPrenom("prenom2_bis");
        etudiant2_bis.setEmail("nom2_bis.prenom2_bis@email.fr");
        etudiant2_bis.setNum_etu("15003864");
        etudiant2_bis.setGroupe_id(groupe_bis.getId());
        etudiant2_bis.setClasse_id(classe.getId());
        response = target.path(token_unlimited+"/etudiants").request().post(Entity.entity(etudiant2_bis, MediaType.APPLICATION_JSON_TYPE)).readEntity(String.class);
        etudiant2_bis = new Gson().fromJson(response, Etudiant.class);

        old_id = emargement.getId();
        int old_nb_groupes = emargement.getGroupes().size();
        emargement.addGroupe(groupe_bis);
        response = target.path(token_unlimited+"/emargements/"+emargement.getId()).request().put(Entity.entity(emargement, MediaType.APPLICATION_JSON_TYPE)).readEntity(String.class);
        System.out.println(response);
        emargement = new Gson().fromJson(response, Emargement.class);
        // verifie que les id correspondent toujours
        assertEquals(old_nb_groupes+1, emargement.getGroupes().size());
        // verifie que le libelle a été mis à jour
        assertEquals(old_id, emargement.getId());
        response = target.path(token_unlimited+"/emargements").request().get(String.class);
        nb_emargements_after_put = new Gson().fromJson(response, Emargement[].class).length;
        // verifie qu'on a toujours le meme nombre d'ecriture en bdd
        assertEquals(nb_emargements_after_insert, nb_emargements_after_put);
        /* ****************** */

        /* DELETE */
        /* response = target.path(token_unlimited+"/etudiants/"+etudiant.getId()).request().delete().readEntity(String.class);
        ResponseObject result = new Gson().fromJson(response, ResponseObject.class);
        // verifie que le serveur renvoie bien un message success
        assertEquals("success", result.getStatus());
        response = target.path(token_unlimited+"/etudiants").request().get(String.class);
        nb_etudiants_after_delete = new Gson().fromJson(response, Groupe[].class).length;
        // verifie qu'on a le meme nombre d'ecriture en bdd qu'avant l'insertion
        assertEquals(nb_etudiants_before_insert, nb_etudiants_after_delete);
        target.path(token_unlimited+"/groupes/"+groupe.getId()).request().delete().readEntity(String.class);
        target.path(token_unlimited+"/classes/"+classe.getId()).request().delete().readEntity(String.class);
        */
        /* ****************** */
    }
}
