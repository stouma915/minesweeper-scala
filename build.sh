#!/bin/bash

readonly SCALA_VERSION=3.2.1

mkdir -p out

cp -f index.html out
cp -rf resources out

if [ "$1" = "production" ]; then
	echo "This is Production Build."
	sbt fullLinkJS
	cp -f bootstrap/target/scala-$SCALA_VERSION/minesweeper-bootstrap-opt/main.js* out
else
	echo "This is Debug Build."
	sbt fastLinkJS
	cp -f bootstrap/target/scala-$SCALA_VERSION/minesweeper-bootstrap-fastopt/main.js* out
fi
