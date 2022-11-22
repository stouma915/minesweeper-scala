#!/bin/bash

mkdir -p target/pages

cp -f src/main/resources/index.html target/pages

sbt fastLinkJS

cp -f target/scala-*/minesweeper-scala-fastopt/main.js target/pages
