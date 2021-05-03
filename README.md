# Szakácskönyv beadandó

## A program

A program egy elektronikus szakácskönyvet hivatott megvalósítani, melyet webes és desktop környezetben egyarán lehet használni.

## Features

### Desktop

- Felhasználó regisztráció
- Felhasználó bejelentkezés
- Felhasználói jogosultságokhoz kötött adatfelvitel (az adminon kívül senki)
- Mértékegységek feltöltése, módosítása és törlése
- Hozzávalók (alapanyagok) feltöltése, módosítása és törlése

### Web

- Felhasználó bejelentkezés
- Felhasználói jogosultságokhoz kötött oldalmegtekintés és műveletek
- Receptek készítése
- Receptek kilistázása
- "Spájz" funkció megvalósítása:
  - A felhasználók elmenthetik maguknak,hogy milyen alapanyagaik vannak otthon
  - Ha ugyanazt az alapanyagot ismét hozzáadják a listához, növekszik a meglévő mennyiség ("Bevásárlás" funkció)
  - Ha fogyasztanak, megadhatják, hogy mennyi maradt még otthon
  - Hogyha a megadott minimálisnál kevesebb alapanyaga marad az egyes fehasználóknak, jelzi feléjük

## Szükséges beállítások

1. Az SQLite adatbázis eléréséhez 2 helyen szükséges megadni az elérési útvonalát (mindkét helyre ugynazt):
    1. `cookerybook-core/src/main/resources/application.properties`
    2. `cookerybook-web/src/main/resources/application.properties`
2. Amikor a cookerybook-web modul futtatható osztályát hozzáadjuk a konfigurációkhóz (Add | Edit configuration) az intellij-n belül, be kell állítani a Working Directory-t %MODULE_WORKING_DIR%-ra, máskülönben nem fogja megtalálni a fájlokat a Spring.
3. A rootban található pom.xml-re `mvn clean package` és már indítható is a desktop vagy a web. (vagy mindkettő)

## Egyéb

Sima felhasználót a desktop-os regisztrációnál tudsz létrehozni, azonban az admin-hoz már bele kell nyúlni az adatbázisba. Azért, hogy ezzel ne legyen probléma, az admin felhasználó:
> Felhasználónév: admin\
> E-mail: admin@example.com\
> Jelszó: admin

[Github Repo](https://github.com/klentroxx/cookerybook)