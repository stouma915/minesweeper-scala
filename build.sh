#!/bin/bash

mkdir -p out

cp -f index.html out
cp -rf resources out

sbt fastLinkJS

cp -f target/scala-*/minesweeper-scala-fastopt/main.js out
