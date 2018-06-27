@echo off
cd tweetscexecutable
call gradlew.bat assemble
call gradlew.bat createJar
cd ..
move tweetscexecutable\build\libs\tweetscexecutable-all-v*.jar .