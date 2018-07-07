package com.jmorenov.tweetsccore.tokenizer;

import com.jmorenov.tweetsccore.extra.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SimpleTokenizer class to tokenize a text.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class SimpleTokenizer extends Tokenizer {
    /**
     * Method to get the tokens from the text.
     * @param text String with the text
     * @return List of String with the tokens
     */
    @Override
    public List<Token> getTokens(String text) {
        List<Token> tokens = new ArrayList<>();
        Matcher matcher = Pattern.compile("\\S+").matcher(text);

        while (matcher.find()) {
            Matcher matcher2 = Pattern.compile("[.,;:?¿¡!]+$").matcher(matcher.group());

            if (matcher2.find()) {
                String word = matcher.group().substring(0, matcher2.start());
                String nonWord = matcher2.group();

                Token wordToken = new Token(word, matcher.start(), matcher.start() + word.length());
                Token nonWordToken = new Token(nonWord, matcher.start() + word.length(), matcher.end());

                tokens.add(wordToken);
                tokens.add(nonWordToken);
            } else {
                Token token = new Token(matcher.group(), matcher.start(), matcher.end());
                tokens.add(token);
            }
        }

        return tokens;
    }
}
