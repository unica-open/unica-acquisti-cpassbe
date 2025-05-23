 cd /c/myworkspace/cpass/csi-cpass/cpassbe

#COMPILAZIONE JENKINS
https://jenkins-cd.toolchain.csi.it

# ADA: 
https://ada.nivolapiemonte.it/deployer/deployments

# VERIFICA DELLA BONTA' DEL CODICE
https://jenkins-ci.csi.it/job/CPASS/
http://sonarqube.csi.it

# Repository Artifactory
https://repo.ecosis.csi.it/artifactory/webapp/#/artifacts/browse/tree/General/procurement/cpass
http://repo.ecosis.csi.it/artifactory/webapp/#/artifacts/browse/tree/General/procurement/cpass/ 
http://repo.ecosis.csi.it/artifactory/simple/procurement/cpass/rp-01/1.0.0/UI/prod/


#REPOSITORY-----------------------------
http://repo.ecosis.csi.it/artifactory/webapp/login.html?1 

#Verificare le licenze
.\mvnw clean package -P add-license
per il FE
C:\myworkspace\cpass\cpassweb\docs\utilities\license> node .\apply-license.js


RILASCIO IN PRODUZIONE
----------------------
1) Aggiornare su anagrafica prodotti versione prodotto e versioni componenti da rilasciare nonchè la relativa 
documentazione che necessita di aggiornamento.

SOFTWARE APPLICATIVO:
2) Committare/pushare il codice sorgente su gitlab e su repository del codice Pl per ogni componente oggetto di rilascio indicando nel 
commento "Rilascio in produzione versione prodotto <versione prodotto> versione u.i. <versione-ui>" .

3) Creare un tag con nome CONSEGNA-<versione componente>-<numero release a tre cifre, partendo da 001> (es CONSEGNA-1.0.0-00)
"Rilascio in produzione versione prodotto <versione-prodotto> u.i.: <elenco delle sole ui oggetto di rilascio con nome-versione (es. cpassbe-1.2.0, cpassrepeng-1.1.2 ... )>"

4) Eseguire su Jenkins il job cpass_masterjob presente sotto la cartella RELEASE_PROD che, a partire dall'ultimo tag di tutte le componenti,
genera i pacchetti delle u.i., anche quelli non oggetto di rilascio, e li pubblica tutti, con le rispettive versioni, su artifactory nel path:
procurement\cpass\CB-release\<linea cliente>\<PRODUCT_VERSION>\UI

COOKBOOK:
5) - Modificare il file deploy_version_constants.rb presente sotto la directory attributes del cookbook ssa184_cpass 
impostando la versione prodotto e solo le versioni delle u.i oggetto di rilascio per l'ambiente di produzione.
   - Modificare la versione del cookbook presente nel file metadata.rb aggiornando la seconda delle tre cifre della versione. 

6) Committare/pushare il cookbook su gitlab indicando nel commento "Rilascio in produzione versione prodotto x.y.z", 
stesso commento da utilizzare anche per la creazione del tag con nome composto dalle sole cifre della versione del cookbook (es. 1.3.0).
N.B.: la versione del cookbook potrebbe non coincidere con la versione del prodotto.  

7) Eseguire su jenkins sotto CPASS/RELEASE_PROD il job envcb_ssa184_cpass_release che, a partire dall'ultimo tag creato su gitlab
e dal valore impostato per il parametro PRODUCT_VERSION, compila, crea e pubblica il cookbook su artifactory nel path 
procurement\cpass\CB-release\<PRODUCT_VERSION>\ssa184_cpass-<versione-cookbook>.sh

8) Creare un nuovo deployment su ada deployer selezionando il cookbook creato al punto precedente ed associarlo 
ai nodi di produzione, al termine della creazione il deployment sarà visibile nella lista dei deployment con il nome del cookbook
associato ad ogni nodo.
Quindi procedere al deploy selezionando i pacchetti relativi ai nodi su dove fare il deploy.

---------------------------------------------
#DEPOSITO sorgenti su repository PL
---------------------------------------------
dal ramo di sviluppo (es dev)
git checkout dev
git pull
git fetch -va --prune

Verificare le licenze
.\mvnw clean package -P add-license
per il FE
C:\myworkspace\cpass\cpassweb\docs\utilities\license> node .\apply-license.js


git checkout master
git pull
git fetch -va --prune
- allineare a origin/master ( git merge --ff-only origin/master )
- allienare a origin/dev    ( git merge --no-ff --no-commit origin/dev )
- verificare di avere tutto (può essere buona cosa lanciare a questo punto lo script delle licenze)

#allineare la revision sui vari pom.xml e su package.json, package-lock

Rilascio in produzione versione prodotto 1.18.0 versione u.i. 1.18.0

git add .
git commit -m "Rilascio in produzione versione prodotto 2.0.0 versione u.i. 2.0.0"
git commit -m "Rilascio in produzione versione prodotto <versione prodotto> versione u.i. <versione componente>"
(esempio git commit -m  Rilascio in produzione versione prodotto 1.1.0 versione u.i. 1.1.0)

- taggare con nome
CONSEGNA-<versione componente>-<numero release a tre cifre, partendo da 001>
(esempio git tag -a "CONSEGNA-1.1.0-002" -m "CONSEGNA-1.1.0-002")


git tag -a "TEST-2.1.0-012" -m "CONSEGNA-2.1.0-012"

- git push --tags ( pusha tutti i tag che ho in locale) 
  git push  (pusha il sorgente) 
- spostarsi su dev
git checkout dev
- allineare dev a master ( git merge --ff-only origin/master )


# ZONA FUORI PL 
# DEPOSITO sorgenti su gitlab  
 // $ git config --list --show-origin | grep user

#da farsi solo la prima volta con il proprio nominativo e la propria mail
 git config user.name "BENEDETTO ANTONINO 71027"
 git config user.email antonino.benedetto@consulenti.csi.it
 git config credential.helper '' (per ogni repository git 1 sola volta)
 
 --clonare i progetti (da farsi solo la prima volta in una cartella di appoggio da dove poi si faranno i rilasci dedicati a gitlab)
 git clone https://gitlab.csi.it/prodotti/cpass/cpassfe.git --config core.autocrlf=input
 git clone https://gitlab.csi.it/prodotti/cpass/cpassbe.git --config core.autocrlf=input
 git clone https://gitlab.csi.it/prodotti/cpass/cpassrepeng.git --config core.autocrlf=input
 git clone https://gitlab.csi.it/prodotti/cpass/cpassdb.git --config core.autocrlf=input
 git clone https://gitlab.csi.it/prodotti/cpass/cpassreptpl.git --config core.autocrlf=input
 
 git clone https://gitlab.csi.it/prodotti/portrilev/portalrilev.git --config core.autocrlf=input
 
 Per ogni progetto in rilascio:
- fetchare/pullare l'ultima versione del pacchetto che verrà allienato al codice precedentemente salvato e taggato su repository PL
  
  Da git bash
  cd /d/myworkspace/CPASS/cpass-csi/cpassbe
  git checkout master
  git merge --ff-only origin/master
  git merge --no-ff  dev
   
  git pull
  git fetch -va --prune

  copiare i sorgenti dal repository Pro Logic e sovrascrivere quelli di CSI 
  (conviene eliminare quelli di CSI e caricare i nostri)
  (per il FE non copiare le cartelle node_modules e utility_scripts)
  verificare di avere tutto e che le versioni siano coerenti

- git add    -A ( aggiunge tutto anche nelle cartelle padre) 
- git commit -m "Rilascio in produzione versione prodotto <versione prodotto> versione u.i. <versione componente>"
  (esempio git commit -m  Rilascio in produzione versione prodotto 1.1.0 versione u.i. 1.1.0)

git commit -m "Rilascio in produzione versione prodotto 2.0.0 versione u.i. 2.0.0"

- taggare con nome CONSEGNA-<versione componente>-<numero release a tre cifre, partendo da 001>
  (esempio git tag -a "CONSEGNA-1.0.0-001" -m "CONSEGNA-1.0.0-001")

git tag -a "TEST-2.0.0-054" -m "TEST-2.0.0-054"

- dovessi sbaliarmi per cancellare
git tag -d CONSEGNA-2.1.0-008
git push origin --delete CONSEGNA-2.1.0-008



- dovessi sbaliarmi per cancellare
  git tag -l lista dei tag locali
  git ls-remote --tags tag pushati      
 
  $ git remote -v controlla dove si va a pushare i sorgenti
  

- git push --tags ( pusha tutti i tag che ho in locale) 
  git push  (pusha il sorgente) 
 
NB. le credenziali richieste per il push son quelle di DOMNT


#RILASCIO PER PROD CON JENKINS E ADA

-------------------------------------
-------------Compilazione -----------
-------------------------------------
https://jenkins-cd.toolchain.csi.it
ci si logga con us e pw di DOMNT

cercarsi il prodotto CPASS
Zoppa$48

RELEASE
  CPASS_MASTERJOB 

--Build with Parameters
  compilare il form
  spuntare solo le componenti che si vogliono compilare	 
 
 #Repository artefatti
 http://repo.ecosis.csi.it/artifactory/webapp/#/artifacts/browse/tree/General/procurement/cpass/ 
 http://repo.ecosis.csi.it/artifactory/simple/procurement/cpass/rp-01/1.0.0/UI/prod/
  
--------------------------DEPLOY ADA ---------------------   
cancello i tag da locale
git tag -l | xargs git tag -d && git fetch -t
git log --oneline --all --graph

ADA: 
https://ada.nivolapiemonte.it/deployer/deployments
usare credenziali mail csi

in ricetta 
deploy_version_constants ci sono le versioni e l'ente da modificare se cambia versione e/o entemi basta 
sul metadata

tag -a "CONSEGNA-1.0.0-001" -m "CONSEGNA-1.0.0-001"

#-----------------------REPOSITORY-----------------------------
# http://repo.ecosis.csi.it/artifactory/webapp/login.html?1  --
#--------------------------------------------------------------

 
--------------AGGIORNAMENTO RICETTA -----------------

Aggiornare la versione di prodotto nel file  \metadata.rb (version);

Aggiornare le versioni delle componenti da rilasciare nel file \atribute\deploy_version_constants.rb 

Verificare per ogni nodo (test e produzione) la "run_list" indicata.
I file dei nodi sono sotto \chef-repo\nodes . Il nome del file è parlante, indica infatti il nodo a cui si riferisce.
	wf1-cpass.site01.nivolapiemonte.it.json -> wildfly di produzione per linea cliente RP
	wfrupar1-cpass.site01.nivolapiemonte.it.json -> apache di produzione per linea cliente RP
	wf1-mult-cpass.site01.nivolapiemonte.it -> wildfly di produzione per linea cliente MULT
	ap1-rupmult-cpass.site01.nivolapiemonte.it -> apache di produzione, esposizione rupar, per linea cliente MULT  


   inst_wf001 -installazione del server nodo 1, da eseguire solo se ci sono cambiamenti infrastrutturali , step con baco nella creazione del ds (eseguire solo se necessario).
   inst_wf002 - installazione del server nodo 2, da eseguire solo se ci sono cambiamenti infrastrutturali , step con baco nella creazione del ds (eseguire solo se necessario)
   deploy_wf001 - deploy della componente be che serve al fe
   deploy_wf002 - be che serve alla parte batch
 
step di esecuzione per lo schedulatore ( in test è condiviso tra le due linee clienti regp E mult, per questo gli step sono definiti solo per regp; per produzione entrambe le runlist li prevedono) 
    umount_fs,
    create_user,
    inst_database_clients,
    create_sysin_db,
    inst_mailx,
    mount_fs,   


Creazione TAG:
tag -a "1.0.0-001" -m "Rilascio in produzione versione prodotto x.x.x"

DEPLOY CON ADA:
(quando si crea un nuovo pacchetto da deployare quello che fa ada è interrogare artifactory per vedere se c'è il pacchetto pubblicato con jenkins
 Per avere una versione congelata di ogni ambiente dev - test - prod su astifactory è opportuno lanciare la compilazione per ogni ambiente di ogni linea cliante - dev, test, prod).
per ogni nuovo deploy di una nuova versione di prodotto deve essere aggiunta la configurazione relativa indicando la ricetta da usare (Realese per ambiente di produzione e snaptshot per test e sviluppo) ed i nodi da caricare selezionando i wildfly e gli eventuali apache su cui deployare
 [selezione pacchetto -> carica nodi]
 