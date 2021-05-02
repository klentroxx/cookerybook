# Szakácskönyv beadandó

A projekt indításához szükséges lépések:
1. Be kell állítani az SQLite-nak a fájlrendszerbeli elérési útvonalát a cookerybook-core/src/main/resources/application.properties fájlban.
2. Amikor a cookerybook-web modul futtatható osztályát hozzáadjuk a konfigurációkhóz (Add | Edit configuration) az intellij-n belül, be kell állítani a Working Directory-t %MODULE_WORKING_DIR%-ra.
3. Telepíteni kell a maven dependency-ket és elindítani a projektnek adott részeit (web-et vagy desktop-ot)