package br.com.coldigogeladeiras.rest;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.coldigogeladeiras.bd.Conexao;
import br.com.coldigogeladeiras.jdbc.JDBCMarcaDAO;
import br.com.coldigogeladeiras.modelo.Marca;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


@Path("marca")
public class MarcaRest extends UtilRest{

	@GET
	@Path("/carregarMarcas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response carregarMarcas() {

		try {
			List<Marca> listaMarcas = new ArrayList<Marca>();

			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCMarcaDAO jdbcMarca = new JDBCMarcaDAO(conexao);
			listaMarcas = jdbcMarca.carregarMarcas();
			conec.fecharConexao();
			return this.buildResponse(listaMarcas);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}

	}

	@POST
	@Path("/inserir")
	@Consumes("application/*")
	public Response inserir(String marcaParam){
		try {
			Marca marca = new Gson().fromJson(marcaParam, Marca.class);

			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCMarcaDAO jdbcMarca = new JDBCMarcaDAO(conexao);
			boolean retorno = jdbcMarca.inserir(marca);

			String msg = "";

			if (retorno){
				msg = "Marca cadastrada com sucesso!";
			}else {
				msg = "Erro ao cadastrar marca.";
			}

			conec.fecharConexao();

			return this.buildResponse(msg);
		} catch (Exception e){
			e.printStackTrace();
			return buildErrorResponse(e.getMessage());
		}
	}

	@GET
	@Path("/buscar")
	@Consumes("application/*")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorNome(@QueryParam("valorBusca") String nome){
		try {
			List<JsonObject> listaMarcas = new ArrayList<JsonObject>();

			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCMarcaDAO jdbcMarca = new JDBCMarcaDAO(conexao);
			listaMarcas = jdbcMarca.buscarPorNome(nome);

			String json = new Gson().toJson(listaMarcas);

			return buildResponse(json);
		} catch (Exception e){
			e.printStackTrace();
			return buildErrorResponse(e.getMessage());
		}
	}

	@DELETE
	@Path("/excluir/{id}")
	@Consumes("application/*")
	public Response excluir(@PathParam("id") int id){
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCMarcaDAO jdbcMarcaDAO = new JDBCMarcaDAO(conexao);

			boolean marcaAtribuidaProduto = jdbcMarcaDAO.verificarAtribuicaoMarcaProduto(id);

			String msg = "";
			if (!marcaAtribuidaProduto) {
				boolean retorno = jdbcMarcaDAO.deletar(id);

				if (retorno) {
					msg = "Marca excluída com sucesso!";
				} else {
					msg = "Erro ao excluir marca.";
				}
			} else {
				msg = "Essa marca está atribuída a um produto já cadastrado, portanto não é possível excluí-la";
			}
			return this.buildResponse(msg);
		} catch (Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@GET
	@Path("/buscarPorId")
	@Consumes("appplication/*")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorId(@QueryParam("id") int id){
		try {
			Marca marca = new Marca();
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCMarcaDAO jdbcMarca = new JDBCMarcaDAO(conexao);

			marca = jdbcMarca.buscarPorId(id);

			conec.fecharConexao();

			return this.buildResponse(marca);
		} catch (Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@PUT
	@Path("/alterar")
	@Consumes("application/*")
	public Response alterar(String marcaParam){
		try{
			Marca marca = new Gson().fromJson(marcaParam, Marca.class);
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCMarcaDAO jdbcMarca = new JDBCMarcaDAO(conexao);

			boolean retorno = jdbcMarca.alterar(marca);

			String msg = "";
			if (retorno){
				msg = "Marca alterado com sucesso!";
			} else {
				msg = "Erro ao alterar marca.";
			}

			conec.fecharConexao();
			return this.buildResponse(msg);
		} catch (Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@PUT
	@Path("/ativarDesativar")
	@Consumes("application/*")
	public Response ativarDesativar(String marcaJson){
		try{
			Marca marca = new Gson().fromJson(marcaJson, Marca.class);
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCMarcaDAO jdbcMarca = new JDBCMarcaDAO(conexao);

			jdbcMarca.ativarDesativar(marca);

			conec.fecharConexao();

			return buildResponse("deu certo");
		} catch (Exception e){
			e.printStackTrace();
			return buildErrorResponse(e.getMessage());
		}
	}

	@GET
	@Path("/listarMarcas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarMarcas() {
		try {
			List<JsonObject> listaMarcas = new ArrayList<JsonObject>();

			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCMarcaDAO jdbcMarca = new JDBCMarcaDAO(conexao);
			listaMarcas = jdbcMarca.listarMarcas();

			String json = new Gson().toJson(listaMarcas);

			return buildResponse(json);
		} catch (Exception e){
			e.printStackTrace();
			return buildErrorResponse(e.getMessage());
		}
	}
}
