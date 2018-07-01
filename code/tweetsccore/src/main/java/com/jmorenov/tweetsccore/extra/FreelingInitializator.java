package com.jmorenov.tweetsccore.extra;

import edu.upc.freeling.Util;

import java.nio.file.Paths;

/**
 * FreelingTokenizer class to initialize Freeling.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class FreelingInitializator {
    /**
     * Constructor of the class
     */
    public static String init() {
        String freeling_javaAPIPath = FreelingInitializator.class.getClassLoader().getResource("freeling_javaAPI.so").getPath();
        String libfreelingPath = FreelingInitializator.class.getClassLoader().getResource("freeling_javaAPI.so").getPath();

        System.load(freeling_javaAPIPath);
        System.load(libfreelingPath);

        String freelingDataPath =  Paths.get("src","main","resources", "freeling").toAbsolutePath() + "/";
        Util.initLocale( "default" );

        return freelingDataPath;
    }
}
