package br.com.bibliotech.services;

import br.com.bibliotech.domains.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GenreService {
    private Connection connection;

    public GenreService(Connection connection) {
        this.connection = connection;
    }

    public boolean createGenre(Genre genre) {
        String query = "INSERT INTO genre(name) VALUE(?)";

        PreparedStatement p;
        try {
            p = this.connection.prepareStatement(query);
            p.setString(1, genre.getName());
            p.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Genre> list() {
        String query = "SELECT * FROM genre";

        List<Genre> genreList = new ArrayList<Genre>();
        Genre genre;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                genre = new Genre();

                int id = rs.getInt("id");
                String name = rs.getString("name");

                genre.setId(id);
                genre.setName(name);

                genreList.add(genre);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return genreList;
    }
}
