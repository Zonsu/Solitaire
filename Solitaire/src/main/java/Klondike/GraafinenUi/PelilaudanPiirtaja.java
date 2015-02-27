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
 * Luokka huolehtii korttien piirtämisestä pelilaudalle.
 *
 * @author Zonsu
 */
public class PelilaudanPiirtaja {

    public static ImageIcon tausta;
    public static ImageIcon tyhja;

    private static JPanel[] ylarivi = new JPanel[8];
    private static JPanel[] alarivi = new JPanel[8];

    /**
     * Tätä kutsutaan pääohjelmatasolta. Luodaan uusi 2 x 7 GridLayout johon
     * aletaan piirretään ja päivitetään kortteja.
     *
     * @param container peritään container
     */
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

        piirraLauta(container);

    }

    /**
     * Tehdään yhteensä 14 JPanel oliota, 7 kummallekkin riville. Paneeleihin
     * talletetaan korteista tehdyt kuvaesitykset.
     *
     * @param container peritään container
     */
    public void piirraLauta(Container container) {

        for (int i = 0; i < 14; i++) {
            if (i < 7) {
                container.add(teePaneeli(i + 1, 1));
            } else {
                container.add(teePaneeli(i - 6, 2));

            }
        }

    }

    /**
     * Alustetaan paneeli, setLayout(null) ja annetaan taustaväri.
     *
     * @param x paneelin horisontaalinen paikka
     * @param y merkkaa kummalla rivillä pelipöytää sijaitaan
     * @return palautetaan paneeli
     */
    public JPanel teePaneeli(int x, int y) {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(null);
        paneeli.setBackground(Color.gray.brighter());
        piirraKortitPaneeliin(paneeli, x, y);

        if (y == 1) {
            ylarivi[x] = paneeli;
        } else {
            alarivi[x] = paneeli;
        }

        return paneeli;
    }

    /**
     * Piirretään muokatut paneelit uudestaan päivityksen jälkeen: paneeli josta
     * otettiin kortti ja paneeli johon laitettiin kortti.
     *
     * @param ekaX paneeli1 horisontaalinen sijainti
     * @param ekaY paneeli1 vertikaalinen sijainti
     * @param tokaX paneeli2 horisontaalinen sijainti
     * @param tokaY paneeli2 vertikaalinen sijainti
     */
    public static void piirraUudestaan(int ekaX, int ekaY, int tokaX, int tokaY) {
        JPanel panel1;
        JPanel panel2;

        if (ekaY == 20) {
            panel1 = ylarivi[ekaX];
            piirraKortitPaneeliin(panel1, ekaX, 1);
        } else {
            panel1 = alarivi[ekaX];
            piirraKortitPaneeliin(panel1, ekaX, 2);
        }
        if (tokaY == 20) {
            panel2 = ylarivi[tokaX];
            piirraKortitPaneeliin(panel2, tokaX, 1);
        } else {
            panel2 = alarivi[tokaX];
            piirraKortitPaneeliin(panel2, tokaX, 2);
        }
    }

    /**
     * Alustetaan ja tarvittaessa päivitetään paneeli.
     *
     * @param paneeli parametrina päivitettävä paneeli
     * @param x koordinaatti
     * @param y koordinaatti
     */
    public static void piirraKortitPaneeliin(JPanel paneeli, int x, int y) {
        paneeli.removeAll();
//Jos paneeli on ekalla rivillä
        if (y == 1) {
            if (x == 1) {
                if (Pelilauta.getNurinPino(0).pinonKoko() >= 0) {
                    JButton nurin = piirraPakka(paneeli);
                    paneeli.add(nurin);
                }

            } else if (x == 2) {
                if (Pelilauta.getOikeinPino(0).pinonKoko() > 0) {
                    Kortti kortti = Pelilauta.getOikeinPino(0).naytaPaalimmainen();
                    JButton oikein = piirraKortti(1, kortti, paneeli);
                    paneeli.add(oikein);
                } else {
                    JButton tyhja = piirraTyhja(paneeli);
                    paneeli.add(tyhja);
                }

            } else if (x > 3) {

                if (Pelilauta.getMaaliPino(x - 4).pinonKoko() == 0) {
                    JButton tyhja = piirraTyhja(paneeli);
                    paneeli.add(tyhja);
                } else {
                    Kortti kortti = Pelilauta.getMaalipinoPaalimmainen(x - 4);
                    JButton nappi = piirraKortti(1, kortti, paneeli);
                    paneeli.add(nappi);
                }

            }

            //Jos paneeli on tokalla rivillä
        } else {
            Korttipino nurin = Pelilauta.getNurinPino(x);
            Korttipino oikein = Pelilauta.getOikeinPino(x);

            if (oikein.pinonKoko() == 0 && nurin.pinonKoko() == 0) {
                JButton tyhja = piirraTyhja(paneeli);
                paneeli.add(tyhja);

            } else {
                for (int i = oikein.pinonKoko() - 1; i >= 0; i--) {
                    Kortti kortti = oikein.getKortti(i);
                    JButton oikeinKortti = piirraKortti(i + nurin.pinonKoko(), kortti, paneeli);
                    paneeli.add(oikeinKortti);
                }

                for (int i = nurin.pinonKoko() - 1; i >= 0; i--) {
                    JLabel nurinKortti = piirraNurin(i, paneeli);
                    paneeli.add(nurinKortti);
                }
            }
        }
        paneeli.repaint();

    }

    /**
     * Piirretään JButton olio joka toimittaa pakan virkaa. Pakalla on myös oma
     * tapahtumakuuntelijansa.
     *
     * @param paneeli parametrina insetsejä varten
     * @return palautetaan luotu JButton
     */
    public static JButton piirraPakka(JPanel paneeli) {
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

        KorttipakanKuuntelija kuuntelija = new KorttipakanKuuntelija();
        kuva.addActionListener(kuuntelija);

        return kuva;
    }

    /**
     * Piirretään korttia edustava JButton sille pelilogiikassa määrätylle
     * paikalle. Oliolla on tapahtumakuuntelija
     *
     * @param monesko y akseli
     * @param kortti  kortti olio
     * @param paneeli paneeli
     * @return
     */
    public static JButton piirraKortti(int monesko, Kortti kortti, JPanel paneeli) {
        ImageIcon img;
        BufferedImage bufImg = null;
        JButton kuva;
        Insets insets;
        Dimension size;

        bufImg = kortti.getKuva();
        img = new ImageIcon(bufImg);

        kuva = new JButton(img);

        insets = paneeli.getInsets();

        kuva.setPreferredSize(new Dimension(72, 100));
        size = kuva.getPreferredSize();

        kuva.setBounds(20 + insets.left, monesko * 20 + insets.top,
                size.width, size.height);

        KortinKuuntelija kuuntelija = new KortinKuuntelija(kuva);
        kuva.addActionListener(kuuntelija);

        return kuva;
    }

    /**
     * Tehdään JLabel olio joka edustaa käännettyä korttia. Piirretään ne
     * pelilogiikan määräämille paikoille.
     *
     * @param monesko kuinka mones kortti pinossa piirtovuorossa
     * @param paneeli paneeli insetsejä varten
     * @return palautetaan jlabel
     */
    public static JLabel piirraNurin(int monesko, JPanel paneeli) {
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
     * Piirretään tyhjää paikkaa edustava JButton. 
     * @param paneeli parametrina paneeli
     * @return palautetaan JButton
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
}
