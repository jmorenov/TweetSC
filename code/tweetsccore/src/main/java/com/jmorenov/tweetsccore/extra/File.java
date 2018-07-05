package com.jmorenov.tweetsccore.extra;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        InputStream stream = File.class.getClassLoader().getResourceAsStream(fileName);

        return IOUtils.toByteArray(stream);
    }

    /**
     * Method to read a file stream from resources.
     * @param fileName the name of the file.
     * @return InputStream of the file.
     * @throws IOException when the file is not found.
     */
    public static InputStream getStreamFromResources(String fileName) throws IOException {
        return File.class.getClassLoader().getResourceAsStream(fileName);
    }

    /**
     * Method ro read a dictionary from resources.
     * @param fileName String
     * @return List of String
     * @throws IOException When the file is not found
     */
    public static List<String> readDictionaryFromResources(String fileName) throws IOException {
        byte[] bytesOfDictionaryFile = File.readToByte(fileName);
        List<String> data = new ArrayList<>();

        Stream.of(new String(bytesOfDictionaryFile).split("\n")).forEach( (word) ->{
            data.add(word);
        });

        return data;
    }

    /**
     * Method to create a temp file from a resource.
     * @param resourcePath String
     * @return File
     */
    public static java.io.File createResourceTempFile(String resourcePath, String extension) {
        try {
            InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath);
            if (in == null) {
                return null;
            }

            java.io.File tempFile = java.io.File.createTempFile(String.valueOf(in.hashCode()), "." + extension);
            tempFile.deleteOnExit();

            try (java.io.FileOutputStream out = new java.io.FileOutputStream(tempFile)) {
                //copy stream
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
            return tempFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getModelsPath() {
        String modelsPath = "";

        try {
            String actualPath = new java.io.File(File.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI()).getPath();

            Pattern r = Pattern.compile("/TweetSC/code");
            Matcher m = r.matcher(actualPath);

            if (m.find( )) {
                modelsPath = actualPath.substring(0, m.end()) + "/models/";
            }

        } catch (URISyntaxException ex) { }

        return modelsPath;
    }
}