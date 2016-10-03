/*
 * Bucket.java
 * ICS 45J : Lab Assignment 5
 * 
 * Completed by: Thomas Liu & Ce Hui Li 
 * UCInetiD:     thomal3 & cel
 * ID:           75716746 & 75935876
 * Modified:     June 5, 2015
 * 
 */

import java.util.LinkedList;
import java.util.ListIterator;

// A bucket contains an alphabetical list, by title, of music items.

public class Bucket implements BucketInterface{
	private LinkedList<MusicItem> items;
	
	
	// A bucket is a list of music items
	public Bucket() {
		items = new LinkedList<MusicItem>();
	}
	
	// Add the music item into the bucket,
	// in alphabetical order by title
	public void addItem(MusicItem itemToAdd){
		ListIterator<MusicItem> musicItemIterator = items.listIterator();
		if(items.isEmpty()) {
			items.add(itemToAdd);
		}
		else if(!musicItemIterator.hasNext()) {
			if(itemToAdd.getTitle().compareTo(items.getFirst().getTitle()) <= 0){
				items.addFirst(itemToAdd);
			}
			else{
				items.add(itemToAdd);
			}
		}
		else{
			while(musicItemIterator.hasNext()){
				String nextTitle = musicItemIterator.next().getTitle();
				if(itemToAdd.getTitle().compareTo(nextTitle) <= 0){
					musicItemIterator.previous();
					musicItemIterator.add(itemToAdd);
					break;
				}
			}
			if(!musicItemIterator.hasNext()) {
				items.add(itemToAdd);
			}
		}
	}
	
	// Accessor -- get the items in the list
	public LinkedList<MusicItem> getItems(){
		return items;
	}
}
