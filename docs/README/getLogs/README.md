# Prendi logs

Il programma prendi logs è un programma scritto da **BERTOLOTTI PAOLO FRANCESCO** per poter prendere in modo agile i logs dall'appliactivo CPASS.

Per un'utilizzo ottimale si consiglia di installare sulla propria macchina il programma [7zip](https://www.7-zip.org/download.html).

Il programma è composto da alcuni files: alcuni di questi rimarranno sul proprio PC, altri dovranno essere ospitati sulla propria macchia ponte. 

Andiamo a vederli uno per uno

## _prendi_logs.sh_
Il file ``prendi_logs.sh`` dovrà essere ospitato sulla "propria" macchina ponte nella cartella indicata dalla costante ``FOLDER_PROJ`` consultabile aprendo il file con un editor adatto (quale blocco note, notepad++ o simile.), ma nel caso esista già tale cartatella (di default ``~/logs``) sarà possibile modificarla.

Una volta messo il file sulla macchina bisognerà accertarsi che il file sia eseguibile tramite il comando ``ll`` che dovrà dare un risultato simile a:

```
ll
total 8
-rwxr--r-- 1 72830 domain^users 7595 Mar  6 11:18 prendi_logs.sh
```
Qualora il risultato mostrasse solo ``-rw--r--`` si dovrà lanciare il comando

```
chmod u+x prendi_logs.sh
```

Il comando non mostrerà alcun risultato, ma ora sarà eseguibile con 
```
./prendi_logs.sh
```


## _prendi_logs_dal_mio_PC.bat_
Il file ``prendi_logs_dal_mio_PC.bat`` dovrà essere lasciato sul proprio PC in qualunque cartella si voglia.

Una volta messo il file nella cartella desiderata, bisognerà editarlo (con blocco note, notepad++ o simili) modificando il proprio ``UTENTE``.

Fatto ciò, il programma sarà lanciabile con Powershell con
```
.\prendi_logs.sh
```



## _prendi_logs_dal_mio_PC.sh_
Il file ``prendi_logs_dal_mio_PC.sh``, analogamente al file _prendi_logs_dal_mio_PC.bat_, dovrà essere lasciato sul proprio PC in qualunque cartella si voglia.

Una volta messo il file nella cartella desiderata, bisognerà editarlo (con blocco note, notepad++ o simili) modificando il proprio ``UTENTE``.

Fatto ciò, il programma sarà lanciabile con Git Bash o Ubuntu con
```
./prendi_logs.sh
```
