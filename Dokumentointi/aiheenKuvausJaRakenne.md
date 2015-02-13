##Aihemäärittely

**Työn nimi:** Solitaire

**Aihe:** Perus pasianssipeli eli klondike. Pelaajan tulee tyhjentää pelilauta lopetuspinoihin korttimaittain ja numerojärjestyksessä siirtelemällä niitä pelin sääntöjen mukaisesti.

**Pelaajan toiminnot:**

Aloitusvalikko:
* Uuden pelin aloitus
* Pistetaulukon tarkastelu
* Sovelluksen sulkeminen

Pelissä
* Pelin pysäyttäminen - Pelin voi pistää "pauselle" tai pelin voi laittaa taas jatkumaan
* Pelin lopettaminen - Pelin keskeytys, paluu aloitusvalikkoon
* Vihjeen pyytäminen tietokoneelta - Peli kertoo jos pelilaudalla on mahdollisia siirtoja
* Ennätyspisteiden tallentaminen - Pelin lopuksi

##Pelin rakenne

**Klondike.GraafinenUI**
	#Kayttoliittyma.java: Luokan metodia run() kutsumalla käynnistyy graafinen käyttöliittymä. Samalla kutsutaan piirtäjää.
	PeliLaudanPiirtaja.java: Jakaa pelilaudan 2 x 7 lokeroihin joihin pakat piirretään. Luokka alustaa pakkoihin oikeat määrät kortteja piirrettäväksi ja piirtää ne pelilaudalle.
	KentanKuuntelija.java: Kuuntelee käyttäkän klikkauksia ja välittää ne edelleen klikkauslaskurille.
	
**Klondike.Pelilauta**
	



##Pelin säännöt

**Pelin alkuasetelma:** Pelaajan tavoitteena on järjestää kortit neljään lopetuspinoon maittain järjestyksessä ässästä kuninkaaseen. Pelin alussa lopetuspinot ovat tyhjät. Pelaajalla on pakka
ja seitsemän aloituspinoa. Aloituspinot on järjestetty vierekkäin niin, että jokaisessa pinossa on aina yksi kortti enemmän kuin edellisessä. Tällöin ensimmäisessä pinossa on yksi kortti ja viimeisessä seitsemän.
Pinojen päällimäinen kortti käännetään kuvapuoli ylöspäin, muut kortit ovat pinossa kuvapuoli alaspäin.

**Pelin säännöt:** 
Pelaaja voi siirtää vain kuvapuoli ylöspäin olevia kortteja, joko pakasta toiseen pinoon tai lopetuspinoon, tai pinosta toiseen pinoon tai lopetuspinoon.
Pelaaja voi koska tahansa kääntää pakasta uuden kortin näkyviin, tällöin peittäen näkyvistä edellisen kortin. Jos pakassa ei ole enää kortteja, muodostetaan käännetyistä korteista uusi
pakka sitä välillä sekoittamatta. Pakasta nostettua korttia ei voi sijoittaa sinne takaisin.

Lopetuspinoon voidaan siirtää vain samaa maata olevia kortteja välittömässä numerojärjestyksessä. Tällöin ensimmäisen kortin pinossa tulee olla aina ässä, toisen kaksi jne. Lopetuspinosta
ei voi siirtää kortteja takaisin pelipinoihin.

Pelipinoihin voi pinota kortteja välittömässsä laskevassa numerojärjestyksessä niin, että peräkkäisten korttien värit eivät voi ole samat. Kuninkaan ollessa pakan isoin kortti
se voidaan sijoittaa vain tyhjään pelipinoon niin, että sen alle ei jää yhtään korttia. Pelipinon voi hajoittaa mistä kohtaa tahansa ja päälimmäisen osan voi sijoittaa toiseen 
paikkaan jos paikan ollessa pelisääntöjen mukainen. Korttia ei voi siirtää lopetuspinoon jos sen päällä on toisia kortteja.

**Pelin päätös:** Peli on voitettu ja päättyy, kun kaikki kortit on järjestetty lopetuspinoihin. Vaihtoehtoisesti pelaaja voi luovuttaa pelin jos hän kokee, että ei pysty sitä
voittamaan ja on ns. jumissa.

**Pistelasku:** Pelissä pisteitä saa sijoittamalla pakasta kortteja pelipinoihin ja siirtämällä kortteja lopetuspinoihin. Lopullinen pistemäärä suhteutetaan plein läpäisemiseen
käytettyyn aikaan.

