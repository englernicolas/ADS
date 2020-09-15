package br.com.bibliotech.services;

import java.sql.*;
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

    public boolean create(User user) {
        Date birthDateSql = new Date(user.getBirthDate().getTime());

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

    public boolean edit(User user) {
        Date birthDateSql = new Date(user.getBirthDate().getTime());

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

    public List<User> list() {
        String query = "SELECT * FROM user WHERE active = 1";

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
                Date birthDate = rs.getDate("birth_date");
                String cpf = rs.getString("cpf");
                int genderId = rs.getInt("gender_id");
                int schoolId = rs.getInt("school_id");
                int userTypeId = rs.getInt("user_type_id");

                user.setId(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
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

    public boolean delete(User user) {
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
