# TweetSC
Tweet Spell Checker (TweetSC) is a spell checker for tweets. It corrects errors in a Spanish short text, e.g., a Tweet.

## How to build / Requirements

- Java 1.8
- Gradle

```
git clone https://github.com/jmorenov/TweetSC
cd TweetSC/code/
chmod +x build_all.sh
./build.sh

```

## How to use from command line
### Spell checker from a text
```
java -jar tweetscexecutable/build/libs/tweetscexecutable-all-v0.1.0-alpha.jar \
    -text Texto de prueba
```

### Evaluate with Tweet Norm 2013 test files
```
java -jar tweetscexecutable/build/libs/tweetscexecutable-all-v0.1.0-alpha.jar \
    -workingDirectory evaluation \
    -annotatedFile tweet-norm-dev10_annotated.txt \
    -tweetsFile tweet-norm-dev10.txt \
    -resultFile results-test-dev10.txt
```

## How to run local web application
```
cd TweetSC/code/tweetscweb
./gradle run
```

## Web online application

https://jmorenov.github.io/TweetSC/

## Where to find test files

https://github.com/jmorenov/TweetSC/blob/master/resources/

## Final Master's Work Report

https://github.com/jmorenov/TweetSC/blob/master/docs/Memory/Memory.pdf

## Software versioning

We use Semantic Versioning for the versions of the project. https://semver.org