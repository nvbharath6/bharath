package com.vowelfinderapp.main;

import com.vowelfinderapp.model.Vowel;

import com.vowelfinderapp.util.FilesUtil;
import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author Bharath
 *
 * Main class to find the average, count of vowels for each word
 */
public class VowelCountMain {
	
	public final static String FLE_EXTN = ".txt";
    public VowelCountMain() {
        super();
    }

    public static void main(String[] args) {
        VowelCountMain finder = new VowelCountMain();
            
        Scanner sc = new Scanner(System.in); 
        System.out.println("Enter a filepath to read and write files:");   
        String filePath = sc.nextLine();
        System.out.println("Enter input file name:");           
        String inputFile = filePath +sc.nextLine();
        System.out.println("Enter output file name:");
        String outputFile = filePath +sc.nextLine();

        try {
            //writeToTextFile is used to write the content into the output file 
            List<String> lines = FilesUtil.readTextFileByLines(inputFile);
            
            //process the line and calculate vowel count and average for each word            
            List<String> finalOutput = finder.calculateAvgNumberOfVowels(lines);
            
            
            //writeToTextFile is used to write the content into the output file 
            FilesUtil.writeToTextFile(outputFile, finalOutput);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * This method process each line and calculates count, average number of vowels for each word
     * @param line
     * @param listOfVowels
     */
    public List<String> calculateAvgNumberOfVowels(List<String> lines) {
    	List<Vowel> listOfVowels = new ArrayList<Vowel>();                                    
        for(String line: lines){
	        String words[] = line.split("\\s+");
	        for (String word : words) {
	        	
	            //Regexp to remove the non letter characters from the word like punctuation etc
	            word = word.replaceAll("[^a-zA-Z ]", "");
	            
	            int wordLength = word.length();
	            Set<Character> uniqueSetOfVowels = new TreeSet<Character>();
	            int vowelCount = 0;
	            char ch[] = word.toCharArray();
	            for (char c : ch) {
	                if (isVowel(c)) {
	                    uniqueSetOfVowels.add(c);
	                    vowelCount++;
	                }
	            }
	
	            Vowel vw = new Vowel();
	            vw.setUniqueSetOfVowels(uniqueSetOfVowels);
	            vw.setLengthOfWord(wordLength);
	            vw.setCountOfVowels(vowelCount);
	            vw.setAvgVowelCount(wordLength / vowelCount);
	            listOfVowels.add(vw);
	        }   
        }
        
         Map<String,List<Double>> output =  listOfVowels.stream()
                .collect(Collectors.groupingBy(Vowel::groupingByVowelsAndLengthOfWord,
                                               Collectors.mapping(Vowel::getAvgVowelCount, Collectors.toList())));
         return output.entrySet().stream().map( e -> "("+e.getKey()+")"+"->"+e.getValue()).collect(Collectors.toList());
    }

    /**
     * Function to check the Vowel
     * @param ch
     * @return true or false
     */
    private boolean isVowel(char ch) {
        ch = Character.toUpperCase(ch);
        if (ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U')
            return true;
        else
            return false;
    }
}
