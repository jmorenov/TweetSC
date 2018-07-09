#!/usr/bin/python

jrc_entities = "jrc_entities.txt" 

with open(jrc_entities) as f:
    content = f.readlines()

content = [x.strip() for x in content]
words = []

for lineContent in content:
    wordsOfLine = lineContent.split("\t")
    enities = wordsOfLine[3].split("+")
    words = words + enities

fileOut = open('test.dict', 'w')

fileOut.write("\n".join(words))


'''entities_files = ["entities.txt", "nombres_propios.txt"]

for entityFile in entities_files:
    with open(entityFile) as f:
        content = f.readlines()

    content = [x.strip() for x in content]
    words = []

    for lineContent in content:
        wordsOfLine = lineContent.split()
        words = words + wordsOfLine

fileOut = open('aspellNormalized.dict', 'w')

fileOut.write("\n".join(words))'''