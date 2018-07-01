package com.jmorenov.tweetsccore.analyzer;

import com.jmorenov.tweetsccore.extra.FreelingInitializator;
import com.jmorenov.tweetsccore.ner.NER;
import com.jmorenov.tweetsccore.ner.NERELement;
import edu.upc.freeling.*;

import java.util.ArrayList;
import java.util.List;

/**
 * FreelingTokenizer class to tokenize a text.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class FreelingAnalyzer extends Analyzer {
    private edu.upc.freeling.Tokenizer tokenizer;
    private Nec neclass;
    private HmmTagger tg;
    private Maco mf;
    private Senses sen;
    private Ukb dis;
    private String freelingDataPath;

    /**
     * Constructor of the class.
     */
    public FreelingAnalyzer() {
        freelingDataPath = FreelingInitializator.init();
        MacoOptions op = new MacoOptions( "es" );

        op.setDataFiles( "",
                freelingDataPath + "common/punct.dat",
                freelingDataPath + "es/dicc.src",
                freelingDataPath + "es/afixos.dat",
                "",
                freelingDataPath + "es/locucions.dat",
                freelingDataPath + "es/np.dat",
                freelingDataPath + "es/quantities.dat",
                freelingDataPath + "es/probabilitats.dat");

        tokenizer = new edu.upc.freeling.Tokenizer( freelingDataPath + "es/tokenizer.dat" );
        neclass = new Nec( freelingDataPath + "es/nerc/nec/nec-ab-poor1.dat" );

        mf = new Maco( op );
        mf.setActiveOptions(false, true, true, true,  // select which among created
                true, true, false, true,  // submodules are to be used.
                true, true, true, true);  // default: all created submodules
        // are used

        tg = new HmmTagger( freelingDataPath + "es/tagger.dat", true, 2 );
        ChartParser parser = new ChartParser(
                freelingDataPath + "es/chunker/grammar-chunk.dat" );
        DepTxala  dep = new DepTxala( freelingDataPath + "es/dep_txala/dependences.dat",
                parser.getStartSymbol() );
        neclass = new Nec( freelingDataPath + "es/nerc/nec/nec-ab-poor1.dat" );

        sen = new Senses(freelingDataPath + "es/senses.dat" ); // sense dictionary
        dis = new Ukb( freelingDataPath + "es/ukb.dat" ); // sense disambiguator
    }

    /**
     * Method to get a list with the NER Elements detected.
     * @return List with the NER Elements.
     */
    public List<AnalysisElement> analyzeText(String text) {
        List<AnalysisElement> analysisElements = new ArrayList<>();
        Splitter sp = new Splitter( freelingDataPath + "es/splitter.dat" );
        SWIGTYPE_p_splitter_status sid = sp.openSession();
        ListWord words = tokenizer.tokenize(text);
        ListSentence ls = sp.split( sid, words, false );

        mf.analyze(ls);
        tg.analyze(ls);
        neclass.analyze(ls);
        sen.analyze(ls);
        dis.analyze(ls);

        ListSentenceIterator sIt = new ListSentenceIterator(ls);
        while (sIt.hasNext()) {
            Sentence s = sIt.next();
            ListWordIterator wIt = new ListWordIterator(s);
            while (wIt.hasNext()) {
                Word w = wIt.next();
                AnalysisElement analysisElement = wordToAnalysisElement(w);
                analysisElements.add(analysisElement);
            }
        }

        sp.closeSession(sid);

        return analysisElements;
    }

    /**
     * Private method to get an Analysis Element from a Word.
     * @param word Word Element
     * @return AnalysisElement
     */
    private AnalysisElement wordToAnalysisElement(Word word) {
        return new AnalysisElement(word.getForm(), word.getLemma(),
                word.getTag(), word.getSensesString(), word.isMultiword());
    }
}
