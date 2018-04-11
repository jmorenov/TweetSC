curl -LO http://nlp.stanford.edu/software/stanford-corenlp-full-2018-02-27.zip
unzip stanford-corenlp-full-2018-02-27.zip
mv stanford-corenlp-full-2018-02-27/stanford-corenlp-3.9.1-models.jar lib/stanford-corenlp-3.9.1-models.jar
rm -R stanford-corenlp-full-2018-02-27
rm stanford-corenlp-full-2018-02-27.zip
curl -o lib/stanford-spanish-corenlp-2018-02-27-models.jar https://nlp.stanford.edu/software/stanford-spanish-corenlp-2018-02-27-models.jar