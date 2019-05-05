# Testausdokumentti

Ohjelman yksikkötestit on toteutettu automatisoiduilla JUnit-testeillä ja järjestelmä/käyttöliittymätestit manuaalisesti.

## Yksikkötestit

Pakkauksen yatzy.logic.domain luokat on testattu kattavasti JUnit-testeillä. Käyttäjällä ei ole juurikaan mahdollisuuksia sotkea ohjelman toimintaa omilla syötteillään joten yksikkötesteissä ei ole huomioitu virheellisten syötteiden riskiä.

### Testauskattavuus

Sovelluksen testauksen rivikattavuus on kokonaisuudessaan 82% ja haaraumakattavuus 54%.

## Järjestelmätestaus

Käyttöliittymää on testattu kattavasti manuaalisella nakutuksella.

### Asennus

Sovellus on asennettu ja saatu toimimaan ohjeen mukaisesti Linux-ympäristössä, Javan versiolla OpenJDK 9.0.4.

### Toiminnallisuudet

Määrittelydokumentin mukaiset toiminnallisuudet toteutuvat. Nimikenttiä on testattu tyhjillä syötteillä, erikoismerkeillä ja duplikaateilla. Nappuloita on paineltu eri järjestyksissä ja useaan kertaan.
