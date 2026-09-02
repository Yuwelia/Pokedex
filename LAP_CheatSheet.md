Projekt erstellen
- New Project
  - Language = Java
  - Build System = Gradle-Groovy
  - JDK = 21
- Dependencies
  - Spring Web
  - Thymeleaf
  - JDBC API
  - H2 Database
  - Spring Boot DevTools
- Create

application.propertie:
- spring.mvc.hiddenmethod.filter.enabled=true -> Nicht nur GET und POST sondern auch HTTP-Methoden wie PUT, PATCH und DELETE
- spring.h2.console.enabled=true -> H2 Datenbank Konsole
  spring.h2.console.path=/h2-console -> Mit welcher URL die Konsole erreichbar ist
  - http://localhost:8080/h2-console


Datenbank Schema erstellen
- neues File unter src/main/resources/schema.sql
- DROP TABLE IF EXISTS
- CREATE TABLE (alle Table die man braucht)
- CREATE SEQUENCE "nameDerTabelle_seq"
  MINVALUE x
  MAXVALUE x
  INCREMENT BY x;
  - -> für IDs


Datenbank Daten erstellen
- neues File unter src/main/resources/data.sql
- INSERT INTO (jeweilige table)

Packet Structure erstellen
- unter src/main/java/com/name/nameVomProjekt/
  - model -> z.B. Person mit Vorname, Nachname, ID, Adresse,... 
  - repository -> Datenbank aufrufe 
  - service -> Zwischen Station zwischen repository und web (baut Daten eventuell noch richtig zusammen oder gibt sie einfach weiter)
  - web -> ist dafür zuständig Daten z.B. auf einer Http seite anzuzeigen 

Über der Controller Klasse 
-> @Controller
@RequestMapping("/name vom Controller")

Über der Service Klase 
@Service

Über der Repository Klasse
@Repository

Am Anfang von einem xhtml File "<html xmlns:th="http://www.thymeleaf.org" lang="de">
"

Für Integrationstest (Controller) 
- @Autowired private MockMvcTester mockMvcTester; -> Hilfsklasse um Controller zu testen
- Über der Klasse
  - @SpringBootTest -> Setzt alles für Spring auf (@Repository/ @Service/ @Controller)
  - @AutoConfigureMockMvc -> Bereitet MockMvc vor zum verwenden im Test
  - @Transactional -> Das Datenbank Statements ein automatisches Rollback haben