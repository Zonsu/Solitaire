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
    public void luoJaLisaaUudenTyhjanKortin() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(null);

        paneeli.add(piirra.piirraTyhja(paneeli));

        assertEquals(1, paneeli.getComponentCount());
    }



}
