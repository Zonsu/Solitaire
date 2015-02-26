/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klondike.GraafinenUi;

import Klondike.Pelilauta.Kortti;
import Klondike.Pelilauta.Korttipino;
import Klondike.Pelilauta.Pelilauta;
import Klondike.Pelilogiikka.KlikkausLaskuri;
import Klondike.Pelilogiikka.KortinSiirto;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author joqpaavo
 */
public class KorttipakanKuuntelija implements ActionListener {

    public KorttipakanKuuntelija() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Kortti kortti = KortinSiirto.kaannaKortti(0);
        System.out.println("Kuuntelijalle palautettiin: " + kortti);
        PelilaudanPiirtaja.piirraKaannetty(kortti);

    }

}
