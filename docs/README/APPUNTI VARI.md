http://localhost:8080/rest/api/v1/common/chiama-token/120/sec/3000

PER CAMBIARE O MEGLIO RESETTARE LA PWD DELLA POSTA CSI:
https://comunica.csi.it/cambia-password/index.html
UTILIZZARE QUESTO LINK DI RESET PWD 
VPN:  https://comunica.ruparpiemonte.it/cambia-password/index.html 
DOMNT:  http://cambiopwddomnt.csi.it/cngPwdSynch.html 


creare un tunnel ssh
ssh -L0.0.0.0:15432:dbs-portrilev-tst-001p.site03.nivolapiemonte.it:5432 71027@cmpto2-gw02.site02.nivolapiemonte.it

https://gitlab.csi.it/prodotti/portrilev/portrilweb.git
https://gitlab.csi.it/prodotti/portrilev/portrildb.git


[71027@cmpto1-cons02 ~]$ beehive ssh nodes get -size 0 |grep -i portrilev
f951a7ee-e40a-4276-8f71-fc9c5ca260e5  dbs-portrilev-prd-001p.site01.nivolapiemonte.it    dbs-portrilev-prd-001p Server 01  10.138.129.171  2021-02-10T12:18:37Z
43bdbd6b-36ac-471f-a5e4-0a9d35a1296b  jb2-rp-portrilev.site01.nivolapiemonte.it          jb2-rp-2f8c06ea                   10.138.129.132  2021-02-10T12:14:46Z
1d97ae7e-ada1-4845-b4dc-9917a465daff  jb1-rp-portrilev.site01.nivolapiemonte.it          jb1-rp-971aee16                   10.138.129.152  2021-02-10T12:08:51Z
a0b2279d-b8ec-4308-87ca-28717ef2c46a  ap2-int-portrilev.site01.nivolapiemonte.it         ap2-int-d8b30bbe                  10.138.137.78   2021-02-10T11:57:02Z
27134ff0-5d1f-432b-96f1-2db5852c22b1  ap1-int-portrilev.site01.nivolapiemonte.it         ap1-int-67f7813c                  10.138.137.97   2021-02-10T11:51:50Z
b2540c09-1d2f-4fbb-bc96-84d1b7a05c0f  dbs-portrilev-tst-001p.site03.nivolapiemonte.it    dbs-portrilev-tst-001p Server 01  10.138.192.62   2021-02-09T15:43:40Z
4f0deb81-7ee5-44e5-b848-c1fbd6851f1c  ts-jb2-rp-portrilev.site03.nivolapiemonte.it       ts-jb2-rp-61b5b45a                10.138.192.55   2021-02-09T15:40:21Z
f50495f3-cac1-4781-88ba-e0bbb22f816c  ts-jb1-rp-portrilev.site03.nivolapiemonte.it       ts-jb1-rp-a1a4d027                10.138.192.37   2021-02-09T15:38:13Z
c127ae8d-8576-4aa4-ab0e-64aa4df80aed  ts-ap1-int-portrilev.site03.nivolapiemonte.it      ts-ap1-int-aa0f49f1               10.138.200.41   2021-02-09T15:34:58Z


beehive ssh nodes get -size 0 -names portrilev

1) scp D:\myworkspace\PORTALERILEVAZIONINUOVO\portrilweb\target\build\archives\tst-rp-01\portalerilevazione.ear 71027@cmpto1-cons02.site01.nivolapiemonte.it:portalerilevazione.ear
2) ssh 71027@cmpto1-cons02.site01.nivolapiemonte.it

#TEST 
3a) beehive ssh nodes-files put  ts-jb1-rp-portrilev.site03.nivolapiemonte.it portalerilevazione.ear /tmp/portalerilevazione.ear
3a) beehive ssh nodes-files put  ts-jb2-rp-portrilev.site03.nivolapiemonte.it portalerilevazione.ear /tmp/portalerilevazione.ear

4a) beehive ssh nodes connect    ts-jb1-rp-portrilev.site03.nivolapiemonte.it
5) chown ajb620:ajb620 /tmp/portalerilevazione.ear     
6) su - ajb620
7) mv /tmp/portalerilevazione.ear part001node01/standalone/deployments

// altro cmd
2) ssh 71027@cmpto1-cons02.site01.nivolapiemonte.it
4a) beehive ssh nodes connect    ts-jb2-rp-portrilev.site03.nivolapiemonte.it
5) chown ajb620:ajb620 /tmp/portalerilevazione.ear     
6) su - ajb620
7) mv /tmp/portalerilevazione.ear part001node02/standalone/deployments

Questi sono ip e porti:

Nome: dbs-portrilev-tst-001p.site03.nivolapiemonte.it
Haproxy: 10.138.154.6:10459
l'utenza di test è portalerilevazioni_rw/mypass

Nome: dbs-portrilev-prd-001p.site01.nivolapiemonte.it
Haproxy: 10.138.154.6:10458


//recuperare l'identità digitale per l'ambiente di dev
http://tst-www.sistemapiemonte.it/routingconf-cons/identita.do
AAAAAA00A11B000J/CSI PIEMONTE/DEMO 21/ACTALIS_EU/20221003085220/16/fsfLwD8uVpNxmoC02u+hkg==
AAAAAA00A11B000J/CSI PIEMONTE/DEMO 21/ACTALIS_EU/20221027123911/16/b0ra7aAs9xxmxh1jorwtng==
accesso acta
http://tst-portale.ruparpiemonte.it/actaweb228/
cf CPSTNT80A01L219S

#POSTMAN esempio di chiamata BATCH
http://localhost:8080/rest/api/v1/batch/aggiornamento-struttura/ente/REGP


lista server a cui sono abilitato 
dalla macchina ponte
beehive ssh nodes get -size 1000

LINK per i progetti in locale sul jboss locale

MKLINK /J js <workspace>\siaccruapp\src\main\webapp\js




# Clono i progetti
```bash
cd /d/myworkspace/CPASS/cpass-web
git clone ssh://gogs/cpass/cpassbe.git
git clone ssh://gogs/cpass/cpassweb.git
```
# Terminare i processi sul db
```sql
SELECT pg_terminate_backend(pg_stat_activity.pid)
FROM pg_stat_activity
WHERE pg_stat_activity.datname = 'CPASS';
se non funziona andare diretto col pid esempio 
SELECT pg_terminate_backend(24548)
FROM pg_stat_activity
WHERE pg_stat_activity.datname = 'CPASS';
```

## Comandi Maven
Compilazione: `mvn clean package`
OWASP dependencies check: `mvn clean package org.owasp:dependency-check-maven:aggregate -P owasp`
Add SPDX license to sources: `mvn clean package license:update-file-header@add-licenses -P add-license`
per il fe 
C:\myworkspace\cpass\cpassweb\docs\utilities\license> node .\apply-license.js


Generate BOM-POMs: `mvn clean package bom-builder:build-bom -Pgenerate-bom`

## Dump DB
### Dump in plain text (with plain inserts):

```bash
DEV-NIVOLA: pg_dump --format=p --inserts --file=./cpass.$(date +%Y%m%d).dmp --verbose --host=10.138.154.6 --port=10548 --username=cpass --dbname=CPASS --schema=cpass
TST-NIVOLA: pg_dump --format=p --inserts --file=./cpass.$(date +%Y%m%d).dmp --verbose --host=10.138.154.6 --port=10603 --username=cpass --dbname=CPASS --schema=cpass
```

Import via

```bash
psql --single-transaction --echo-errors --variable=ON_ERROR_STOP=ON --hostname=<HOST> --username=<USER> --dbname=<DBNAME> --file=<FILE>
```
---
### Dump custom:

```bash
pg_dump --format=c --compress=9 --file=./cpass.$(date +%Y%m%d).dmp --verbose --hostname=<HOST> --username=<USER> --dbname=<DBNAME>
```
Import via

```bash
pg_restore
```
---------------------------------------------GENERARE UUID--------------------------------------------------------
esempio
--select * from uuid_generate_v5(uuid_tabella, anno + '|' + numero + '|' + cpassTSettore.getId() );
select * from uuid_generate_v5('04750401-39e1-515b-9fb7-d34818fdfd55'::UUID, '2022|1|04750401-39e1-515b-9fb7-d34818fdfd55' );


------------------- GENERAZIONE SOURCE per client esterni----------------------
cd D:\myworkspace\CPASS\cpassbe
"C:\Program Files\apache-maven-3.6.3\bin\mvn.cmd" clean generate-sources package -P generate-adapters -pl integ-stilo -am
./mvnw clean generate-sources package -P generate-adapters -pl integ-stilo -am

#COMANDI GIT
https://gist.github.com/tesseslol/da62aabec74c4fed889ea39c95efc6cc
https://www.atlassian.com/git/tutorials/atlassian-git-cheatsheet
git push
git fetch -va
git add .
git commit -m "listino"
git merge --squash dev
git checkout -b listino-fornitore (nuovo ramo)
git chekout dev
git config --list
git push origin :int_stilo-wawa :evasione :rename_ord-destinatari :ListinoFornitore


#Comandi per FE 
npm start
npm run extract-i18n 
npm install
ng -g -c modules\ord\modules\ordine\components\riepilogo-impegni -d 
npx ng g c modules\ord\modules\ordine\components\riepilogo-impegni -d 
Da lanciare per compilare e pacchettizzare la parte FE per nivola
D:\myworkspace\CPASS\cpassweb\utility_scripts> .\import-swagger.bat   (genera  le calassi dallo ymlperi i servizzi) 
D:\myworkspace\CPASS\cpassweb\utility_scripts> .\compile.bat (genera la parte js la copia sul BE)


#Terminare i processi sul db
// vedere le transazione loccate 
SELECT * FROM pg_stat_activity WHERE state IN ('active', 'idle in transaction');
SELECT pg_terminate_backend(pg_stat_activity.pid) FROM pg_stat_activity WHERE pg_stat_activity.datname = 'CPASS';

#DB NIVOLA REGP
host 10.138.154.6 (RP)
Database CPASS
user cpass
pw (dev test) myPass!8
Port 
PROD: 10495
DEV:  10548 (esiste solo RP)
TEST: 10603
user cpass_rw
pw (dev test) mypass

#DB NIVOLA MULT
host 10.138.154.6 (MULT)
Database CPASS
user cpass
pw (dev test) myPass!8
Port 
TEST: 10773
PROD: ?????
user cpass_rw
pw (dev test) mypass

#faccio il Dump da nivola dev regp
cd "C:\Program Files\PostgreSQL\15\bin"
pg_dump --format=p --inserts --username=cpass --port=5432 --host=10.138.198.19 --file=c:\cpass.2025-01-03_dev_coto_nivola.dmp --verbose --schema=cpass CPASS
pw mypass


pg_dump --format=p --inserts --username=cpass --port=10962 --host=10.138.154.6 --file=d:\cpass.2023-09-22_test_nivola.dmp --verbose --schema=cpass CPASS



#faccio il Dump da nivola dev mult
cd "D:\Programmi\PostgreSQL\9.6\bin"
pg_dump --format=p --inserts --username=cpass --port=10775 --host=10.138.154.6 --file=d:\cpass.20200601_dev_nivola-mult.dmp --verbose --schema=cpass CPASS
pw mypass

#faccio il Dump da nivola dev coto
cd "D:\Programmi\PostgreSQL\9.6\bin"
cd "C:\Program Files\PostgreSQL\15\bin"
pg_dump --format=p --inserts --username=cpass --port=10164 --host=10.138.154.6 --file=d:\cpass.2023-05-14_dev_coto_nivola.dmp --verbose --schema=cpass CPASS
pw mypass

#faccio il Dump da nivola TEST coto
cd "D:\Programmi\PostgreSQL\9.6\bin"
pg_dump --format=p --inserts --username=cpass --port=10962 --host=10.138.154.6 --file=d:\cpass.2023-05-14_test_nivola-coto.dmp --verbose --schema=cpass CPASS
pw mypass


#carico in locale il DB
psql --single-transaction --username postgres --dbname cpass --file=xxxxx.dmp
Aggiungere il DROP SCHEMA CPASS CASCADE;
psql --username postgres --dbname cpass --file=c:\cpass.2024-17-05_dev_coto_nivola.dmp
pw locale postgresql :mypass



psql --username postgres --dbname cpass --port=10961 --host=10.138.154.6 --file=D:\cpass.testCOTO.dmp



## Comandi Maven
Compilazione: 'mvn clean package'
Sonar: 'mvn clean package sonar:sonar -Psonar'
OWASP dependencies check: 'mvn clean package org.owasp:dependency-check-maven:check -Powasp'

:CONSEGNA1.3.0 :PATCH-1.1.0 :PATCH-1.2.0 :batch :batch_DDT :csv :evolutive_pba :nso-notier :patch-1.2.0                 

remotes/origin/spostamento-generazione-xml fafe4d2d refactoring generazione xml peppol   ?????????
remotes/origin/verifica-evasione           65027204 versione da portare su dev           ????????? 
  
#Cancellazione dei branch REMOTI (fare molta molta molta Attenzione)
git push origin :CONSEGNA1.3.0 :PATCH-1.1.0 :PATCH-1.2.0 :batch :batch_DDT :csv :evolutive_pba :nso-notier :patch-1.2.0        
git push  origin  --delete TEST-2.1.0-027

#Cancellazione dei branch LOCALE
git tag --d TEST-2.1.0-027



#Aumentare il limite massimo di una transazione
annotare il metodo dell'ejb con @TransactionTimeout , esplicitando il nuovo timeout massimo e l'unità del nuovo valore.
@TransactionTimeout(value=120, unit=TimeUnit.SECONDS) -- aumenta a 120 secondi la durata massima della transazione oltre la quale la stessa viene interrota generando eccezione.

#rename di tag 
https://stackoverflow.com/questions/1028649/how-do-you-rename-a-git-tag

git tag new old
git tag -d old
git push origin new :old

esempio
git tag CONSEGNA-1.1.0-001 CONSEGNA-1.4.0-002
git tag -d CONSEGNA-1.4.0-002
git push origin CONSEGNA-1.1.0-001 :CONSEGNA-1.4.0-001


cd "D:\Programmi\PostgreSQL\9.6\bin"
pg_dump --format=p --inserts --username=cpass --port=10164 --host=10.138.154.6 --file=d:\cpass.2022-07-19_dev_cmto_nivola.dmp --verbose --schema=cpass CPASS
pw mypass


SCHEDULATORE
http://cmpto1-skedadmin.site01.nivolapiemonte.it:4445/joc/#!/login


pg_dump --format=p --inserts --username=cpass --port=10164 --host=10.138.154.6 --file=d:\cpass.2022-11-07_dev_nivola_coto.dmp --verbose --schema=cpass CPASS

