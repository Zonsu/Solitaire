package Klondike.Pelilogiikka;

import Klondike.Pelilauta.Kortti;
import static Klondike.Pelilauta.KorttienJako.*;
import Klondike.Pelilauta.Korttipino;
import Klondike.Pelilogiikka.KortinSiirto.*;
import static Klondike.Pelilauta.Pelilauta.maaliPinot;
import static Klondike.Pelilauta.Pelilauta.pinotNurin;
import static Klondike.Pelilauta.Pelilauta.pinotOikein;
import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Zonsu
 */
public class KortinSiirtoTest {



    @Before

    public void setUp() {
        luoPakka();
        jaaUudestaan();
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void nostaaKortinPakasta() {
        Korttipino testiPakka = pinotNurin[0];
        Korttipino testiKaannetyt;

        Kortti kortti = testiPakka.naytaPaalimmainen();

        KortinSiirto.nostaKorttiPakasta();

        testiKaannetyt = pinotOikein[0];

        assertEquals(kortti, testiKaannetyt.naytaPaalimmainen());
    }

    @Test
    public void kaantaaKaannetytKortitUudeksiPakaksi() {
        Korttipino testiPakka = pinotNurin[0];
        Korttipino testiKaannetyt = pinotOikein[0];

        int a = testiPakka.pinonKoko();
        for (int i = 0; i < a; i++) {
            testiKaannetyt.lisaaKortti(testiPakka.naytaPaalimmainen());
            testiPakka.nostaPaalimmainen();
        }

        KortinSiirto.nostaKorttiPakasta();

        assertEquals(24, testiPakka.pinonKoko());
    }

    @Test
    public void kaannoksenJalkeenOikeaKorttiPaalimmaisena() {
        Korttipino testiPakka = pinotNurin[0];
        Korttipino testiKaannetyt = pinotOikein[0];

        Kortti kortti = testiPakka.naytaPaalimmainen();

        int a = testiPakka.pinonKoko();
        for (int i = 0; i < a; i++) {
            testiKaannetyt.lisaaKortti(testiPakka.naytaPaalimmainen());
            testiPakka.nostaPaalimmainen();
        }

        KortinSiirto.nostaKorttiPakasta();

        assertEquals(kortti, testiPakka.naytaPaalimmainen());

    }

    @Test
    public void toimiikoLaillisenSiirronTarkistus() {
        Kortti kortti1 = new Kortti(2);
        Kortti kortti2 = new Kortti(3);

        kortti1.setMaa("Hertta");
        kortti2.setMaa("Pata");

        assertEquals(true, KortinSiirto.onkoLaillinenSiirto(kortti1, kortti2));
    }

    @Test
    public void toimiikoLaillisenSiirronTarkistus2() {
        Kortti kortti1 = new Kortti(2);
        Kortti kortti2 = new Kortti(3);

        kortti1.setMaa("Hertta");
        kortti2.setMaa("Hertta");

        assertEquals(false, KortinSiirto.onkoLaillinenSiirto(kortti1, kortti2));
    }

    @Test
    public void toimiikoLaillisenMaalipinoSiirronTarkistus() {
        Kortti kortti1 = new Kortti(3);
        Kortti kortti2 = new Kortti(2);

        kortti1.setMaa("Hertta");
        kortti2.setMaa("Pata");

        assertEquals(false, KortinSiirto.onkoLaillinenSiirtoMaalipinoon(kortti1, kortti2));
    }

    @Test
    public void toimiikoLaillisenMaalipinoSiirronTarkistus2() {
        Kortti kortti1 = new Kortti(3);
        Kortti kortti2 = new Kortti(2);

        kortti1.setMaa("Hertta");
        kortti2.setMaa("Hertta");

        assertEquals(true, KortinSiirto.onkoLaillinenSiirtoMaalipinoon(kortti1, kortti2));
    }
    
    @Test
    public void kortinIndeksiPalautuuOikein() {
   
    }

}
