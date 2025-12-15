##DigiDens Backend (Monialaprojekti)
•Spring Boot -pohjainen backend-sovellus.
•Backendin tehtävänä on tarjota REST API -rajapinnat DigiDens-frontendille.

###Teknologiat
•Java 17
•Spring Boot
•Spring Data JPA
•Spring Security (kehitysvaiheessa avoin konfiguraatio)
•H2-tietokanta
•Maven

###Tietokanta
Sovellus käyttää kehitysvaiheessa H2 in-memory -tietokantaa.
Tietokanta luodaan automaattisesti sovelluksen käynnistyessä, eikä erillistä asennusta tarvita.
Mock-data (käyttäjät, kurssit, kurssitoteutukset ja testisuoritekortit) alustetaan käynnistyksen yhteydessä DataInitializer-luokan avulla.

###Käyttäjäroolit
Sovelluksessa on alustavasti kolme user-roolia: admin, teacher ja student.
Kirjautuminen on toteutettu backendissä, mutta kehitysvaiheessa rajapinnat ovat avoimia testaamisen helpottamiseksi.

###Asennus ja käynnistys
1. Kloonaa projekti
2. Siirry projektin juurikansioon (sama kansio kuin pom.xml) komentorivillä
3. Käynnistä sovellus komennolla mvn spring-boot:run
4. Sovellus käynnistyy osoitteeseen http://localhost:8080

###API-endpoint -esimerkki
•Hae suoritekorttipohjat: GET /api/taskCardTemplates

###Huom
Projekti on keskeneräinen ja tarkoitettu jatkokehitykseen ja testaamiseen, ei tuotantokäyttöön. Projektia ei ole integroitu uusimman Digidens frontend -version kanssa.

