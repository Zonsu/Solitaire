package Klondike.GraafinenUi;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.ImageIcon.*;
import Klondike.GraafinenUi.PelilaudanPiirtaja.*;

/**
 * TODELLA KESKEN.
 *
 * @author Zonsu
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private BufferedImage[] sprites;
    private ImageIcon tausta;
    private ImageIcon tyhja;

    public Kayttoliittyma() {
    }

    @Override

    public void run() {
        frame = new JFrame("Klondike Solitaire");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        PelilaudanPiirtaja piirra = new PelilaudanPiirtaja();
        piirra.luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void cardsFromSprite() throws IOException {
        BufferedImage bigImg = ImageIO.read(new File("src/main/resources/Images/temp_cards_sprite.gif"));

        final int width = 72;
        final int height = 100;
        final int rows = 4;
        final int cols = 13;

        sprites = new BufferedImage[rows * cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sprites[(i * cols) + j] = bigImg.getSubimage(
                        j * width,
                        i * height,
                        width,
                        height
                );

            }
        }
        BufferedImage img = bigImg.getSubimage(0, 400, width, height);
        tausta = new ImageIcon(img);

        img = bigImg.getSubimage(147, 400, width, height);
        tyhja = new ImageIcon(img);
    }

}
