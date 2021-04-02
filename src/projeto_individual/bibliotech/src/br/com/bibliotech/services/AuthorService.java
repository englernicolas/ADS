package br.com.bibliotech.services;

import br.com.bibliotech.domains.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AuthorService {
    private Connection connection;

    public AuthorService(Connection connection) {
        this.connection = connection;
    }

    public boolean createAuthor(Author author) {
        String query = "INSERT INTO author(name) VALUE(?)";

        PreparedStatement p;
        try {
            p = this.connection.prepareStatement(query);
            p.setString(1, author.getName());
            p.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Author> list() {
        String query = "SELECT * FROM author";

        List<Author> authorList = new ArrayList<Author>();
        Author author;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                author = new Author();

                int id = rs.getInt("id");
                String name = rs.getString("name");

                author.setId(id);
                author.setName(name);

                authorList.add(author);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return authorList;
    }
}
