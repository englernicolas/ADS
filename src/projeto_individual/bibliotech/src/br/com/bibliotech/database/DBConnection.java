package br.com.bibliotech.database;

import java.sql.Connection;

public class DBConnection {
    private java.sql.Connection connection;

    public java.sql.Connection openConnection() {
        String basepath = "localhost";
        String database = "bibliotech";
        String username = "informant";
        String password = "informant";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://" + basepath + "/" + database +
                            "?user=" + username +
                            "&password=" + password +
                            "&useTimezone=true&serverTimezone=UTC"
            );
        } catch(Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
