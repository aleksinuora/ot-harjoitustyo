# Yatzy

Simppeli Yatzy-peli 1-4 pelaajalle, graafisella käyttöliittymällä. Peli toimii aluksi vain paikallisesti.

## Dokumentaatio

[Käyttöohje](https://github.com/aleksinuora/ot-harjoitustyo/blob/master/ot-harjoitustyo/dokumentaatio/kayttoohje.md)

[Release](https://github.com/aleksinuora/ot-harjoitustyo/releases/tag/yatzy)

[Vaatimusmäärittely](https://github.com/aleksinuora/ot-harjoitustyo/blob/master/ot-harjoitustyo/dokumentaatio/vaatimusmaarittely.md)

[Testausdokumentti](https://github.com/aleksinuora/ot-harjoitustyo/blob/master/ot-harjoitustyo/dokumentaatio/testausdokumentti.md)

[Työaikakirjanpito](https://github.com/aleksinuora/ot-harjoitustyo/blob/master/ot-harjoitustyo/dokumentaatio/tuntikirjanpito.md)


## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Checkstyle

Tiedoston checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

Virheilmoitukset selviävät avaamalla tiedosto _target/site/checkstyle.html_

### JavaDoc

JavaDoc luodaan komennolla

```
mvn javadoc:javadoc
```
kansioon target/site/apidocs/index.html

### Jar

Jarin voi halutessaan luoda itse komennolla

```
mvn package
```
