package com.jmorenov.tweetsccore.extra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * File class with funcionality to files.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class File {
    /**
     * Method to read a file.
     * @param filename String with the name of the file.
     * @return String[] with the lines.
     * @throws IOException when the file is not found.
     */
    public static String[] read(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;

        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();

        return lines.toArray(new String[lines.size()]);
    }
}