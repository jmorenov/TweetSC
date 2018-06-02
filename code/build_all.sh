#!/bin/bash

cd tweetsccore
./gradlew build
cd ..
cd tweetscweb
./gradlew build
cd ..
cd tweetscexecutable
./gradlew build
./gradlew createJar