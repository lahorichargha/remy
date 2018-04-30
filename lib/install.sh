#!/bin/sh
for jar in */*/*.jar ; do 
  echo Install to repository: $jar ;
  ARTIFACT_ID=$(basename $jar .jar); 
  GROUP_VERSION=$(dirname $jar) ;
  GROUP_ID=$(dirname $GROUP_VERSION) ;
  VERSION=$(basename $GROUP_VERSION) ;
  mvn -e install:install-file -DgeneratePom=true -Dpackaging=jar -Dversion=${VERSION} -DgroupId=${GROUP_ID} -DartifactId=${ARTIFACT_ID} -Dfile=$jar ;
done
