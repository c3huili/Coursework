// MusicListInterface.java
// 
// ICS 45J : Lab Assignment 5
// 
// Coded by Norman Jacobson, August 2012
// 
// The list of music items (an ArrayList of 26 cells, representing
// the letters A to Z, with each cell a Bucket that contains a list
// of MusicItems whose title all start with the letter the cell
// represents, and in sorted order by title.

import java.util.ArrayList;

interface MusicListInterface
{
	
	// Make a bucket for each of the 26 list locations;
	// public MusicList()
	
	// Add an item into the correct bucket in the list
	// Bucket to use is one corresponding to first letter in title
	
	public void addItem(MusicItem item);
	
	// Accessors
	
	public ArrayList<Bucket> getBuckets();
	
}

