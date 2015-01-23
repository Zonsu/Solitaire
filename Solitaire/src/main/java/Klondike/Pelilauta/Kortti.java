/*
 Tästä luokasta generoidaan pelissä käytettävät kortit. Kortin arvo voi olla välillä 1-13 ja maa
 hertta, pata, ruutu tai risti.
 */
package Klondike.Pelilauta;

import java.util.Objects;

public class Kortti {

    private String maa;
    private int arvo;

    public Kortti(int arvo) {
        this.maa = "";
        this.arvo = arvo;
    }

    public String getMaa() {
        return maa;
    }

    public void setMaa(String maa) {
        this.maa = maa;
    }

    public int getArvo() {
        return arvo;
    }

    public void setArvo(int arvo) {
        this.arvo = arvo;
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
