package Klondike.GraafinenUi;

import Klondike.Pelilauta.Kortti;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *  Generoidaan pelikorteille kuvat joita käytetään piirtämiseen
 * @author Zonsu
 */
public class KorttienKuvat {

    public static BufferedImage[] padat;
    public static BufferedImage[] hertat;
    public static BufferedImage[] ristit;
    public static BufferedImage[] ruudut;
    public static ImageIcon tausta;
    public static ImageIcon tyhja;
    public static BufferedImage kuva;

    /**
     * Jokainen kortti BufferedImage kuvan joka on kortin visuaalinen edustus.
     *
     * @param kortti
     */
    public static void liitaKuvaKorttiin(Kortti kortti) {

        kuva = null;

        if (kortti.getMaa().equals("Pata")) {
            kuva = padat[kortti.getArvo() - 1];
            kortti.setKuva(kuva);

        } else if (kortti.getMaa().equals("Hertta")) {
            kuva = hertat[kortti.getArvo() - 1];
            kortti.setKuva(kuva);

        } else if (kortti.getMaa().equals("Risti")) {
            kuva = ristit[kortti.getArvo() - 1];
            kortti.setKuva(kuva);

        } else if (kortti.getMaa().equals("Ruutu")) {
            kuva = ruudut[kortti.getArvo() - 1];
            kortti.setKuva(kuva);

        }

    }

    /*
     Palautetetaan kuva kortin taustalle.
     */
    public static ImageIcon getTausta() {
        return tausta;
    }
    /*
     Palautetaan kuva tyhjälle kortille.
     */

    public static ImageIcon getTyhja() {
        return tyhja;
    }

    /**
     * Luodaan spritesta pelissä käytettävät kortit. Kortit kokoa 72 x 100,
     * talletetaan maittain listoihin.
     *
     * @throws IOException
     */
    public static void tuoKuvatSpritesta() throws IOException {
        BufferedImage sprite = ImageIO.read(KorttienKuvat.class.getResource("/temp_cards_sprite.gif"));

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
        
        //Taustalle ja tyhjälle kuvalle omansa
        BufferedImage kuva = sprite.getSubimage(0, 400, width, height);
        tausta = new ImageIcon(kuva);

        kuva = sprite.getSubimage(147, 400, width, height);
        tyhja = new ImageIcon(kuva);
    }

}
