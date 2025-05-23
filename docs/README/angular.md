ANGULAR

https://drive.google.com/drive/folders/1zldR_A6nu-6Iz7lpKfHgrrDzVbPiIceW?usp=sharing

Angular
--> dedicato alle single page application

Peculiarità angular versione (2+) (versione 8)

Angular ha un framework + ambiente di sviluppo


nodeJS --> è uno strumento che nasce per darci un runtime javascript
con nodejs posso avere lib in js che si eseguono a prescindere dal browser
ha un node package manager -> gestore di pacchetti (similar maven)

da cmd 
node --version per vedere se c'è
ng --version per vedere se installato

ambiente di lavoro
VsCode
nodejs

npm cache verify
npm uninstall -g @angular/cli   (-g identifica il fatto sia globale)

npm install installa librerie
	npm install -g @angular/cli@8.3.24 --> il parametro (-g) installazione globale


ng --> rimappa a riga di comando
ng serve starta il sever nella installazione globale


--ambiente di sviluppo 
Angular cli (command line interface)

- scaffolding (generazione file e cartelle per progetto un template etc )
- transpier (build) traduce il typescript in javascript
- server di sviluppo (preview, watch, live reload)
- pacchettizzazione 
- linting (regole di costrutto del codice)
- unit testing / e2e testing
				e2e serve eventualmente per i test
				node_moduls librerie etc

Tutto è dispobnibile col comando ng 


-------------------------------------------------
Inizializzare un progetto
ng new <nome progetto> (nella cartella che sarà la home del progetto)

ng serve starta il sever nella installazione globale va fatto nella cartella dove sta il progetto

Struttura progetto

in node_moduls abbiamo la roba angular 
e2e la roba per il test
src i nostri codici (lavoriamo qui)
angular.json --> configurazione di angular cli 
package.jeson --> configurazione per node del progetto
tsconfig.json -> configurazione del transpiler (traduzione e compilatore)


-- dentro src
index è la pagina
-- dentro src/assets per le risorse statiche (img etc)
-- dentro src/enviroments differenziazione per ambiente(default prod etc)

-- dentro src/app 
	Modulo   : contenitore di  elementi applicativi
	component: porzione di pagina con un controller 


--
Typescript

Tipizzazione paradigma 00 (classi, tipi, eriditarieta  modificatori accesso metodi di classe, import)
traducibile a js a versione arbitraria con lo stesso codice genero js per varie versioni
anche vecchie
https://www.typescriptlang.org/play/index.html


Sintassi component
	{{}} espressioni interpolazioni

pipe  formattatori esempio 0.00 per i numeri
	<b>Prezzo</b>  {{prodotto.prezzo | currency:'EUR':false }}

direttive
	strutturali (aggiungono o tolgono nodi(tag) alla pagina  *ngTemplate (ngIf ngFor))
		<div *ngIf="prodotti.length" > vuole una condizione booleana
		<div *ngFor="let prodotto of prodotti">

	attributo (modificano attibuti nel nodo ospite [] es cambio di style)
		<div *ngFor="let prodotto of prodotti" [style.background-color]="prodottoSelezionato?.id==prodotto.id ? 'cyan' :'' " >

	event binding (serve per legare un evento a una funzione nel component)			

	

ng generate (per generare moduli component nuovi)
	D:\myworkspace\corso-angular\mio-negozio>ng generate module vetrina
        D:\myworkspace\corso-angular\mio-negozio>ng generate component vetrina/negozio



per aggiungere bootstap
	npm install bootstrap




--gestione template 
expression {}
direttive: sintassi html-ligt(attributi tag) agganciano funzionalità framework
	strutturali:aggiungono o tolgono nodi(tag) alla pagina  *ngTemplate (ngIf ngFor)
	attributo:modificano attibuti nel nodo ospite [] es cambio di style
	event binding : associa un evento ad un metodo ()
 
DtaBinding
	sincroizzazione tra template e controller
	one way -> se info da template al controller o viceversa
        two way -> info sempre sincrovizzata T <--> C 

elementi di input --> non contenute in modulo base

template driven (FormsModule) -> lavoriamo con direttive (lato template)
model-driven (ReactiveFormsModule) -> campi di classe del component


---------------------------------------------------------------
giorno 2


creo il service
ng generate service model/prodotto  


Servizio injectable
Classe istanziata dal framework
e iniettabile agli altri elementi

Chiamate asincrone
simulare server dati -> json-server  
		npm install -g json-server
 		json-server db.json
                json-server db.json per startarlo


D:\myworkspace\corso-angular\mio-negozio>json-server db.json


in angular
HttpClient --> HttpClientModule 
rxjs Observqable, operatori

Routing (simulazione di navigazione pagina)

-> aggiungere modulo legato al routing
-> configurare le "rotte" col metodo forRoot
-> aggiungere il routerOutlet
-> aggiungere i routerLink 


--elemento Form
2 modalita
	Template driven -> lavora prevalentemente con html e direttive
	FormsModule

	Model driven -> lavora con classi dedicate a rappresentare il form
	ReactiveFormsModule


stato del form/campo
	valido/invalido 
	dirty pristine 
	touched untouched

Variabili a livello di form group control corrispondenti allo stato


http://localhost:4200/ord/ordine/87654aed-9fb7-5c97-b8d5-a1dd5cba33ce

 D:\myworkspace\CPASS\cpassweb\utility_scripts> .\import-swagger.bat   (genera  le calassi dallo ymlperi i servizzi) 

 D:\myworkspace\CPASS\cpassweb\utility_scripts> .\compile.bat (genera la parte js la copia sul BE)

 npm start starto il server

Passi preliminari per un nuovo component 

#creo un nuovo modulo
npx ng g m bo --dry-run
creo il rooting.module
npx ng g m bo-routing 

#creo un nuovo component
npx ng g c smistamento-rms-csv
    ng g c avvia-intervento






gestisco il rooting
npm run extract-i18n

gestisco la lingua

come lo chiamo da un bottone



ANGULAR

https://drive.google.com/drive/folders/1zldR_A6nu-6Iz7lpKfHgrrDzVbPiIceW?usp=sharing

Angular
--> dedicato alle single page application

Peculiarità angular versione (2+) (versione 8)

Angular ha un framework + ambiente di sviluppo


nodeJS --> è uno strumento che nasce per darci un runtime javascript
con nodejs posso avere lib in js che si eseguono a prescindere dal browser
ha un node package manager -> gestore di pacchetti (similar maven)

da cmd 
node --version per vedere se c'è
ng --version per vedere se installato

ambiente di lavoro
VsCode
nodejs

npm cache verify
npm uninstall -g @angular/cli   (-g identifica il fatto sia globale)

npm install installa librerie
	npm install -g @angular/cli@8.3.24 --> il parametro (-g) installazione globale


ng --> rimappa a riga di comando
ng serve starta il sever nella installazione globale


--ambiente di sviluppo 
Angular cli (command line interface)

- scaffolding (generazione file e cartelle per progetto un template etc )
- transpier (build) traduce il typescript in javascript
- server di sviluppo (preview, watch, live reload)
- pacchettizzazione 
- linting (regole di costrutto del codice)
- unit testing / e2e testing
				e2e serve eventualmente per i test
				node_moduls librerie etc

Tutto è dispobnibile col comando ng 


-------------------------------------------------
Inizializzare un progetto
ng new <nome progetto> (nella cartella che sarà la home del progetto)

ng serve starta il sever nella installazione globale va fatto nella cartella dove sta il progetto

Struttura progetto

in node_moduls abbiamo la roba angular 
e2e la roba per il test
src i nostri codici (lavoriamo qui)
angular.json --> configurazione di angular cli 
package.jeson --> configurazione per node del progetto
tsconfig.json -> configurazione del transpiler (traduzione e compilatore)


-- dentro src
index è la pagina
-- dentro src/assets per le risorse statiche (img etc)
-- dentro src/enviroments differenziazione per ambiente(default prod etc)

-- dentro src/app 
	Modulo   : contenitore di  elementi applicativi
	component: porzione di pagina con un controller 


--
Typescript

Tipizzazione paradigma 00 (classi, tipi, eriditarieta  modificatori accesso metodi di classe, import)
traducibile a js a versione arbitraria con lo stesso codice genero js per varie versioni
anche vecchie
https://www.typescriptlang.org/play/index.html


Sintassi component
	{{}} espressioni interpolazioni

pipe  formattatori esempio 0.00 per i numeri
	<b>Prezzo</b>  {{prodotto.prezzo | currency:'EUR':false }}

direttive
	strutturali (aggiungono o tolgono nodi(tag) alla pagina  *ngTemplate (ngIf ngFor))
		<div *ngIf="prodotti.length" > vuole una condizione booleana
		<div *ngFor="let prodotto of prodotti">

	attributo (modificano attibuti nel nodo ospite [] es cambio di style)
		<div *ngFor="let prodotto of prodotti" [style.background-color]="prodottoSelezionato?.id==prodotto.id ? 'cyan' :'' " >

	event binding (serve per legare un evento a una funzione nel component)			

	

ng generate (per generare moduli component nuovi)
	D:\myworkspace\corso-angular\mio-negozio>ng generate module vetrina
        D:\myworkspace\corso-angular\mio-negozio>ng generate component vetrina/negozio



per aggiungere bootstap
	npm install bootstrap




--gestione template 
expression {}
direttive: sintassi html-ligt(attributi tag) agganciano funzionalità framework
	strutturali:aggiungono o tolgono nodi(tag) alla pagina  *ngTemplate (ngIf ngFor)
	attributo:modificano attibuti nel nodo ospite [] es cambio di style
	event binding : associa un evento ad un metodo ()
 
DtaBinding
	sincroizzazione tra template e controller
	one way -> se info da template al controller o viceversa
        two way -> info sempre sincrovizzata T <--> C 

elementi di input --> non contenute in modulo base

template driven (FormsModule) -> lavoriamo con direttive (lato template)
model-driven (ReactiveFormsModule) -> campi di classe del component


---------------------------------------------------------------
giorno 2


creo il service
ng generate service model/prodotto  


Servizio injectable
Classe istanziata dal framework
e iniettabile agli altri elementi

Chiamate asincrone
simulare server dati -> json-server  
		npm install -g json-server
 		json-server db.json
                json-server db.json per startarlo


D:\myworkspace\corso-angular\mio-negozio>json-server db.json


in angular
HttpClient --> HttpClientModule 
rxjs Observqable, operatori

Routing (simulazione di navigazione pagina)

-> aggiungere modulo legato al routing
-> configurare le "rotte" col metodo forRoot
-> aggiungere il routerOutlet
-> aggiungere i routerLink 


--elemento Form
2 modalita
	Template driven -> lavora prevalentemente con html e direttive
	FormsModule

	Model driven -> lavora con classi dedicate a rappresentare il form
	ReactiveFormsModule


stato del form/campo
	valido/invalido 
	dirty pristine 
	touched untouched

Variabili a livello di form group control corrispondenti allo stato


http://localhost:4200/ord/ordine/87654aed-9fb7-5c97-b8d5-a1dd5cba33ce

 D:\myworkspace\CPASS\cpassweb\utility_scripts> .\import-swagger.bat   (genera  le calassi dallo ymlperi i servizzi) 

 D:\myworkspace\CPASS\cpassweb\utility_scripts> .\compile.bat (genera la parte js la copia sul BE)

 npm start starto il server

Passi preliminari per un nuovo component 

#creo un nuovo modulo
npx ng g m bo --dry-run
creo il rooting.module
npx ng g m bo-routing 

#creo un nuovo component
npx ng g c smistamento-rms-csv
    ng g c avvia-intervento






gestisco il rooting
npm run extract-i18n

gestisco la lingua

come lo chiamo da un bottone



