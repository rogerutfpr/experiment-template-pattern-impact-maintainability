package br.edu.utfpr.dv.sireata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class TemplateDAO<T> {

	abstract String getSQLStringBuscar();

	public T buscarPorId(int id) throws SQLException{
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
	abstract List<T> popularList (ResultSet rs);

	public List<T> listarPorAta(int idAta) throws SQLException{
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
	abstract void popularRegistroComObjetoOperacaoInsert(PreparedStatement stmt, T entity);
	abstract void popularRegistroComObjetoOperacaoUpdate (PreparedStatement stmt, T entity);
	abstract int getId(T entity); 
	abstract void setId(T entity, int id);
	
	public int salvar(T entity) throws SQLException{
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
					this.setId(entity, rs.getInt(1));
				}
			}
			
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
	
	abstract T carregarObjeto(ResultSet rs) throws SQLException;
}