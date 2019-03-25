package br.edu.utfpr.dv.sireata.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.dv.sireata.model.Pauta;
import br.edu.utfpr.dv.sireata.model.TemplateEntity;

public class PautaDAO extends TemplateDAO {
	
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
	void popularRegistroComObjetoOperacaoInsert(PreparedStatement stmt, TemplateEntity entity) {

		Pauta pauta = (Pauta) entity;

		try {
			stmt.setInt(1, pauta.getAta().getIdAta());
			stmt.setInt(2, pauta.getOrdem());
			stmt.setString(3, pauta.getTitulo());
			stmt.setString(4, pauta.getDescricao());
	
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	void popularRegistroComObjetoOperacaoUpdate(PreparedStatement stmt, TemplateEntity entity) {

		Pauta pauta = (Pauta) entity;

		try {
			stmt.setInt(1, pauta.getAta().getIdAta());
			stmt.setInt(2, pauta.getOrdem());
			stmt.setString(3, pauta.getTitulo());
			stmt.setString(4, pauta.getDescricao());
			stmt.setInt(5, pauta.getId());
	
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}
	
	@Override
	String getSQLStringExcluir() {
		return "DELETE FROM pautas WHERE idPauta=";
	}
	
	@Override
	Pauta carregarObjeto(ResultSet rs) throws SQLException{
		Pauta pauta = new Pauta();
		
		pauta.setId(rs.getInt("idPauta"));
		pauta.getAta().setIdAta(rs.getInt("idAta"));
		pauta.setOrdem(rs.getInt("ordem"));
		pauta.setTitulo(rs.getString("titulo"));
		pauta.setDescricao(rs.getString("descricao"));
		
		return pauta;
	}

}
