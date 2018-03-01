#!/usr/bin/env python
# -*- coding: utf-8 -*- 

import re, collections
import emoji

def words(text):
    """
    Escoje todas las palabras del text
    """
    return re.findall(u'[\xf1\xe1\xe9\xed\xf3\xfaa-z]+', unicode(text.lower(), 'utf-8'))

def train(features):
    """
    calcula el P(C)
    """
    model = collections.defaultdict(lambda: 1)
    for f in features:
        model[f] += 1  
    list = []
    return model

NWORDS = train(words(file('../resources/LibreOffice_es_ES.dic').read()))
alphabet = u'abcdefghijklmnopqrstuvwxyz\xf1\xe1\xe9\xed\xf3\xfa'

def edits1(word): 
    """
    Calcula el P(W/C) ----- distancia=1
    """
    s = [(word[:i], word[i:]) for i in range(len(word) + 1)]
    deletes= [a + b[1:] for a, b in s if b]
    transposes = [a + b[1] + b[0] + b[2:] for a, b in s if len(b) > 1]
    replaces= [a + c + b[1:] for a, b in s for c in alphabet if b]
    inserts= [a + c + b for a, b in s for c in alphabet]
    return set(deletes + transposes + replaces + inserts)

def known_edits2(word):
    """
        Calcula el P(W/C) ----- distancia=2 y se descarta si P(C)=0
    """
    return set(e2 for e1 in edits1(word) for e2 in edits1(e1) if e2 in NWORDS)

def known(words):
    """
        Descarta si P(C)=0
    """
    return set(w for w in words if w in NWORDS)

def removeEmojiFromText(text):
    emojis = extract_emojis(text)
    normalizedText = text
    for emoji in emojis:
        normalizedText = normalizedText.replace(emoji, '')

    return normalizedText

def extract_emojis(str):
    emojis_list = map(lambda x: ''.join(x.split()), emoji.UNICODE_EMOJI.keys())
    r = re.compile('|'.join(re.escape(p) for p in emojis_list))
    aux = [' '.join(r.findall(s)) for s in [str]][0]
    aux = aux.split()

    return(aux)

def correct(word):
    candidates = known([word]) or known(edits1(word)) or known_edits2(word) or [word]  
    return max(candidates, key = NWORDS.get).encode('utf-8')

def spellChecker(text):
    unicodeText = unicode(text.lower(), 'utf-8')
    textWithoutEmojis = removeEmojiFromText(unicodeText)
    normalizedText = ''

    for word in textWithoutEmojis.split():
        if not(re.match('@[a-zA-Z0-9_]*$', word)) and not(re.match('#[a-zA-Z0-9_]*$', word)):
            if re.match("^[a-zA-Z0-9_]*$", word):
                wordCorrect = correct(word)
            else:
                wordCorrect = word

            normalizedText = normalizedText + wordCorrect + ' '

    return normalizedText

text = 'Hola aaa sii no 😒 sde hoy, @jmorenov #aaa la mejor camión karateka de la historia en katas de españa 👏 👏'
normalizedText = spellChecker(text)
print(normalizedText)