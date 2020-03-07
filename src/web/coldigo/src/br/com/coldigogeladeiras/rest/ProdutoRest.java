package br.com.coldigogeladeiras.rest;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.coldigogeladeiras.jdbc.JDBCMarcaDAO;
import com.google.gson.Gson;

import br.com.coldigogeladeiras.bd.Conexao;
import br.com.coldigogeladeiras.jdbc.JDBCProdutoDAO;
import br.com.coldigogeladeiras.modelo.Produto;
import com.google.gson.JsonObject;

@Path("produto")
public class ProdutoRest extends UtilRest{
	@POST
	@Path("/inserir")
	@Consumes("application/*")
	public Response inserir(String produtoParam) {
		try {
			Produto produto = new Gson().fromJson(produtoParam, Produto.class);
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			
			JDBCProdutoDAO jdbcProduto = new JDBCProdutoDAO(conexao);
			boolean marcaExiste = jdbcProduto.verificarExistenciaMarca(produto.getMarcaId());

			String msg = "";
			if (marcaExiste) {
				boolean retorno = jdbcProduto.inserir(produto);
				if (retorno) {
					msg = "Produto cadastrado com sucesso!";
				} else {
					msg = "Erro ao cadastrar produto.";
				}
			} else {
				msg = "Erro ao cadastrar produto, a marca não existe! Tente recarregar a página.";
			}
			conec.fecharConexao();
			
			return this.buildResponse(msg);
		} catch(Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@GET
	@Path("/buscar")
	@Consumes("application/*")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorNome(@QueryParam("valorBusca") String nome){
		try {
			List<JsonObject> listaProdutos = new ArrayList<JsonObject>();

			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCProdutoDAO jdbcProduto = new JDBCProdutoDAO(conexao);
			listaProdutos = jdbcProduto.buscarPorNome(nome);
			conec.fecharConexao();

			String json = new Gson().toJson(listaProdutos);
			return this.buildResponse(json);

		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@DELETE
	@Path("/excluir/{id}")
	@Consumes("application/*")
	public Response excluir(@PathParam("id") int id){
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCProdutoDAO jdbcProdutoDAO = new JDBCProdutoDAO(conexao);

			boolean retorno = jdbcProdutoDAO.deletar(id);

			String msg = "";
			if (retorno){
				msg = "Produto excluído com sucesso!";
			} else {
				msg = "Erro ao excluir produto.";
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
			Produto produto = new Produto();
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCProdutoDAO jdbcProduto = new JDBCProdutoDAO(conexao);

			produto = jdbcProduto.buscarPorId(id);

			conec.fecharConexao();

			return this.buildResponse(produto);
		} catch (Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@PUT
	@Path("/alterar")
	@Consumes("application/*")
	public Response alterar(String produtoParam){
		try{
			Produto produto = new Gson().fromJson(produtoParam, Produto.class);
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCProdutoDAO jdbcProduto = new JDBCProdutoDAO(conexao);

			boolean retorno = jdbcProduto.alterar(produto);

			String msg = "";
			if (retorno){
				msg = "Produto alterado com sucesso!";
			} else {
				msg = "Erro ao alterar produto.";
			}

			conec.fecharConexao();
			return this.buildResponse(msg);
		} catch (Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@GET
	@Path("/listarProdutos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarProdutos() {
		try {
			List<JsonObject> listaProdutos = new ArrayList<JsonObject>();

			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCProdutoDAO jdbcProdutos = new JDBCProdutoDAO(conexao);
			listaProdutos = jdbcProdutos.listarProdutos();

			String json = new Gson().toJson(listaProdutos);

			return buildResponse(json);
		} catch (Exception e){
			e.printStackTrace();
			return buildErrorResponse(e.getMessage());
		}
	}
}
