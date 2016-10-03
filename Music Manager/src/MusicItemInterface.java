// MusicItemInterface.java
// 
// ICS 45J : Lab Assignment 5
// 
// Coded by Norman Jacobson, August 2012
// Some comments added by Norman Jacobson, December 2012
// 
// MusicItem contains the fields common to all music items:
// 
//	 - accession number
//   - title
//   - media code
// 
// The class is used as the basis of all music item types, but
// since there is no non-specific music item, the class is abstract

abstract interface MusicItemInterface
{
	
	// Where the fields are located in input array
	public static final int ACCESSION_NUMBER_POSITION = 0;
	public static final int TITLE_POSITION = 1;
	public static final int MEDIA_CODE_POSITION = 2;
	
	
	// Construct a music item from item :
	// 
	// position 0: accession number
	// position 1: title
	// position 2: media code
	// positions 3 and beyond : additional information;
	//     it's dependent upon media code so is not specified here;
	//     see requirements for details
	
	// public MusicItem(ArrayList<String> item)
	
	
	// Force each media type to provide its supplemental
	// information in a form suitable for printing
	
	public abstract String displaySupplementalInfo();
	
	// Accessors
	
	public String getAccessionNumber();
	public String getTitle();
	public String getMedia();
	
}

