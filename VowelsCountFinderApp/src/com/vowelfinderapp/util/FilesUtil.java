package com.vowelfinderapp.util;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.util.List;

/**
 * @author Bharath
 *  
 * Utility class to read and write the files
 */
public class FilesUtil {

    /**
     * readTextFileByLines is written to read the content from the input file and return list of lines
     * @param fileName
     * @return list of lines as string
     * @throws IOException
     */
    public static List<String> readTextFileByLines(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        return lines;
    }

    /**
     * writeToTextFile is written to write the content into the output file 
     * @param fileName
     * @param content
     * @throws IOException
     */
    public static void writeToTextFile(String fileName, List content) throws IOException {
        Files.write(Paths.get(fileName), content, StandardOpenOption.CREATE);
    }
}