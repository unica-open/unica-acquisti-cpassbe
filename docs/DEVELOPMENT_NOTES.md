# Clono i progetti
```bash
cd /d/myworkspace/CPASS/cpass-web
git clone ssh://gogs/cpass/cpassbe.git
git clone ssh://gogs/cpass/cpassweb.git
```

# DB TEST
```
jdbc:postgresql://10.136.6.151:5432/cpass
USER: cpass
PWD:  cpass
```
TEST
`http://10.136.6.151/cpass/home`

# Terminare i processi sul db
```sql
SELECT pg_terminate_backend(pg_stat_activity.pid)
FROM pg_stat_activity
WHERE pg_stat_activity.datname = 'cpass';
```

## Comandi Maven
Compilazione: `mvn clean package`

OWASP dependencies check: `mvn clean package org.owasp:dependency-check-maven:aggregate -P owasp`

Add SPDX license to sources: `mvn clean package license:update-file-header@add-licenses -P add-license`

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

# Add certificate to JDK
- Download certificate via browser/openssl
- Import: `$JAVA_HOME/keytool -importcert -alias serviziocontrattipubblici -cacerts -file <certificate>`
- Verify: `$JAVA_HOME/keytool -list -cacerts -alias serviziocontrattipubblici`