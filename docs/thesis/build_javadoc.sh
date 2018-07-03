#!/bin/bash

javadoc -docletpath TeXDoclet.jar \
    -doclet org.stfm.texdoclet.TeXDoclet \
    -noindex \
    -tree \
    -hyperref \
    -output tweetsccore_javadoc.tex \
    -title "TweetSCCore Javadoc" \
    -author "Javier Moreno" \
    -sourcepath ../../code/tweetsccore/src/main/java \
    -subpackages com \
    -include \
    -sectionlevel subsection

javadoc -docletpath TeXDoclet.jar \
    -doclet org.stfm.texdoclet.TeXDoclet \
    -noindex \
    -tree \
    -hyperref \
    -output tweetscweb_javadoc.tex \
    -title "TweetSCWeb Javadoc" \
    -author "Javier Moreno" \
    -sourcepath ../../code/tweetscweb/src/main/java \
    -subpackages com \
    -include \
    -sectionlevel subsection

javadoc -docletpath TeXDoclet.jar \
    -doclet org.stfm.texdoclet.TeXDoclet \
    -noindex \
    -tree \
    -hyperref \
    -output tweetscexecutable_javadoc.tex \
    -title "TweetSCExecutable Javadoc" \
    -author "Javier Moreno" \
    -sourcepath ../../code/tweetscexecutable/src/main/java \
    -subpackages com \
    -include \
    -sectionlevel subsection