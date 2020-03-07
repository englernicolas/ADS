package br.com.coldigogeladeiras.jdbcinterface;

import br.com.coldigogeladeiras.modelo.Produto;
import com.google.gson.JsonObject;

import java.util.List;

public interface ProdutoDAO {
	public boolean inserir(Produto produto);
	public List<JsonObject> buscarPorNome(String nome);
	public boolean deletar(int id);
	public Produto buscarPorId(int id);
	public boolean alterar(Produto produto);
	public boolean verificarExistenciaMarca(int idMarca);
	public List<JsonObject> listarProdutos();
}
