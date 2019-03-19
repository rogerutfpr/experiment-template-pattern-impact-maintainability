package br.edu.utfpr.dv.sireata.dao;

import java.sql.SQLException;

import org.junit.Test;

import br.edu.utfpr.dv.sireata.model.Anexo;
import br.edu.utfpr.dv.sireata.model.Ata;

public class TesteAnexoDAO {

    @Test
    public void testaSalvar() throws SQLException {
        Anexo anexo = new Anexo();
        anexo.setIdAnexo(10);
        anexo.setDescricao("descricao");
        anexo.setArquivo(new byte[] {1,2,3});
        anexo.setAta(new Ata());
        anexo.setOrdem(10);

        AnexoDAO dao = new AnexoDAO();

        dao.salvar(anexo);
        
    }

    
}