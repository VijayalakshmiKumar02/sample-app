#!/usr/bin/env bash

set -e -x

git clone sample-app resource-app
echo "creating file"
cd resource-app
mvn sonar:sonar \
    -Dsonar.host.url=https://sonarqube.com \
    -Dsonar.login=3f448d410b4f84583ae793c965e5ee8d00602528
#
#cd
#mvn clean package


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
#mvn clean compile
#mvn cobertura:cobertura -Dcobertura.report.format=xml

#echo "$(<resource-app/target/surefire-reports)"
#mvn sonar:sonar
echo "done"
