package Klondike.GraafinenUi;

import Klondike.Pelilauta.KorttienJako;
import Klondike.GraafinenUi.KorttienKuvat;
import Klondike.Pelilauta.Kortti;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Piirretään kortit pelilaudalle.
 *
 * @author Zonsu
 */
public class PelilaudanPiirtaja {

    public ImageIcon tausta;
    public ImageIcon tyhja;
    private int korttiLaskuri;

    private static JPanel[] pinot = new JPanel[10];

    void luoKomponentit(Container container) {

        /* 
         Tästä tulee todennäköisesti myöhemmin GridBgLayout kunhan jaksan selvittää.
         */
        container.setLayout(new GridLayout(2, 7, 0, 0));

        try {
            KorttienKuvat.tuoKuvatSpritesta();
        } catch (IOException ex) {
            Logger.getLogger(Kayttoliittyma.class.getName()).log(Level.SEVERE, null, ex);
        }

        KorttienJako.uudetKortit();

        tausta = KorttienKuvat.getTausta();
        tyhja = KorttienKuvat.getTyhja();

        alustaPakat(container);

    }

    /**
     * Alustetaan pinoihin oikeat määrät kortteja pelin aloitusta varten.
     *
     * @param container
     */
    public void alustaPakat(Container container) {

        korttiLaskuri = 0;


        /*
         Jaetaan oikea määrä oikeanlaisia kortteja pöydälle. Ensin pakka ja tyhjä pino.
         */
        JPanel pakka = new JPanel();
        pakka.setLayout(null);
        JButton korttipakka = piirraPakka(pakka);
        pakka.add(korttipakka);
        KorttipakanKuuntelija kuuntelija = new KorttipakanKuuntelija();
        korttipakka.addActionListener(kuuntelija);
        container.add(pakka);

        JPanel tyhjaPakka = new JPanel();
        tyhjaPakka.setLayout(null);
        uusiPaneeli(0, 0, tyhjaPakka);
        container.add(tyhjaPakka);

        /*
         Tehdään tyhjä paneeli esteettisistä syistä.
         */
        JPanel laatikko = new JPanel();
        laatikko.setLayout(null);
        container.add(laatikko);

        /*
         Neljä maalipinoa.
         */
        JPanel maaliPino1 = new JPanel();
        maaliPino1.setLayout(null);
        uusiPaneeli(0, 0, maaliPino1);
        container.add(maaliPino1);

        JPanel maaliPino2 = new JPanel();
        maaliPino2.setLayout(null);
        uusiPaneeli(0, 0, maaliPino2);
        container.add(maaliPino2);

        JPanel maaliPino3 = new JPanel();
        maaliPino3.setLayout(null);
        uusiPaneeli(0, 0, maaliPino3);
        container.add(maaliPino3);

        JPanel maaliPino4 = new JPanel();
        maaliPino4.setLayout(null);
        uusiPaneeli(0, 0, maaliPino4);
        container.add(maaliPino4);
        /*
         Alimmalle riville seitsemän eri kokoista pakkaa joiden päällä käännetty img.
         */

        JPanel pino1 = new JPanel();
        pino1.setLayout(null);
        uusiPaneeli(0, 1, pino1);
        container.add(pino1);
        pinot[0] = pino1;

        JPanel pino2 = new JPanel();
        pino2.setLayout(null);
        uusiPaneeli(1, 1, pino2);
        container.add(pino2);
        pinot[1] = pino2;

        JPanel pino3 = new JPanel();
        pino3.setLayout(null);
        uusiPaneeli(2, 1, pino3);
        container.add(pino3);
        pinot[2] = pino3;

        JPanel pino4 = new JPanel();
        pino4.setLayout(null);
        uusiPaneeli(3, 1, pino4);
        container.add(pino4);
        pinot[3] = pino4;

        JPanel pino5 = new JPanel();
        pino5.setLayout(null);
        uusiPaneeli(4, 1, pino5);
        container.add(pino5);
        pinot[4] = pino5;

        JPanel pino6 = new JPanel();
        pino6.setLayout(null);
        uusiPaneeli(5, 1, pino6);
        container.add(pino6);
        pinot[5] = pino6;

        JPanel pino7 = new JPanel();
        pino7.setLayout(null);
        pino7.revalidate();
        uusiPaneeli(6, 1, pino7);
        container.add(pino7);
        pinot[6] = pino7;

    }

    /**
     * Paneeli on paikka laudalla joihin korttipinot piirretään. Jokaisella
     * korttipinolla on omansa. Paneelit tullaan myöhemmin tallettamaan
     * taulukkoon josta ne voidaan ottaa muokattavaksi.
     *
     * @param nurin: montako korttia pinoon jaetaan nurin
     * @param oikein: ja montako oikein
     * @param container
     */
    public void uusiPaneeli(int nurin, int oikein, JPanel paneeli) {

        paneeli.setLayout(null);

        if (nurin == 0 && oikein == 0) {
            paneeli.add(piirraTyhja(paneeli));
        } else {

            for (int i = 0; i < oikein; i++) {
                JButton kortti;

                korttiLaskuri = korttiLaskuri + nurin;

                kortti = piirraOikein(paneeli, nurin + oikein - i);
                paneeli.add(kortti);
                KortinKuuntelija kuuntelija = new KortinKuuntelija(kortti);
                kortti.addActionListener(kuuntelija);

                korttiLaskuri++;

            }

            for (int i = 0; i < nurin; i++) {
                paneeli.add(piirraNurin(paneeli, nurin - i));

            }

        }
    }

    /**
     * Metodi piirtää laudalle nurinpäin olevan kortin
     *
     * @param paneeli: annetaan parametrina paneeli jotta saadaan sen speksit
     * metodille
     * @param monesko: kertoo montako korttia vielä piirretään, eli mihin
     * paikkaan lautaa img piirretään
     * @return
     */
    public JLabel piirraNurin(JPanel paneeli, int monesko) {

        ImageIcon img;
        JLabel kuva;
        Insets insets;
        Dimension size;

        img = tausta;
        kuva = new JLabel(img);

        insets = paneeli.getInsets();
        size = kuva.getPreferredSize();
        kuva.setBounds(20 + insets.left, monesko * 20 + insets.top,
                size.width, size.height);

        return kuva;
    }

    /**
     * Metodi piirtää laudalle oikein päin olevan kortin.
     *
     * @param paneeli
     * @param monesko
     * @return
     */
    public JButton piirraOikein(JPanel paneeli, int monesko) {

        ImageIcon img;
        BufferedImage bufImg = null;
        JButton kuva;
        Insets insets;
        Dimension size;

        ArrayList<Kortti> korttipakka = new ArrayList<Kortti>();
        korttipakka = KorttienJako.getKorttiPakka();

        
        
        
        Kortti kortti = korttipakka.get(korttiLaskuri);

        bufImg = kortti.getKuva();
        img = new ImageIcon(bufImg);

        kuva = new JButton(img);

        insets = paneeli.getInsets();

        kuva.setPreferredSize(new Dimension(72, 100));
        size = kuva.getPreferredSize();

        kuva.setBounds(20 + insets.left, monesko * 20 + insets.top,
                size.width, size.height);

        return kuva;
    }

    /**
     * Piirtää laudalle tyhjää paikkaa kuvaavan kuvan.
     *
     * @param paneeli
     * @return
     */
    public JButton piirraTyhja(JPanel paneeli) {

        ImageIcon img;
        JButton kuva;
        Insets insets;
        Dimension size;

        img = tyhja;
        kuva = new JButton(img);

        insets = paneeli.getInsets();

        kuva.setPreferredSize(new Dimension(72, 100));
        size = kuva.getPreferredSize();

        kuva.setBounds(20 + insets.left, 20 + insets.top,
                size.width, size.height);

        return kuva;
    }

//    public JButton piirraPiilotettu(JPanel paneeli, int monesko) {
//
//        JButton kuva = null;
//        Insets insets;
//        Dimension size;
//
//        kuva = new JButton();
//
//        insets = paneeli.getInsets();
//        size = kuva.getPreferredSize();
//        kuva.setBounds(20 + insets.left, monesko * 20 + insets.top,
//                size.width, size.height);
//        
//        kuva.setVisible(false);
//
//        return kuva;
//    }
    public JButton piirraPakka(JPanel paneeli) {

        ImageIcon img;
        JButton kuva;
        Insets insets;
        Dimension size;

        img = tausta;
        kuva = new JButton(img);

        insets = paneeli.getInsets();

        kuva.setPreferredSize(new Dimension(72, 100));
        size = kuva.getPreferredSize();

        kuva.setBounds(20 + insets.left, 20 + insets.top,
                size.width, size.height);

        return kuva;
    }

    public static void siirraKortti(int mista, JButton mika, int mihin, JButton mika2) {

        JPanel pino = new JPanel();
        pino = pinot[mista / 20 - 1];
        pino.remove(mika);

        pino = pinot[mihin / 20 - 1];
        int montako = pino.getComponentCount();
        pino.add(mika);
        
        Kayttoliittyma.frame.pack();
    }

    /**
     * Haetaan annetusta spritestä kaikki 52 korttia ja taulukoidaan ne maiden
     * mukaan. Haetaan myös kortin taustakuva ja tyhjää paikkaa kuvaava kuva.
     *
     * @throws IOException
     */
}

//        for (int i = 0; i < 1; i++) {
//            BufferedImage img = sprites[i];
//            JLabel kuva = new JLabel(new ImageIcon(img));
//
//            paneeli.add(kuva);
//
//            Insets insets = paneeli.getInsets();
//            Dimension size = kuva.getPreferredSize();
//            kuva.setBounds(25 + insets.left, 5 + insets.top,
//                    size.width, size.height);
//
//        }
//        JPanel paneeli = new JPanel();
//
//        OverlayLayout overlay = new OverlayLayout(paneeli);
//        paneeli.setLayout(overlay);
//        paneeli.setBackground(Color.green);
//
//        JLabel kuva = new JLabel(tausta);
//        paneeli.add(kuva);
//
//        container.add(paneeli, BorderLayout.NORTH);
//
//        paneeli = new JPanel();
//
//        overlay = new OverlayLayout(paneeli);
//        paneeli.setLayout(overlay);
//        paneeli.setBackground(Color.green);
//
//        kuva = new JLabel(tyhja);
//        paneeli.add(kuva);
//
//        container.add(paneeli, BorderLayout.NORTH);
//
//        paneeli = new JPanel();
//
//        overlay = new OverlayLayout(paneeli);
//        paneeli.setLayout(overlay);
//        paneeli.setBackground(Color.green);
//
//        container.add(paneeli, BorderLayout.NORTH);
//
//        for (int i = 0; i < 4; i++) {
//            paneeli = new JPanel();
//
//            overlay = new OverlayLayout(paneeli);
//            paneeli.setLayout(overlay);
//            paneeli.setBackground(Color.green);
//
//            kuva = new JLabel(tyhja);
//            paneeli.add(kuva);
//
//            container.add(paneeli, BorderLayout.NORTH);
//        }
//
//        for (int i = 0; i < 7; i++) {
//
//            paneeli = new JPanel();
//            overlay = new OverlayLayout(paneeli);
//
//            paneeli.setLayout(overlay);
//            paneeli.setBackground(Color.green);
//
//            //BufferedImage img = sprites[2];
//            BufferedImage kortti2 = sprites[5];
//            JLabel kuva2 = new JLabel(new ImageIcon(kortti2));
//            paneeli.add(kuva2);
//
//            kuva = new JLabel(tausta);
//            paneeli.add(kuva);
//
//            container.add(paneeli, BorderLayout.NORTH);
//        }
