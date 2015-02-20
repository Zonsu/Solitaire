/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klondike.GraafinenUi;

import Klondike.GraafinenUi.PelilaudanPiirtaja.*;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
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
public class PelilaudanPiirtajaTest {

    private JFrame frame;
    private PelilaudanPiirtaja piirra;
    private Container container;

    public PelilaudanPiirtajaTest() {

    }

    @Before
    public void setUp() {
        frame = new JFrame("Testi-frame");
        frame.setPreferredSize(new Dimension(800, 600));

        piirra = new PelilaudanPiirtaja();
        container = frame.getContentPane();

    }

    @After
    public void tearDown() {
        frame.dispose();
    }

    @Test
    public void jakaaKorttienKuviaOikeanMaaranTaulukoihin() throws IOException {
        piirra.tuoKuvatSpritesta();

        int korttienMaara = piirra.hertat.length + piirra.padat.length + piirra.ristit.length + piirra.ruudut.length;

        assertEquals(52, korttienMaara);
    }

    @Test
    public void luoJaLisaaUudenPaneeliOlion() {
        piirra.uusiPaneeli(0, 0, container);
        assertEquals(1, container.getComponentCount());
    }

    public void luoJaLisaaUudenPaneeliOlion2() throws IOException {
        piirra.tuoKuvatSpritesta();
        piirra.uusiPaneeli(0, 2, container);
        assertEquals(2, container.getComponentCount());
    }

    public void luoJaLisaaUudenPaneeliOlion3() {
        piirra.uusiPaneeli(3, 0, container);
        assertEquals(1, container.getComponentCount());
    }

    @Test
    public void luoJaLisaaUudenTyhjanKortin() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(null);

        paneeli.add(piirra.piirraTyhja(paneeli));

        assertEquals(1, paneeli.getComponentCount());
    }

    @Test
    public void luoJaLisaaUudenOikeinKortin() throws IOException {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(null);
        piirra.tuoKuvatSpritesta();

        paneeli.add(piirra.piirraOikein(paneeli, 1));

        assertEquals(1, paneeli.getComponentCount());
    }

    @Test
    public void luoJaLisaaUudenNurinKortin() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(null);

        paneeli.add(piirra.piirraNurin(paneeli, 1));

        assertEquals(1, paneeli.getComponentCount());
    }

    @Test
    public void piirtaaTyhjanKortinOikeallePaikalle() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(null);
        JLabel kortti = piirra.piirraTyhja(paneeli);

        Point piste = new Point();
        piste.setLocation(20, 20);

        assertEquals(piste, kortti.getLocation());

    }

    @Test
    public void piirtaaKaannetynKortinOikeallePaikalle() throws IOException {
        piirra.tuoKuvatSpritesta();

        JPanel paneeli = new JPanel();
        paneeli.setLayout(null);
        JLabel kortti = piirra.piirraOikein(paneeli, 2);

        Point piste = new Point();
        piste.setLocation(20, 40);

        assertEquals(piste, kortti.getLocation());

    }

    @Test
    public void piirtaaNurinKortinOikeallePaikalle() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(null);
        JLabel kortti = piirra.piirraNurin(paneeli, 4);

        Point piste = new Point();
        piste.setLocation(20, 80);

        assertEquals(piste, kortti.getLocation());
    }

}
