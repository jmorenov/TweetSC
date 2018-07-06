# TweetSC
Tweet Spell Checker (TweetSC) is a spell checker for tweets. It corrects errors in a Spanish short text, e.g., a Tweet.

## How to build

### Minimum requirements
- Java 1.8
- Gradle 4.4
- Python 2.7

### Linux/Mac
```
git clone https://github.com/jmorenov/TweetSC
cd TweetSC/code/
chmod +x build_all.sh
./build_all.sh
```

### Windows
```
git clone https://github.com/jmorenov/TweetSC
cd TweetSC\code\
.\build_all.bat
```

## How to use from command line
### Spell checker from a text
```
java -jar tweetscexecutable-all-v0.2.0-alpha.jar \
    -text Texto de prueba
```

### Evaluate with Tweet Norm 2013 test files
```
java -jar tweetscexecutable-all-v0.2.0-alpha.jar \
    -workingDirectory evaluation \
    -annotatedFile tweet-norm-dev100_annotated.txt \
    -tweetsFile tweet-norm-dev100.txt \
    -resultFile results-dev100.txt \
    -method TweetSCMethod \
    -repetitions 1
```

## How to run local web application
```
cd tweetscweb
./gradlew appEngineRun
```

## Web online application

https://jmorenov.github.io/TweetSC/

## Where to find test files

https://github.com/jmorenov/TweetSC/blob/master/code/evaluation

## Final Master's Work Report

https://github.com/jmorenov/TweetSC/blob/master/docs/thesis/thesis.pdf

## Software versioning

We use Semantic Versioning for the versions of the project. https://semver.org