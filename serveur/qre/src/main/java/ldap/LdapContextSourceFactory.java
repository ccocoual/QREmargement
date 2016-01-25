package ldap;

import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.support.LdapContextSource;

public class LdapContextSourceFactory {

    private static String ldap_url = "ldap://ldap.forumsys.com:389"; //"ldap://www.openldap.com:389"
    private static String ldap_base = "ou=scientists,dc=example,dc=com"; // "dc=OpenLDAP,dc=org"

    public static ContextSource getLdapContextSource() throws Exception {
        LdapContextSource ldapContextSource = new LdapContextSource();
        ldapContextSource.setUrl(ldap_url);
        ldapContextSource.setBase(ldap_base);
        ldapContextSource.setAnonymousReadOnly(true);
        ldapContextSource.afterPropertiesSet();
        return ldapContextSource;
    }
}