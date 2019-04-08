package br.edu.utfpr.dv.sireata.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.dv.sireata.model.Pauta;

public class PautaDAO extends TemplateDAO<Pauta> {
	
	@Override
	String getSQLStringBuscar() {
		return "SELECT * FROM pautas WHERE idPauta = ?";
	}

	@Override
	String getSQLStringListar(int idAta) {
		return "SELECT * FROM pautas WHERE idAta=" + String.valueOf(idAta) + " ORDER BY ordem";
	}

	@Override
	List<Pauta> popularList(ResultSet rs) {
		List<Pauta> list = new ArrayList<Pauta>();
			
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
		return "INSERT INTO pautas(idAta, ordem, titulo, descricao) VALUES(?, ?, ?, ?)";
	}

	@Override
	String getSQLStringUpdate() {
		return "UPDATE pautas SET idAta=?, ordem=?, titulo=?, descricao=? WHERE id=?";
	}

	@Override
	void popularRegistroComObjetoOperacaoInsert(PreparedStatement stmt, Pauta entity) {

		try {
			stmt.setInt(1, entity.getAta().getIdAta());
			stmt.setInt(2, entity.getOrdem());
			stmt.setString(3, entity.getTitulo());
			stmt.setString(4, entity.getDescricao());
	
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	void popularRegistroComObjetoOperacaoUpdate(PreparedStatement stmt, Pauta entity) {

		try {
			stmt.setInt(1, entity.getAta().getIdAta());
			stmt.setInt(2, entity.getOrdem());
			stmt.setString(3, entity.getTitulo());
			stmt.setString(4, entity.getDescricao());
			stmt.setInt(5, entity.getIdPauta());
	
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}
	
	int getId(Pauta entity) {
		return entity.getIdPauta();
	}

	void setId(Pauta entity, int id) {
		entity.setIdPauta(id);
	}

	@Override
	String getSQLStringExcluir() {
		return "DELETE FROM pautas WHERE idPauta=";
	}
	
	@Override
	Pauta carregarObjeto(ResultSet rs) throws SQLException{
		Pauta pauta = new Pauta();
		
		pauta.setIdPauta(rs.getInt("idPauta"));
		pauta.getAta().setIdAta(rs.getInt("idAta"));
		pauta.setOrdem(rs.getInt("ordem"));
		pauta.setTitulo(rs.getString("titulo"));
		pauta.setDescricao(rs.getString("descricao"));
		
		return pauta;
	}

}
