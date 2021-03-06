package Klondike.Pelilauta;

import Klondike.GraafinenUi.KorttienKuvat;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.swing.ImageIcon;

/**
 * Tästä luokasta generoidaan pelissä käytettävät kortit. Kortin arvo voi olla
 * välillä 1-13 ja maa hertta, pata, ruutu tai risti. Sen väri on musta tai
 * punainen.
 *
 * @author Zonsu
 */
public class Kortti {

    private String maa;
    private String vari;
    private int arvo;
    private BufferedImage kuva;

    public Kortti(int arvo) {
        this.maa = "";
        this.arvo = arvo;
    }

    public String getMaa() {
        return maa;
    }

    public void setMaa(String maa) {

        this.maa = maa;

        if (this.maa == "Hertta" | this.maa == "Ruutu") {
            this.vari = "punainen";
        } else {
            this.vari = "musta";
        }
    }

    public int getArvo() {
        return arvo;
    }

    public String getVari() {
        return vari;
    }

    public void setArvo(int arvo) {
        this.arvo = arvo;
    }
    
    public void setKuva(BufferedImage kuva) {
        this.kuva = kuva;
    }
    
    public BufferedImage getKuva() {
        return this.kuva;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kortti other = (Kortti) obj;
        if (!Objects.equals(this.maa, other.maa)) {
            return false;
        }
        if (this.arvo != other.arvo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getMaa() + " " + getArvo();
    }

}
