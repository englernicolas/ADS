package br.com.bibliotech.services;

import br.com.bibliotech.domains.Book;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private Connection connection;

    public BookService(Connection connection) {
        this.connection = connection;
    }

    public boolean create(Book book) throws ParseException {
        String query = "INSERT INTO `book`(" +
                "title, " +
                "pages, " +
                "genre_id, " +
                "author_id, " +
                ") VALUE(?,?,?,?)";

        PreparedStatement p;
        try {
            p = this.connection.prepareStatement(query);
            p.setString(1, book.getTitle());
            p.setString(2, book.getPages());
            p.setInt(3, book.getGenreId());
            p.setInt(4, book.getAuthorId());
            p.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean edit(Book book) throws ParseException {
        String query = "UPDATE book SET " +
                "title, " +
                "pages, " +
                "genre_id, " +
                "author_id, " +
                ") VALUE(?,?,?,?)";

        PreparedStatement p;

        try {
            p = this.connection.prepareStatement(query);
            p.setString(1, book.getTitle());
            p.setString(2, book.getPages());
            p.setInt(3, book.getGenreId());
            p.setInt(4, book.getAuthorId());
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Book> list() {
        String query = "SELECT * FROM book WHERE is_available = 1";

        List<Book> bookList = new ArrayList<Book>();
        Book book;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                book = new Book();

                int id = rs.getInt("id");
                String title = rs.getString("title");
                String pages = rs.getString("pages");
                int genreId = rs.getInt("genre_id");
                int authorId = rs.getInt("author_id");

                book.setId(id);
                book.setTitle(title);
                book.setPages(pages);
                book.setGenreId(genreId);
                book.setAuthorId(authorId);

                bookList.add(book);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return bookList;
    }

    public List<Book> getBySearch(String searchText) {
        String query = "SELECT * FROM book WHERE is_available = 1 ";

        if (!searchText.trim().equals("")) {
            query += "AND (title LIKE '%" + searchText + "%')";
        }

        List<Book> bookList = new ArrayList<Book>();

        try {
            PreparedStatement p = this.connection.prepareStatement(query);
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                Book book = new Book();

                int id = rs.getInt("id");
                String title = rs.getString("title");
                String pages = rs.getString("pages");
                int genreId = rs.getInt("genre_id");
                int authorId = rs.getInt("author_id");

                book.setId(id);
                book.setTitle(title);
                book.setPages(pages);
                book.setGenreId(genreId);
                book.setAuthorId(authorId);

                bookList.add(book);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return bookList;
    }

    public Book getById(int bookId) {
        String query = "SELECT * FROM book WHERE id = ?";

        Book book = new Book();

        try {
            PreparedStatement p = this.connection.prepareStatement(query);
            p.setInt(1, bookId);
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String pages = rs.getString("pages");
                int genreId = rs.getInt("genre_id");
                int authorId = rs.getInt("author_id");

                book.setId(id);
                book.setTitle(title);
                book.setPages(pages);
                book.setGenreId(genreId);
                book.setAuthorId(authorId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return book;
    }

    public boolean delete(Book book) {
        String query = "UPDATE book SET " +
                "is_available = 0, " +
                "deleted_reason = ? " +
                "WHERE id = ?";

        PreparedStatement p;

        try {
            p = this.connection.prepareStatement(query);
            p.setString(1, book.getDeletedReason());
            p.setInt(2, book.getId());
            p.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
