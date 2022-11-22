#!/bin/bash

mkdir -p target/pages

cp -f index.html target/pages

sbt fastLinkJS

cp -f target/scala-*/minesweeper-scala-fastopt/main.js target/pages
