package Klondike.Pelilogiikka;

import Klondike.Pelilauta.Kortti;
import static Klondike.Pelilauta.KorttienJako.*;
import Klondike.Pelilauta.Korttipino;
import Klondike.Pelilogiikka.KortinSiirto.*;
import static Klondike.Pelilauta.Peli.maaliPinot;
import static Klondike.Pelilauta.Peli.pinotNurin;
import static Klondike.Pelilauta.Peli.pinotOikein;
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

    @Test
    public void eiKaannaKorttiaKunPaallaOnKortteja() {
        Korttipino testiPino = pinotNurin[2];
        Kortti paalimmainen = testiPino.naytaPaalimmainen();

        KortinSiirto.kaannaKortti(2);
        testiPino = pinotNurin[2];

        assertEquals(paalimmainen, testiPino.naytaPaalimmainen());
    }

    @Test
    public void kaantaaKortinPinostaJosPaallaEiKaannettyja() {
        Korttipino testiPino = pinotNurin[2];
        Korttipino tyhjaPino = pinotOikein[2];
        tyhjaPino.tyhjennaPino();
        pinotOikein[2] = tyhjaPino;

        Kortti kortti = testiPino.naytaPaalimmainen();
        KortinSiirto.kaannaKortti(2);
        tyhjaPino = pinotOikein[2];

        assertEquals(kortti, tyhjaPino.naytaPaalimmainen());
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
    public void siirtaakoKortinKaannetystaPinostaToiseenPinoon() {
        Kortti testiKortti = new Kortti(9);
        testiKortti.setMaa("Hertta");
        Kortti testiKortti2 = new Kortti(10);
        testiKortti2.setMaa("Risti");

        Korttipino testiPino = pinotOikein[1];
        testiPino.lisaaKortti(testiKortti);
        Korttipino testiPino2 = pinotOikein[2];
        testiPino2.lisaaKortti(testiKortti2);

        pinotOikein[1] = testiPino;
        pinotOikein[2] = testiPino2;

        KortinSiirto.siirraKortti(1, 2);
        testiPino = pinotOikein[2];

        assertEquals(testiKortti, testiPino.naytaPaalimmainen());
    }

    @Test
    public void eiSiirraEpasopivaaKorttiaKaannetystaPinostaToiseen() {
        Kortti testiKortti = new Kortti(9);
        testiKortti.setMaa("Hertta");
        Kortti testiKortti2 = new Kortti(10);
        testiKortti2.setMaa("Risti");

        Korttipino testiPino = pinotOikein[1];
        testiPino.lisaaKortti(testiKortti);
        Korttipino testiPino2 = pinotOikein[2];
        testiPino2.lisaaKortti(testiKortti2);

        pinotOikein[1] = testiPino;
        pinotOikein[2] = testiPino2;

        KortinSiirto.siirraKortti(2, 1);
        testiPino = pinotOikein[1];

        assertEquals(testiKortti, testiPino.naytaPaalimmainen());
    }
}
