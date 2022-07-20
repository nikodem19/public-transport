#! /bin/sh
# xdaubn00
# Script that downloads required libraries for IJA Project 2020

PATH=$PATH:/bin:/usr/bin
export PATH

wget https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.11.0/jackson-core-2.11.0.jar
wget https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.11.0/jackson-databind-2.11.0.jar
wget https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.11.0/jackson-annotations-2.11.0.jar
wget https://repo1.maven.org/maven2/com/fasterxml/jackson/module/jackson-module-paranamer/2.11.0/jackson-module-paranamer-2.11.0.jar

