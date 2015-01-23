/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klondike.Pelilauta;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Johanna
 */
public class PelilautaTest {

    Korttipino pino;
    Kortti kortti;

    @Before
    public void setUp() {
        pino = new Korttipino(5);

        kortti = new Kortti(1);
        kortti.setMaa("Pata");
        pino.lisaaKortti(kortti);

        kortti = new Kortti(2);
        kortti.setMaa("Hertta");
        pino.lisaaKortti(kortti);

        kortti = new Kortti(3);
        kortti.setMaa("Risti");
        pino.lisaaKortti(kortti);

        kortti = new Kortti(4);
        kortti.setMaa("Ruutu");
        pino.lisaaKortti(kortti);
    }

    @Test
    public void konstruktoriAsettaaPinonPaikanOikein() {
        assertEquals(5, pino.getPino());
    }
    
    @Test
    public void onkoPinonKokoOikein() {
        assertEquals(4, pino.pinonKoko());
    }
    
    @Test
    public void nayttaakoPaalimmaisenOikein() {
        kortti = new Kortti(4);
        kortti.setMaa("Ruutu");
        
        assertEquals(pino.naytaPaalimmainen(), kortti);
    }
    
//    @Test
//    public void poistaakoViimeisenKortinPinosta() {
//        Korttipino testiPino = new Korttipino(5);
//        
//        kortti = new Kortti(1);
//        kortti.setMaa("Pata");
//        testiPino.lisaaKortti(kortti);
//
//        kortti = new Kortti(2);
//        kortti.setMaa("Hertta");
//        testiPino.lisaaKortti(kortti);
//
//        kortti = new Kortti(3);
//        kortti.setMaa("Risti");
//        testiPino.lisaaKortti(kortti);
//        
//        pino.poistaViimeinen();
//        
//        assertEquals(testiPino.getKortit(), pino.getKortit());
//    }

}
