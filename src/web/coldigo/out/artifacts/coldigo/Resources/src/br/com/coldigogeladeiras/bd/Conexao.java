package br.com.coldigogeladeiras.bd;

import java.sql.Connection;

public class Conexao {
	private Connection conexao;

	public Connection abrirConexao() {
		String password = "123";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conexao = java.sql.DriverManager.getConnection("jdbc:mysql://localhost/bdcoldigo?"
					+ "user=root&password="+password+"&useTimezone=true&serverTimezone=UTC");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conexao;
	}
	
	public void fecharConexao() {
		try {
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
