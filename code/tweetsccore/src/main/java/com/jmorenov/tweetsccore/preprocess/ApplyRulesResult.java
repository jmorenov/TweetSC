package com.jmorenov.tweetsccore.preprocess;

import com.jmorenov.tweetsccore.extra.OOV;
import com.jmorenov.tweetsccore.extra.Token;

import java.util.ArrayList;
import java.util.List;

public class ApplyRulesResult {
    private List<OOV> oovs;
    private List<Token> tokens;

    /**
     * Constructor of the class.
     */
    public ApplyRulesResult() {
        this.oovs = new ArrayList<>();
        this.tokens = new ArrayList<>();
    }

    /**
     * Method to get the list of OOVs.
     * @return List OOV
     */
    public List<OOV> getOOVList() {
        return oovs;
    }

    /**
     * Method to get the list of tokens.
     * @return List Token
     */
    public List<Token> getTokens() {
        return tokens;
    }

    /**
     * Method to set the list of OOV.
     * @param oov List OOV
     */
    public void setOOVList(List<OOV> oov) {
        this.oovs = oov;
    }

    /**
     * Method to set the list of tokens.
     * @param tokens List Token
     */
    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    /**
     * Method to add an OOV to the list.
     * @param oov OOV
     */
    public void addOOV(OOV oov) {
        this.oovs.add(oov);
    }

    /**
     * Method to add a token to the list.
     * @param token Token
     */
    public void addToken(Token token) {
        this.tokens.add(token);
    }
}
