#!/bin/bash

SCALA_VERSION=3.2.1

mkdir -p out

cp -f index.html out
cp -rf resources out

if [ "$1" = "production" ]; then
	echo "This is Production Build."
	sbt fullLinkJS
	cp -f target/scala-$SCALA_VERSION/minesweeper-scala-opt/main.js* out
else
	echo "This is Debug Build."
	sbt fastLinkJS
	cp -f target/scala-$SCALA_VERSION/minesweeper-scala-fastopt/main.js* out
fi
