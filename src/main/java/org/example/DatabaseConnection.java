package org.example;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseConnection {
    // Informations de connexion
    private static final String URL = "jdbc:postgresql://localhost:5432/nomdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "xxxx";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
