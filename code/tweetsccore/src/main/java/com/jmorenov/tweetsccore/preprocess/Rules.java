package com.jmorenov.tweetsccore.preprocess;

import com.jmorenov.tweetsccore.extra.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Rule class that define the Rules element.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class Rules {
    private List<Rule> rules;

    /**
     * Constructor of the class.
     * @param rulesFilename String with the file name of the rules
     * @throws IOException When the file is not found
     */
    public Rules(String rulesFilename) throws IOException {
        List<String> rulesFileContent = File.readDictionaryFromResources(rulesFilename);
        rules = new ArrayList<>();

        for (String line : rulesFileContent) {
            String[] lineContent = line.split(" ");
            String regex = "(?:^|\\W)(" + lineContent[0] + ")(?:$|\\W)";
            String result = lineContent[1].replace("_", " ");
            Rule rule = new Rule(regex, result);

            rules.add(rule);
        }
    }

    /**
     * Method to get the rules.
     * @return List of Rule
     */
    public List<Rule> getRules() {
        return rules;
    }

    /**
     * Method to add a new rule.
     * @param rule Rule
     */
    public void addRule(Rule rule) {
        this.rules.add(rule);
    }
}
