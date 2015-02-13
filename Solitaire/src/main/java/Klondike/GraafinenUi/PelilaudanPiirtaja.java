package Klondike.GraafinenUi;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Piirretään kortit pelilaudalle.
 *
 * @author Zonsu
 */
public class PelilaudanPiirtaja {

    private BufferedImage[] padat;
    private BufferedImage[] hertat;
    private BufferedImage[] ristit;
    private BufferedImage[] ruudut;
    private ImageIcon tausta;
    private ImageIcon tyhja;

    /* Korttilaskuri tulee käyttöön kun sekoitetusta pakasta otetaan kortteja. Ei vielä implementoitu
     */
    private int korttiLaskuri;

    void luoKomponentit(Container container) {

        /* 
         Tästä tulee todennäköisesti myöhemmin GridBgLayout kunhan jaksan selvittää.
         */
        container.setLayout(new GridLayout(2, 7, 0, 0));

        try {
            cardsFromSprite();
        } catch (IOException ex) {
            Logger.getLogger(Kayttoliittyma.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        uusiPaneeli(1, 0, container);
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
         Alimmalle riville seitsemän eri kokoista pakkaa joiden päällä käännetty kortti.
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
    public void uusiPaneeli(int nurin, int oikein, Container container) {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(null);

        if (nurin == 0 && oikein == 0) {
            paneeli.add(piirraTyhja(paneeli));
        }

        for (int i = 0; i < oikein; i++) {
            paneeli.add(piirraOikein(paneeli, nurin + oikein - i));
        }

        for (int i = 0; i < nurin; i++) {
            paneeli.add(piirraNurin(paneeli, nurin - i));
        }
       
        container.add(paneeli);
    }

    /**
     * Metodi piirtää laudalle nurinpäin olevan kortin
     *
     * @param paneeli: annetaan parametrina paneeli jotta saadaan sen speksit
     * metodille
     * @param monesko: kertoo montako korttia vielä piirretään, eli mihin
     * paikkaan lautaa kortti piirretään
     * @return
     */
    public JLabel piirraNurin(JPanel paneeli, int monesko) {

        ImageIcon kortti;
        JLabel kuva;
        Insets insets;
        Dimension size;

        kortti = tausta;
        kuva = new JLabel(kortti);

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
    public JLabel piirraOikein(JPanel paneeli, int monesko) {

        BufferedImage kortti;
        JLabel kuva;
        Insets insets;
        Dimension size;

        /*
         Tässä välissä tulevaisuudessa kysytään KorttienJaolta mikä kortti jaetaan. 
         Nyt placeholderina muita kortteja.
         */
        if (korttiLaskuri > 12) {
            korttiLaskuri = 0;
        }
        kortti = hertat[korttiLaskuri];
        korttiLaskuri++;
        kuva = new JLabel(new ImageIcon(kortti));
        insets = paneeli.getInsets();

        size = kuva.getPreferredSize();
        kuva.setBounds(20 + insets.left, monesko * 20 + insets.top + insets.top,
                size.width, size.height);

        return kuva;
    }

    /**
     * Piirtää laudalle tyhjää paikkaa kuvaavan kuvan.
     *
     * @param paneeli
     * @return
     */
    public JLabel piirraTyhja(JPanel paneeli) {

        ImageIcon kortti;
        JLabel kuva;
        Insets insets;
        Dimension size;

        kortti = tyhja;
        kuva = new JLabel(kortti);

        insets = paneeli.getInsets();
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
    public void cardsFromSprite() throws IOException {
        BufferedImage sprite = ImageIO.read(new File("src/main/resources/Images/temp_cards_sprite.gif"));

        final int width = 72;
        final int height = 100;
        final int rows = 4;
        final int cols = 13;

        padat = new BufferedImage[cols];
        hertat = new BufferedImage[cols];
        ristit = new BufferedImage[cols];
        ruudut = new BufferedImage[cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (i == 0) {
                    hertat[j] = sprite.getSubimage(
                            j * width,
                            i * height,
                            width,
                            height
                    );
                }
                if (i == 1) {
                    ruudut[j] = sprite.getSubimage(
                            j * width,
                            i * height,
                            width,
                            height
                    );
                }
                if (i == 2) {
                    ristit[j] = sprite.getSubimage(
                            j * width,
                            i * height,
                            width,
                            height
                    );
                }
                if (i == 3) {
                    padat[j] = sprite.getSubimage(
                            j * width,
                            i * height,
                            width,
                            height
                    );
                }
            }
        }
        BufferedImage kuva = sprite.getSubimage(0, 400, width, height);
        tausta = new ImageIcon(kuva);

        kuva = sprite.getSubimage(147, 400, width, height);
        tyhja = new ImageIcon(kuva);
    }

}

//        for (int i = 0; i < 1; i++) {
//            BufferedImage kortti = sprites[i];
//            JLabel kuva = new JLabel(new ImageIcon(kortti));
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
//            //BufferedImage kortti = sprites[2];
//            BufferedImage kortti2 = sprites[5];
//            JLabel kuva2 = new JLabel(new ImageIcon(kortti2));
//            paneeli.add(kuva2);
//
//            kuva = new JLabel(tausta);
//            paneeli.add(kuva);
//
//            container.add(paneeli, BorderLayout.NORTH);
//        }
