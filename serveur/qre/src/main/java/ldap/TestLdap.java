package ldap;

import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;

import java.util.List;
public class TestLdap {
    /** Retrieve a Kurt Person from ldap server and display Kurt in
     Standard Out */
    public static void main(String[] args) {
        // 1 Retrieve a LdapContextSource
        ContextSource ldapContextSource = null;
        try {
            ldapContextSource = LdapContextSourceFactory.getLdapContextSource();
        } catch (Exception e) {
            System.out.println("Impossible to get a LdapContextSource.");
            e.printStackTrace();
        }
        // 2 Instanciate a LdapTemplate
        LdapTemplate ldapTemplate = new LdapTemplate();
        ldapTemplate.setContextSource(ldapContextSource);

        // 3 instanciate a PersonDao
        PersonDao dao = new PersonDao();
        dao.setLdapTemplate(ldapTemplate);

        // 4 retrieve a Person and display it
        Person person = dao.findByPrimaryKey("kurt");
        System.out.println("Uid: " + person.getUid());
        System.out.println("FirstName: " + person.getFirstName());
        System.out.println("LastName: " + person.getLastName());
        System.out.println("Email: " + person.getEmail() + "\n");
        // 5 retrieve a list of person
        List listPerson = dao.getPersonNamesByLastName("*e*");
        for (Object object : listPerson) {
            System.out.println("Person: " + object);
        }
    }
}
