package EmailVerwaltung;

import java.sql.*;

public class EmailKontaktDao {
    private String url;

    public EmailKontaktDao() throws ClassNotFoundException, SQLException {


        Connection conn = null;
        Statement stmt = null;
        //class wird dynamisch erzeugt für sql
        Class.forName("org.sqlite.JDBC");
        //verbindung wird initialisiert
        conn = DriverManager.getConnection("jdbc:sqlite:email.db");
        url = "jdbc:sqlite:email.db";
        //Connection conn = DriverManager.getConnection(url);
        stmt = conn.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS Email " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " Vorname TEXT, " +
                " Nachname TEXT, " +
                " Email TEXT)";
        stmt.executeUpdate(sql);

        /* Daten einfügen
        sql = "INSERT INTO Email (Vorname, Nachname, Email) " +
                "VALUES ('Max', 'Mustermann', 'max.mustermann@example.com')";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO Email (Vorname, Nachname, Email) " +
                "VALUES ('Anna', 'Müller', 'anna.mueller@example.com')";
        stmt.executeUpdate(sql);

        System.out.println("Daten wurden erfolgreich in die Tabelle Email eingefügt.");

    */}

    public EmailKontakt select(int id) throws SQLException {
        EmailKontakt emailKontakt = new EmailKontakt();
        String sql = "Select id from email where id=" + id;
        try {


            Connection conn = DriverManager.getConnection(url);
            //Daten auslesen
            //Wie erzeugen eine SQL Anweisung
            sql = "SELECT * from email WHERE id = " + id;
            PreparedStatement statement = conn.prepareStatement(sql);
            //SQL Anweisung ausführen
            ResultSet resultSet = statement.executeQuery();

            // Datensatzzeiger auf den ersten Datensatz setzen.
            while (resultSet.next()) {
                // Daten auslesen.
                id = resultSet.getInt("id");
                emailKontakt.setId(id);
                String vorname = resultSet.getString("Vorname");
                emailKontakt.setVorname(vorname);
                String nachname = resultSet.getString("Nachname");
                emailKontakt.setNachname(nachname);
                String email = resultSet.getString("Email");
                emailKontakt.setEmail(email);
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return emailKontakt;
    }

    public EmailKontakt first(EmailKontakt select) {
        EmailKontakt firstEmail = new EmailKontakt();
        try {
            Connection conn = DriverManager.getConnection(url);

            String sql = "SELECT * FROM email id = min ";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String vorname = resultSet.getString("Vorname");
                String nachname = resultSet.getString("Nachname");
                String email = resultSet.getString("Email");

                firstEmail.setId(id);
                firstEmail.setVorname(vorname);
                firstEmail.setNachname(nachname);
                firstEmail.setEmail(email);
               // System.out.println(firstEmail);
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return firstEmail;
    }

    public EmailKontakt last(EmailKontakt select) {
        EmailKontakt lastEmail = new EmailKontakt();
        try {
            Connection conn = DriverManager.getConnection(url);

            String sql = "SELECT * FROM email ORDER BY id DESC LIMIT 1";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String vorname = resultSet.getString("Vorname");
                String nachname = resultSet.getString("Nachname");
                String email = resultSet.getString("Email");

                lastEmail.setId(id);
                lastEmail.setVorname(vorname);
                lastEmail.setNachname(nachname);
                lastEmail.setEmail(email);
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lastEmail;
    }

    public EmailKontakt next(EmailKontakt emailKontakt) {
        EmailKontakt nextEmail = new EmailKontakt();
        try {


            Connection conn = DriverManager.getConnection(url);
            int currentId = emailKontakt.getId();

            String sql = "SELECT * FROM email WHERE id > ? ORDER BY id ASC LIMIT 1";
            PreparedStatement statement = conn.prepareStatement(sql);
            //SQL Anweisung ausführen
            statement.setInt(1, currentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String vorname = resultSet.getString("Vorname");
                String nachname = resultSet.getString("Nachname");
                String email = resultSet.getString("Email");

                nextEmail.setId(id);
                nextEmail.setVorname(vorname);
                nextEmail.setNachname(nachname);
                nextEmail.setEmail(email);
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return nextEmail;
    }

    public EmailKontakt previous(EmailKontakt emailKontakt) {
        EmailKontakt previousEmail = new EmailKontakt();
        try {


            Connection conn = DriverManager.getConnection(url);
            int currentId = emailKontakt.getId();

            String sql = "SELECT * FROM email WHERE id < ? ORDER BY id desc LIMIT 1";
            PreparedStatement statement = conn.prepareStatement(sql);
            //SQL Anweisung ausführen
            statement.setInt(1, currentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String vorname = resultSet.getString("Vorname");
                String nachname = resultSet.getString("Nachname");
                String email = resultSet.getString("Email");

                previousEmail.setId(id);
                previousEmail.setVorname(vorname);
                previousEmail.setNachname(nachname);
                previousEmail.setEmail(email);
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return previousEmail;
    }


    public EmailKontakt delete(EmailKontakt emailKontakt) {
        try {
            Connection conn = DriverManager.getConnection(url);

            // Erzeugen einer SQL-Anweisung zum Löschen des Datensatzes mit der gegebenen ID
            String sql = "DELETE FROM email WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, emailKontakt.getId());

            // Ausführen der SQL-Anweisung
            statement.executeUpdate();

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return emailKontakt;
    }

    public EmailKontakt insert(EmailKontakt emailKontakt) {
        try {
            Connection conn = DriverManager.getConnection(url);

            String sql = "INSERT INTO email (Vorname, Nachname, Email) VALUES ( ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, emailKontakt.getVorname());
            statement.setString(2, emailKontakt.getNachname());
            statement.setString(3, emailKontakt.getEmail());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                emailKontakt.setId(id);
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return emailKontakt;
    }


    public EmailKontakt update(EmailKontakt emailKontakt) throws SQLException {
        Connection conn = DriverManager.getConnection(url);
        String sql = "UPDATE email SET vorname=?, nachname=?, email=? WHERE id=?";
        try (
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, emailKontakt.getVorname());
            pstmt.setString(2, emailKontakt.getNachname());
            pstmt.setString(3, emailKontakt.getEmail());
            pstmt.setInt(4, emailKontakt.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }finally {conn.close();
        }
        return emailKontakt;
    }

}
