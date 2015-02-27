package Klondike.GraafinenUi;

import java.awt.*;
import javax.swing.*;


/**
 * TODELLA KESKEN.
 *
 * @author Zonsu
 */
public class Kayttoliittyma implements Runnable {

    static JFrame frame;

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

}
