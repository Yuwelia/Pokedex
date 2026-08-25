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


Dependencie Block in build.gradle soll so aussehen:
dependencies {
  implementation 'org.springframework.boot:spring-boot-h2console'
  implementation 'org.springframework.boot:spring-boot-starter-jdbc'
  implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
  implementation 'org.springframework.boot:spring-boot-starter-webmvc'
  // Source: https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
  implementation("org.apache.commons:commons-lang3:3.20.0")
  developmentOnly 'org.springframework.boot:spring-boot-devtools'
  runtimeOnly 'com.h2database:h2'
  testImplementation 'org.springframework.boot:spring-boot-starter-jdbc-test'
  testImplementation 'org.springframework.boot:spring-boot-starter-thymeleaf-test'
  testImplementation 'org.springframework.boot:spring-boot-starter-webmvc-test'
  testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
-> Load Gradle Changes (Elephant icon)


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