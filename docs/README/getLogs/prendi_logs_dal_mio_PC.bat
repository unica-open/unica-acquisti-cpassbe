@echo off
setlocal

REM Impostazione delle variabili
set UTENTE=72830
set INDIRIZZO_SERVER=cmpto1-cons02.site01.nivolapiemonte.it
set REMOTE_LOG_DIR=/home/DOMNT/%UTENTE%/logs
set FILE_TO_DOWNLOAD=tmp-take-me-out.tar.gz
REM Rimuove l'estensione .gz dal nome del FILE_TO_DOWNLOAD
set EXTRACTED_TAR=%FILE_TO_DOWNLOAD:.gz=% 
set AMBIENTE=%1
REM Percorso completo di 7-Zip
set SEVENZIP="C:\Program Files\7-Zip\7z.exe"



REM Controllo dell'argomento
if "%AMBIENTE%"=="" (
	echo Usage: %0 ^<ambiente^>
	echo. ^<ambiente^> can be: PROD-COTO, TST-COTO, DEV-COTO, PROD-CMTO, TST-CMTO, DEV-CMTO, PROD-MULT, TST-MULT, DEV-MULT, PROD-RP, TST-RP, DEV-RP
	echo. Se ci sono problemi, verifica OpenSSH tramite Impostazioni ^> App ^> Funzionalità facoltative.
	endlocal
	exit 1
)

REM Esecuzione di prendi_logs.sh sul server remoto
echo Esecuzione di prendi_logs.sh con ambiente %AMBIENTE%...
ssh %UTENTE%@%INDIRIZZO_SERVER% "cd %REMOTE_LOG_DIR%; rm -rf tmp* take-me-out.tar.gz take-me-out.tar; ./prendi_logs.sh %AMBIENTE%"


if errorlevel 1 (
	echo Errore durante l'esecuzione di prendi_logs.sh.
	endlocal
	exit 1
)

REM Download del file
echo Download del file %FILE_TO_DOWNLOAD%...
scp %UTENTE%@%INDIRIZZO_SERVER%:%REMOTE_LOG_DIR%/%FILE_TO_DOWNLOAD% "%FILE_TO_DOWNLOAD%"

if errorlevel 1 (
	echo Errore durante il download del file.
	endlocal
	exit 1
)


REM Estrarre il file .tar dal file .tar.gz
%SEVENZIP% e %FILE_TO_DOWNLOAD% -oC:\PercorsoDestinazione -r

REM Estrarre il contenuto del file .tar
%SEVENZIP% x %EXTRACTED_TAR% -oC:\PercorsoDestinazione -r

echo Operazione completata.
pause
endlocal