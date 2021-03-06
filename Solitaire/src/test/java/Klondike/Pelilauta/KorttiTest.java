
package Klondike.Pelilauta;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class KorttiTest {
    
    Kortti kortti;

    @Before
    public void setUp() {
        kortti = new Kortti(7);
        kortti.setMaa("Hertta");
    }

     @Test
     public void konstruktoriAsettaaArvonOikein() {
         assertEquals(7, kortti.getArvo());
     }
     
     @Test
     public void maanAettaminenToimii() {
        assertEquals("Hertta", kortti.getMaa());
     }

}
