package br.edu.utfpr.dv.sireata.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.Test;

import br.edu.utfpr.dv.sireata.model.Ata;
import br.edu.utfpr.dv.sireata.model.AtaParticipante;
import br.edu.utfpr.dv.sireata.model.Usuario;

public class TesteAtaParticipanteDAO {
    
    @Test
    public void testaSalvar() throws SQLException {
        AtaParticipante ataParticipante = new AtaParticipante();
        ataParticipante.setAta(new Ata());
        ataParticipante.setDesignacao("designação");
        ataParticipante.setMembro(true);
        ataParticipante.setMotivo("motivo");
        ataParticipante.setParticipante(new Usuario());
        ataParticipante.setPresente(true);
        
        AtaParticipanteDAO dao = new AtaParticipanteDAO();

        int id = dao.salvar(ataParticipante);

        assertEquals(ataParticipante.getDesignacao(), dao.buscarPorId(id).getDesignacao());
    }

    @Test
    public void testaExcluir() throws SQLException {
        AtaParticipante ataParticipante = new AtaParticipante();
        ataParticipante.setAta(new Ata());
        ataParticipante.setDesignacao("designação");
        ataParticipante.setMembro(true);
        ataParticipante.setMotivo("motivo");
        ataParticipante.setParticipante(new Usuario());
        ataParticipante.setPresente(true);
        
        AtaParticipanteDAO dao = new AtaParticipanteDAO();

        int id = dao.salvar(ataParticipante);

        assertEquals(ataParticipante.getDesignacao(), dao.buscarPorId(id).getDesignacao());

        dao.excluir(id);

        assertNull(dao.buscarPorId(id));
    }

    @Test
    public void testaBuscarPorId() throws SQLException {

        AtaParticipante ataParticipante = new AtaParticipante();
        ataParticipante.setAta(new Ata());
        ataParticipante.setDesignacao("designação");
        ataParticipante.setMembro(true);
        ataParticipante.setMotivo("motivo");
        ataParticipante.setParticipante(new Usuario());
        ataParticipante.setPresente(true);
        
        AtaParticipanteDAO dao = new AtaParticipanteDAO();

        int id = dao.salvar(ataParticipante);

        assertEquals(ataParticipante.getDesignacao(), dao.buscarPorId(id).getDesignacao());      
    }


    @Test
    public void testaListarPorAta() throws SQLException {
        AtaParticipante ataParticipante = new AtaParticipante();
        ataParticipante.setAta(new Ata());
        ataParticipante.setDesignacao("designação");
        ataParticipante.setMembro(true);
        ataParticipante.setMotivo("motivo");
        ataParticipante.setParticipante(new Usuario());
        ataParticipante.setPresente(true);
        
        AtaParticipanteDAO dao = new AtaParticipanteDAO();

        int id = dao.salvar(ataParticipante);

        assertNotEquals(0, dao.listarPorAta(0).size());
    }    
    
}