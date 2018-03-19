#!/bin/bash
#
# Copyright (c) METAMAGIC GLOBAL INC, USA
#
# This script will load the data in associated database

FILE_PATH=$(pwd)
POSTGRES=/usr/lib/postgresql/9.6/bin
DBNAME=notificationdb
DBUSER=postgres

echo Load database process started.

echo Loading data.
$POSTGRES/psql -d $DBNAME -U $DBUSER -c "\COPY \"TEMPLATE\" from '$FILE_PATH/data/TEMPLATE.csv' with delimiter '#' CSV HEADER"
echo Loading data successfully done.

echo Load database process successfully completed.
