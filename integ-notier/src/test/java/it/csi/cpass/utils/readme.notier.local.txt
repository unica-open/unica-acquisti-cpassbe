PROD	https://notier.regione.emilia-romagna.it/notier/
TEST	https://test-notier.regione.emilia-romagna.it/notier/
annarita.tallarico
!Acquisti1!


DOC analisi
C:\Users\filip\Dropbox\CPASS\Analisi\01.Cdu\03.Integrazioni\BOZZA

DOC notier
file:///C:/Users/filip/Downloads/NOTIER_AT_SpecificheServizi_v1.23%20(3).pdf
https://intercenter.regione.emilia-romagna.it/noti-er-ordini-ddt-fatture/ambiente-di-test
https://intercenter.regione.emilia-romagna.it/noti-er-ordini-ddt-fatture/specifiche-tecniche
https://intercenter.regione.emilia-romagna.it/noti-er-ordini-ddt-fatture/specifiche-tecniche/specifiche-di-comunicazione/specifiche-funzionali/@@download/file/NOTIER_Specifiche_Funzionali_1.1.pdf


host	test-notier.regione.emilia-romagna.it
port	8443

host	localhost
port	7080


https://test-notier.regione.emilia-romagna.it:8443/notier/rest/v1.0/documenti/invio

ekrG$x+e3^

keytool -importkeystore -srckeystore "D:\certificato\ACSI.p12" -srcstoretype pkcs12 -srcalias acsi -destkeystore "D:\Programmi\java\jdk-11.0.6\lib\security\cacerts" -deststoretype jks -deststorepass changeit -destalias notier-test
password di origine: ekrG$x+e3^
cd D:\Programmi\java\jdk-11.0.6\bin
keytool -list -cacerts -alias notier-test

AINTERCENTER


-- - import certificato server (esportato da Chrome - https://test-notier.regione.emilia-romagna.it:8443/notier/rest/v1.0/documenti/invio)
	keytool -importcert -alias notier-server -cacerts -file D:\workspace-cpass\cpassbe\integ-notier\src\test\resources\notier_certificato_server.cer
	keytool -list -cacerts -alias notier-server
	
	keytool -list --cacerts | find "notier"
	
	per cancellare un certificato
	keytool -cacerts -delete -alias notier-test
	keytool -cacerts -delete -alias notier-server


--- filippo locale ---
senza certificato
	executing request POST https://test-notier.regione.emilia-romagna.it:8443/notier/rest/v1.0/documenti/invio HTTP/1.1
	javax.net.ssl.SSLHandshakeException: Received fatal alert: bad_certificate

C:\java\jdk-11.0.6.10-hotspot\bin>
	keytool -importkeystore -srckeystore "D:\workspace-cpass\cpassbe\integ-notier\src\test\resources\ACSI.p12" -srcstoretype pkcs12 -srcalias acsi -deststoretype jks -destalias notier-test
	keytool -importkeystore -srckeystore "D:\workspace-cpass\cpassbe\integ-notier\src\test\resources\ACSI.p12" -srcstoretype pkcs12 -srcalias acsi -destalias notier-test -cacerts
	
	Importazione del keystore D:\workspace-cpass\cpassbe\integ-notier\src\test\resources\ACSI.p12 in C:\Users\filip\.keystore in corso...
	Immettere la password del keystore di destinazione:
	Immettere la password del keystore di origine:

C:\java\jdk-11.0.6.10-hotspot\bin>
	keytool -list
		
	Immettere la password del keystore:
	Tipo keystore: PKCS12
	Provider keystore: SUN
	
	Il keystore contiene 2 voci
	
	notier-test, 9 dic 2020, PrivateKeyEntry,
	Copia di certificato (SHA-256): 58:31:1E:27:EB:73:2F:66:37:6F:B8:A1:16:F0:C2:CC:E3:29:F4:0C:67:E9:57:5E:23:C5:95:44:D8:76:E7:B8
	serviziocontrattipubblici, 9 dic 2020, trustedCertEntry,
	Copia di certificato (SHA-256): BC:27:2D:EF:C7:B9:8B:AC:30:3E:FD:75:26:96:D1:67:A3:23:6A:AC:F8:44:1E:2B:A3:FB:67:31:92:E0:1E:03


