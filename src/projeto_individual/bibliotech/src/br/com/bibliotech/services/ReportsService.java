package br.com.bibliotech.services;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;


public class ReportsService {
    private Connection connection;

    public ReportsService(Connection connection) {
        this.connection = connection;
    }

    public void mostBorrowedBooks(String initialDate, String endDate) {
        String query = getMostBorrowedBooksQuery(initialDate, endDate);

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            StringBuilder dataCommaSeparated = new StringBuilder();

            dataCommaSeparated.append("Titulo");
            dataCommaSeparated.append(",");
            dataCommaSeparated.append("Paginas");
            dataCommaSeparated.append(",");
            dataCommaSeparated.append("Autor");
            dataCommaSeparated.append(",");
            dataCommaSeparated.append("Quantidade Emprestada");
            dataCommaSeparated.append("\r\n");

            String header = dataCommaSeparated.toString();

            while (rs.next()) {
                String title = rs.getString("title");
                int pages = rs.getInt("pages");
                String authorName = rs.getString("author_name");
                int borrowedQuantity = rs.getInt("borrowed_quantity");

                dataCommaSeparated.append(title);
                dataCommaSeparated.append(",");
                dataCommaSeparated.append(pages);
                dataCommaSeparated.append(",");
                dataCommaSeparated.append(authorName);
                dataCommaSeparated.append(",");
                dataCommaSeparated.append(borrowedQuantity);
                dataCommaSeparated.append("\r\n");
            }

            if (!header.equals(dataCommaSeparated.toString())) {
                generateCSV(dataCommaSeparated, "livros_mais_emprestados");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void studentsThatMostLent(String initialDate, String endDate) {
        String query = getStudentsThatMostLentQuery(initialDate, endDate);

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            StringBuilder dataCommaSeparated = new StringBuilder();

            dataCommaSeparated.append("Quantidade de emprestimos");
            dataCommaSeparated.append(",");
            dataCommaSeparated.append("Aluno");
            dataCommaSeparated.append(",");
            dataCommaSeparated.append("Data Nascimento");
            dataCommaSeparated.append(",");
            dataCommaSeparated.append("Genero");
            dataCommaSeparated.append(",");
            dataCommaSeparated.append("Escola");
            dataCommaSeparated.append(",");
            dataCommaSeparated.append("\r\n");

            String header = dataCommaSeparated.toString();

            while (rs.next()) {
                int lentQuantity = rs.getInt("lent_quantity");
                String name = rs.getString("name");
                String birthDate = rs.getString("birth_date");
                String gender = rs.getString("gender");
                String school = rs.getString("school");

                dataCommaSeparated.append(lentQuantity);
                dataCommaSeparated.append(",");
                dataCommaSeparated.append(name);
                dataCommaSeparated.append(",");
                dataCommaSeparated.append(birthDate);
                dataCommaSeparated.append(",");
                dataCommaSeparated.append(gender);
                dataCommaSeparated.append(",");
                dataCommaSeparated.append(school);
                dataCommaSeparated.append("\r\n");
            }

            if (!header.equals(dataCommaSeparated.toString())) {
                generateCSV(dataCommaSeparated, "alunos_que_mais_emprestam");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void generateCSV(StringBuilder dataCommaSeparated, String fileName) {
        try {
            String date = new Date().toString().replaceAll(" ", "_").toLowerCase();
            fileName = fileName + "_" + date + ".csv";
            String path = "/home/nicolas/college/ADS/src/projeto_individual/bibliotech/reports/" + fileName;
            File file = new File(path);
            PrintWriter pw = new PrintWriter(new FileWriter(file, true));
            pw.write(dataCommaSeparated.toString());
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMostBorrowedBooksQuery (String initialDate, String endDate) {
        String query = "SELECT " +
                "b.id, b.title, b.pages, a.name as author_name, COUNT(book_id) as borrowed_quantity " +
                "FROM book_has_loan " +
                "INNER JOIN loan l ON book_has_loan.loan_id = l.id " +
                "INNER JOIN book b ON book_has_loan.book_id = b.id " +
                "INNER JOIN author a ON b.author_id = a.id ";

        query += getDateRangeQuery(initialDate, endDate);

        query += "GROUP BY book_id ORDER BY borrowed_quantity DESC";

        return query;
    }

    public String getStudentsThatMostLentQuery (String initialDate, String endDate) {
        String query = "SELECT " +
                "user_id, COUNT(l.user_id) as lent_quantity, " +
                "CONCAT(u.first_name, ' ', u.last_name) as name, " +
                "u.birth_date, g.name as gender, s.name as school " +
                "FROM loan l " +
                "INNER JOIN user u ON l.user_id = u.id " +
                "INNER JOIN gender g ON u.gender_id = g.id " +
                "INNER JOIN school s ON u.school_id = s.id ";

        query += getDateRangeQuery(initialDate, endDate);

        query += "GROUP BY user_id ORDER BY lent_quantity DESC";

        return query;
    }

    private String getDateRangeQuery(String initialDate, String endDate) {
        String query = "";
        if (initialDate!=null || endDate!=null) {
            if (initialDate!=null && endDate!=null) {
                query += "WHERE (l.loan_date >= '"+initialDate+"' AND " +
                        "l.loan_date <= '"+endDate+"') ";
            }
            else if (initialDate!=null) {
                query += "WHERE (l.loan_date >= '"+initialDate+"') ";
            }
            else {
                query += "WHERE (l.loan_date <= '"+endDate+"') ";
            }
        }
        return query;
    }
}