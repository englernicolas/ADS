package br.com.bibliotech.services;

import br.com.bibliotech.domains.School;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SchoolService {
    private Connection connection;

    public SchoolService(Connection connection) {
        this.connection = connection;
    }

    public boolean createSchool(School school) {
        String query = "INSERT INTO school(name) VALUE(?)";

        PreparedStatement p;
        try {
            p = this.connection.prepareStatement(query);
            p.setString(1, school.getName());
            p.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
