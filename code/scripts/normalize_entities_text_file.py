#!/usr/bin/python
import re

'''entities_files = ["test.dict", "test2.dict"]

words = []
for entityFile in entities_files:
    with open(entityFile) as f:
        content = f.readlines()

    content = [x.strip() for x in content]

    for lineContent in content:
        wordsOfLine = lineContent.split()
        words = words + wordsOfLine

fileOut = open('entities.dict', 'w')

fileOut.write("\n".join(words))'''

result = [re.findall(r'^[\w0-9_\']+$',line) for line in open('file.txt')]

print result

#fileOut = open('entities2.dict', 'w')

#fileOut.write(result)