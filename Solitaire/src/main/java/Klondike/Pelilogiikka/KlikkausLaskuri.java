package Klondike.Pelilogiikka;

/**
 * Luokka pitää kirjaa kenttien saamasta klikkausten määristä ja tekee niiden
 * perusteella päätöksen yritetäänkö korttia siirtää ja minne.
 *
 * @author Zonsu
 */
public class KlikkausLaskuri {

    //Klikkauksia korkeintaan 2 samaan aikaan, eli paikat joiden välillä siirto tehdään.
    static int paikka1;
    static int paikka2;

    public KlikkausLaskuri() {
        this.paikka1 = 0;
        this.paikka2 = 0;
    }

    /**
     * Metodi kuuntelee korttien klikkauksia. Jos peräkkäin klikataan kahta eri
     * korttia, lähdetään suorittamaan metodia joka yrittää siirtää korttia.
     *
     * @param tarkistetaan missä kohdassa olevaa korttia on klikattu
     */
    public static void lisaaKlikkaus(int paikka) {

        if (paikka1 == 0) {
            paikka1 = paikka;

        } else if (paikka1 == paikka) {
            paikka1 = 0;

        } else {
            paikka2 = paikka;

            /*
               Kutsutaan metodia joka yrittää siirtää korttia.  
            */
            paikka1 = 0;
            paikka2 = 0;
        }

    }

}
