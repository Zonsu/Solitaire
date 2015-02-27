package Klondike.GraafinenUi;
import javax.swing.*;
import java.util.*;

/*
Klondike Solitaire main class. Aloitetaan ohjelma kutsumalla graafista käyttöliittymää.
*/
public class Main {

    public static void main(String[] args) {
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttoliittyma);
    }
}
