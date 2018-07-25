#!/bin/bash
#
# Copyright (c) METAMAGIC GLOBAL INC, USA
#
# This script will restore the database setup

FILE_PATH=$(pwd)
POSTGRES=/usr/lib/postgresql/9.6/bin
DBNAME=notificationdb
DBUSER=postgres
DBVERION=v1.3

#sh ./drop.sh
#sh ./create.sh
#sh ./load.sh

echo incremental script started.

$POSTGRES/psql -d $DBNAME -U $DBUSER -f $FILE_PATH/incremental/$DBVERION/TEMPLATE.sql

echo incremental script end.
