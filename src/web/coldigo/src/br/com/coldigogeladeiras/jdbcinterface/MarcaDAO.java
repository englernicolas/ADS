package br.com.coldigogeladeiras.jdbcinterface;

import java.util.List;

import br.com.coldigogeladeiras.modelo.Marca;
import br.com.coldigogeladeiras.modelo.Produto;
import com.google.gson.JsonObject;

public interface MarcaDAO {
	
	public List<Marca> carregarMarcas();
	public boolean inserir(Marca marca);
	public List<JsonObject> buscarPorNome(String nome);
	public boolean deletar(int id);
	public Marca buscarPorId(int id);
	public boolean alterar(Marca marca);
}
