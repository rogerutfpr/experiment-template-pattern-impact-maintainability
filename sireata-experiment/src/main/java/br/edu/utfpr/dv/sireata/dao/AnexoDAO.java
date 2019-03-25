package br.edu.utfpr.dv.sireata.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.dv.sireata.model.Anexo;
import br.edu.utfpr.dv.sireata.model.TemplateEntity;

public class AnexoDAO extends TemplateDAO {

	@Override
	String getSQLStringBuscar() {
		return "SELECT anexos.* FROM anexos " +
					"WHERE idAnexo = ?";
	}
	
	@Override
	String getSQLStringListar(int idAta) {
		return 	"SELECT anexos.* FROM anexos " +
		"WHERE idAta=" + String.valueOf(idAta) + " ORDER BY anexos.ordem";
	}

	@Override
	List<Anexo> popularList(ResultSet rs) {
		List<Anexo> list = new ArrayList<Anexo>();
			
		try {
			while(rs.next()){
				list.add(this.carregarObjeto(rs));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return list;
	}

	@Override
	String getSQLStringInsert() {
		return "INSERT INTO anexos(idAta, ordem, descricao, arquivo) VALUES(?, ?, ?, ?)";
	}

	@Override
	String getSQLStringUpdate() {
		return "UPDATE anexos SET idAta=?, ordem=?, descricao=?, arquivo=? WHERE idAnexo=?";
	}

	@Override
	void popularRegistroComObjetoOperacaoInsert(PreparedStatement stmt, TemplateEntity entity) {

		Anexo anexo = (Anexo) entity;

		try {
			stmt.setInt(1, anexo.getAta().getIdAta());
			stmt.setInt(2, anexo.getOrdem());
			stmt.setString(3, anexo.getDescricao());
			stmt.setBytes(4, anexo.getArquivo());
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	int getId(TemplateEntity entity) {
		Anexo anexo = (Anexo) entity;
		return anexo.getIdAnexo();
	}

	void setId(TemplateEntity entity, int id) {
		Anexo anexo = (Anexo) entity;
		anexo.setIdAnexo(id);
	}

	@Override
	void popularRegistroComObjetoOperacaoUpdate(PreparedStatement stmt, TemplateEntity entity) {
		Anexo anexo = (Anexo) entity;

		try {
			stmt.setInt(1, anexo.getAta().getIdAta());
			stmt.setInt(2, anexo.getOrdem());
			stmt.setString(3, anexo.getDescricao());
			stmt.setBytes(4, anexo.getArquivo());
			stmt.setInt(5, anexo.getIdAnexo()); 

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	Anexo carregarObjeto(ResultSet rs) throws SQLException{
		Anexo anexo = new Anexo();
		
		anexo.setIdAnexo(rs.getInt("idAnexo"));
		anexo.getAta().setIdAta(rs.getInt("idAta"));
		anexo.setDescricao(rs.getString("descricao"));
		anexo.setOrdem(rs.getInt("ordem"));
		anexo.setArquivo(rs.getBytes("arquivo"));
		
		return anexo;
	}

	@Override
	String getSQLStringExcluir() {
		return "DELETE FROM anexos WHERE idanexo=";
	}
}
