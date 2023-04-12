package EmailVerwaltung;

public class EmailKontakt {

    private int id;
    private String vorname;
    private String nachname;
    private String email;

    public EmailKontakt(int id, String vorname, String nachname, String email) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
    }

    public EmailKontakt() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
