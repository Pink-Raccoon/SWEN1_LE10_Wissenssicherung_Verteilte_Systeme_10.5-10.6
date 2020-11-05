# SWEN1_LE10_Wissenssicherung_Verteilte_Systeme_10.5-10.6

Dies ist der Quellcode für die Aufgaben 10.5 und 10.6 in der Wissensicherung.  

## Bilden und Ausführen der Applikation  
Die Applikation kann direkt als Maven-Projekt in eine IDE importiert werden. 

Für die Ausführung des GUI-Clients (Skript client2) muss ein JavaFX SDK installiert sein (https://openjfx.io/). Der Pfad auf die Libs kann direkt in den Skripts oder durch eine gesetzte Systemumgebungsvariable angegeben werden (Windows: ```%PATH_TO_JAVAFX%``` oder MacOS/Linux: ```$PATH_TO_JAVAFX```).  

Die Applikation kann wie folgt ausgeführt werden:
1. Bilden des Projektes in der IDE oder mit Maven: ```mvnw package``` (Windows) oder ```./mvnw package``` (MacOS, Linux)   
2. Starten des Servers (Skript in Root-Verzeichns): ```server``` (Windows) oder ```./server.sh``` (MacOS, Linux) 
3. Starten eines CLI-Clients (Skript in Root-Verzeichns): ```client1 <nickname> <server> [<chatroom>]``` (Windows) oder ```./client1.sh <nickname> <server> [<chatroom>]``` (MacOS, Linux) 
3. Starten eines GUI-Clients (Skript in Root-Verzeichns): ```client2 <nickname> <server> [<chatroom>]``` (Windows) oder ```./client2.sh <nickname> <server> [<chatroom>]``` (MacOS, Linux) 
4. Starten eines Web-Clients (im Browser): ```http://localhost:8080/ws/```

## Hints
* Für den Test der Applikation auf einem Rechner kann bei ```<server>``` einfach ```localhost``` angegeben werden.
