#!/bin/bash

clear

javac -d bin/ $(find ./src/* | grep .java) && jar cvmf MANIFEST.MF Kaydenji.jar  bin/src/* && java -jar Kaydenji.jar