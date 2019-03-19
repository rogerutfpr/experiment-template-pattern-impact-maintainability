package br.edu.utfpr.dv.sireata.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Updated to use Derby (in memory) DB to simplify the 
// experimental execution. Note that this change does not 
// impact experimental tasks, but it just simplify the 
// experimental environment setup.

public class ConnectionDAO {

	private static ConnectionDAO instance = null;
	private Connection conn;
	
	private ConnectionDAO() { }
	
	public static synchronized ConnectionDAO getInstance() throws SQLException{
		if(instance == null){
			instance = new ConnectionDAO();
			instance.createDataSource();
		}
		
		return instance;
	}
	
	public Connection getConnection() throws SQLException{
        return conn;
	}
	
	private void createDataSource() {

		// Create tables in the database
		try {
			conn = DriverManager.getConnection("jdbc:derby:database;create=true"); 
			conn.createStatement().executeUpdate(
					"CREATE TABLE pautas (" +
						"idpauta int NOT NULL," +
						"idata int NOT NULL," +
						"ordem int NOT NULL," + 
						"titulo VARCHAR(255) NOT NULL," + 
						"descricao VARCHAR(255) NOT NULL)");
					// 	," +
					// 	"PRIMARY KEY (idpauta)," +
					// 	"KEY fk_pauta_ata_idx (idata)," + 
					// 	"CONSTRAINT fk_pauta_ata FOREIGN KEY (idata) REFERENCES atas (idata)"+ 
					// 	"ON DELETE NO ACTION ON UPDATE NO ACTION" + 
					// ");");

			conn.createStatement().executeUpdate(
					"CREATE TABLE ataparticipantes (" +
						"idataparticipante int NOT NULL," +
						"idata int NOT NULL," + 
						"idusuario int NOT NULL," +
						"designacao VARCHAR(50) NOT NULL," +
						"presente int NOT NULL," +
						"membro int NOT NULL," + 
						"motivo VARCHAR(255) NOT NULL)");
					// 	,"+ 
					// 	"PRIMARY KEY (idataparticipante)," +
					// 	"KEY fk_ataparticipantes_ata_idx (idata)," +
					// 	"CONSTRAINT fk_ataparticipantes_ata FOREIGN KEY (idata) REFERENCES atas (idata) ON DELETE NO ACTION" + 
					// 	"ON UPDATE NO ACTION," +
					// 	"KEY fk_ataparticipantes_usuario_idx (idusuario)," +
					// 	"CONSTRAINT fk_ataparticipantes_usuario FOREIGN KEY (idusuario) REFERENCES usuarios (idusuario) ON" + 
					// 	"DELETE NO ACTION ON UPDATE NO ACTION" +
					// ");");

			conn.createStatement().executeUpdate(
					"CREATE TABLE anexos (" +
						"idanexo INT NOT NULL," +
						"idata INT NOT NULL," +
						"descricao VARCHAR(50) NOT NULL," +
						"ordem INT NOT NULL," +
						"arquivo blob NOT NULL)");
					// 	," +
					// 	"PRIMARY KEY (idanexo)," +
					// 	"CONSTRAINT fk_anexos_ata FOREIGN KEY (idata) REFERENCES atas (idata) ON DELETE NO ACTION ON UPDATE"+ "NO ACTION," +
					// 	"KEY fk_anexos_ata_idx (idata)" +
					// ");");

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}