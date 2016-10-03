/*
 * MusicManager.java
 * ICS 45J : Lab Assignment 5
 * 
 * Completed by: Thomas Liu & Ce Hui Li 
 * UCInetiD:     thomal3 & cel
 * ID:           75716746 & 75935876
 * Modified:     June 5, 2015
 * 
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
// Manage the making of the index and category counts
public class MusicManager implements MusicManagerInterface{
	private static final String WEB_LINK = "http://www.christophemagnan.com/ICS45J/ProvidedSourceCode/music.txt";
	private static final String DISK_LINK = "music.txt";
	private static final String INDEX_FILE_NAME = "index.txt";
	private static Scanner scanner;
	private IndexFile indexFile;
	private MusicFile musicFile;
	private MusicList musicList;
	private String input;
	private static int compactMediaCount;
	private static int paperCount;
	private static int vinylRecordCount;
	private static int waxCylinderCount;
	private static int mediaCount;
		
	// constructor that creates instances of music file and index file and initializes counters
	public MusicManager(){
		indexFile = new IndexFile();
		musicFile = new MusicFile();
		compactMediaCount = 0;
		paperCount = 0;
		vinylRecordCount = 0;
		waxCylinderCount = 0;
		mediaCount = 0;
	}
	
	// Constructs the music list
	// Prepares an index file from an incoming list of music items;
	// displays to the screen the media category counts
	// and a total count of items processed.
	public void makeIndexAndDisplayCounts(){
		// Ask user if music file is on disk or on the Web
		scanner = new Scanner(System.in);
		do{
			System.out.println("Would you like to locate music file on disk or web?");
			System.out.print("Please enter DISK, WEB, or QUIT: ");
			input = scanner.nextLine().toUpperCase();
			
			if(input.equals("DISK") ||  input.equals("WEB") || input.equals("QUIT")){
				break;
			}
			else{
				System.out.println("Invalid Input");
			}
			
		} while(true);
		// depending on input, it will parse the text file or the web link
		if(input.equals("DISK")) {
			try {
				musicFile.openDiskFile(DISK_LINK);
			} catch(IOException e) {
				System.out.println(e.getMessage());
			}
		}
		else if(input.equals("WEB")) {
			try {
				musicFile.openWebFile(WEB_LINK);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		constructMusicList();
		getMediaCount();
		openIndexFile();
		
		System.out.println("Program finished");
	}
	
	// function to display the media counts and total counts
	private void getMediaCount()
	{
		System.out.println("Compact Media: " + compactMediaCount);
		System.out.println("Paper Count: " + paperCount);
		System.out.println("Vinyl Record Count: " + vinylRecordCount);
		System.out.println("Wax Cylinder Count: " + waxCylinderCount);
		System.out.println("Total Count: " + mediaCount);
	}
	
	/* 
	Construct an (empty) MusicList
	For each line of the music file,
	      use it to build a MusicItem object
	      find the Bucket in the MusicList at which the MusicItem is to be inserted
	      place it into that Bucket, in alphabetical order by title
	      increment the media count for the media type of this MusicItem
	Close the music file
	*/
	private void constructMusicList() {
		musicList = new MusicList();
		while(musicFile.hasMoreItems()){
			ArrayList<String> musicInfo = new ArrayList<String>();
			musicInfo = musicFile.readItem();
			MusicItem musicItem = new MusicItem(musicInfo);
			musicList.addItem(musicItem);
			countMedia(musicItem.getMedia());
			// increment media count
		}
		musicFile.close();
	}
	
	// function that parses the media codes and increments the corresponding coutns
	private void countMedia(String mediaCode)
	{
		switch(mediaCode)
		{
			case "C":
				compactMediaCount++;
				break;
			case "P":
				paperCount++;
				break;
			case "V":
				vinylRecordCount++;
				break;
			case "W":
				waxCylinderCount++;
				break;
		}
		mediaCount++;
	}
	
	/*
	Open the index file for sequential output; if any problems, exit with a message to the user
    For each Bucket in the MusicList
        For each MusicItem in the Bucket
            write the MusicItem out (nicely formatted) to the index file
    Close the index file.
    Print a message telling the user the index' file's name
    Print out the media counts, labeled so the user knows what's what
    Compute and print the count of all the items, also appropriately labeled 
	*/	
	private void openIndexFile() {
		try {
			indexFile.open(INDEX_FILE_NAME);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		// start with for each bucket here
		ArrayList<Bucket> buckets = musicList.getBuckets();
		for(Bucket b : buckets) {
			LinkedList<MusicItem> musicItemList = b.getItems();
			for(MusicItem musicItem : musicItemList){
				indexFile.writeItem(musicItem);
			}
		}
		indexFile.close();
	}
}