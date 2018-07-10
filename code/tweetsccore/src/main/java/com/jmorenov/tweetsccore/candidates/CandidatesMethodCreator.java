package com.jmorenov.tweetsccore.candidates;

import java.util.concurrent.Callable;

public class CandidatesMethodCreator implements Callable<CandidatesMethod> {
    private CandidatesMethodType type;

    public CandidatesMethodCreator(CandidatesMethodType type) {
        this.type = type;
    }

    @Override
    public CandidatesMethod call() throws Exception {
        CandidatesMethod method = new AccentedCandidatesMethod();

        switch (type) {
            case Accented:
                method = new AccentedCandidatesMethod();
                break;
            case LevenshteinFST:
                method = new LevenshteinFSTCandidatesMethod();
                break;
            case FastText:
                method = new FastTextCandidatesMethod();
                break;
            case L_L:
                method = new L_LCandidatesMethod();
                break;
            case Metaphone:
                method = new MetaphoneCandidatesMethod();
                break;
        }

        return method;
    }
}
