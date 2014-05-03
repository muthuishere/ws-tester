#!/bin/sh

TEST_ENVIRONMENT="localdev"

EXT_LIB=${HOME}/adapters/bespoken/lib/httpcomponents-client-4.3.3/*;${HOME}/adapters/bespoken/lib/groovy-wslite/*



export EXT_LIB



export TEST_ENVIRONMENT
CLASSPATH=${CLASSPATH}:${EXT_LIB}

export CLASSPATH

JAVA_HOME=${HOME}/adapters/jdk1.7.0_25 ; export JAVA_HOME
PATH=${HOME}/adapters/groovy-2.1.6/bin:${JAVA_HOME}/bin:${PATH}:. ; export PATH
