https://dev-unica-acquisti-coto.nivolapiemonte.it/rest/api/v1/stampa/withname/CONFRONTA_PROGRAMMI/Report_Confronto/default
https://dev-unica-acquisti-mult.nivolapiemonte.it/rest/api/v1/stampa/withname/CONFRONTA_PROGRAMMI/Report_Confronto/default

cd /c/myworkspace/cpass/csi-cpass/cpassbe

#https://dev-cpass-coto.nivolapiemonte.it/cpass-devsliv1wrup/Shibboleth.sso/Logout

#NAS
https://10.136.6.116
us abenedetto
pw la solita

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


#pagina permessi occulta
https://dev-unica-acquisti-coto.nivolapiemonte.it/cpass/bo/gestione-permessi

cd /c/myworkspace/cpass/csi-cpass/cpassbe


#Creare un tunnel ssh per la connessione al db lanciare da gitbash ( pw Zoppa$61 in generale quella di domnt)
DEV COTO 
ssh -vL 5433:10.138.198.19:5432 71027@10.138.154.14  -N
DEV REGP
ssh -vL 5433:10.138.198.6:5432 71027@10.138.154.14  -N
DEV CMTO 
ssh -vL 5433:10.138.198.20:5432 71027@10.138.154.14  -N
DEV MULT 
ssh -vL 5433:10.138.198.23:5432 71027@10.138.154.14  -N
 
 
#TEST COTO 
ssh -vL 5433:10.138.167.144:5432 71027@10.138.154.14  -N
TEST REGP
ssh -vL 5433:10.138.167.140:5432 71027@10.138.154.14  -N
TEST CMTO 
ssh -vL 5433:10.138.167.147:5432 71027@10.138.154.14  -N
TEST MULT 
ssh -vL 5433:10.138.167.142:5432 71027@10.138.154.14  -N 
 
#PROD COTO 
ssh -vL 5433:10.138.157.39:5432 71027@10.138.154.14  -N
PROD REGP
ssh -vL 5433:10.138.157.37:5432 71027@10.138.154.14  -N
PROD CMTO 
ssh -vL 5433:10.138.157.40:5432 71027@10.138.154.14  -N
PROD MULT 
ssh -vL 5433:10.138.157.41:5432 71027@10.138.154.14  -N 


#per vedere tutti i nodi a cui sono associato da lanciare sulla macchina ponte
beehive ssh nodes get -size 0 -name cpass -order ASC -field name

# Per vedere a cio' che sono abilitato 
beehive ssh nodes get -names dev-jboss1-cpass-pre.site02
beehive ssh nodes get -size 100

#copia dal server su macchina ponte
beehive ssh nodes-files get dv-wf0123-coto-cpass.site02.nivolapiemonte.it  /appserv/jboss/awf230/part001cpassnode01/modules/modules.tgz  ./modules.tgz
# http://localhost:8080/rest/api/v1/common/caricamento-massivo/carica-tabelle-xmit/ALL

#per prendere i file di log COTO PROD E COPIARLI SULLA  MACCHINA PONTE*/
beehive ssh nodes-files get  wf1-coto-cpass.site01.nivolapiemonte.it /appserv/jboss/awf170/part001cpassnode01/standalone/log/cpassbe-part001cpassnode01.log ./cpassbe-part001cpassnode01.log
/*il file te lo ritrovi sulla macchina ponte e da qui con un wiscp, mobaxterm etc ti fai il download sul tuo pc*/


#PASSARE DA wf170 wf230
1) collegarsi dalla cli sul nodo dv-ap1-rupcoto-cpass.site03.nivolapiemonte.it
2) come utente root spostarsi sotto il path /etc/httpd/config_vhosts/dev-cpass-pa-coto.nivolapiemonte.it_443
3) eseguire i comandi: 
mv balancer_wf230.BAK balancer_wf230.conf
mv balancer_wf170.conf balancer_wf170.BAK
4)eseguire un riavvio dell'apache con il comando service httpd restart



#PASSARE DA wf230 wf170 
1) collegarsi dalla cli sul nodo dv-ap1-rupcoto-cpass.site03.nivolapiemonte.it
2) come utente root spostarsi sotto il path /etc/httpd/config_vhosts/dev-cpass-pa-coto.nivolapiemonte.it_443
3) eseguire i comandi: 
mv balancer_wf170.BAK balancer_wf170.conf
mv balancer_wf230.conf balancer_wf230.BAK
4)riavviare l'apache con il comando systemctl restart httpd


/*
ssh 71027@cmpto1-cons02.site01.nivolapiemonte.it
beehive ssh nodes connect   ts-wf01-trasv-batch-cpass.site02.nivolapiemonte.it	

--su - awf230
systemctl stop wildfly230-part001cpassnode01    (da farsi con utente root)
systemctl stop/start/status wildfly230-part001cpassnode01


awf230-info.sh 
awf230-stop.sh   part001cpassnode01
awf230-start.sh  part001cpassnode01

beehive ssh nodes connect  ts-wf01-trasv-batch-cpass.site02.nivolapiemonte.it	

*/
*******************************************
*** RILASCIO SU DEV NIVOLA  MULT CPASSBE ***
*******************************************
Pull delle componenti
(pw Zoppa$61)

Il rilascio su dev / test si può effettuare invocando lo script nella cartella /cpassweb/utility_scripts/
/cpassweb/utility_scripts/compile-dev-mult.bat (configurandolo opportunamente).

1) scp C:\myworkspace\CPASS\cpassbe\ear\target\cpassbe.ear 71027@cmpto1-cons02.site01.nivolapiemonte.it:cpassbe.ear
2) ssh 71027@cmpto1-cons02.site01.nivolapiemonte.it


#DEV RP
3a) beehive ssh nodes-files put dv-wf1-ua-rp-cpass.site03.nivolapiemonte.it cpassbe.ear /tmp/cpassbe.ear
4b) beehive ssh nodes connect   dv-wf1-ua-rp-cpass.site03.nivolapiemonte.it

#DEV MULT
3a) beehive ssh nodes-files put dv-wf1-ua-mult-cpass.site03.nivolapiemonte.it cpassbe.ear /tmp/cpassbe.ear
4b) beehive ssh nodes connect   dv-wf1-ua-mult-cpass.site03.nivolapiemonte.it


#DEV CMTO
3a) beehive ssh nodes-files put dv-wf1-ua-cmto-cpass.site03.nivolapiemonte.it cpassbe.ear /tmp/cpassbe.ear
4b) beehive ssh nodes connect   dv-wf1-ua-cmto-cpass.site03.nivolapiemonte.it

#DEV COTO
3a) beehive ssh nodes-files put dv-wf1-ua-coto-cpass.site03.nivolapiemonte.it cpassbe.ear /tmp/cpassbe.ear
4b) beehive ssh nodes connect   dv-wf1-ua-coto-cpass.site03.nivolapiemonte.it



#TEST RP
3a) beehive ssh nodes-files put ts-wf1-ua-rp-cpass.site02.nivolapiemonte.it cpassbe.ear /tmp/cpassbe.ear
4b) beehive ssh nodes connect   ts-wf1-ua-rp-cpass.site02.nivolapiemonte.it

#TEST MULT
3a) beehive ssh nodes-files put ts-wf1-ua-mult-cpass.site02.nivolapiemonte.it cpassbe.ear /tmp/cpassbe.ear
4b) beehive ssh nodes connect   ts-wf1-ua-mult-cpass.site02.nivolapiemonte.it

#TEST CMTO
3a) beehive ssh nodes-files put ts-wf1-ua-cmto-cpass.site02.nivolapiemonte.it cpassbe.ear /tmp/cpassbe.ear
4b) beehive ssh nodes connect   ts-wf1-ua-cmto-cpass.site02.nivolapiemonte.it

#TEST COTO
3a) beehive ssh nodes-files put ts-wf1-ua-coto-cpass.site02.nivolapiemonte.it cpassbe.ear /tmp/cpassbe.ear
4b) beehive ssh nodes connect   ts-wf1-ua-coto-cpass.site02.nivolapiemonte.it


#PROD RP
3a) beehive ssh nodes-files put wf1-ua-rp-cpass.site01.nivolapiemonte.it cpassbe.ear /tmp/cpassbe.ear
4b) beehive ssh nodes connect   wf1-ua-rp-cpass.site01.nivolapiemonte.it

#PROD MULT
3a) beehive ssh nodes-files put wf1-ua-mult-cpass.site01.nivolapiemonte.it cpassbe.ear /tmp/cpassbe.ear
4b) beehive ssh nodes connect   wf1-ua-mult-cpass.site01.nivolapiemonte.it

#PROD CMTO
3a) beehive ssh nodes-files put wf1-ua-cmto-cpass.site01.nivolapiemonte.it cpassbe.ear /tmp/cpassbe.ear
4b) beehive ssh nodes connect   wf1-ua-cmto-cpass.site01.nivolapiemonte.it

#PROD COTO
3a) beehive ssh nodes-files put wf1-ua-coto-cpass.site01.nivolapiemonte.it cpassbe.ear /tmp/cpassbe.ear
4b) beehive ssh nodes connect   wf1-ua-coto-cpass.site01.nivolapiemonte.it


5) chown awf230:wildfly /tmp/cpass*
6) su - awf230
7) mv /tmp/cpassbe.ear	 part002cpassnode01/standalone/deployments

Verificare lo stato con
tail -1000f part001cpassnode01/standalone/log/server.log
less +F   part001cpassnode01/standalone/log/server.log
/appserv/jboss/awf230/part004cpassnode01/standalone/log

#Comandi per startare e stoppare il server
awf230-info.sh 
awf230-stop.sh   part002cpassnode01
awf230-start.sh  part002cpassnode01

************************************************
*** RILASCIO SU DEV NIVOLA  MULT CPASSREPENG ***
************************************************
Pull delle componenti e compilazione tramite maven

1) scp C:\myworkspace\CPASS\cpassrepeng\ear\target\cpassrepeng.ear 71027@cmpto1-cons02.site01.nivolapiemonte.it:cpassrepeng.ear

2) ssh 71027@cmpto1-cons02.site01.nivolapiemonte.it


#DEV RP
3a) beehive ssh nodes-files put dv-wf1-ua-rp-cpass.site03.nivolapiemonte.it cpassrepeng.ear /tmp/cpassrepeng.ear
4b) beehive ssh nodes connect   dv-wf1-ua-rp.site03.nivolapiemonte.it

#DEV MULT
3a) beehive ssh nodes-files put dv-wf1-ua-mult-cpass.site03.nivolapiemonte.it cpassrepeng.ear /tmp/cpassrepeng.ear
4b) beehive ssh nodes connect   dv-wf1-ua-mult-cpass.site03.nivolapiemonte.it


#DEV CMTO
3a) beehive ssh nodes-files put dv-wf1-ua-cmto-cpass.site03.nivolapiemonte.it cpassrepeng.ear /tmp/cpassrepeng.ear
4b) beehive ssh nodes connect   dv-wf1-ua-cmto-cpass.site03.nivolapiemonte.it

#DEV COTO
3a) beehive ssh nodes-files put dv-wf1-ua-coto-cpass.site03.nivolapiemonte.it cpassrepeng.ear /tmp/cpassrepeng.ear
4b) beehive ssh nodes connect   dv-wf1-ua-coto-cpass.site03.nivolapiemonte.it



#TEST RP
3a) beehive ssh nodes-files put ts-wf1-ua-rp-cpass.site02.nivolapiemonte.it cpassrepeng.ear /tmp/cpassrepeng.ear
4b) beehive ssh nodes connect   ts-wf1-ua-rp-cpass.site02.nivolapiemonte.it

#TEST MULT
3a) beehive ssh nodes-files put ts-wf1-ua-mult-cpass.site02.nivolapiemonte.it cpassrepeng.ear /tmp/cpassrepeng.ear
4b) beehive ssh nodes connect   ts-wf1-ua-mult-cpass.site02.nivolapiemonte.it

#TEST CMTO
3a) beehive ssh nodes-files put ts-wf1-ua-cmto-cpass.site02.nivolapiemonte.it cpassrepeng.ear /tmp/cpassrepeng.ear
4b) beehive ssh nodes connect   ts-wf1-ua-cmto-cpass.site02.nivolapiemonte.it

#TEST COTO
3a) beehive ssh nodes-files put ts-wf1-ua-coto-cpass.site02.nivolapiemonte.it cpassrepeng.ear /tmp/cpassrepeng.ear
4b) beehive ssh nodes connect   ts-wf1-ua-coto-cpass.site02.nivolapiemonte.it

#PROD RP
3a) beehive ssh nodes-files put wf1-ua-rp-cpass.site01.nivolapiemonte.it cpassrepeng.ear /tmp/cpassrepeng.ear
4b) beehive ssh nodes connect   wf1-ua-rp-cpass.site01.nivolapiemonte.it

#PROD MULT
3a) beehive ssh nodes-files put wf1-ua-mult-cpass.site01.nivolapiemonte.it cpassrepeng.ear /tmp/cpassrepeng.ear
4b) beehive ssh nodes connect   wf1-ua-mult-cpass.site01.nivolapiemonte.it

#PROD CMTO
3a) beehive ssh nodes-files put wf1-ua-cmto-cpass.site01.nivolapiemonte.it cpassrepeng.ear /tmp/cpassrepeng.ear
4b) beehive ssh nodes connect   wf1-ua-cmto-cpass.site01.nivolapiemonte.it

#PROD COTO
3a) beehive ssh nodes-files put wf1-ua-coto-cpass.site01.nivolapiemonte.it ccpassrepeng.ear /tmp/cpassrepeng.ear
4b) beehive ssh nodes connect   wf1-ua-coto-cpass.site01.nivolapiemonte.it

5) chown awf230:wildfly /tmp/cpass*
6) su - awf230
7) mv /tmp/cpassrepeng.ear part001cpassnode01/standalone/deployments

Verificare lo stato con
tail -10f part001cpassnode01/standalone/log/server.log
less +F part001cpassnode01/standalone/log/server.log

#Comandi per startare e stoppare il server
awf230-info.sh 
awf230-stop.sh   part001cpassnode01
awf230-start.sh  part001cpassnode01


************************************************
*** RILASCIO SU DEV NIVOLA  MULT CPASSBATCH  E CPASSBE X BATCH ***
************************************************
Pull delle componenti e compilazione tramite maven

1) scp C:\myworkspace\CPASS\cpassbatch\target\cpassbatch-2.0.0.jar 71027@cmpto1-cons02.site01.nivolapiemonte.it:cpassbatch-2.0.0.jar
2) ssh 71027@cmpto1-cons02.site01.nivolapiemonte.it

#DEV (attualmente non esistono gli ambienti)
3) beehive ssh nodes-files put dv-wf1-ua-trasv-batch-cpass.site02.nivolapiemonte.it cpassbatch.jar /tmp/cpassbatch-1.0.0.jar
4) beehive ssh nodes connect   dv-wf1-ua-trasv-batch-cpass.site02.nivolapiemonte.it

#TEST
3a) beehive ssh nodes-files put ts-wf1-ua-trasv-batch-cpass.site02.nivolapiemonte.it cpassbatch.jar /tmp/cpassbatch-2.0.0.jar
4)  beehive ssh nodes connect   ts-wf1-ua-trasv-batch-cpass.site02.nivolapiemonte.it

#PROD 
3a) beehive ssh nodes-files put wf1-ua-trasv-batch-cpass.site01.nivolapiemonte.it cpassbatch.jar /tmp/cpassbatch-2.0.0.jar
4)  beehive ssh nodes connect   wf1-ua-trasv-batch-cpass.site01.nivolapiemonte.it

5) chown awf230:wildfly /tmp/cpass*
6) su - skedul
7) mv /tmp/cpassbatch.jar /skedul/java/cpass/coto-01/cpassbatch/cpassbatch.jar
7) mv /tmp/cpassbatch.jar /skedul/java/cpass/pvto-01/cpassbatch/cpassbatch.jar
7) mv /tmp/cpassbatch.jar /skedul/java/cpass/int-01/cpassbatch/cpassbatch.jar
7) mv /tmp/cpassbatch.jar /skedul/java/cpass/rp-01/cpassbatch/cpassbatch.jar


6) su - skedul
7) mv /tmp/cpassbatch.jar /skedul/java/cpass/coto-01/cpassbatch/cpassbatch.jar
7) mv /tmp/cpassbatch.jar /skedul/java/cpass/pvto-01/cpassbatch/cpassbatch.jar
7) mv /tmp/cpassbatch.jar /skedul/java/cpass/int-01/cpassbatch/cpassbatch.jar
7) mv /tmp/cpassbatch.jar /skedul/java/cpass/rp-01/cpassbatch/cpassbatch.jar



************************************************
*** RILASCIO SU DEV NIVOLA  MULT CPASSSCRIPT  ***
************************************************
Pull delle componenti e compilazione tramite maven

1) scp D:\myworkspace\CPASS\cpassscript\target\cpassscript.tar 71027@cmpto1-cons02.site01.nivolapiemonte.it:cpassscript.tar
2) ssh 71027@cmpto1-cons02.site01.nivolapiemonte.it

#DEV RP
3a) beehive ssh nodes-files put dev-jboss1-cpass-pre.site02.nivolapiemonte.it cpassscript.tar /tmp/cpassscript.tar
4a) beehive ssh nodes connect   dev-jboss1-cpass-pre.site02.nivolapiemonte.it
#DEV MULT
3) beehive ssh nodes-files put dv-wf1-mult-cpass-pre.site02.nivolapiemonte.it cpassscript.tar /tmp/cpassscript.tar
4) beehive ssh nodes connect   dv-wf1-mult-cpass-pre.site02.nivolapiemonte.it

#TEST RP
3a) beehive ssh nodes-files put tst-jboss1-cpass-pre.site02.nivolapiemonte.it cpassscript.tar /tmp/cpassscript.tar
4a) beehive ssh nodes connect   tst-jboss1-cpass-pre.site02.nivolapiemonte.it
#TEST MULT
3a) beehive ssh nodes-files put ts-wf1-mult-cpass-pre.site02.nivolapiemonte.it cpassscript.tar /tmp/cpassscript.tar
4a) beehive ssh nodes connect ts-wf1-mult-cpass-pre.site02.nivolapiemonte.it

#PROD RP
3b) beehive ssh nodes-files put jboss1-cpass.site01.nivolapiemonte.it cpassscript.tar /tmp/cpassscript.tar
4b) beehive ssh nodes connect   jboss1-cpass.site01.nivolapiemonte.it
#PROD MULT
3b) beehive ssh nodes-files put wf1-mult-cpass.site01.nivolapiemonte.it cpassscript.tar /tmp/cpassscript.tar
4b) beehive ssh nodes connect wf1-mult-cpass.site01.nivolapiemonte.it

5) chown awf170:wildfly /tmp/cpass*
6) su - awf170
7) mv /tmp/cpassbatch-1.0.0.jar da capire dove scompattarlo ?????????????????????????


******************************************************
*** RILASCIO SU DEV NIVOLA  MULT REPORT cpassreptpl
******************************************************
Pull delle componenti e compilazione tramite maven

1) scp D:\myworkspace\CPASS\cpassreptpl\src\reports\Stampa_confronto.rptdesign 71027@cmpto1-cons02.site01.nivolapiemonte.it:Stampa_confronto.rptdesign

2) ssh 71027@cmpto1-cons02.site01.nivolapiemonte.it

#DEV RP
3a) beehive ssh nodes-files put dev-jboss1-cpass-pre.site02.nivolapiemonte.it Allegato_II_Triennale.rptdesign /tmp/Allegato_II_Triennale.rptdesign
4a) beehive ssh nodes connect   dev-jboss1-cpass-pre.site02.nivolapiemonte.it

#DEV MULT
3) beehive ssh nodes-files put dv-wf1-mult-cpass-pre.site02.nivolapiemonte.it Allegato_II_Triennale.rptdesign /tmp/Allegato_II_Triennale.rptdesign
4) beehive ssh nodes connect   dv-wf1-mult-cpass-pre.site02.nivolapiemonte.it

#DEV CMTO
3a) beehive ssh nodes-files put dv-wf1-cmto-cpass.site03.nivolapiemonte.it Allegato_II_Triennale.rptdesign /tmp/Allegato_II_Triennale.rptdesign
4a) beehive ssh nodes connect   dv-wf1-cmto-cpass.site03.nivolapiemonte.it

#DEV COTO
3a) beehive ssh nodes-files put dv-wf1-coto-cpass.site03.nivolapiemonte.it Stampa_confronto.rptdesign /tmp/Stampa_confronto.rptdesign
4a) beehive ssh nodes connect   dv-wf1-coto-cpass.site03.nivolapiemonte.it

#TEST RP
3a) beehive ssh nodes-files put tst-jboss1-cpass-pre.site02.nivolapiemonte.it Allegato_II_Triennale.rptdesign /tmp/Allegato_II_Triennale.rptdesign
4a) beehive ssh nodes connect   tst-jboss1-cpass-pre.site02.nivolapiemonte.it

#TEST MULT
3a) beehive ssh nodes-files put ts-wf1-mult-cpass-pre.site02.nivolapiemonte.it Allegato_II_Triennale.rptdesign /tmp/Allegato_II_Triennale.rptdesign
4a) beehive ssh nodes connect ts-wf1-mult-cpass-pre.site02.nivolapiemonte.it

#PROD RP
3b) beehive ssh nodes-files put jboss1-cpass.site01.nivolapiemonte.it Allegato_II_Triennale.rptdesign /tmp/Allegato_II_Triennale.rptdesign
4b) beehive ssh nodes connect   jboss1-cpass.site01.nivolapiemonte.it

#PROD MULT
3b) beehive ssh nodes-files put wf1-mult-cpass.site01.nivolapiemonte.it Allegato_II_Triennale.rptdesign /tmp/Allegato_II_Triennale.rptdesign
4b) beehive ssh nodes connect wf1-mult-cpass.site01.nivolapiemonte.it

#PROD CMTO
3b) beehive ssh nodes-files put wf1-coto-cpass.site01.nivolapiemonte.it Allegato_II_Triennale.rptdesign /tmp/Allegato_II_Triennale.rptdesign
4b) beehive ssh nodes connect wf1-coto-cpass.site01.nivolapiemonte.it

#PROD COTO
3b) beehive ssh nodes-files put wf1-coto-cpass.site01.nivolapiemonte.it Allegato_II_Triennale.rptdesign /tmp/Allegato_II_Triennale.rptdesign
4b) beehive ssh nodes connect wf1-coto-cpass.site01.nivolapiemonte.it



5) chown awf170:wildfly /tmp/Stampa_confronto.rptdesign
6) su - awf170
7) mv /tmp/Stampa_confronto.rptdesign reports/cpassreptpl


TUTTI I REPORT 
1) scp D:\myworkspace\CPASS\cpassreptpl\target\cpassreptpl.tar 71027@cmpto1-cons02.site01.nivolapiemonte.it:cpassreptpl.tar

2) ssh 71027@cmpto1-cons02.site01.nivolapiemonte.it

beehive ssh nodes-files put dev-wfrupar1-cpass-pre.site02.nivolapiemonte.it cpassreptpl.tar /tmp/cpassreptpl.tar
beehive ssh nodes connect   dev-wfrupar1-cpass-pre.site02.nivolapiemonte.it

beehive ssh nodes-files put dv-wf1-cmto-cpass.site03.nivolapiemonte.it cpassreptpl.tar /tmp/cpassreptpl.tar
beehive ssh nodes connect   dv-wf1-cmto-cpass.site03.nivolapiemonte.it

beehive ssh nodes-files put dv-wf1-mult-cpass-pre.site02.nivolapiemonte.it cpassreptpl.tar /tmp/cpassreptpl.tar
beehive ssh nodes connect   dv-wf1-mult-cpass-pre.site02.nivolapiemonte.it

beehive ssh nodes-files put dv-wf1-coto-cpass.site03.nivolapiemonte.it cpassreptpl.tar /tmp/cpassreptpl.tar
beehive ssh nodes connect   dv-wf1-coto-cpass.site03.nivolapiemonte.it

chown awf170:wildfly /tmp/cpass*
su - awf170
tar xvf /tmp/cpassreptpl.tar -C reports/cpassreptpl/




============= DEPLOY MANUAL ============================
1) scp D:\myworkspace\CPASS\cpassmanual\target\cpassmanual.tar 71027@cmpto1-cons02.site01.nivolapiemonte.it:cpassmanual.tar

2) ssh 71027@cmpto1-cons02.site01.nivolapiemonte.it

======== (su apache DEV RP) ===========
beehive ssh nodes-files put dev-wfrupar1-cpass-pre.site02.nivolapiemonte.it cpassmanual.tar /tmp/cpassmanual.tar
beehive ssh nodes connect dev-wfrupar1-cpass-pre.site02.nivolapiemonte.it
tar xvf /tmp/cpassmanual.tar -C /var/www/dev-cpass-pa.nivolapiemonte.it/htdocs/
======== (su apache DEV MULT) ===========
beehive ssh nodes-files put dv-ap1-rupmult-cpass-pre.site02.nivolapiemonte.it cpassmanual.tar /tmp/cpassmanual.tar
beehive ssh nodes connect dv-ap1-rupmult-cpass-pre.site02.nivolapiemonte.it
tar xvf /tmp/cpassmanual.tar -C /var/www/dev-cpass-pa-mult.nivolapiemonte.it/htdocs/
======== (su apache DEV COTO) ===========
beehive ssh nodes-files put dv-ap1-rupcoto-cpass.site03.nivolapiemonte.it cpassmanual.tar /tmp/cpassmanual.tar
beehive ssh nodes connect   dv-ap1-rupcoto-cpass.site03.nivolapiemonte.it
tar xvf /tmp/cpassmanual.tar -C /var/www/dev-cpass-pa-coto.nivolapiemonte.it_443/htdocs/
======== (su apache DEV CMTO) ===========
beehive ssh nodes-files put dv-ap1-rupcmto-cpass.site03.nivolapiemonte.it cpassmanual.tar /tmp/cpassmanual.tar
beehive ssh nodes connect   dv-ap1-rupcmto-cpass.site03.nivolapiemonte.it
tar xvf /tmp/cpassmanual.tar -C /var/www/dev-cpass-pa-cmto.nivolapiemonte.it_443/htdocs/


======== (su apache TEST RP) ===========
beehive ssh nodes-files put tst-wfrupar1-cpass-pre.site02.nivolapiemonte.it cpassmanual.tar /tmp/cpassmanual.tar
beehive ssh nodes connect tst-wfrupar1-cpass-pre.site02.nivolapiemonte.it
tar xvf /tmp/cpassmanual.tar -C /var/www/tst-cpass-pa.nivolapiemonte.it/htdocs/
======== (su apache TEST MULT) ===========
beehive ssh nodes-files put ts-ap1-rupmult-cpass-pre.site02.nivolapiemonte.it cpassmanual.tar /tmp/cpassmanual.tar
beehive ssh nodes connect ts-ap1-rupmult-cpass-pre.site02.nivolapiemonte.it
tar xvf /tmp/cpassmanual.tar -C /var/www/tst-cpass-pa-mult.nivolapiemonte.it/htdocs/


#Ambiente Dev nivola regp
http://dev-cpass-pa.nivolapiemonte.it/cpass/pba/home
csi.demo 21
PIEMONTE
#Ambiente Dev nivola mult
http://dev-cpass-pa-mult.nivolapiemonte.it/cpass
csi.demo 21
PIEMONTE

#MANUAL

1) scp D:\myworkspace\CPASS\cpassmanual\target\cpassmanual.tar 71027@cmpto1-cons02.site01.nivolapiemonte.it:cpassmanual.tar
2) ssh 71027@cmpto1-cons02.site01.nivolapiemonte.it

-- DEV-RP\
3)beehive ssh nodes-files put dev-wfrupar1-cpass-pre.site02.nivolapiemonte.it cpassmanual.tar /tmp/cpassmanual.tar
4)beehive ssh nodes connect dev-wfrupar1-cpass-pre.site02.nivolapiemonte.it
5)tar xvf /tmp/cpassmanual.tar -C /var/www/dev-cpass-pa.nivolapiemonte.it/htdocs/
-- DEV-MULT
3)beehive ssh nodes-files put dv-ap1-rupmult-cpass-pre.site02.nivolapiemonte.it cpassmanual.tar /tmp/cpassmanual.tar
4)beehive ssh nodes connect dv-ap1-rupmult-cpass-pre.site02.nivolapiemonte.it
5)tar xvf /tmp/cpassmanual.tar -C /var/www/dev-cpass-pa-mult.nivolapiemonte.it/htdocs/
-- DEV-COTO
3)beehive ssh nodes-files put dv-ap1-rupmult-cpass-pre.site02.nivolapiemonte.it cpassmanual.tar /tmp/cpassmanual.tar
4)beehive ssh nodes connect dv-ap1-rupmult-cpass-pre.site02.nivolapiemonte.it
5)tar xvf /tmp/cpassmanual.tar -C /var/www/dev-cpass-pa-mult.nivolapiemonte.it/htdocs/

-- TEST-RP
beehive ssh nodes-files put tst-wfrupar1-cpass-pre.site02.nivolapiemonte.it cpassmanual.tar /tmp/cpassmanual.tar
beehive ssh nodes connect tst-wfrupar1-cpass-pre.site02.nivolapiemonte.it
tar xvf /tmp/cpassmanual.tar -C /var/www/tst-cpass-pa.nivolapiemonte.it/htdocs/
-- TEST-MULT
beehive ssh nodes-files put ts-ap1-rupmult-cpass-pre.site02.nivolapiemonte.it cpassmanual.tar /tmp/cpassmanual.tar
beehive ssh nodes connect ts-ap1-rupmult-cpass-pre.site02.nivolapiemonte.it
tar xvf /tmp/cpassmanual.tar -C /var/www/tst-cpass-pa-mult.nivolapiemonte.it/htdocs/


1) scp D:\myworkspace\CPASS\cpassreptpl\src\reports\Stampa_acquisti.rptdesign 71027@cmpto1-cons02.site01.nivolapiemonte.it:Stampa_acquisti.rptdesign
2) ssh 71027@cmpto1-cons02.site01.nivolapiemonte.it

#DEV RP
3a) beehive ssh nodes-files put dev-jboss1-cpass-pre.site02.nivolapiemonte.it Stampa_acquisti.rptdesign /tmp/Stampa_acquisti.rptdesign
4a) beehive ssh nodes connect   dev-jboss1-cpass-pre.site02.nivolapiemonte.it

#DEV COTO
3a) beehive ssh nodes-files put dv-wf1-coto-cpass.site03.nivolapiemonte.it Stampa_acquisti.rptdesign /tmp/Stampa_acquisti.rptdesign
4a) beehive ssh nodes connect   dv-wf1-coto-cpass.site03.nivolapiemonte.it

#TEST COTO
3a) beehive ssh nodes-files put ts-wf1-coto-cpass.site03.nivolapiemonte.it Stampa_Copia_Destinatario.rptdesign /tmp/Stampa_Copia_Destinatario.rptdesign
4a) beehive ssh nodes connect   ts-wf1-coto-cpass.site03.nivolapiemonte.it




beehive ssh nodes connect dv-wf1-ua-rp.site03.nivolapiemonte.it

