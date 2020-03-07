package br.com.coldigogeladeiras.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.coldigogeladeiras.jdbcinterface.ProdutoDAO;
import br.com.coldigogeladeiras.modelo.Produto;
import com.google.gson.JsonObject;

public class JDBCProdutoDAO implements ProdutoDAO {
	private Connection conexao;
	
	public JDBCProdutoDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public boolean inserir(Produto produto) {
		String comando = "INSERT INTO produtos "
				+ "(categoria, modelo, capacidade, valor, marcas_id) "
				+ "VALUES (?,?,?,?,?)";
		PreparedStatement p;
		
		try {
			p = this.conexao.prepareStatement(comando);
			p.setInt(1, produto.getCategoria());
			p.setString(2, produto.getModelo());
			p.setInt(3, produto.getCapacidade());
			p.setFloat(4, produto.getValor());
			p.setInt(5, produto.getMarcaId());
			
			p.execute();
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<JsonObject> buscarPorNome(String nome) {
		String comando = "SELECT produtos.*, marcas.nome as marca FROM produtos "
				+ "INNER JOIN marcas ON produtos.marcas_id = marcas.id WHERE marcas.status = 1 ";

		if (!nome.equals("")){
			comando += "AND modelo LIKE '%" + nome + "%' ";
		}

		comando += "ORDER BY categoria ASC, marcas.nome ASC, modelo ASC";

		List<JsonObject> listaProdutos = new ArrayList<JsonObject>();
		JsonObject produto = null;

		try {
			Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);

			while (rs.next()) {
				int id = rs.getInt("id");
				String categoria = rs.getString("categoria");
				String modelo = rs.getString("modelo");
				int capacidade = rs.getInt("capacidade");
				float valor = rs.getFloat("valor");
				String marcaNome = rs.getString("marca");

				if (categoria.equals("1")) {
					categoria = "Geladeira";
				} else if (categoria.equals("2")){
					categoria = "Freezer";
				}

				produto = new JsonObject();
				produto.addProperty("id", id);
				produto.addProperty("categoria", categoria);
				produto.addProperty("modelo", modelo);
				produto.addProperty("capacidade", capacidade);
				produto.addProperty("valor", valor);
				produto.addProperty("marcaNome", marcaNome);

				listaProdutos.add(produto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaProdutos;
	}

	@Override
	public boolean deletar(int id) {
		String comando = "DELETE FROM produtos WHERE id = ?";
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setInt(1, id);
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Produto buscarPorId(int id) {
		String comando = "SELECT * FROM produtos WHERE produtos.id = ?";
		Produto produto = new Produto();
		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			while (rs.next()){
				String categoria = rs.getString("categoria");
				String modelo = rs.getString("modelo");
				int capacidade = rs.getInt("capacidade");
				float valor = rs.getFloat("valor");
				int marcaId = rs.getInt("marcas_id");

				produto.setId(id);
				produto.setCategoria(Integer.parseInt(categoria));
				produto.setMarcaId(marcaId);
				produto.setModelo(modelo);
				produto.setCapacidade(capacidade);
				produto.setValor(valor);

			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return produto;
	}

	@Override
	public boolean alterar(Produto produto) {
		String comando = "UPDATE produtos "
				+ "SET categoria=?, modelo=?, capacidade=?, valor=?, marcas_id=? "
				+ "WHERE id=?";
		PreparedStatement p;

		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, String.valueOf(produto.getCategoria()));
			p.setString(2, produto.getModelo());
			p.setInt(3, produto.getCapacidade());
			p.setFloat(4, produto.getValor());
			p.setInt(5, produto.getMarcaId());
			p.setInt(6, produto.getId());
			p.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean verificarExistenciaMarca(int idMarca) {
		String comando = "SELECT * FROM marcas WHERE id = ?";
		PreparedStatement p;

		try {
			p = this.conexao.prepareStatement(comando);
			p.setInt(1, idMarca);
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
	public List<JsonObject> listarProdutos() {
		String comando = "SELECT * FROM produtos";

		List<JsonObject> listaProdutos = new ArrayList<JsonObject>();
		JsonObject produto = null;

		try {
			Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);

			while (rs.next()) {
				int id = rs.getInt("id");
				String categoria = rs.getString("categoria");
				String modelo = rs.getString("modelo");
				int capacidade = rs.getInt("capacidade");
				float valor = rs.getFloat("valor");
				int marcaId = rs.getInt("marcas_Id");

				if (categoria.equals("1")) {
					categoria = "Geladeira";
				} else if (categoria.equals("2")){
					categoria = "Freezer";
				}

				produto = new JsonObject();
				produto.addProperty("id", id);
				produto.addProperty("categoria", categoria);
				produto.addProperty("modelo", modelo);
				produto.addProperty("capacidade", capacidade);
				produto.addProperty("valor", valor);
				produto.addProperty("marcaId", marcaId);

				listaProdutos.add(produto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaProdutos;
	}
}
