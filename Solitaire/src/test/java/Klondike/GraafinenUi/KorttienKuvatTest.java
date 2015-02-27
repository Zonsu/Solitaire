
package Klondike.GraafinenUi;

import static Klondike.GraafinenUi.KorttienKuvat.*;
import Klondike.Pelilauta.Kortti;
import java.awt.image.BufferedImage;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joqpaavo
 */
public class KorttienKuvatTest {

    Kortti kortti;

    public KorttienKuvatTest() {
    }

    @Before
    public void setUp() throws IOException {
        tuoKuvatSpritesta();
        
        kortti = new Kortti(1);
        kortti.setMaa("hertta");

    }

    @Test
    public void liittaKuvanKorttiin() {
        liitaKuvaKorttiin(kortti);

        BufferedImage kuva = null;

        assertEquals(kuva, kortti.getKuva());
    }

}
