#!/bin/bash
#// set -x # Enable debugging

# Usage: ./prendi_logs.sh <environment>
# This script creates a tar.gz archive of the logs directory
# on the remote host and stores it in the local directory
# as logs_<hostname>.tar.gz

FOLDER_PROJ="~/logs"
WANTED_REMOTE_HOST=$1
#// echo "WANTED_REMOTE_HOST: $WANTED_REMOTE_HOST" # Debug: verifica WANTED_REMOTE_HOST
REMOTE_HOST=""
REMOTE_LOG_FOLDER=""
ARCHIVE_NAME="tmp-take-me-out"  # Nome base dell'archivio
ARCHIVE_TAR="$ARCHIVE_NAME.tar" # Archivio tar non compresso
ARCHIVE_TAR_GZ="$ARCHIVE_NAME.tar.gz" # Archivio compresso finale


# Check if the user has provided the environment
if [ $# -ne 1 ]; then
	echo "Usage: $0 <ambiente>"
	echo "  <ambiente> can be: PROD-COTO, TST-COTO, DEV-COTO, PROD-CMTO, TST-CMTO, DEV-CMTO, PROD-MULT, TST-MULT, DEV-MULT, PROD-RP, TST-RP, DEV-RP"
	exit 1
fi

cd $FOLDER_PROJ

########## COTO ##########
PROD_COTO_HOST_WF23=(wf1-ua-coto-cpass.site01.nivolapiemonte.it wf2-ua-coto-cpass.site01.nivolapiemonte.it)
PROD_COTO_LOG_FOLDER_WF23=(/appserv/jboss/awf230/part002cpassnode01/standalone/log /appserv/jboss/awf230/part002cpassnode02/standalone/log)

TST_COTO_HOST_WF23=(ts-wf1-ua-coto-cpass.site02.nivolapiemonte.it ts-wf2-ua-coto-cpass.site02.nivolapiemonte.it)
TST_COTO_LOG_FOLDER_WF23=(/appserv/jboss/awf230/part006cpassnode01/standalone/log /appserv/jboss/awf230/part006cpassnode02/standalone/log)

DEV_COTO_HOST_WF23=(dv-wf1-ua-coto-cpass.site03.nivolapiemonte.it)
DEV_COTO_LOG_FOLDER_WF23=(/appserv/jboss/awf230/part002cpassnode01/standalone/log)

########## CMTO ##########
PROD_CMTO_HOST_WF23=(wf1-ua-cmto-cpass.site01.nivolapiemonte.it)
PROD_CMTO_LOG_FOLDER_WF23=(/appserv/jboss/awf230/part004cpassnode01/standalone/log)

TST_CMTO_HOST_WF23=(ts-wf1-ua-cmto-cpass.site02.nivolapiemonte.it)
TST_CMTO_LOG_FOLDER_WF23=(/appserv/jboss/awf230/part008cpassnode01/standalone/log)

DEV_CMTO_HOST_WF23=(dv-wf1-ua-cmto-cpass.site03.nivolapiemonte.it)
DEV_CMTO_LOG_FOLDER_WF23=(/appserv/jboss/awf230/part004cpassnode01/standalone/log)

########## MULT ##########
PROD_MULT_HOST_WF23=(wf1-ua-mult-cpass.site01.nivolapiemonte.it)
PROD_MULT_LOG_FOLDER_WF23=(/appserv/jboss/awf230/part003cpassnode01/standalone/log)

TST_MULT_HOST_WF23=(ts-wf1-ua-mult-cpass.site02.nivolapiemonte.it)
TST_MULT_LOG_FOLDER_WF23=(/appserv/jboss/awf230/part007cpassnode01/standalone/log)

DEV_MULT_HOST_WF23=(dv-wf1-ua-mult-cpass.site03.nivolapiemonte.it)
DEV_MULT_LOG_FOLDER_WF23=(/appserv/jboss/awf230/part003cpassnode01/standalone/log)

########## RP ##########
PROD_RP_HOST_WF23=(wf1-ua-rp-cpass.site01.nivolapiemonte.it)
PROD_RP_LOG_FOLDER_WF23=(/appserv/jboss/awf230/part001cpassnode01/standalone/log)

TST_RP_HOST_WF23=(ts-wf1-ua-rp-cpass.site02.nivolapiemonte.it)
TST_RP_LOG_FOLDER_WF23=(/appserv/jboss/awf230/part005cpassnode01/standalone/log)

DEV_RP_HOST_WF23=(dv-wf1-ua-rp-cpass.site03.nivolapiemonte.it)
DEV_RP_LOG_FOLDER_WF23=(/appserv/jboss/awf230/part001cpassnode01/standalone/log)

# Check the environment and set the remote host and log folder
if [ "$WANTED_REMOTE_HOST" = "PROD-COTO" ]; then
	REMOTE_HOST=("${PROD_COTO_HOST_WF23[@]}")
	REMOTE_LOG_FOLDER="${PROD_COTO_LOG_FOLDER_WF23}"
elif [ "$WANTED_REMOTE_HOST" = "TST-COTO" ]; then
	REMOTE_HOST=("${TST_COTO_HOST_WF23[@]}")
	REMOTE_LOG_FOLDER="${TST_COTO_LOG_FOLDER_WF23}"
	echo "REMOTE_HOST: ${REMOTE_HOST[0]}" # Debug: verifica REMOTE_HOST
	echo "REMOTE_LOG_FOLDER: $REMOTE_LOG_FOLDER" # Debug: verifica REMOTE_LOG_FOLDER
elif [ "$WANTED_REMOTE_HOST" = "DEV-COTO" ]; then
	REMOTE_HOST=("${DEV_COTO_HOST_WF23[@]}")
	REMOTE_LOG_FOLDER="${DEV_COTO_LOG_FOLDER_WF23}"
elif [ "$WANTED_REMOTE_HOST" = "PROD-CMTO" ]; then
	REMOTE_HOST=("${PROD_CMTO_HOST_WF23[@]}")
	REMOTE_LOG_FOLDER="${PROD_CMTO_LOG_FOLDER_WF23}"
elif [ "$WANTED_REMOTE_HOST" = "TST-CMTO" ]; then
	REMOTE_HOST=("${TST_CMTO_HOST_WF23[@]}")
	REMOTE_LOG_FOLDER="${TST_CMTO_LOG_FOLDER_WF23}"
elif [ "$WANTED_REMOTE_HOST" = "DEV-CMTO" ]; then
	REMOTE_HOST=("${DEV_CMTO_HOST_WF23[@]}")
	REMOTE_LOG_FOLDER="${DEV_CMTO_LOG_FOLDER_WF23}"
elif [ "$WANTED_REMOTE_HOST" = "PROD-MULT" ]; then
	REMOTE_HOST=("${PROD_MULT_HOST_WF23[@]}")
	REMOTE_LOG_FOLDER="${PROD_MULT_LOG_FOLDER_WF23}"
elif [ "$WANTED_REMOTE_HOST" = "TST-MULT" ]; then
	REMOTE_HOST=("${TST_MULT_HOST_WF23[@]}")
	REMOTE_LOG_FOLDER="${TST_MULT_LOG_FOLDER_WF23}"
elif [ "$WANTED_REMOTE_HOST" = "DEV-MULT" ]; then
	REMOTE_HOST=("${DEV_MULT_HOST_WF23[@]}")
	REMOTE_LOG_FOLDER="${DEV_MULT_LOG_FOLDER_WF23}"
elif [ "$WANTED_REMOTE_HOST" = "PROD-RP" ]; then
	REMOTE_HOST=("${PROD_RP_HOST_WF23[@]}")
	REMOTE_LOG_FOLDER="${PROD_RP_LOG_FOLDER_WF23}"
elif [ "$WANTED_REMOTE_HOST" = "TST-RP" ]; then
	REMOTE_HOST=("${TST_RP_HOST_WF23[@]}")
	REMOTE_LOG_FOLDER="${TST_RP_LOG_FOLDER_WF23}"
elif [ "$WANTED_REMOTE_HOST" = "DEV-RP" ]; then
	REMOTE_HOST=("${DEV_RP_HOST_WF23[@]}")
	REMOTE_LOG_FOLDER="${DEV_RP_LOG_FOLDER_WF23}"
else
	echo "Error: unknown environment: $WANTED_REMOTE_HOST"
	echo "  <ambiente> can be: PROD-COTO, TST-COTO, DEV-COTO, PROD-CMTO, TST-CMTO, DEV-CMTO, PROD-MULT, TST-MULT, DEV-MULT, PROD-RP, TST-RP, DEV-RP"
	exit 1
fi

# Check if the remote host is found
if [ -z "${REMOTE_HOST}" ]; then
	echo "Error: remote host not found"
	exit 1
fi
# Check if the remote log folder is found
if [ -z "${REMOTE_LOG_FOLDER}" ]; then
	echo "Error: remote log folder not found"
	exit 1
fi

#// cat "WANTED_REMOTE_HOST: $WANTED_REMOTE_HOST" > done.log
#// cat "REMOTE_HOST: ${REMOTE_HOST[@]}" >> done.log
#// cat "REMOTE_LOG_FOLDER: ${REMOTE_LOG_FOLDER[@]}" >> done.log


echo "$(pwd) sul server"

rm -r tmp*

# Create an empty tar.gz archive
tar -cvf $ARCHIVE_TAR /dev/null

# Iterate over the indices of the arrays REMOTE_HOST
for i in "${!REMOTE_HOST[@]}"; do
	echo "------------------------------ SERVER $i ------------------------------"

	CURRENT_REMOTE_HOST="${REMOTE_HOST[$i]}"
	CURRENT_REMOTE_LOG_FOLDER="${REMOTE_LOG_FOLDER[$i]}"

	echo "Processing host: $CURRENT_REMOTE_HOST"

	# Extract the environment and entity from the remote node name
	environment=$(echo $CURRENT_REMOTE_HOST | cut -d'-' -f 1)
	entity=$(echo $CURRENT_REMOTE_HOST | cut -d'-' -f 4)
	wf=$(echo $CURRENT_REMOTE_HOST | cut -d'-' -f 2)
	server_num=${wf:2:1}

	echo "environment: $environment"
	echo "entity: $entity"
	echo "wf: $wf"
	echo "server_num: $server_num"



	if [[ $environment == wf* ]]; then
		environment="prod"
	elif [[ $environment == ts* ]]; then
		environment="tst"
	elif [[ $environment == dv* ]]; then
		environment="dev"
	fi

	if [[ $entity == rp ]]; then
		entity="regp"
	fi

	# List the files in the folder and put them in an array
	echo "ls $CURRENT_REMOTE_LOG_FOLDER" > ./tmp_ls_remote_log_folder.sh
	chmod u+x ./tmp_ls_remote_log_folder.sh
	files=($(beehive ssh nodes cmd "$CURRENT_REMOTE_HOST" "tmp_ls_remote_log_folder.sh"))

	#// echo "${files[@]}" # Debug: verifica files

	folder_name="tmp-$environment-$entity-$server_num"
	echo "folder_name: $folder_name"
	mkdir $folder_name # Create a directory for the entity
	cd $folder_name # Go to the directory for the entity

	# Print the elements of the array (optional)
	echo "Files in $CURRENT_REMOTE_LOG_FOLDER:"
	for file in "${files[@]}"; do
		echo "Dowloading $file from $CURRENT_REMOTE_HOST here"
		beehive ssh nodes-files get "$CURRENT_REMOTE_HOST" "$CURRENT_REMOTE_LOG_FOLDER/$file" "./$file"
	done

	cd .. # Go back from to parent directory

	# Adding dir to archive
	tar -rvf $ARCHIVE_TAR $folder_name
	#rm -rf $folder_name

done

# Compress the tar archive to tar.gz
tar -czvf $ARCHIVE_TAR_GZ $ARCHIVE_TAR

# Remove the temporary tar archive
#rm -f $ARCHIVE_TAR

#// set +x # Disable debugging
