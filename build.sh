#!/bin/sh
cd `dirname $0`
git pull
mvn clean install -DskipTests
