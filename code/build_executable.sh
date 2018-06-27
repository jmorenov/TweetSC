#!/bin/bash

cd tweetscexecutable
./gradlew assemble
./gradlew createJar
cd ..
mv tweetscexecutable/build/libs/tweetscexecutable-all-v*.jar .