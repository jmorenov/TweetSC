#!/usr/bin/python

import phonetic_algorithms_es

fname = "aspellNormalized.dict"

with open(fname) as f:
    content = f.readlines()

content = [x.strip() for x in content]
pa = phonetic_algorithms_es.PhoneticAlgorithmsES()
fout = open("aspellNormalizedPhonetic.dict", "w")

for word in content:
    phoneticWord = pa.metaphone(word)
    fout.write(word + " : " + phoneticWord + "\n")

fout.close()