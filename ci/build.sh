#!/usr/bin/env bash

set -e -x

git clone sample-app resource-app

cd resource-app

mvn clean

mvn install
