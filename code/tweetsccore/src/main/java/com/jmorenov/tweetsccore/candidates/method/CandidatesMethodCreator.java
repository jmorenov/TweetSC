package com.jmorenov.tweetsccore.candidates.method;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CandidatesMethodCreator {
    public static CandidatesMethod getMethodByType(CandidatesMethodType type) {
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

    public static List<CandidatesMethod> getAllMethodParallel() {
        List<CandidatesMethod> methods = new ArrayList<>();

        try {
            ExecutorService executor = Executors.newFixedThreadPool(8);
            CompletionService<CandidatesMethod> compService = new ExecutorCompletionService<>(executor);

            compService.submit(new CandidatesMethodCreateInstance(CandidatesMethodType.Accented));
            compService.submit(new CandidatesMethodCreateInstance(CandidatesMethodType.LevenshteinFST));
            compService.submit(new CandidatesMethodCreateInstance(CandidatesMethodType.Metaphone));
            compService.submit(new CandidatesMethodCreateInstance(CandidatesMethodType.L_L));
            compService.submit(new CandidatesMethodCreateInstance(CandidatesMethodType.FastText));
            Future<CandidatesMethod> future = compService.take();

            try {
                methods.add(future.get());
            } catch (ExecutionException ex) {
                methods = null;
            }
        } catch (InterruptedException ex) {
            methods = null;
        }

        return methods;
    }

    public static List<CandidatesMethod> getAllMethods() {
        List<CandidatesMethod> methods = new ArrayList<>();

        methods.add(new LevenshteinFSTCandidatesMethod());
        methods.add(new MetaphoneCandidatesMethod());
        methods.add(new L_LCandidatesMethod());
        methods.add(new FastTextCandidatesMethod());
        methods.add(new AccentedCandidatesMethod());

        return methods;
    }
}