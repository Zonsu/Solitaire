package Klondike.GraafinenUi;

import Klondike.Pelilauta.KorttienJako;
import Klondike.GraafinenUi.KorttienKuvat;
import Klondike.Pelilauta.Kortti;
import Klondike.Pelilauta.Korttipino;
import Klondike.Pelilauta.Pelilauta;
import Klondike.Pelilogiikka.KortinSiirto;
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
    public static ImageIcon tyhja;
    private int korttiLaskuri;
    private static JPanel[] pinot = new JPanel[10];
    private static Color taustaVari = new Color(104, 185, 234, 45);

    static JPanel pakka = new JPanel();
    static JPanel tyhjaPakka = new JPanel();
    static JPanel laatikko = new JPanel();
    static JPanel maaliPino1 = new JPanel();
    static JPanel maaliPino2 = new JPanel();
    static JPanel maaliPino3 = new JPanel();
    static JPanel maaliPino4 = new JPanel();
    static JPanel pino1 = new JPanel();
    static JPanel pino2 = new JPanel();
    static JPanel pino3 = new JPanel();
    static JPanel pino4 = new JPanel();
    static JPanel pino5 = new JPanel();
    static JPanel pino6 = new JPanel();
    static JPanel pino7 = new JPanel();

    void luoKomponentit(Container container) {


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
        pakka.setLayout(null);
        JButton korttipakka = piirraPakka(pakka);
        pakka.add(korttipakka);
        KorttipakanKuuntelija kuuntelija = new KorttipakanKuuntelija();
        korttipakka.addActionListener(kuuntelija);
        pakka.setBackground(Color.gray.brighter());
        container.add(pakka);

        tyhjaPakka.setLayout(null);
        uusiPaneeli(0, 0, tyhjaPakka);
        container.add(tyhjaPakka);

        /*
         Tehdään tyhjä paneeli esteettisistä syistä.
         */
        laatikko.setLayout(null);
        laatikko.setBackground(Color.gray.brighter());
        container.add(laatikko);

        /*
         Neljä maalipinoa.
         */
        maaliPino1.setLayout(null);
        uusiPaneeli(0, 0, maaliPino1);
        container.add(maaliPino1);

        maaliPino2.setLayout(null);
        uusiPaneeli(0, 0, maaliPino2);
        container.add(maaliPino2);

        maaliPino3.setLayout(null);
        uusiPaneeli(0, 0, maaliPino3);
        container.add(maaliPino3);

        maaliPino4.setLayout(null);
        uusiPaneeli(0, 0, maaliPino4);
        container.add(maaliPino4);
        /*
         Alimmalle riville seitsemän eri kokoista pakkaa joiden päällä käännetty img.
         */

        pino1.setLayout(null);
        uusiPaneeli(0, 1, pino1);
        container.add(pino1);
        pinot[0] = pino1;

        pino2.setLayout(null);
        uusiPaneeli(1, 1, pino2);
        container.add(pino2);
        pinot[1] = pino2;

        pino3.setLayout(null);
        uusiPaneeli(2, 1, pino3);
        container.add(pino3);
        pinot[2] = pino3;

        pino4.setLayout(null);
        uusiPaneeli(3, 1, pino4);
        container.add(pino4);
        pinot[3] = pino4;

        pino5.setLayout(null);
        uusiPaneeli(4, 1, pino5);
        container.add(pino5);
        pinot[4] = pino5;

        pino6.setLayout(null);
        uusiPaneeli(5, 1, pino6);
        container.add(pino6);
        pinot[5] = pino6;

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
        paneeli.setBackground(Color.gray.brighter());

        if (nurin == 0 && oikein == 0) {
            paneeli.add(piirraTyhja(paneeli));

        } else {

            for (int i = 0; i < oikein; i++) {
                JButton kortti;

                kortti = piirraOikein(paneeli, nurin + oikein - i, nurin + 1);
                paneeli.add(kortti);
                KortinKuuntelija kuuntelija = new KortinKuuntelija(kortti);
                kortti.addActionListener(kuuntelija);

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
    public static JButton piirraOikein(JPanel paneeli, int monesko, int i) {

        ImageIcon img;
        BufferedImage bufImg = null;
        JButton kuva;
        Insets insets;
        Dimension size;

        Kortti kortti = Pelilauta.getOikeinPaalimmainen(i);

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
    public static JButton piirraTyhja(JPanel paneeli) {

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

        KortinKuuntelija kuuntelija = new KortinKuuntelija(kuva);
        kuva.addActionListener(kuuntelija);

        return kuva;
    }
        public static JLabel piirraPohja(JPanel paneeli) {

        ImageIcon img;
        JLabel kuva;
        Insets insets;
        Dimension size;

        img = tyhja;
        kuva = new JLabel(img);

        insets = paneeli.getInsets();

        kuva.setPreferredSize(new Dimension(72, 100));
        size = kuva.getPreferredSize();

        kuva.setBounds(20 + insets.left, 20 + insets.top,
                size.width, size.height);

        return kuva;
    }

    public static void piirraMaalipinoon(int pino, Kortti kortti) {
        JPanel korttiPaneeli = null;
        ImageIcon img;
        JButton kuva;
        Insets insets;
        Dimension size;
        BufferedImage bufImg = null;

        if (pino == 4) {
            korttiPaneeli = maaliPino1;
        } else if (pino == 5) {
            korttiPaneeli = maaliPino2;
        } else if (pino == 6) {
            korttiPaneeli = maaliPino3;
        } else if (pino == 7) {
            korttiPaneeli = maaliPino4;
        }

        korttiPaneeli.removeAll();
        System.out.println("Piirretään " + kortti);
        bufImg = kortti.getKuva();
        img = new ImageIcon(bufImg);

        kuva = new JButton(img);

        insets = korttiPaneeli.getInsets();

        kuva.setPreferredSize(new Dimension(72, 100));
        size = kuva.getPreferredSize();

        kuva.setBounds(20 + insets.left, 20 + insets.top,
                size.width, size.height);

        korttiPaneeli.add(kuva);
        KortinKuuntelija kuuntelija = new KortinKuuntelija(kuva);
        kuva.addActionListener(kuuntelija);

        korttiPaneeli.repaint();
    }

    public static void poistaPinosta(int pino, int monesko) {
        JPanel korttiPaneeli = getPaneeli(pino);
        korttiPaneeli = getPaneeli(pino);
        korttiPaneeli.remove(0);

        if (KortinSiirto.getTyhja() && korttiPaneeli.getComponentCount() > 0) {
            korttiPaneeli.remove(0);
            System.out.println("Paneelin koko: " + korttiPaneeli.getComponentCount() + " monesko: " + monesko);
            JButton kuva = piirraOikein(korttiPaneeli, (monesko - 21) / 20, pino);
            korttiPaneeli.add(kuva, 0);
            KortinKuuntelija kuuntelija = new KortinKuuntelija(kuva);
            kuva.addActionListener(kuuntelija);

            KortinSiirto.setTyhja(false);
        } else {
            KortinSiirto.setTyhja(false);
            JLabel kuva = piirraPohja(korttiPaneeli);
            korttiPaneeli.add(kuva, 0);
        }

        korttiPaneeli.repaint();
    }

    public static void poistaKaannetyista(Kortti kort) {
        tyhjaPakka.remove(tyhjaPakka.getComponentCount() - 1);

        if (Pelilauta.getOikeinPino(0).pinonKoko() > 0) {
            Kortti kortti = Pelilauta.getOikeinPaalimmainen(0);
            piirraKaannetty(kortti);
        } else {
            JButton kor = piirraTyhja(tyhjaPakka);
            tyhjaPakka.add(kor);
        }
        tyhjaPakka.repaint();
    }

    public static void piirraKaannetty(Kortti kortti) {

        ImageIcon img;
        JButton kuva;
        Insets insets;
        Dimension size;
        BufferedImage bufImg = null;

        if (kortti == null) {

            tyhjaPakka.removeAll();

            JButton kort = piirraTyhja(tyhjaPakka);
            tyhjaPakka.add(kort);

        } else {

            tyhjaPakka.removeAll();
            System.out.println("Piirretään " + kortti);
            bufImg = kortti.getKuva();
            img = new ImageIcon(bufImg);

            kuva = new JButton(img);

            insets = tyhjaPakka.getInsets();

            kuva.setPreferredSize(new Dimension(72, 100));
            size = kuva.getPreferredSize();

            kuva.setBounds(20 + insets.left, 20 + insets.top,
                    size.width, size.height);

            tyhjaPakka.add(kuva);
            KortinKuuntelija kuuntelija = new KortinKuuntelija(kuva);
            kuva.addActionListener(kuuntelija);
        }
        tyhjaPakka.repaint();

    }

    public static void piirraKortinSiirto(int mista, int mihin, int mistaKorkeus, int mihinKorkeus) {

        if (Pelilauta.getOikeinPino(mista).pinonKoko() <= 1) {
            KortinSiirto.setTyhja(true);
        }

        poistaPinosta(mista, mistaKorkeus);
        JPanel korttiPaneeli = getPaneeli(mihin);
        JButton kort = piirraOikein(korttiPaneeli, (mihinKorkeus + 19) / 20, mihin);
        korttiPaneeli.add(kort, 0);
        KortinKuuntelija kuuntelija = new KortinKuuntelija(kort);
        kort.addActionListener(kuuntelija);
        korttiPaneeli.repaint();
    }

    public static void siirraKorttiPakastaLaudalle(Kortti kortti, int mihin, int mihinKorkeus) {
        Kortti kort = kortti;
        poistaKaannetyista(kort);

        JPanel korttiPaneeli = getPaneeli(mihin);

        JButton kuva = piirraOikein(korttiPaneeli, (mihinKorkeus+19)/20, mihin);
        korttiPaneeli.add(kuva, 0);
        KortinKuuntelija kuuntelija = new KortinKuuntelija(kuva);
        kuva.addActionListener(kuuntelija);

        korttiPaneeli.repaint();
    }

    public static JPanel getPaneeli(int pino) {
        JPanel korttiPaneeli = null;

        if (pino == 1) {
            korttiPaneeli = pino1;

        } else if (pino == 2) {
            korttiPaneeli = pino2;
        } else if (pino == 3) {
            korttiPaneeli = pino3;
        } else if (pino == 4) {
            korttiPaneeli = pino4;
        } else if (pino == 5) {
            korttiPaneeli = pino5;
        } else if (pino == 6) {
            korttiPaneeli = pino6;
        } else if (pino == 7) {
            korttiPaneeli = pino7;
        }
        return korttiPaneeli;
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

}
