# Gestionale Aziendale

## Descrizione

Gestionale web sviluppato con Spring Boot per la visualizzazione e la gestione dei dati aziendali.

- **Utenti ADMIN**: possono visualizzare e modificare i dati.
- **Utenti USER**: hanno accesso in sola lettura ai dati.

## Funzionalità attuali

- Autenticazione e autorizzazione con Spring Security
- Gestione ruoli USER e ADMIN
- Criptazione sicura delle password con BCrypt
- Pagine protette in base al ruolo (admin può modificare, user solo leggere)

## Come avviare

Prerequisiti:
- Java 17+
- Maven
- Database configurato (MySQL, H2, ecc.)

Esegui:

```bash
mvn clean install
mvn spring-boot:run
```
Visita http://localhost:8080

Stato del progetto
⚠️ Work in progress — il progetto è in fase di sviluppo e alcune funzionalità potrebbero non essere ancora complete.

Contributi e suggerimenti sono benvenuti! Apri pure issue o pull request.

Autore: Luca16-95
