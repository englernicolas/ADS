package br.com.coldigogeladeiras.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.coldigogeladeiras.jdbcinterface.MarcaDAO;
import br.com.coldigogeladeiras.modelo.Marca;
import com.google.gson.JsonObject;

public class JDBCMarcaDAO implements MarcaDAO {
    private Connection conexao;

    public JDBCMarcaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public List<Marca> carregarMarcas() {
        String comando = "SELECT * FROM marcas";

        List<Marca> listMarcas = new ArrayList<Marca>();
        Marca marca = null;

        try {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(comando);

            while (rs.next()) {
                marca = new Marca();

                int id = rs.getInt("id");
                String nome = rs.getString("nome");

                marca.setId(id);
                marca.setNome(nome);

                listMarcas.add(marca);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listMarcas;
    }

    @Override
    public boolean inserir(Marca marca) {
        String comando = "INSERT INTO marcas(nome) VALUE(?)";

        PreparedStatement p;
        try {
            p = this.conexao.prepareStatement(comando);
            p.setString(1, marca.getNome());
            p.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<JsonObject> buscarPorNome(String nome) {
        String comando = "SELECT * FROM marcas ";

        if (!nome.equals("")) {
            comando += "WHERE nome LIKE '%" + nome + "%' ";
        }

        comando += "ORDER BY nome ASC";

        List<JsonObject> listaMarcas = new ArrayList<JsonObject>();

        try {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(comando);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nomeMarca = rs.getString("nome");
                int status = rs.getInt("status");

                JsonObject marca = new JsonObject();
                marca.addProperty("id", id);
                marca.addProperty("nome", nomeMarca);
                marca.addProperty("status", status);

                listaMarcas.add(marca);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaMarcas;
    }

    @Override
    public boolean deletar(int id) {
        String comando = "DELETE FROM marcas WHERE id = ?";
        PreparedStatement p;
        try {
            p = this.conexao.prepareStatement(comando);
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
        String comando = "SELECT * FROM marcas WHERE marcas.id = ?";
        Marca marca = new Marca();
        try {
            PreparedStatement p = this.conexao.prepareStatement(comando);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            while (rs.next()){
                String nome = rs.getString("nome");

                marca.setId(id);
                marca.setNome(nome);

            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return marca;
    }

    @Override
    public boolean alterar(Marca marca) {
        String comando = "UPDATE marcas "
                + "SET nome=? "
                + "WHERE id=?";
        PreparedStatement p;

        try {
            p = this.conexao.prepareStatement(comando);
            p.setString(1, String.valueOf(marca.getNome()));
            p.setInt(2, marca.getId());
            p.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean verificarAtribuicaoMarcaProduto(int id) {
        String comando = "SELECT * FROM produtos WHERE marcas_id = ?";
        PreparedStatement p;

        try {
            p = this.conexao.prepareStatement(comando);
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
    public void ativarDesativar(Marca marca) {
        int status = 0;
        if (marca.getStatus() == 0) {
            status = 1;
        }

        String comando = "UPDATE marcas SET status=? WHERE id=?";

        PreparedStatement p;
        try {
            p = this.conexao.prepareStatement(comando);
            p.setInt(1, status);
            p.setInt(2, marca.getId());
            p.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<JsonObject> listarMarcas() {
        String comando = "SELECT * FROM marcas";

        List<JsonObject> listaMarcas = new ArrayList<JsonObject>();

        try {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(comando);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nomeMarca = rs.getString("nome");
                int status = rs.getInt("status");

                JsonObject marca = new JsonObject();
                marca.addProperty("id", id);
                marca.addProperty("nome", nomeMarca);
                marca.addProperty("status", status);

                listaMarcas.add(marca);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaMarcas;
    }
}
