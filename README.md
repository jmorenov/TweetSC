# TweetSC
Tweet Spell Checker (TweetSC) is a spell checker for tweets. It corrects errors in a Spanish short text, e.g., a Tweet.

## How to clone / Requirements

- Java 1.8
- Gradle

```
git clone https://github.com/jmorenov/TweetSC
```

## How to use from command line
```
cd TweetSC/code/tweetscexecutable
```
```
./gradlew clean build
```
```
./gradlew createJar
```
```
java -jar org.jmorenov.tweetscexecutable-0.1.0-alpha.jar -text "Texto de prueba"
```

## How to run local web application
```
cd TweetSC/code/tweetscweb
```
```
./gradlew clean build
```
```
./gradle run
```
```
java -jar org.jmorenov.tweetscexecutable-0.1.0-alpha.jar -text "Texto de prueba"
```

## Web online application

https://jmorenov.github.io/TweetSC/

## Where to find test files

https://github.com/jmorenov/TweetSC/blob/master/resources/

## Final Master's Work Report

https://github.com/jmorenov/TweetSC/blob/master/docs/Memory/Memory.pdf

## Software versioning

We use Semantic Versioning for the versions of the project. https://semver.org