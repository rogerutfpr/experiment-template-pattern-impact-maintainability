package br.edu.utfpr.dv.sireata.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

// Updated to use Derby (local) DB to simplify the 
// experimental execution. Note that this change does not 
// impact experimental tasks, but it just simplify the 
// experimental environment setup.

public class ConnectionDAO {

	private String connectionUrl = "jdbc:derby:memory:database;create=true";
	private Connection conn = null;

	private static ConnectionDAO connectionFactory = null;

	private ConnectionDAO() { }

	public Connection getConnection() throws SQLException {
		
		conn = DriverManager.getConnection(connectionUrl);

		DatabaseMetaData dbmd = conn.getMetaData();
		ResultSet rs = dbmd.getTables(null, null, "%", null);

		boolean tableExists = false;
		while (rs.next())
			if (rs.getString(3).equalsIgnoreCase("pautas"))
				tableExists = true;
			  

		if(!tableExists)
			createTables(); 

		return conn;
	}

	public static ConnectionDAO getInstance() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionDAO();
		}

		return connectionFactory;
	}

	private void createTables() {

		System.out.println("Creating tables ...");

		try {			
			conn.createStatement().executeUpdate(
					"CREATE TABLE pautas (" +
						"idpauta int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT IDPAUTA_PK PRIMARY KEY," +
						"idata int NOT NULL," +
						"ordem int NOT NULL," + 
						"titulo VARCHAR(255) NOT NULL," + 
						"descricao VARCHAR(255) NOT NULL)");

			conn.createStatement().executeUpdate(
					"CREATE TABLE anexos (" +
						"idanexo INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT IDANEXO_PK PRIMARY KEY," +
						"idata INT NOT NULL," +
						"descricao VARCHAR(50) NOT NULL," +
						"ordem INT NOT NULL," +
						"arquivo blob NOT NULL)");						

			// conn.createStatement().executeUpdate(
			// 		"CREATE TABLE usuarios (" +
			// 			"idusuario int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT IDUSUARIO_PK PRIMARY KEY," +
			// 			"nome varchar(100) NOT NULL," +
			// 			"login varchar(50) NOT NULL," +
			// 			"senha varchar(100) NOT NULL," +
			// 			"email varchar(100) NOT NULL," +
			// 			"externo int NOT NULL," +
			// 			"ativo int NOT NULL," +
			// 			"administrador int NOT NULL," +
			// 			")");								

			// conn.createStatement().executeUpdate(
			// 		"CREATE TABLE ataparticipantes (" +
			// 			"idataparticipante int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT IDATAPARTICIPANTE_PK PRIMARY KEY," +
			// 			"idata int NOT NULL," + 
			// 			"idusuario int NOT NULL," +
			// 			"designacao VARCHAR(50) NOT NULL," +
			// 			"presente int NOT NULL," +
			// 			"membro int NOT NULL," + 
			// 			"motivo VARCHAR(255) NOT NULL)");


				

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}