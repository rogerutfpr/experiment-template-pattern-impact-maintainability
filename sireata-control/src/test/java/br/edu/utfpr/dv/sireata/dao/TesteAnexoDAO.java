package br.edu.utfpr.dv.sireata.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.Test;

import br.edu.utfpr.dv.sireata.model.Anexo;
import br.edu.utfpr.dv.sireata.model.Ata;

public class TesteAnexoDAO {

    @Test
    public void testaSalvar() throws SQLException {
        Anexo anexo = new Anexo();
        anexo.setDescricao("descricao");
        anexo.setArquivo(new byte[] {1,2,3});
        anexo.setAta(new Ata());
        anexo.setOrdem(10);

        AnexoDAO dao = new AnexoDAO();

        dao.salvar(anexo);
    }

    @Test
    public void testaExcluir() throws SQLException {
        Anexo anexo = new Anexo();
        anexo.setDescricao("descricao");
        anexo.setArquivo(new byte[] {1,2,3});
        anexo.setAta(new Ata());
        anexo.setOrdem(10);

        AnexoDAO dao = new AnexoDAO();

        int id = dao.salvar(anexo);

        assertEquals(anexo.getDescricao(), dao.buscarPorId(id).getDescricao());

        dao.excluir(id);

        assertNull(dao.buscarPorId(id));
    }

    @Test
    public void testaBuscarPorId() throws SQLException {
        Anexo anexo = new Anexo();
        anexo.setDescricao("descricao");
        anexo.setArquivo(new byte[] {1,2,3});
        anexo.setAta(new Ata());
        anexo.setOrdem(10);

        AnexoDAO dao = new AnexoDAO();

        int id = dao.salvar(anexo);

        assertEquals(anexo.getDescricao(), dao.buscarPorId(id).getDescricao());
    }


    @Test
    public void testaListarPorAta() throws SQLException {
        Anexo anexo = new Anexo();
        anexo.setDescricao("descricao");
        anexo.setArquivo(new byte[] {1,2,3});
        anexo.setAta(new Ata());
        anexo.setOrdem(10);

        AnexoDAO dao = new AnexoDAO();

        int id = dao.salvar(anexo);

        assertNotEquals(0, dao.listarPorAta(0).size());
    }

    
}