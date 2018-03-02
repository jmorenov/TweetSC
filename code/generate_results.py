#!/usr/bin/env python
# -*- coding: utf-8 -*- 

import re
import spell_checker

lines = tuple(open('../resources/tweet-norm-dev10.txt', 'r'))

resultFile = open('../resources/results-dev10.txt', 'w') 

for line in lines:
    idIndex = line.find('\t')
    id = line[0:idIndex]
    tweetIndex = line.rfind('\t')
    tweet = line[tweetIndex+1:-1]
    result = spell_checker.spellChecker(tweet)
    writeToFile = str(id + '\t' + result)

    resultFile.write(writeToFile)

resultFile.close()
