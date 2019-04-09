# Yatzy

Simppeli Yatzy-peli 1-4 pelaajalle, graafisella käyttöliittymällä. Peli toimii aluksi vain paikallisesti.

## Dokumentaatio

[Työaikakirjanpito](https://github.com/aleksinuora/ot-harjoitustyo/blob/master/ot-harjoitustyo/dokumentaatio/tuntikirjanpito.md)

[Vaatimusmäärittely](https://github.com/aleksinuora/ot-harjoitustyo/blob/master/ot-harjoitustyo/dokumentaatio/vaatimusmaarittely.md)


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
