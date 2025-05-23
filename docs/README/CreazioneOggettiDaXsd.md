#creazione di oggetti java a partire da xsd 

1)copiare un pacchetto integrazione esistente e rinominarlo
modificare il pom specifico con la nuova dicitura esempio <artifactId>cpassbe-integ-acta</artifactId>
aggiungere un blocco per ogni wsdl o xsd da trattare
<execution></execution>
oppure 
<xsdOption>
	<xsd>${basedir}/src/main/resources/notier/xsd/ddt.xsd</xsd>
	<extensionArgs>-no-header</extensionArgs>
	<!-- <packagename>it.notier.model.inviodocumento.request</packagename> -->
</xsdOption>

eliminare il contenuto 
src\main\java\it\csi\cpass\cpassbe\lib\mapper
src\main\java\it\csi\cpass\cpassbe\lib\external\impl


cd D:\myworkspace\CPASS\cpassbe
d:
.\mvnw.cmd clean package -P generate-adapters -X -am -pl integ-acta (verboso)
.\mvnw.cmd clean package -P generate-adapters -am -pl integ-acta

.\mvnw.cmd clean package -P generate-adapters -X -am -pl integ-hr
.\mvnw.cmd clean package -P generate-adapters -X -am -pl integ-notier



echo %ERRORLEVEL% 
se torna 0 è tutto ok



N.B.
se viene aggiunto o modificato un xsd ricordarsi di modificare il pom dell'integrazione specifica 
esempio:

<xsdOption>
	<xsd>${basedir}/src/main/resources/notier/xsd/ddt.xsd</xsd>
	<extensionArgs>-no-header</extensionArgs>
	<!-- <packagename>it.notier.model.inviodocumento.request</packagename> -->
</xsdOption>

