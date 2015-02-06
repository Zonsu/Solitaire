
package Klondike.GraafinenUi;

import java.awt.*;
import javax.swing.*;

/**
 * TODELLA KESKEN. 
 * 
 * @author Zonsu
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;

    public Kayttoliittyma() {
    }

    @Override

    public void run() {
        frame = new JFrame("Klondike Solitaire");
        frame.setPreferredSize(new Dimension(800, 500));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        GridLayout layout = new GridLayout(2, 7);
        container.setLayout(layout);

        JTextArea textArea11 = new JTextArea("Eka rivi eka");
        JTextArea textArea12 = new JTextArea("Eka rivi toka");
        JTextArea textArea13 = new JTextArea("Eka rivi kolmas");
        JTextArea textArea14 = new JTextArea("Eka rivi neljäs");
        JTextArea textArea15 = new JTextArea("Eka rivi viides");
        JTextArea textArea16 = new JTextArea("Eka rivi kuudes");
        JTextArea textArea17 = new JTextArea("Eka rivi seitsemäs");

        JTextArea textArea21 = new JTextArea("Toka rivi eka");
        JTextArea textArea22 = new JTextArea("Toka rivi toka");
        JTextArea textArea23 = new JTextArea("Toka rivi kolmas");
        JTextArea textArea24 = new JTextArea("Toka rivi neljäs");
        JTextArea textArea25 = new JTextArea("Toka rivi viides");
        JTextArea textArea26 = new JTextArea("Toka rivi kuudes");
        JTextArea textArea27 = new JTextArea("Toka rivi seitsemäs");

        container.add(textArea11);
        container.add(textArea12);
        container.add(textArea13);
        container.add(textArea14);
        container.add(textArea15);
        container.add(textArea16);
        container.add(textArea17);

        container.add(textArea21);
        container.add(textArea22);
        container.add(textArea23);
        container.add(textArea24);
        container.add(textArea25);
        container.add(textArea26);
        container.add(textArea27);

    }

    public JFrame getFrame() {
        return frame;
    }

}
