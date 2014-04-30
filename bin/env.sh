#!/bin/sh

for f in `ls ${HOME}/adapters/jetty-distribution-9.0.4.v20130625/lib/*.jar`
do
  CLASSPATH=${f}:${CLASSPATH}
done

for f in `ls ${HOME}/adapters/httpcomponents-client-4.3.3/lib/*.jar`
do
  CLASSPATH=${f}:${CLASSPATH}
done

export CLASSPATH

JAVA_HOME=${HOME}/adapters/jdk1.7.0_25 ; export JAVA_HOME
PATH=${HOME}/adapters/groovy-2.1.6/bin:${JAVA_HOME}/bin:${PATH}:. ; export PATH
