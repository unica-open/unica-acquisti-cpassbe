#!/bin/bash
#// set -x # Attiva il debug

# Usage: ./prendi_logs_dal_mio_PC.sh <ambiente>
# This script creates a tar.gz archive of the logs directory
# on the remote host and stores it in the local directory
# as logs.tar.gz

# Check if the user has provided the environment
if [ $# -ne 1 ]; then
	echo "Usage: $0 <ambiente>"
	echo "  <ambiente> can be: PROD-COTO, TST-COTO, DEV-COTO, PROD-CMTO, TST-CMTO, DEV-CMTO, PROD-MULT, TST-MULT, DEV-MULT, PROD-RP, TST-RP, DEV-RP"
	echo "Se ci sono problemi con Windows, verifica OpenSSH tramite Impostazioni > App > Funzionalità facoltative."
	exit 1
fi

UTENTE="72830"
INDIRIZZO_SERVER="cmpto1-cons02.site01.nivolapiemonte.it"
REMOTE_LOG_DIR="~/logs"
FILE_TO_DOWNLOAD="tmp-take-me-out.tar.gz"



# Call prendi_logs.sh on the remote host
ssh $UTENTE@$INDIRIZZO_SERVER -t "cd $REMOTE_LOG_DIR; rm -r tmp* take-me-out.tar.gz take-me-out.tar; ./prendi_logs.sh $1"

# Copy the tar.gz file from the remote host to the local directory
scp $UTENTE@$INDIRIZZO_SERVER:$REMOTE_LOG_DIR/$FILE_TO_DOWNLOAD ./

# Uncompress the tar.gz file
tar -xzf $FILE_TO_DOWNLOAD

#// set +x # Disattiva il debug