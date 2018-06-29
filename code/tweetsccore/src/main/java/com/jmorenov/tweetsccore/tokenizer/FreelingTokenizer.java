package com.jmorenov.tweetsccore.tokenizer;

import edu.upc.freeling.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * OpenNLPTokenizer class to tokenize a text.
 * https://opennlp.apache.org/docs/1.8.4/manual/opennlp.html
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class FreelingTokenizer extends Tokenizer {
    /**
     * Constructor of the class
     */
    public FreelingTokenizer() {
        String freeling_javaAPIPath = FreelingTokenizer.class.getClassLoader().getResource("freeling_javaAPI.so").getPath();
        String libfreelingPath = FreelingTokenizer.class.getClassLoader().getResource("freeling_javaAPI.so").getPath();

        System.load(freeling_javaAPIPath);
        System.load(libfreelingPath);

        String freelingDataPath =  Paths.get("src","main","resources", "freeling").toAbsolutePath() + "/";

        Util.initLocale( "default" );
        String LANG = "es";
        MacoOptions op = new MacoOptions( LANG );

        op.setDataFiles( "",
                freelingDataPath + "common/punct.dat",
                freelingDataPath + LANG + "/dicc.src",
                freelingDataPath + LANG + "/afixos.dat",
                "",
                freelingDataPath + LANG + "/locucions.dat",
                freelingDataPath + LANG + "/np.dat",
                freelingDataPath + LANG + "/quantities.dat",
                freelingDataPath + LANG + "/probabilitats.dat");

        LangIdent lgid = new LangIdent(freelingDataPath + "common/lang_ident/ident-few.dat");

        edu.upc.freeling.Tokenizer tk = new edu.upc.freeling.Tokenizer( freelingDataPath + LANG + "/tokenizer.dat" );
        Splitter sp = new Splitter( freelingDataPath + LANG + "/splitter.dat" );
        SWIGTYPE_p_splitter_status sid = sp.openSession();

        Maco mf = new Maco( op );
        mf.setActiveOptions(false, true, true, true,  // select which among created
                true, true, false, true,  // submodules are to be used.
                true, true, true, true);  // default: all created submodules
        // are used

        HmmTagger tg = new HmmTagger( freelingDataPath + LANG + "/tagger.dat", true, 2 );
        ChartParser parser = new ChartParser(
                freelingDataPath + LANG + "/chunker/grammar-chunk.dat" );
        DepTxala dep = new DepTxala( freelingDataPath + LANG + "/dep_txala/dependences.dat",
                parser.getStartSymbol() );
        Nec neclass = new Nec( freelingDataPath + LANG + "/nerc/nec/nec-ab-poor1.dat" );

        Senses sen = new Senses(freelingDataPath + LANG + "/senses.dat" ); // sense dictionary
        Ukb dis = new Ukb( freelingDataPath + LANG + "/ukb.dat" ); // sense disambiguator
    }

    /**
     * Method to get the tokens from the text.
     * @param text String with the text
     * @return List of String with the tokens
     */
    @Override
    public List<String> getTokens(String text) {
        return new ArrayList<>();
    }
}