package br.edu.utfpr.dv.sireata.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.utfpr.dv.sireata.model.Anexo;
import br.edu.utfpr.dv.sireata.model.Ata;

public class TesteAnexoDAO {

    private static Anexo anexo;
    private static AnexoDAO dao;

    @BeforeClass
    public static void setup() {
        anexo = new Anexo();
        anexo.setDescricao("descricao");
        anexo.setArquivo(new byte[] {1,2,3});
        anexo.setAta(new Ata());
        anexo.setOrdem(10);

        dao = new AnexoDAO();
    }

    @Test
    public void testaSalvar() throws SQLException {
        dao.salvar(anexo);
    }

    @Test
    public void testaBuscarPorId() throws SQLException {
        int id = dao.salvar(anexo);

        assertEquals(anexo.getDescricao(), dao.buscarPorId(id).getDescricao());
    }

    @Test
    public void testaExcluir() throws SQLException {
        int id = dao.salvar(anexo);

        assertEquals(anexo.getDescricao(), dao.buscarPorId(id).getDescricao());

        dao.excluir(id);

        assertNull(dao.buscarPorId(id));
    }

    
}