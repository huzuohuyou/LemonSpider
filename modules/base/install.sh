#!/bin/bash

echo "[INFO] Install jar to local repository."

mvn clean install

#cp target/lemon-base*.jar ../