package com.jmorenov.tweetsccore.extra;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * File class with funcionality to files.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class File {
    /**
     * Method to read a file to array of string.
     * @param fileName String with the name of the file.
     * @return String[] with the lines.
     * @throws IOException when the file is not found.
     */
    public static String[] readToStringArray(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        Path filePath = Paths.get(fileName);
        Stream<String> fileLines = Files.lines(filePath);

        fileLines.forEach((line)-> lines.add(line));

        return lines.toArray(new String[0]);
    }

    /**
     * Method to read a file to byte from resources.
     * @param fileName the name of the file.
     * @return byte[] of the file.
     * @throws IOException when the file is not found.
     */
    public static byte[] readToByte(String fileName) throws IOException {
        InputStream streamOfDictionaryFile = File.class.getClassLoader().getResourceAsStream(fileName);

        return IOUtils.toByteArray(streamOfDictionaryFile);
    }
}