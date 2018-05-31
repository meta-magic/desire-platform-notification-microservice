#!/bin/bash
#
# Copyright (c) METAMAGIC GLOBAL INC, USA
#
# This script will restore the database setup

FILE_PATH=$(pwd)

echo "Database inremental process started"

sh ./drop.sh
sh ./create.sh
sh ./load.sh

echo "Database inremental process completed successfully"