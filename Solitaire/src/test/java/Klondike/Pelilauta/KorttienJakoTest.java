/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klondike.Pelilauta;

import static Klondike.Pelilauta.KorttienJako.*;
import static Klondike.Pelilauta.Kortti.*;
import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KorttienJakoTest {

    ArrayList<Kortti> korttiPakka;

    @Before
    public void setUp() {
        korttiPakka = new ArrayList();

    }

    @Test
    public void luokoUuteenPakkaanTapeeksiKortteja() {
        korttiPakka = luoPakka();
        assertEquals(52, pakanKoko(korttiPakka));
    }

    @Test
    public void pakassaYksiJokaistaKorttia() {
        korttiPakka = luoPakka();
        Kortti kortti;
        int check = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 14; j++) {

                if (i == 0) {
                    kortti = korttiPakka.get(j - 1);
                    if (kortti.getArvo() == j || kortti.getMaa() == "Pata") {
                        check++;
                    }
                }
                if (i == 1) {
                    kortti = korttiPakka.get(12 + j);
                    if (kortti.getArvo() == j || kortti.getMaa() == "Hertta") {
                        check++;
                    }
                }
                if (i == 2) {
                    kortti = korttiPakka.get(25 + j);
                    if (kortti.getArvo() == j || kortti.getMaa() == "Risti") {
                        check++;
                    }
                }
                if (i == 3) {
                    kortti = korttiPakka.get(38 + j);
                    if (kortti.getArvo() == j || kortti.getMaa() == "Ruutu") {
                        check++;
                    }
                }
            }
        }
        assertEquals(52, check);
    }

    @Test
    public void pinoissaOikeaMaaraKortteja() {
        uudetKortit();
        Korttipino pino = palautaPino(0, 6);
        assertEquals(5, pino.pinonKoko());
    }

    @Test
    public void pinonPaallaOikeaMaaraKortteja() {
        uudetKortit();
        Korttipino pino = palautaPino(1, 6);
        assertEquals(1, pino.pinonKoko());
    }

    @Test
    public void pakassaOikeaMaaraKortteja() {
        uudetKortit();
        Korttipino pino = palautaPino(0, 0);
        assertEquals(24, pino.pinonKoko());
    }

    @Test
    public void maalipinoAluksiTyhja() {
        uudetKortit();
        Korttipino pino = palautaPino(2, 0);
        assertEquals(0, pino.pinonKoko());
    }
    @Test
    public void palauttaaPakan() {
        uudetKortit();

        korttiPakka = getKorttiPakka();
        
        assertEquals(52, korttiPakka.size());
    }
}
