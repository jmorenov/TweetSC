package com.jmorenov.tweetsccore.candidates;

import com.jmorenov.tweetsccore.extra.File;
import com.jmorenov.tweetsccore.extra.OOV;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.*;

/**
 * MetaphoneCandidatesMethod class that define a method to generate candidates.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class MetaphoneCandidatesMethod extends CandidatesMethod {
    private static Map<String, String> phoneticWordsDictionaryMap;

    /**
     * Constructor of the class.
     */
    public MetaphoneCandidatesMethod() {
        try {
            phoneticWordsDictionaryMap = new HashMap<>();
            List<String> phoneticWordsDictionary = File.readDictionaryFromResources("aspellNormalizedPhonetic.dict");

            for (String phoneticWordsDictionaryLine : phoneticWordsDictionary) {
                String wordsLine[] = phoneticWordsDictionaryLine.split(" : ");

                phoneticWordsDictionaryMap.put(wordsLine[0], wordsLine[1]);
            }
        } catch (IOException ex) {
            phoneticWordsDictionaryMap = null;
        }
    }

    /**
     * Method to generate condidates from an OOV.
     * @param oov OOV
     * @return List of Candidates
     */
    @Override
    public List<Candidate> generateCandidates(OOV oov) {
        List<Candidate> candidates = new ArrayList<>();

        try {
            String phoneticWord = getPhoneticWord(oov.getToken());
            Iterator it = phoneticWordsDictionaryMap.keySet().iterator();

            while(it.hasNext()) {
                Object key = it.next();

                double distance = StringUtils.getJaroWinklerDistance(phoneticWord, phoneticWordsDictionaryMap.get(key));

                if (distance >= 0.92) {
                    Candidate candidate = new Candidate((String) key, this.getMethod());
                    candidates.add(candidate);
                }
            }

        } catch (Exception ex) {
            return candidates;
        }

        return candidates;
    }

    /**
     * Method to obtain the method description.
     * @return CandidatesMethodType
     */
    @Override
    public CandidatesMethodType getMethod() {
        return CandidatesMethodType.Metaphone;
    }

    /**
     * Method to get the phonetic word-
     * @param word String
     * @return String
     * @throws Exception when error executing the script
     */
    private String getPhoneticWord(String word) throws Exception {
        java.io.File tempPath = File.createResourceTempFile("phonetic_algorithms_es.py", "py");
        String scriptTempPath = ((java.io.File) tempPath).getAbsolutePath();
        String command = "python " + scriptTempPath + " " + word;
        Process processScript = Runtime.getRuntime().exec(command);
        BufferedReader stdOutput = new BufferedReader(new InputStreamReader(processScript.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(processScript.getErrorStream()));
        String output = "", error = "", outputLine;

        while ((outputLine = stdError.readLine()) != null) {
            error = error.concat(outputLine + "\n");
        }

        while ((outputLine = stdOutput.readLine()) != null) {
            output = output.concat(outputLine);
        }

        if (!error.equals("")) {
            throw new Exception("Error running the script.");
        }

        return output;
    }
}
