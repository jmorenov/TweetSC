package com.jmorenov.tweetsccore.tokenizer;

import edu.upc.freeling.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * OpenNLPTokenizer class to tokenize a text.
 * https://opennlp.apache.org/docs/1.8.4/manual/opennlp.html
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class FreelingTokenizer extends Tokenizer {
    private edu.upc.freeling.Tokenizer tokenizer;

    /**
     * Constructor of the class
     */
    public FreelingTokenizer() {
        //String freeling_javaAPIPath = FreelingTokenizer.class.getClassLoader().getResource("freeling_javaAPI.so").getPath();
        //String libfreelingPath = FreelingTokenizer.class.getClassLoader().getResource("freeling_javaAPI.so").getPath();
        String freeling_javaAPIPath = FreelingTokenizer.class.getClassLoader().getResource("libfreeling_javaAPI.dylib").getPath();
        String libfreelingPath = FreelingTokenizer.class.getClassLoader().getResource("libfreeling.dylib").getPath();

        System.load(libfreelingPath);
        System.load(freeling_javaAPIPath);

        String freelingDataPath =  Paths.get("src","main","resources", "freeling").toAbsolutePath() + "/";
        Util.initLocale( "default" );
        String LANG = "es";

        tokenizer = new edu.upc.freeling.Tokenizer( freelingDataPath + LANG + "/tokenizer.dat" );
    }

    /**
     * Method to get the tokens from the text.
     * @param text String with the text
     * @return List of String with the tokens
     */
    @Override
    public List<String> getTokens(String text) {
        List<String> tokens = new ArrayList<>();
        ListWord words = tokenizer.tokenize(text);
        int wordsCount = (int) words.size();
        Word a = words.front();

        for (int i = 0; i < wordsCount; i++) {
            tokens.add(words.front().getForm());
            words.popFront();
        }

        return tokens;
    }
}