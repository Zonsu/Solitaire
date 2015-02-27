package Klondike.GraafinenUi;


import java.awt.*;
import javax.swing.*;


/**
 * 
 *
 * @author Zonsu
 */
public class Kayttoliittyma implements Runnable {

    static JFrame frame;

    public Kayttoliittyma() {
        
        this.frame = new JFrame("Klondike Solitaire");
    }

    @Override

    public void run() {
        
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
