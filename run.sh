#!/bin/bash

clear

javac -d bin/ $(find ./src/* | grep .java) && jar cvmf MANIFEST.MF Algeo01-22013.jar  bin/src/* && java -jar Algeo01-22013.jar