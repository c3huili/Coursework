/*
 * IndexFile.java
 * ICS 45J : Lab Assignment 5
 * 
 * Completed by: Thomas Liu & Ce Hui Li 
 * UCInetiD:     thomal3 & cel
 * ID:           75716746 & 75935876
 * Modified:     June 5, 2015
 * 
 */

import java.io.IOException;
import java.io.PrintWriter;

//Sets up requirements for open, write, and close routines for
//the index file. Note it does not specify that file's location,
//so the implementing class is free to place the file where needed

public class IndexFile implements IndexFileInterface{
	private static final String TITLE_BAR = "TITLE                                             ACCESSION # MEDIA  ADDITIONAL INFORMATION";
	private static final String LINE = "--------------------------------";
	private static PrintWriter out;
	
	// Constructor
	public IndexFile() {
		
	}

	// Open index file with name indexFileName for sequential writing
	// Opens a sequential text file to accept output; prints out report headings
	// Throws IOException if index file cannot be opened or other IO problems occur
	public void open(String indexFileName) throws IOException{	
		out = new PrintWriter(indexFileName, "UTF-8");
		out.println(TITLE_BAR);
		out.println(LINE);

	}
	
	// Writes out the current line of the report, contained in itemToWrite		
	public void writeItem(MusicItem itemToWrite){
		out.printf("%-50s%-10s  %-7s%s%n",itemToWrite.getTitle(), itemToWrite.getAccessionNumber(), itemToWrite.getMedia(), itemToWrite.displaySupplementalInfo());
	}
	
	// closes the index file		
	public void close(){
		out.close();
	}
}
