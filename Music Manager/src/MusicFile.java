/*
 * MusicFile.java
 * ICS 45J : Lab Assignment 5
 * 
 * Completed by: Thomas Liu & Ce Hui Li 
 * UCInetiD:     thomal3 & cel
 * ID:           75716746 & 75935876
 * Modified:     June 5, 2015
 * 
 */

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


// Handle opening, closing, reading of the music file.
// The music file may be located on the local disk or 
// web. The class merely handles opening and reading 
// from given input.

public class MusicFile implements MusicFileInterface{
	// Contains a scanner
	private static Scanner fileScanner;
	
	// Constructor
	public MusicFile() {
		
	}
	
	// openDiskFile opens the file when it's located on the local disk
	public void openDiskFile(String musicFileName) throws IOException {
		File file = new File(musicFileName);
		fileScanner =  new Scanner(file);
		
	}
	
	// openWebFile opens the file when it's located on the web site
	public void openWebFile(String musicFileName) throws IOException {
		URL url = new URL(musicFileName);
		fileScanner = new Scanner(url.openStream());
	}
	
	// function that returns true if there are more items that need processing
	public boolean hasMoreItems() {
		return fileScanner.hasNextLine();
	}
	
	// function to read each item (line of text) and breaks it apart into its components
	// and adds it an ArrayList
	public ArrayList<String> readItem() {	
		// Parse line into array list
		ArrayList<String> musicItemInfo = new ArrayList<String>(); 
		String line = fileScanner.nextLine();
		Scanner lineScanner = new Scanner(line);
		// uses a user define delimiter to split apart the items
		lineScanner.useDelimiter("; ");
		while(lineScanner.hasNext()) {	
			musicItemInfo.add(lineScanner.next());
		}
		lineScanner.close();
		
		return musicItemInfo;
	}

	// Close the file	
	public void close() {
		fileScanner.close();
	}
	
}
