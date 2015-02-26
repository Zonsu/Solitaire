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
        JPanel pakkaPaneeli = new JPanel();
        pakkaPaneeli.setLayout(null);
        pakkaPaneeli.add(piirraPakka(pakkaPaneeli));
        container.add(pakkaPaneeli);

        
        uusiPaneeli(0, 0, container);

        /*
         Tehdään tyhjä paneeli esteettisistä syistä.
         */
        JPanel laatikko = new JPanel();
        laatikko.setLayout(null);
        container.add(laatikko);

        /*
         Neljä maalipinoa.
         */
        for (int i = 0; i < 4; i++) {
            uusiPaneeli(0, 0, container);
        }
        /*
         Alimmalle riville seitsemän eri kokoista pakkaa joiden päällä käännetty img.
         */

        for (int i = 0; i < 7; i++) {
            uusiPaneeli(i, 1, container);
        }
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
        }

        for (int i = 0; i < oikein; i++) {
            JButton kortti;

            kortti = piirraOikein(paneeli, nurin + oikein - i);
            paneeli.add(kortti);
            korttiLaskuri++;

        }

        for (int i = 0; i < nurin; i++) {
            paneeli.add(piirraNurin(paneeli, nurin - i));
            korttiLaskuri++;
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
