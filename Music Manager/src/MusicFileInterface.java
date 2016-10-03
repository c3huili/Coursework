// MusicFileInterface.java
// 
// ICS 45J : Lab Assignment 5
// 
// Coded by Norman Jacobson, August 2012
// Updated to reflect simplified disk vs web file
//   logic, by Norman Jacobson, December 2012
// 
// Handle opening, closing, reading of the music file.
// 
// There are two locations the file can be, web and disk.
// The only differences between them is opening the stream
// -- the methods used are different -- but we then
// wrap that stream in a Scanner, hiding any differences.
// From there on, the file is handled the same way -- via the
// Scanner -- and its location no longer matters.
// 
// Note it does not specify that file's location, so the
// implementing class is free to place the file where needed

import java.io.IOException;
import java.util.ArrayList;

public interface MusicFileInterface
{
	
	// Contains a scanner
	
	// openDiskFile opens the file when it's located on the local disk
	// openWebFile opens the file when it's located on the web site
	// Throws IOException if music file cannot be opened or other IO problems occur
	
	public void openDiskFile(String musicFileName) throws IOException;
	public void openWebFile(String musicFileName) throws IOException;
	
	// Are there more items in the file?
	// true = yes (not at end of file); false otherwise
	
	public boolean hasMoreItems();
	
	// Read and return one music item, as as ArrayList<String>
	// Number of attributes will varying depending on type of item read
	// -- but first three are always the same: accession number, title, media code
	// Hint: Read in a line, then use string tokenizer split() method to break
	// it into fields, with ";" (and end of string) as the delimiter
	
	public ArrayList<String> readItem();
	
	// Close the file
	
	public void close();
	
}

