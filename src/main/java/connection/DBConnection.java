package connection;

import exception.CredentialsInvalidException;
import exception.PostgresClassNotFoundException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {

    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new PostgresClassNotFoundException("Postgres class could not loaded");
        }
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "password");
            System.out.println("Database connection successfully");
        } catch (SQLException e) {
            throw new CredentialsInvalidException("Database credentials is invalid");
        }

        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
