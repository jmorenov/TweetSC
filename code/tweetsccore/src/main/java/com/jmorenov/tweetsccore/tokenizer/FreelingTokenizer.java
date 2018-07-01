package com.jmorenov.tweetsccore.tokenizer;

import com.jmorenov.tweetsccore.extra.FreelingInitializator;
import edu.upc.freeling.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * FreelingTokenizer class to tokenize a text.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class FreelingTokenizer extends Tokenizer {
    private edu.upc.freeling.Tokenizer tokenizer;

    /**
     * Constructor of the class
     */
    public FreelingTokenizer() {
        String freelingDataPath = FreelingInitializator.init();

        tokenizer = new edu.upc.freeling.Tokenizer( freelingDataPath + "es/tokenizer.dat" );
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

        for (int i = 0; i < wordsCount; i++) {
            tokens.add(words.front().getForm());
            words.popFront();
        }

        return tokens;
    }
}