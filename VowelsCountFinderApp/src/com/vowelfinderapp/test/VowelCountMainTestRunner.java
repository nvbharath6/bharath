/**
 * 
 */
package com.vowelfinderapp.test;

import org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import com.vowelfinderapp.main.VowelCountMain;
import com.vowelfinderapp.util.FilesUtil;

/**
 * @author Bharath
 *
 * test class to check the vowel count finder application
 */
public class VowelCountMainTestRunner {

	   @Rule
	   public TemporaryFolder tempFolder = new TemporaryFolder();
	   
	   @Test
	   public void testWrite() throws IOException {
	     // Create a temporary file.
	     final File tempFile = tempFolder.newFile("tempFile.txt");
	   
	     // Write something to it.
	     FilesUtil.writeToTextFile(tempFile.getAbsolutePath(), Arrays.asList("Platon made bamboo boats."));
	   
	     // Read it from temp file
	     List<String> lines = FilesUtil.readTextFileByLines(tempFile.getAbsolutePath());
	   
	     // Verify the content
	     VowelCountMain finder = new VowelCountMain();
	     List<String> finalOutput = finder.calculateAvgNumberOfVowels(lines);
	     
	     //If List has this value?
	     assertThat(finalOutput, hasItems("({a, o},5)->[2.0]"));
         //Check List Size
         assertThat(finalOutput.size(), is(3));	        
	      
	     //Note: File is guaranteed to be deleted after the test finishes.
	   }
}
