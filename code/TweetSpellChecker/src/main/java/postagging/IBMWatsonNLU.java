package postagging;

import java.util.ArrayList;

public class IBMWatsonNLU extends POSTagging {
    public IBMWatsonNLU(String text) {

    }

    public ArrayList<String> getTokens() {
        ArrayList<String> tokens = new ArrayList<String>();

        tokens.add("TEST");

        return tokens;
    }
}
