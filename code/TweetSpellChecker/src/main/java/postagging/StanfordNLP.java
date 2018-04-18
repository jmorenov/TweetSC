package postagging;

import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.pipeline.*;

import java.io.IOException;
import java.util.*;

public class StanfordNLP extends POSTagging {
    private Properties props;
    private StanfordCoreNLP pipeline;
    private CoreDocument document;

    public StanfordNLP(String text) {
        props = new Properties();

        try {
            props.load(IOUtils.readerFromString("StanfordCoreNLP-spanish.properties"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        props.setProperty("coref.algorithm", "neural");

        pipeline = new StanfordCoreNLP(props);
        document = new CoreDocument(text);

        pipeline.annotate(document);
    }

    public ArrayList<String> getTokens() {
        ArrayList<String> tokens = new ArrayList<String>();

        for (CoreLabel token : document.tokens()) {
            tokens.add(token.toString());
        }

        return tokens;
    }
}
