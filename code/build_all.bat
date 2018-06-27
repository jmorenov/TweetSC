@echo off
cd tweetsccore
call gradlew.bat assemble
cd ..
cd tweetscweb
call gradlew.bat assemble
cd ..
cd tweetscexecutable
call gradlew.bat assemble
call gradlew.bat createJar
cd ..
move tweetscexecutable\build\libs\tweetscexecutable-all-v*.jar .