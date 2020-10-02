package br.com.bibliotech.services;

import br.com.bibliotech.domains.School;
import br.com.bibliotech.domains.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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

    public List<School> list() {
        String query = "SELECT * FROM school";

        List<School> schoolList = new ArrayList<School>();
        School school;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                school = new School();

                int id = rs.getInt("id");
                String name = rs.getString("name");

                school.setId(id);
                school.setName(name);

                schoolList.add(school);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return schoolList;
    }
}
