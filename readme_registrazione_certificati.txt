registrare certificato nel jdk
	scaricato certificato da Chrome

	<<< NO >>>
	keytool -importcert -alias serviziocontrattipubblici -trustcacerts -file C:\Users\filip\Desktop\CPASS\serviziocontrattipubblici.cer

	keytool -importcert -alias serviziocontrattipubblici -cacerts -file C:\Users\filip\Desktop\CPASS\serviziocontrattipubblici.cer
	keytool -list -cacerts -alias serviziocontrattipubblici

	vedi:
	Directory di C:\java\jdk-11.0.6.10-hotspot\lib\security
		22/09/2020  17:32           106.267 cacerts
	