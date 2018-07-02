#!/usr/bin/python

fname = "aspell.dict"

with open(fname) as f:
    content = f.readlines()

content = [x.strip() for x in content]

words = []

for lineContent in content:
    wordsOfLine = lineContent.split()
    words = words + wordsOfLine

fileOut = open('aspellNormalized.dict', 'w')

fileOut.write("\n".join(words))