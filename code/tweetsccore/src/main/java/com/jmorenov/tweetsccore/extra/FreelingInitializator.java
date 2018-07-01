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
        String freelingDataPath =  Paths.get("src","main","resources", "freeling").toAbsolutePath() + "/";
        String libfreelingPath = freelingDataPath + "libfreeling.so";
        String freeling_javaAPIPath = freelingDataPath + "libfreeling_javaAPI.so";
        //String libfreelingPath = freelingDataPath + "libfreeling.jnilib";
        //String freeling_javaAPIPath = freelingDataPath + "libfreeling_javaAPI.jnilib";

        System.load(libfreelingPath);
        System.load(freeling_javaAPIPath);

        Util.initLocale( "default" );

        return freelingDataPath;
    }
}
