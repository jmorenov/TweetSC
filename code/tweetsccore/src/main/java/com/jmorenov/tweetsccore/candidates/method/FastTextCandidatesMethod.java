package com.jmorenov.tweetsccore.candidates.method;

import com.jmorenov.tweetsccore.candidates.Candidate;
import com.jmorenov.tweetsccore.extra.File;
import com.jmorenov.tweetsccore.extra.OOV;
import com.github.jfasttext.JFastText;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * FastTextCandidatesMethod class that define a method to generate candidates.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class FastTextCandidatesMethod extends CandidatesMethod {
    private JFastText jft;

    /**
     * Constructor of the class.
     */
    public FastTextCandidatesMethod() {
        try {
            jft = new JFastText();
            String modelPath = File.getModelsPath();

            jft.loadModel(modelPath + "cc.es.300.bin");
        } catch (Exception ex) {
            jft = null;
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

        if (this.jft != null) {
            List<Float> oovVector = jft.getVector(oov.getToken());

            try (Stream<String> stream = Files.lines(Paths.get(File.getModelsPath() + "word.vec"))) {
                stream.parallel().forEach((line) ->{
                    String[] lineElements = line.split(" ");
                    String word = lineElements[0];
                    List<Float> wordVector = new ArrayList<>();

                    for (int i = 1; i < lineElements.length; i++) {
                        wordVector.add(new Float(lineElements[i]));
                    }

                    double similarity = cosineSimilarity(oovVector, wordVector);

                    if (similarity > 0.995) {
                        candidates.add(new Candidate(word, getMethod()));
                    }
                });

            } catch (IOException e) { }
        }

        return candidates;
    }

    /**
     * Method to obtain the method description.
     * @return CandidatesMethodType
     */
    @Override
    public CandidatesMethodType getMethod() {
        return CandidatesMethodType.FastText;
    }

    /**
     * Method to calculate the cosine similarity between two vector.
     * @param vectorA double[]
     * @param vectorB double[]
     * @return double with the result
     */
    private double cosineSimilarity(List<Float> vectorA, List<Float> vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.size(); i++) {
            dotProduct += vectorA.get(i) * vectorB.get(i);
            normA += Math.pow(vectorA.get(i), 2);
            normB += Math.pow(vectorB.get(i), 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}