package br.com.bibliotech.services;

import br.com.bibliotech.domains.Gender;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GenderService {
    private Connection connection;

    public GenderService(Connection connection) {
        this.connection = connection;
    }

    public List<Gender> listGenders() {
        String query = "SELECT * FROM gender";

        List<Gender> genderList = new ArrayList<Gender>();
        Gender gender;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                gender = new Gender();

                int id = rs.getInt("id");
                String name = rs.getString("name");

                gender.setId(id);
                gender.setName(name);

                genderList.add(gender);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return genderList;
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
