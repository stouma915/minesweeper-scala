#!/bin/bash

mkdir -p out

cp -f *.html out
cp -f *.css out

sbt fastLinkJS

cp -f target/scala-*/minesweeper-scala-fastopt/main.js out
