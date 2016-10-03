/*
 * MusicArchive.java
 * ICS 45J : Lab Assignment 5
 * 
 * Completed by: Thomas Liu & Ce Hui Li 
 * UCInetiD:     thomal3 & cel
 * ID:           75716746 & 75935876
 * Modified:     June 5, 2015
 * 
 */

// MusicArchive.java
// 
// ICS 45J : Lab Assignment 5
// 
// Writen by Norman Jacobson for ICS45J< 2012, August, 2012;
//   based on code from ICS 21, Summer 2009 version
// 
// Make a manager and turn it loose!

public class MusicArchive
{
	
	public static void main(String[] args)
	{
		MusicManager musicManager = new MusicManager();
		musicManager.makeIndexAndDisplayCounts();
	}
	
}

