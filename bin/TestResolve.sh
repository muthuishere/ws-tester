#!/bin/sh

cd ${HOME}/test

. ./env.sh

UNAME=`uname -n`

if [ $# -ne 1 ]
then
  echo "Usage: TestResolve.sh -{TestcaseFile}"
  exit 1
fi

if [ X$1 = X-start ]
then
  if [ ! -d logs ]
  then
    mkdir logs
  fi

  TMP=/tmp/ps_$$
  /usr/ucb/ps -wwaux > $TMP

  if [ `grep -c "TestResolve.sh -daemon" $TMP` -lt 1 ]
  then
    if [ `grep -c "TestResolve.groovy" $TMP` -lt 1 ]
    then
      echo "Starting \"TestResolve.groovy\""
      nohup TestResolve.sh -daemon 2>&1 | logmgr.pl logs/TestResolve.log 1000000 4 &
    fi
  fi

  rm -f $TMP
  exit 0
fi

if [ X$1 = X-stop ]
then
  TMP=/tmp/ps_$$
  /usr/ucb/ps -wwaux > $TMP

  for p in `grep "TestResolve.sh -daemon" $TMP | nawk '{ print $2 }'`
  do
    echo "Stopping TestResolve.sh -daemon (PID=$p)"
    kill -9 $p
  done

  for p in `grep "TestResolve.groovy" $TMP | nawk '{ print $2 }'`
  do
    echo "Stopping TestResolve.groovy (PID=$p)"
    kill -9 $p
  done

  rm -f $TMP
  exit 0
fi


    echo ""
    echo "Start: `date`"
    groovy TestResolve.groovy TestResolve.conf_$UNAME
    echo "Fin: `date`"
    sleep 10
