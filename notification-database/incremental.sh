#!/bin/bash
#
# Copyright (c) METAMAGIC GLOBAL INC, USA
#
# This script will restore the database setup

FILE_PATH=$(pwd)
POSTGRES=/usr/lib/postgresql/9.6/bin
DBNAME=notificationdb
DBUSER=postgres

echo "Database inremental process started"

#sh ./drop.sh
#sh ./create.sh
#sh ./load.sh

$POSTGRES/psql -d $DBNAME -U $DBUSER -f $FILE_PATH/incremental/TEMPLATE.sql

echo "Database inremental process completed successfully"