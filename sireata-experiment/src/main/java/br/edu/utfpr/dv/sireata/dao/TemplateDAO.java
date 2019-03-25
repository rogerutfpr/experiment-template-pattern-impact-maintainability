package br.edu.utfpr.dv.sireata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.edu.utfpr.dv.sireata.model.TemplateEntity;

public abstract class TemplateDAO {

	abstract String getSQLStringBuscar();

	public TemplateEntity buscarPorId(int id) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = ConnectionDAO.getInstance().getConnection();
			stmt = conn.prepareStatement(this.getSQLStringBuscar());
		
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			
			if(rs.next()){
				return this.carregarObjeto(rs);
			}else{
				return null;
			}
		}finally{
			if((rs != null) && !rs.isClosed())
				rs.close();
			if((stmt != null) && !stmt.isClosed())
				stmt.close();
			if((conn != null) && !conn.isClosed())
				conn.close();
		}
	}
	
	abstract String getSQLStringListar(int idAta);
	abstract List<? extends TemplateEntity> popularList (ResultSet rs);

	public List<? extends TemplateEntity> listarPorAta(int idAta) throws SQLException{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = ConnectionDAO.getInstance().getConnection();
			stmt = conn.createStatement();
		
			rs = stmt.executeQuery(this.getSQLStringListar(idAta));
		
			return this.popularList(rs);
			
		}finally{
			if((rs != null) && !rs.isClosed())
				rs.close();
			if((stmt != null) && !stmt.isClosed())
				stmt.close();
			if((conn != null) && !conn.isClosed())
				conn.close();
		}
	}

	abstract String getSQLStringInsert();
	abstract String getSQLStringUpdate();
	abstract void popularRegistroComObjetoOperacaoInsert(PreparedStatement stmt, TemplateEntity entity);
	abstract void popularRegistroComObjetoOperacaoUpdate (PreparedStatement stmt, TemplateEntity entity);
	abstract int getId(TemplateEntity entity); 
	abstract void setId(TemplateEntity entity, int id);
	
	public int salvar(TemplateEntity entity) throws SQLException{
		boolean insert = (this.getId(entity) == 0);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = ConnectionDAO.getInstance().getConnection();
		
			if(insert){
				stmt = conn.prepareStatement(this.getSQLStringInsert(), Statement.RETURN_GENERATED_KEYS);
			}else{
				stmt = conn.prepareStatement(this.getSQLStringUpdate());
			}
			
			if(!insert)
				this.popularRegistroComObjetoOperacaoUpdate(stmt, entity);
			else
				this.popularRegistroComObjetoOperacaoInsert(stmt, entity);
			
			stmt.execute();
			
			if(insert){
				rs = stmt.getGeneratedKeys();
				
				if(rs.next()){
					// entity.setId(rs.getInt(1));
					this.setId(entity, rs.getInt(1));
				}
			}
			
			// return entity.getId();
			return this.getId(entity);
		}finally{
			if((rs != null) && !rs.isClosed())
				rs.close();
			if((stmt != null) && !stmt.isClosed())
				stmt.close();
			if((conn != null) && !conn.isClosed())
				conn.close();
		}
	}

	abstract String getSQLStringExcluir();
	
	public void excluir(int id) throws SQLException{
		Connection conn = null;
		Statement stmt = null;
		
		try{
			conn = ConnectionDAO.getInstance().getConnection();
			stmt = conn.createStatement();
		
			stmt.execute(this.getSQLStringExcluir() + String.valueOf(id));
		}finally{
			if((stmt != null) && !stmt.isClosed())
				stmt.close();
			if((conn != null) && !conn.isClosed())
				conn.close();
		}
	}
	
	abstract TemplateEntity carregarObjeto(ResultSet rs) throws SQLException;
}