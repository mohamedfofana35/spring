package org.example;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseConnection {
    // Informations de connexion
    private static final String URL = "jdbc:postgresql://localhost:5432/demo";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1994";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}