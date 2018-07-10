package com.jmorenov.tweetsccore.postprocess;

import com.jmorenov.tweetsccore.extra.Parser;
import org.apache.commons.lang3.StringUtils;

/**
 * PostProcess class to apply postprocess
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class PostProcess {
    /**
     * Method to apply postprocess
     * @param text String
     * @return String
     */
    public static String apply(String text) {
        for (String s : text.split(" ")) {
            if (Parser.isValidWord(s)) {
                StringUtils.capitalize(s);
            }
        }

        if (!Parser.isPunctuationSign(text.substring(text.length() -1, text.length()))) {
            text += ".";
        }

        if (text.contains("?")) {
            text = "¿" + text;
        } else if(text.contains("!")) {
            text = "¡" + text;
        }

        return text;
    }
}