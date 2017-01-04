#!/usr/bin/env bash

set -e -x

git clone sample-app resource-app
echo "creating file"
cd resource-app
echo "created the file"
mvn clean compile
#echo " done compiling"
#mvn install
echo "done installing"
#java de/bitkings/nitram509/ConcourseJavaMavenTestPrjApplication
#mvn test
echo "done testing"
