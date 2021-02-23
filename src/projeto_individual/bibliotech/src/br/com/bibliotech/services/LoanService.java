package br.com.bibliotech.services;

import br.com.bibliotech.domains.Book;
import br.com.bibliotech.domains.Loan;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LoanService {
    private Connection connection;

    public LoanService(Connection connection) {
        this.connection = connection;
    }

    public boolean create(Loan loan) throws ParseException {
        java.util.Date loanDate = new SimpleDateFormat("yyyy-MM-dd").parse(loan.getLoanDate());
        Date loanDateSql = new Date(loanDate.getTime());
        java.util.Date deliveryDate = new SimpleDateFormat("yyyy-MM-dd").parse(loan.getDeliveryDate());
        Date deliveryDateSql = new Date(deliveryDate.getTime());

        String queryInsertLoan = "INSERT INTO loan (" +
                "loan_date, " +
                "delivery_date, " +
                "user_id)" +
                " VALUE(?,?,?)";

        String queryInsertBookHasLoan = "INSERT INTO book_has_loan (" +
                "book_id, " +
                "loan_id)" +
                " VALUE(?,?)";

        String queryUpdateBookAvailability = "UPDATE book SET " +
                "is_available = 0 " +
                "WHERE id = ?";

        String queryUpdateUserBookQuantity = "UPDATE user SET " +
                "books = books + 1 " +
                "WHERE id = ?";

        PreparedStatement p;
        try {
            p = this.connection.prepareStatement(queryInsertLoan, Statement.RETURN_GENERATED_KEYS);

            p.setDate(1, loanDateSql);
            p.setDate(2, deliveryDateSql);
            p.setInt(3, loan.getStudentId());
            p.execute();

            ResultSet rs = p.getGeneratedKeys();

            if (rs.next()) {
                int loanId = rs.getInt(1);

                for (int bookId : loan.getBooksIds()) {
                    p = this.connection.prepareStatement(queryInsertBookHasLoan);

                    p.setInt(1, bookId);
                    p.setInt(2, loanId);

                    p.execute();

                    // Atualiza disponibilidade do livro

                    p = this.connection.prepareStatement(queryUpdateBookAvailability);

                    p.setInt(1, bookId);

                    p.executeUpdate();

                    // Atualiza quantidade livros do estudante

                    p = this.connection.prepareStatement(queryUpdateUserBookQuantity);

                    p.setInt(1, loan.getStudentId());

                    p.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Loan> list() {
        return list("");
    }

    public List<Loan> list(String userId) {
        String queryGetLoans = "SELECT * FROM loan WHERE active = 1";
        if (!userId.equals(""))
            queryGetLoans+= " AND user_id = " + userId;

        String queryGetBooks = "SELECT * FROM book_has_loan WHERE loan_id = ?";

        List<Loan> loanList = new ArrayList<Loan>();
        Loan loan;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queryGetLoans);

            while (rs.next()) {
                loan = new Loan();

                int id = rs.getInt("id");
                String loanDate = rs.getDate("loan_date").toString();
                String deliveryDate = rs.getDate("delivery_date").toString();
                int studentId = rs.getInt("user_id");

                PreparedStatement p = connection.prepareStatement(queryGetBooks);
                p.setInt(1, id);
                p.execute();

                ResultSet bookResultSet = p.getResultSet();
                List<Integer> booksIds = new ArrayList<Integer>();

                while (bookResultSet.next()) {
                    booksIds.add(bookResultSet.getInt("book_id"));
                }

                loan.setId(id);
                loan.setLoanDate(loanDate);
                loan.setDeliveryDate(deliveryDate);
                loan.setStudentId(studentId);
                loan.setBooksIds(booksIds);

                loanList.add(loan);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return loanList;
    }

    public List<Loan> getBySearch(String searchText) {
        String queryGetLoans = "SELECT * FROM loan l " +
                "INNER JOIN `user` u ON l.user_id = u.id " +
                "INNER JOIN book_has_loan bhl ON l.id = bhl.loan_id " +
                "INNER JOIN book b ON bhl.book_id = b.id ";

        if (!searchText.trim().equals("")) {
            queryGetLoans += "AND (u.first_name LIKE '%" + searchText + "%' " +
                    "OR u.last_name LIKE '%" + searchText + "%' " +
                    "OR u.email LIKE '%" + searchText + "%' " +
                    "OR b.title LIKE '%" + searchText + "%') ";
        }

        queryGetLoans += "AND l.active = 1";

        String queryGetBooks = "SELECT * FROM book_has_loan WHERE loan_id = ?";

        List<Loan> loanList = new ArrayList<Loan>();
        Loan loan;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queryGetLoans);

            while (rs.next()) {
                loan = new Loan();

                int id = rs.getInt("id");
                String loanDate = rs.getDate("loan_date").toString();
                String deliveryDate = rs.getDate("delivery_date").toString();
                int studentId = rs.getInt("user_id");

                PreparedStatement p = connection.prepareStatement(queryGetBooks);
                p.setInt(1, id);
                p.execute();

                ResultSet bookResultSet = p.getResultSet();
                List<Integer> booksIds = new ArrayList<Integer>();

                while (bookResultSet.next()) {
                    booksIds.add(bookResultSet.getInt("book_id"));
                }

                loan.setId(id);
                loan.setLoanDate(loanDate);
                loan.setDeliveryDate(deliveryDate);
                loan.setStudentId(studentId);
                loan.setBooksIds(booksIds);

                loanList.add(loan);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return loanList;
    }

    /*
        public Loan getById(int loanId) {
            String query = "SELECT * FROM loan WHERE id = ?";

            Loan loan = new Loan();

            try {
                PreparedStatement p = this.connection.prepareStatement(query);
                p.setInt(1, loanId);
                ResultSet rs = p.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String loanDate = rs.getDate("birth_date").toString();
                    String cpf = rs.getString("cpf");
                    int genderId = rs.getInt("gender_id");
                    int schoolId = rs.getInt("school_id");
                    int loanTypeId = rs.getInt("loan_type_id");

                    loan.setId(id);
                    loan.setFirstName(firstName);
                    loan.setLastName(lastName);
                    loan.setEmail(email);
                    loan.setPassword(password);
                    loan.setBirthDate(loanDate);
                    loan.setCpf(cpf);
                    loan.setGenderId(genderId);
                    loan.setSchoolId(schoolId);
                    loan.setLoanTypeId(loanTypeId);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return loan;
        }
        */
    public boolean deleteAndDeliver(Loan loan) {
        String queryUpdateLoan = "UPDATE loan SET " +
                "active = 0, " +
                "deleted_reason = ? " +
                "WHERE id = ?";

        String queryUpdateBookAvailability = "UPDATE book SET " +
                "is_available = 1 " +
                "WHERE id = ?";

        String queryUpdateUserBookQuantity = "UPDATE user SET " +
                "books = books - 1 " +
                "WHERE id = ?";

        PreparedStatement p;

        try {
            p = this.connection.prepareStatement(queryUpdateLoan);
            p.setString(1, loan.getDeletedReason());
            p.setInt(2, loan.getId());
            p.executeUpdate();

            for (int bookId : loan.getBooksIds()) {
                // Atualiza disponibilidade do livro

                p = this.connection.prepareStatement(queryUpdateBookAvailability);

                p.setInt(1, bookId);

                p.executeUpdate();

                // Atualiza quantidade livros do estudante

                p = this.connection.prepareStatement(queryUpdateUserBookQuantity);

                p.setInt(1, loan.getStudentId());

                p.executeUpdate();
            }


        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;

        /*
        String queryInsertLoan = "INSERT INTO loan (" +
                "loan_date, " +
                "delivery_date, " +
                "user_id)" +
                " VALUE(?,?,?)";

        String queryInsertBookHasLoan = "INSERT INTO book_has_loan (" +
                "book_id, " +
                "loan_id)" +
                " VALUE(?,?)";

        String queryUpdateBookAvailability = "UPDATE book SET " +
                "is_available = 0 " +
                "WHERE id = ?";

        String queryUpdateUserBookQuantity = "UPDATE user SET " +
                "books = books + 1 " +
                "WHERE id = ?";

        PreparedStatement p;
        try {
            p = this.connection.prepareStatement(queryInsertLoan, Statement.RETURN_GENERATED_KEYS);

            p.setDate(1, loanDateSql);
            p.setDate(2, deliveryDateSql);
            p.setInt(3, loan.getStudentId());
            p.execute();

            ResultSet rs = p.getGeneratedKeys();

            if (rs.next()) {
                int loanId = rs.getInt(1);

                for (int bookId : loan.getBooksIds()) {
                    p = this.connection.prepareStatement(queryInsertBookHasLoan);

                    p.setInt(1, bookId);
                    p.setInt(2, loanId);

                    p.execute();

                    // Atualiza disponibilidade do livro

                    p = this.connection.prepareStatement(queryUpdateBookAvailability);

                    p.setInt(1, bookId);

                    p.executeUpdate();

                    // Atualiza quantidade livros do estudante

                    p = this.connection.prepareStatement(queryUpdateUserBookQuantity);

                    p.setInt(1, loan.getStudentId());

                    p.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
         */
    }
}
