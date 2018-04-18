curl http://nlp.stanford.edu/software/stanford-corenlp-full-2018-02-27.zip
jar xf stanford-corenlp-full-2018-02-27.zip
move stanford-corenlp-full-2018-02-27/stanford-corenlp-3.9.1-models.jar lib/stanford-corenlp-3.9.1-models.jar
del stanford-corenlp-full-2018-02-27
del stanford-corenlp-full-2018-02-27.zip
curl https://nlp.stanford.edu/software/stanford-spanish-corenlp-2018-02-27-models.jar
move stanford-spanish-corenlp-2018-02-27-models.jar lib/stanford-spanish-corenlp-2018-02-27-models.jar