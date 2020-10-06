package br.com.bibliotech.services;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.bibliotech.domains.User;
import com.google.gson.JsonObject;

import br.com.bibliotech.domains.User;

public class UserService {
    private Connection connection;

    public UserService(Connection connection) {
        this.connection = connection;
    }

    public boolean createStudent(User user) throws ParseException {
        java.util.Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(user.getBirthDate());
        Date birthDateSql = new Date(birthDate.getTime());

        String query = "INSERT INTO `user`(" +
                "first_name, " +
                "last_name, " +
                "email, " +
                "password, " +
                "birth_date, " +
                "cpf, " +
                "gender_id, " +
                "school_id, " +
                "user_type_id" +
                ") VALUE(?,?,?,?,?,?,?,?,3)";

        PreparedStatement p;
        try {
            p = this.connection.prepareStatement(query);
            p.setString(1, user.getFirstName());
            p.setString(2, user.getLastName());
            p.setString(3, user.getEmail());
            p.setString(4, user.getPassword());
            p.setDate(5, birthDateSql);
            p.setString(6, user.getCpf());
            p.setInt(7, user.getGenderId());
            p.setInt(8, user.getSchoolId());
            p.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean editStudent(User user) throws ParseException {
        java.util.Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(user.getBirthDate());
        Date birthDateSql = new Date(birthDate.getTime());

        String query = "UPDATE user SET " +
                "first_name = ?, " +
                "last_name = ?, " +
                "email = ?, " +
                "password = ?, " +
                "birth_date = ?, " +
                "cpf = ?, " +
                "gender_id = ?, " +
                "school_id = ? " +
                "WHERE id = ?";

        PreparedStatement p;

        try {
            p = this.connection.prepareStatement(query);
            p.setString(1, user.getFirstName());
            p.setString(2, user.getLastName());
            p.setString(3, user.getEmail());
            p.setString(4, user.getPassword());
            p.setDate(5, birthDateSql);
            p.setString(6, user.getCpf());
            p.setInt(7, user.getGenderId());
            p.setInt(8, user.getSchoolId());
            p.setInt(9, user.getId());
            p.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<User> listStudents() {
        String query = "SELECT * FROM user WHERE active = 1 AND user_type_id = 3";

        List<User> studentList = new ArrayList<User>();
        User user;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                user = new User();

                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String birthDate = rs.getDate("birth_date").toString();
                String cpf = rs.getString("cpf");
                int genderId = rs.getInt("gender_id");
                int schoolId = rs.getInt("school_id");
                int userTypeId = rs.getInt("user_type_id");

                user.setId(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPassword(password);
                user.setBirthDate(birthDate);
                user.setCpf(cpf);
                user.setGenderId(genderId);
                user.setSchoolId(schoolId);
                user.setUserTypeId(userTypeId);

                studentList.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return studentList;
    }

    public List<User> getStudentBySearch(String searchText) {
        String query = "SELECT * FROM user WHERE active = 1 AND user_type_id = 3 ";

        if (!searchText.trim().equals("")) {
            query += "AND (first_name LIKE '%" + searchText + "%' " +
                    "OR last_name LIKE '%" + searchText + "%' " +
                    "OR email LIKE '%" + searchText + "%')";
        }

        List<User> studentList = new ArrayList<User>();

        try {
            PreparedStatement p = this.connection.prepareStatement(query);
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                User user = new User();

                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String birthDate = rs.getDate("birth_date").toString();
                String cpf = rs.getString("cpf");
                int genderId = rs.getInt("gender_id");
                int schoolId = rs.getInt("school_id");
                int userTypeId = rs.getInt("user_type_id");

                user.setId(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPassword(password);
                user.setBirthDate(birthDate);
                user.setCpf(cpf);
                user.setGenderId(genderId);
                user.setSchoolId(schoolId);
                user.setUserTypeId(userTypeId);

                studentList.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return studentList;
    }

    public User getStudentById(int studentId) {
        String query = "SELECT * FROM user WHERE id = ?";

        User user = new User();

        try {
            PreparedStatement p = this.connection.prepareStatement(query);
            p.setInt(1, studentId);
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String birthDate = rs.getDate("birth_date").toString();
                String cpf = rs.getString("cpf");
                int genderId = rs.getInt("gender_id");
                int schoolId = rs.getInt("school_id");
                int userTypeId = rs.getInt("user_type_id");

                user.setId(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPassword(password);
                user.setBirthDate(birthDate);
                user.setCpf(cpf);
                user.setGenderId(genderId);
                user.setSchoolId(schoolId);
                user.setUserTypeId(userTypeId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return user;
    }

    public boolean deleteStudent(User user) {
        String query = "UPDATE user SET " +
                "active = 0, " +
                "deleted_reason = ? " +
                "WHERE id = ?";

        PreparedStatement p;

        try {
            p = this.connection.prepareStatement(query);
            p.setString(1, user.getDeletedReason());
            p.setInt(2, user.getId());
            p.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createLibrarian(User user) throws ParseException {
        java.util.Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(user.getBirthDate());
        Date birthDateSql = new Date(birthDate.getTime());

        String query = "INSERT INTO `user`(" +
                "first_name, " +
                "last_name, " +
                "email, " +
                "password, " +
                "birth_date, " +
                "cpf, " +
                "gender_id, " +
                "school_id, " +
                "user_type_id" +
                ") VALUE(?,?,?,?,?,?,?,?,2)";

        PreparedStatement p;
        try {
            p = this.connection.prepareStatement(query);
            p.setString(1, user.getFirstName());
            p.setString(2, user.getLastName());
            p.setString(3, user.getEmail());
            p.setString(4, user.getPassword());
            p.setDate(5, birthDateSql);
            p.setString(6, user.getCpf());
            p.setInt(7, user.getGenderId());
            p.setInt(8, user.getSchoolId());
            p.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean editLibrarian(User user) throws ParseException {
        java.util.Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(user.getBirthDate());
        Date birthDateSql = new Date(birthDate.getTime());

        String query = "UPDATE user SET " +
                "first_name = ?, " +
                "last_name = ?, " +
                "email = ?, " +
                "password = ?, " +
                "birth_date = ?, " +
                "cpf = ?, " +
                "gender_id = ?, " +
                "school_id = ? " +
                "WHERE id = ?";

        PreparedStatement p;

        try {
            p = this.connection.prepareStatement(query);
            p.setString(1, user.getFirstName());
            p.setString(2, user.getLastName());
            p.setString(3, user.getEmail());
            p.setString(4, user.getPassword());
            p.setDate(5, birthDateSql);
            p.setString(6, user.getCpf());
            p.setInt(7, user.getGenderId());
            p.setInt(8, user.getSchoolId());
            p.setInt(9, user.getId());
            p.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<User> listLibrarians() {
        String query = "SELECT * FROM user WHERE active = 1 AND user_type_id = 2";

        List<User> librarianList = new ArrayList<User>();
        User user;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                user = new User();

                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String birthDate = rs.getDate("birth_date").toString();
                String cpf = rs.getString("cpf");
                int genderId = rs.getInt("gender_id");
                int schoolId = rs.getInt("school_id");
                int userTypeId = rs.getInt("user_type_id");

                user.setId(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPassword(password);
                user.setBirthDate(birthDate);
                user.setCpf(cpf);
                user.setGenderId(genderId);
                user.setSchoolId(schoolId);
                user.setUserTypeId(userTypeId);

                librarianList.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return librarianList;
    }

    public List<User> getLibrarianBySearch(String searchText) {
        String query = "SELECT * FROM user WHERE active = 1 AND user_type_id = 2 ";

        if (!searchText.trim().equals("")) {
            query += "AND (first_name LIKE '%" + searchText + "%' " +
                    "OR last_name LIKE '%" + searchText + "%' " +
                    "OR email LIKE '%" + searchText + "%')";
        }

        List<User> librarianList = new ArrayList<User>();

        try {
            PreparedStatement p = this.connection.prepareStatement(query);
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                User user = new User();

                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String birthDate = rs.getDate("birth_date").toString();
                String cpf = rs.getString("cpf");
                int genderId = rs.getInt("gender_id");
                int schoolId = rs.getInt("school_id");
                int userTypeId = rs.getInt("user_type_id");

                user.setId(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPassword(password);
                user.setBirthDate(birthDate);
                user.setCpf(cpf);
                user.setGenderId(genderId);
                user.setSchoolId(schoolId);
                user.setUserTypeId(userTypeId);

                librarianList.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return librarianList;
    }

    public User getLibrarianById(int librarianId) {
        String query = "SELECT * FROM user WHERE id = ?";

        User user = new User();

        try {
            PreparedStatement p = this.connection.prepareStatement(query);
            p.setInt(1, librarianId);
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String birthDate = rs.getDate("birth_date").toString();
                String cpf = rs.getString("cpf");
                int genderId = rs.getInt("gender_id");
                int schoolId = rs.getInt("school_id");
                int userTypeId = rs.getInt("user_type_id");

                user.setId(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPassword(password);
                user.setBirthDate(birthDate);
                user.setCpf(cpf);
                user.setGenderId(genderId);
                user.setSchoolId(schoolId);
                user.setUserTypeId(userTypeId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return user;
    }

    public boolean deleteLibrarian(User user) {
        String query = "UPDATE user SET " +
                "active = 0, " +
                "deleted_reason = ? " +
                "WHERE id = ?";

        PreparedStatement p;

        try {
            p = this.connection.prepareStatement(query);
            p.setString(1, user.getDeletedReason());
            p.setInt(2, user.getId());
            p.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*@Override
    public List<JsonObject> buscarPorNome(String nome) {
        String query = "SELECT * FROM marcas ";

        if (!nome.equals("")) {
            query += "WHERE nome LIKE '%" + nome + "%' ";
        }

        query += "ORDER BY nome ASC";

        List<JsonObject> listaMarcas = new ArrayList<JsonObject>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nomeMarca = rs.getString("nome");
                int status = rs.getInt("status");

                JsonObject user = new JsonObject();
                user.addProperty("id", id);
                user.addProperty("nome", nomeMarca);
                user.addProperty("status", status);

                listaMarcas.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaMarcas;
    }

    @Override
    public boolean deletar(int id) {
        String query = "DELETE FROM marcas WHERE id = ?";
        PreparedStatement p;
        try {
            p = this.connection.prepareStatement(query);
            p.setInt(1, id);
            p.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            e.getErrorCode();
            return false;
        }
        return true;
    }

    @Override
    public Marca buscarPorId(int id) {
        String query = "SELECT * FROM marcas WHERE marcas.id = ?";
        Marca user = new Marca();
        try {
            PreparedStatement p = this.connection.prepareStatement(query);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            while (rs.next()){
                String nome = rs.getString("nome");

                user.setId(id);
                user.setNome(nome);

            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean alterar(Marca user) {
        String query = "UPDATE marcas "
                + "SET nome=? "
                + "WHERE id=?";
        PreparedStatement p;

        try {
            p = this.connection.prepareStatement(query);
            p.setString(1, String.valueOf(user.getNome()));
            p.setInt(2, user.getId());
            p.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean verificarAtribuicaoMarcaProduto(int id) {
        String query = "SELECT * FROM produtos WHERE marcas_id = ?";
        PreparedStatement p;

        try {
            p = this.connection.prepareStatement(query);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();

            if (rs.next()) {
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public void ativarDesativar(Marca user) {
        int status = 0;
        if (user.getStatus() == 0) {
            status = 1;
        }

        String query = "UPDATE marcas SET status=? WHERE id=?";

        PreparedStatement p;
        try {
            p = this.connection.prepareStatement(query);
            p.setInt(1, status);
            p.setInt(2, user.getId());
            p.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<JsonObject> listarMarcas() {
        String query = "SELECT * FROM marcas";

        List<JsonObject> listaMarcas = new ArrayList<JsonObject>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nomeMarca = rs.getString("nome");
                int status = rs.getInt("status");

                JsonObject user = new JsonObject();
                user.addProperty("id", id);
                user.addProperty("nome", nomeMarca);
                user.addProperty("status", status);

                listaMarcas.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaMarcas;
    }*/
}
