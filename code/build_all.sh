#!/bin/bash

cd tweetsccore
./gradlew assemble
cd ..
cd tweetscweb
./gradlew assemble
cd ..
cd tweetscexecutable
./gradlew assemble
./gradlew createJar
cd ..
move tweetscexecutable/build/libs/tweetscexecutable-all-v*.jar .