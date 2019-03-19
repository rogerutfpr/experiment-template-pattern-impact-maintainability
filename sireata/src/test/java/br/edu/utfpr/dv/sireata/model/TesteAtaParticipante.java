package br.edu.utfpr.dv.sireata.model;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class TesteAtaParticipante {

    private static AtaParticipante ata;

    @BeforeClass
    public static void setup() {
        ata = new AtaParticipante();

        ata.setIdAtaParticipante(10);
        ata.setAta(new Ata());
        ata.setParticipante(new Usuario());
        ata.setPresente(true);
        ata.setMotivo("motivo");
        ata.setDesignacao("designação");
        ata.setMembro(true);
    }

    @Test
    public void testaIdAtaParticipante() {
        assertEquals(10, ata.getIdAtaParticipante());
    }

    @Test
    public void testaAta() {
        assertEquals(new Ata().getClass().getName(), ata.getAta().getClass().getName());
    }

    @Test
    public void testaUsuario() {
        assertEquals(new Usuario().getClass().getName(), ata.getParticipante().getClass().getName());
    }

    @Test
    public void testaPresente() {
        assertEquals(true, ata.isPresente());
    }

    @Test
    public void testaMotivo() {
        assertEquals("motivo", ata.getMotivo());
    }

    @Test
    public void testaDesignacao() {
        assertEquals("designação", ata.getDesignacao());
    }

    public void testaMembro() {
        assertEquals(true, ata.isMembro());
    }

}