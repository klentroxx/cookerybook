# Szakácskönyv beadandó

A projekt indításához szükséges elemek:
- Mindenképpen szükséges engedélyezni az annotáció feldolgozást, ugyanis az osztályok használnak annotációkat.
- Telepíteni kell a projet függőségeit. Ezeket a modulok egyéni illetve a fő pom.xml-ben találhatjuk meg.
- Szükség lesz egy MariaDB típusú adatbázis szerverre.
  - A szerverhez az egyéni ajánlásom a XAMPP
  - A szükséges SQL fájlt a projekt gyökerében cookery_book_db.sql néven található. Ezt importáljuk be a saját adatbázisunkba.
    - Importáláskor figyeljünk oda a táblák karakterkódolására és hogyha van lehetőségünk, válasszuk ki az utf8mb4 kódolást. Ellenkező esetben a program helytelen működést eredményezhet.
  - A cookerybook-core modulon belül a hu.cookerybook.dao package-ben van egy Labels osztály, amelyben át kell írni a final adattagok értékeit az adatbázisunk értékeire (csatlakozási sztring, adatbázis felhasználó és ennek a jelszava)

