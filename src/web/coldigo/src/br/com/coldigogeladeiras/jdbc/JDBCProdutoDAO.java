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
				+ "INNER JOIN marcas ON produtos.marcas_id = marcas.id ";

		if (!nome.equals("")){
			comando += "WHERE modelo LIKE '%" + nome + "%' ";
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
}