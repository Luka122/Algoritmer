Skip to content
Navigation Menu
Luka122
/
Algoritmer

Type / to search
Code
Issues
Pull requests
Actions
Projects
Wiki
Security
Insights
Settings
Files
Go to file
t
algoritme_oppgaver/algoritmer-oblig2-main
build
gradle/wrapper
src
README.md
build.gradle
gradlew
gradlew.bat
img.png
img_1.png
settings.gradle
BreadcrumbsAlgoritmer/algoritme_oppgaver
/algoritmer-oblig2-main/
Directory actionsAdd file
More options
Latest commit
Luka122
Luka122
Add files via upload
1e9760c
 · 
now
History
BreadcrumbsAlgoritmer/algoritme_oppgaver
/algoritmer-oblig2-main/
Folders and files
Name	Last commit message	Last commit date
parent directory
..
build
Add files via upload
now
gradle/wrapper
Add files via upload
now
src
Add files via upload
now
README.md
Add files via upload
now
build.gradle
Add files via upload
now
gradlew
Add files via upload
now
gradlew.bat
Add files via upload
now
img.png
Add files via upload
now
img_1.png
Add files via upload
now
settings.gradle
Add files via upload
now
README.md
Review Assignment Due Date

Obligatorisk Oppgave 2 i DATS2300 - Algoritmer og Datastrukturer
Denne oppgaven er en innlevering i DATS2300 - Algoritmer og datastrukturer. Den er innlevert av følgende studenter:

Luka Dordevic, s374915
Arbeidsfordeling
I oppgaven har vi hatt følgende arbeidsfordeling:

s374915 har gjort oppgave 0, 1, 2, 3, 4, 5, 6, 8
Oppgavebeskrivelser
Oppgave 1
I konstruktøren vil elementer bli lagt til i listen så lenge de ikke er null-verdier. Først legges den første noden til, som samtidig blir både hode og hale, og antallet oppdateres. Deretter følger tillegg av nye noder og antallet blir oppdatert i prosessen.

Oppgave 2
I toString-metoden brukte jeg en løkke som går gjennom hele lenkede listen. og deretter samlet jeg verdiene i en tekststreng ved hjelp av en StringBuilder. Dette ga meg en streng som representerer verdiene i listen i riktig rekkefølge, og jeg la til klammeparenteser rundt den. I omvendtString metoden gjorde jeg omtrent det samme som i toString, men i stedet for å starte fra hodet, begynte jeg fra halen og gikk gjennom listen baklengs. Dette resulterte i en streng som viste verdiene i motsatt rekkefølge.

Oppgave 3
jeg implementerte finnNode for å lokalisere noden ved den gitte indeksen. Deretter bruker både hent(int indeks) og oppdater(int indeks, T verdi) denne metoden for å hente eller oppdatere verdien til noden på den spesifikke indeksen. Når det gjelder opprettelse av sublister, har jeg opprettet et Object-array som midlertidig lagrer elementene som skal inkluderes i sublisten. Jeg kopierer verdiene fra hovedlisten til dette arrayet, utfører en nødvendig typekonvertering og oppretter en ny liste ved å bruke dette arrayet. Sublisten er uavhengig av hovedlisten, noe som betyr at endringer i en liste ikke vil påvirke den andre.

Oppgave 4
For å finne indeksen til en bestemt verdi i listen, begynner metoden med å sjekke om verdien som er oppgitt som parameter, er null. Hvis den ikke er null, starter den en iterasjon gjennom listen ved hjelp av en iterator. Den sammenligner verdien til hver node med den gitte parameterverdien. Hvis den finner en match, returnerer den indeksen til denne noden. Dette gjør det mulig å raskt finne plasseringen til en spesifikk verdi i listen

Oppgave 5
For en tom liste blir både hode og hale noden satt til den nye verdien. Når den første noden fjernes da blirt den forrige pekeren justert fra gamle hode noden til den nye hode-noden. Hvis den siste noden fjernes, oppdateres neste-pekeren til gamle hale-noden til den nye hale-noden. Når en "mellomnod"e fjernes da oppdateres pekerne til nodene rundt den og noden med den gitte indeksen flyttes bakover.

Oppgave 6
T fjern(int indeks): Metoden håndterer fjerning av elementer ved å først validere indeksen. Hvis listen er tom, indeksen er utenfor grensene, eller indeksen er mindre enn null, kaster den en unntak. Deretter finner den den korteste veien til indeksen ved å sammenligne indeksen med halvparten av listen. Den justerer deretter pekerne avhengig av om indeksen er i midten, på slutten eller i begynnelsen.

boolean fjern(T verdi) metoden ligner fjern(int indeks), men den må validere hver iterasjon for å finne riktig verdi. Sjekken for hvor verdien er i listen forblir den samme, og metoden tar vare på å justere pekerne korrekt når den finner og fjerner verdien.

Oppgave 8
sjekket om å kast eunntak i metoden var nødvendig. lagde temp variabel for å kunne iterere videre. returnerte med konstruktør. brukte konstruktør for finnnode metoden slik at jeg kunne finne riktig node. videre returnerte jeg en ny iterator ved hjelp av konstruktør i finnnode metode.

img_1.png

Algoritmer/algoritme_oppgaver/algoritmer-oblig2-main at main · Luka122/Algoritmer
