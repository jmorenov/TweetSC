package com.jmorenov.tweetsccore.candidates.method;

import java.util.concurrent.Callable;

public class CandidatesMethodCreateInstance implements Callable<CandidatesMethod> {
    private CandidatesMethodType type;

    public CandidatesMethodCreateInstance(CandidatesMethodType type) {
        this.type = type;
    }

    @Override
    public CandidatesMethod call() throws Exception {
        return CandidatesMethodCreator.getMethodByType(type);
    }
}