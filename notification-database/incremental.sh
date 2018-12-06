#!/bin/bash
#
# Copyright (c) METAMAGIC GLOBAL INC, USA
#
# This script will restore the database setup

FILE_PATH=$(pwd)
POSTGRES=/usr/lib/postgresql/9.6/bin
DBNAME=notificationdb
DBUSER=postgres
DBVERION=v1.6

#sh ./drop.sh
#sh ./create.sh
#sh ./load.sh

echo incremental script started.

$POSTGRES/psql -d $DBNAME -U $DBUSER -f $FILE_PATH/incremental/$DBVERION/TEMPLATE.sql
$POSTGRES/psql -d $DBNAME -U $DBUSER -c "\COPY \"TEMPLATE\" from '$FILE_PATH/incremental/$DBVERION/UPDATETEMPLATE.csv' with delimiter '#' CSV HEADER"

echo incremental script end.
