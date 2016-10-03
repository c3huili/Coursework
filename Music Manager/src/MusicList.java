/*
 * MusicList.java
 * ICS 45J : Lab Assignment 5
 * 
 * Completed by: Thomas Liu & Ce Hui Li 
 * UCInetiD:     thomal3 & cel
 * ID:           75716746 & 75935876
 * Modified:     June 5, 2015
 * 
 */

import java.util.ArrayList;

//The list of music items (an ArrayList of 26 cells, representing
//the letters A to Z, with each cell a Bucket that contains a list
//of MusicItems whose title all start with the letter the cell
//represents, and in sorted order by title.

public class MusicList implements MusicListInterface{
	private static final int numOfBuckets = 26;
	private static ArrayList<Bucket> buckets;
	
	// Make a bucket for each of the 26 list locations;
	public MusicList() {
		buckets = new ArrayList<Bucket>();
		for(int i = 0; i < numOfBuckets; i++) {
			buckets.add(new Bucket());
		}
	}
	
	// Add an item into the correct bucket in the list
	// Bucket to use is one corresponding to first letter in title
	public void addItem(MusicItem item){
		char firstLetter = getFirstLetter(item);
		int bucketPos = letterToNumberIndex(firstLetter);
		buckets.get(bucketPos).addItem(item);
	}
	
	// Accessors
	// returns the arraylist of buckets
	public ArrayList<Bucket> getBuckets(){
		return buckets;
	}
	
	// returns the index at of a given character reducing it from its ascii representation
	private int letterToNumberIndex(char firstLetter) {
		return (int) firstLetter - 65;
	}
	
	// returns the upper case of the first letter of the title of the music item
	private char getFirstLetter(MusicItem item) {
		return Character.toUpperCase(item.getTitle().charAt(0));
	}

}

