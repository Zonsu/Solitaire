**Klondike Solitaire**
*Rakennekuvaus*

Ohjelma on jaettu kolmeen pääluokkaan, graafiseen käyttöliittymään, pelilogiikaan ja olioihin.
 
Ohjelma alkaa kutsuttaessa graafista käyttöliittymää, jossa piirretään pelille alustaksi Swing 7 x 2 gridlayout. Generoidaan 52 kortin korttipakka johon talletetaan Kortti oliot. Pakka sekoitetaan ja lisätään pino-olioihin. Pinot määritellään indeksin mukaan 0-7 käänneetyihin ja nurin oleviin kortteihin sekä maalipinoihin. Piirretään yhteensä 14 pystysaraketta joihin kortit lisätään. Käännetyt kortit ovat JButton olioita, muut kuvat JPaneleita. Kuvat tuodaan spritesta ja jokaiselle kortille annetaan oma kuva.

Jokaisella käännetyllä kortilla eli JButtonilla on klikkauksenkuuntelija, sekä korttipakalla omansa. Kortit tunnistetaan klikkauksen koordinaattien perusteella. Korttia klikatessa kasvatetaan klikkausLaskuria ja odotetaan toista klikkausta. Kun on molemmat klikkaukset on saatu, kysytään ensin pelilogiikalta onko siirto sallittu. Jos on sallittu, tehdään ensin siirto pelilogiikan puolella. Tämän jälkene kutsutaan siirrolle sopivaa piirtäjän metodia.

Alarivin kentistä ei voi siirtää pakkaan tai dicard pakkaan. Maalipinoista ei voi siirtää kortteja pois. Kortti lisätään aina päälimmäiseksi. Jos pakan päällä ei ole enää oikein käännettyjä kortteja, käännetään paljastuneesta nurin pinosta uusi kortti jos siellä sellaisia on. Maalipinoihin voi siirtää vain samaa maata järjestyksessä 1-13. Pelipöydällä voi siirtää kortin vain eri väriä ja yhtä isomman olevan kortin päälle. Kuningas voidaan siirtää vain tyhjentyneeseen pinoon.

Pakasta voidaan kääntää uusi kortti discard pinoon niin kauan kuin siellä niitä on, jonka jälkeen discard pino käännetään uudeksi pakaksi. Discard pinosta voidaan siirtää päälimmäinen kortti pelin sääntöjen mukaisesti.

Peli päättyy kun kaikki kortit ovat maalipinoissa.
