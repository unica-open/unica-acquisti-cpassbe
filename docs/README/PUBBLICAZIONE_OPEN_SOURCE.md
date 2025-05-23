in radice dobbiamo avere i file
Copyrights.txt
EUPL v1_2 EN-LICENSE.txt
EUPL v1_2 IT-LICENSE.txt

#sotto docs
BOM.csv
THIRD_PARTY_NOTE.md
readme.md

#progetto cappello
https://github.com/unica-open/unica-acquisti
changelog
publiccode
readme

Come generare il file

scaricarsi 
https://github.com/amarchino/bom-generator
configurarsi il config.js
npm install
seguire il readme
npm run grab
npm run run




per i progetti tipo maven
#./mvnw clean bom-builder:build-bom -Pgenerate-bom dal pom del progetto specifico
D:\myworkspace\CPASS\cpassbe>.\mvnw.cmd clean package bom-builder:build-bom -Pgenerate-bom


