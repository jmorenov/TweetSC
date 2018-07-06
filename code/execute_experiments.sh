#!/bin/bash

rm -f experiments_results.txt
touch experiments_results.txt

echo -e "Tweet Norm 2013:\n" >> experiments_results.txt

echo -e "DictionaryMethod:\n" >> experiments_results.txt

echo -e "Data: 10" >> experiments_results.txt
java -jar tweetscexecutable-all-v0.2.0-alpha.jar \
    -workingDirectory evaluation \
    -annotatedFile tweet-norm-dev10_annotated.txt \
    -tweetsFile tweet-norm-dev10.txt \
    -resultFile results-dev10.txt \
    -method DictionaryMethod \
    -repetitions 10 >> experiments_results.txt
echo -e "\n" >> experiments_results.txt

echo -e "Data: 100" >> experiments_results.txt
java -jar tweetscexecutable-all-v0.2.0-alpha.jar \
    -workingDirectory evaluation \
    -annotatedFile tweet-norm-dev100_annotated.txt \
    -tweetsFile tweet-norm-dev100.txt \
    -resultFile results-dev100.txt \
    -method DictionaryMethod \
    -repetitions 10 >> experiments_results.txt
echo -e "\n" >> experiments_results.txt

echo -e "Data: 500" >> experiments_results.txt
java -jar tweetscexecutable-all-v0.2.0-alpha.jar \
    -workingDirectory evaluation \
    -annotatedFile tweet-norm-dev500_annotated.txt \
    -tweetsFile tweet-norm-dev500.txt \
    -resultFile results-dev500.txt \
    -method TweetSCMethod \
    -repetitions 10 >> experiments_results.txt
echo -e "\n" >> experiments_results.txt

echo -e "-----------------------------------------------------------" >> experiments_results.txt

echo -e "TweetSCMethod:\n" >> experiments_results.txt

echo -e "Data: 10" >> experiments_results.txt
java -jar tweetscexecutable-all-v0.2.0-alpha.jar \
    -workingDirectory evaluation \
    -annotatedFile tweet-norm-dev10_annotated.txt \
    -tweetsFile tweet-norm-dev10.txt \
    -resultFile results-dev10.txt \
    -method TweetSCMethod \
    -repetitions 10 >> experiments_results.txt
echo -e "\n" >> experiments_results.txt

echo -e "Data: 100" >> experiments_results.txt
java -jar tweetscexecutable-all-v0.2.0-alpha.jar \
    -workingDirectory evaluation \
    -annotatedFile tweet-norm-dev100_annotated.txt \
    -tweetsFile tweet-norm-dev100.txt \
    -resultFile results-dev100.txt \
    -method TweetSCMethod \
    -repetitions 10 >> experiments_results.txt
echo -e "\n" >> experiments_results.txt

echo -e "Data: 500" >> experiments_results.txt
java -jar tweetscexecutable-all-v0.2.0-alpha.jar \
    -workingDirectory evaluation \
    -annotatedFile tweet-norm-dev500_annotated.txt \
    -tweetsFile tweet-norm-dev500.txt \
    -resultFile results-dev500.txt \
    -method TweetSCMethod \
    -repetitions 10 >> experiments_results.txt
echo -e "\n" >> experiments_results.txt