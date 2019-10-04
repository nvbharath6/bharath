package com.vowelfinderapp.model;

import java.util.Set;

/**
 * @author Bharath
 *
 */
public class Vowel {
  
    Set<Character> uniqueSetOfVowels;
    int countOfVowels;
    int lengthOfWord;
    double avgVowelCount;
    public Vowel() {
        super();
    }

    public void setLengthOfWord(int lengthOfWord) {
        this.lengthOfWord = lengthOfWord;
    }

    public int getLengthOfWord() {
        return lengthOfWord;
    }

    public void setAvgVowelCount(double avgVowelCount) {
        this.avgVowelCount = avgVowelCount;
    }

    public double getAvgVowelCount() {
        return avgVowelCount;
    }

    public void setUniqueSetOfVowels(Set<Character> uniqueSetOfVowels) {
        this.uniqueSetOfVowels = uniqueSetOfVowels;
    }

    public Set<Character> getUniqueSetOfVowels() {
        return uniqueSetOfVowels;
    }

    public void setCountOfVowels(int countOfVowels) {
        this.countOfVowels = countOfVowels;
    }

    public int getCountOfVowels() {
        return countOfVowels;
    }
    
    public String groupingByVowelsAndLengthOfWord()
    {
        
        return uniqueSetOfVowels.toString().replace("[", "{").replace("]", "}")+","+lengthOfWord;
    }
}
