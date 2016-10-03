/*
 * MusicItem.java
 * ICS 45J : Lab Assignment 5
 * 
 * Completed by: Thomas Liu & Ce Hui Li 
 * UCInetiD:     thomal3 & cel
 * ID:           75716746 & 75935876
 * Modified:     June 5, 2015
 * 
 */

import java.util.ArrayList;

// MusicItem contains the fields common to all music items:
// 
//	 - accession number
//   - title
//   - media code
// 
// The class is used as the basis of all music item types, but
// since there is no non-specific music item, the class is abstract

public class MusicItem implements MusicItemInterface{
	// Where the fields are located in input array
	public static final int ACCESSION_NUMBER_POSITION = 0;
	public static final int TITLE_POSITION = 1;
	public static final int MEDIA_CODE_POSITION = 2;
	private static String displayString = "";	
	private ArrayList<String> item;
	
	// Constructs a music item from item :
	// 
	// position 0: accession number
	// position 1: title
	// position 2: media code
	// positions 3 and beyond : additional information;
	//	depending on the media code addition information may vary
	public MusicItem(ArrayList<String> item) {
		this.item = item;
	}
	
	
	// Force each media type to provide its supplemental
	// information in a form suitable for printing	
	public String displaySupplementalInfo(){
		switch(getMedia())
		{
			case "C": 
				displayString = "# of tracks: " + item.get(3) + " | Year released " + item.get(4);
				break;
			case "P":
				displayString = "# of pages: " + item.get(3);
				break;
			case "V":
				displayString = "Label: " + item.get(3)  + " | RPM: " + item.get(4);
				break;
			case "W":
				displayString = "Maker: " + item.get(3);
				break;
		}
		return displayString;
	}
	
	// Accessors
	// returns the Accession Number in String form
	public String getAccessionNumber() {
		return item.get(ACCESSION_NUMBER_POSITION);
	}
	
	// returns the Title in String form
	public String getTitle() {
		return item.get(TITLE_POSITION);
	}
	
	// returns the Media Code in String form
	public String getMedia() {
		return item.get(MEDIA_CODE_POSITION);
	}
}
