package br.edu.utfpr.dv.sireata.model;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class TesteAnexo {

    private static Anexo anexo;

    @BeforeClass
    public static void setup() {
        anexo = new Anexo();
        anexo.setIdAnexo(10);
        anexo.setAta(new Ata());
        anexo.setOrdem(10);
        anexo.setDescricao("description");
        anexo.setArquivo(new byte[] {1, 2, 3}); 
    } 

    @Test
    public void testaIdAnexo() {
        assertEquals(10, anexo.getIdAnexo());
    }

    @Test
    public void testaAta() {
        assertEquals(new Ata().getClass().getName(), anexo.getAta().getClass().getName());
    }

    @Test
    public void testaOrdem() {
        assertEquals(10, anexo.getOrdem());
    }

    @Test
    public void testaDescricao() {
        assertEquals("description", anexo.getDescricao());
    }

    @Test
    public void testaArquivo() {
        assertEquals(3, anexo.getArquivo().length);
    }
    
}