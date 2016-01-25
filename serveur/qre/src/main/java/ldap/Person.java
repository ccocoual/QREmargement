package ldap;

public class Person {

    private Integer id;
    private String firstName;
    private String lastName;
    private String uid;
    private String email;
    private String promo;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int compareTo(Person p) {
        return this.getUid().compareTo(p.getUid());
    }

    public String getPromo() {
        return promo;
    }
    public void setPromo(String promo) {
        this.promo = promo;
    }

    public boolean equals(Person p) {
        return this.uid.equals(p.getUid());
    }
}
