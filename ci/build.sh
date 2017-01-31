#!/usr/bin/env bash

set -e -x

git clone sample-app resource-app
echo "creating file"
cd resource-app
mvn clean package
#echo "created the file"
#mvn clean compile
#mvn package
#echo " done compiling"
#mvn install
#echo "done installing"
#mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install
#mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install -Pcoverage-per-test
#mvn sonar:sonar
#java de/bitkings/nitram509/ConcourseJavaMavenTestPrjApplication
#mvn test
echo "done"
