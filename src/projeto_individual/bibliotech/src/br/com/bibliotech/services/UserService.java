package br.com.bibliotech.services;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.bibliotech.auth.Base64Code;
import br.com.bibliotech.auth.JWTCode;
import br.com.bibliotech.auth.MD5Code;
import br.com.bibliotech.domains.User;
import br.com.bibliotech.dtos.UserPasswordDTO;
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

    public List<User> listStudents(Boolean needVerifyBookQuantity) {
        String query = "SELECT * FROM user WHERE active = 1 AND user_type_id = 3";

        if (needVerifyBookQuantity) {
            query += " AND books < 2";
        }

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

    public Boolean changePassword(UserPasswordDTO passwordInfo) {
        MD5Code md5Code = new MD5Code();
        Base64Code base64 = new Base64Code();

        String givenOldPassword = md5Code.encode(base64.decode(passwordInfo.getOldPassword()));
        String oldPassword = getUserPassword(passwordInfo.getUserId());
        String prepearedNewPassword = md5Code.encode(base64.decode(passwordInfo.getNewPassword()));
        
        if (!oldPassword.equals(givenOldPassword)) 
            return false;


        String query = "UPDATE user SET " +
                "password = ? " +
                "WHERE id = ?";

        PreparedStatement p;

        try {
            p = this.connection.prepareStatement(query);
            p.setString(1, prepearedNewPassword);
            p.setInt(2, passwordInfo.getUserId());
            p.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public String getUserPassword(int userId) {
        String query = "SELECT password FROM user WHERE id = ?";
        String password = "";
        
        try {
            PreparedStatement p = this.connection.prepareStatement(query);
            p.setInt(1, userId);
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                password = rs.getString("password");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return password;
    }
}
