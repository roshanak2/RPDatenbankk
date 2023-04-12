import java.sql.*;

public class DBConnectionTest {
    public static void main(String[] args) throws SQLException {
        try {
            //class wird dynamisch erzeugt für sql
            Class.forName("org.sqlite.JDBC");
            //verbindung wird initialisiert
            String datei = "World-2.db3";
            String url = "jdbc:sqlite:" + datei;
            Connection conn = DriverManager.getConnection(url);

            //daten auslesen
            // wir erzeugen eine sql anwesung
            String sql = "SELECT * FROM City WHERE CountryCode = 'DEU'";
            PreparedStatement statement = conn.prepareStatement(sql);

            //sqlanweisung ausführen
            ResultSet resultSet = statement.executeQuery();

            //datenbankszeiger auf den ersten datensatz setzen
            resultSet.next();

            //daten auslesen
            String inhalt = resultSet.getString("Name");
            System.out.println(inhalt);
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }catch (SQLClientInfoException e){
            throw new RuntimeException(e);
        }
    }
}
