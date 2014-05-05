#!/bin/sh

cd ${HOME}/test

UNAME=`uname -n`

if [ $# -ne 1 ]
then
  echo "Usage: TestResolve.sh -{TestcaseFile}"
  exit 1
fi


. ./env.sh


    echo ""
    echo "Start: `date`"
    groovy TestResolve.groovy $1 
    echo "Fin: `date`"
    sleep 10
