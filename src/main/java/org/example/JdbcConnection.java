package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnection {


   // Informations de connexion
    private static final String URL = "jdbc:postgresql://localhost:5432/nomdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "xxxx";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Étape 1: Charger le driver JDBC (facultatif avec JDBC 4+)
            Class.forName("org.postgresql.Driver");

            // Étape 2: Établir la connexion
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion établie avec succès.");

            // Étape 3: Créer un statement pour exécuter une requête
            statement = connection.createStatement();
            //La requête a excécuté
            String sql = "SELECT id, nom, age FROM student";
            // Étape 3: Exécuter la requête
            resultSet = statement.executeQuery(sql);

            // Étape 4: Parcourir les résultats
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                int age = resultSet.getInt("age");
                System.out.println("ID: " + id + ", Nom: " + nom + ", Age: " + age);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Le driver JDBC est introuvable.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erreur de connexion ou d'exécution SQL.");
            e.printStackTrace();
        } finally {
            // Étape 5: Libérer les ressources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

