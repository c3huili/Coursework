// RacingGroupInterface.java
// 
// ICS 45J : Lab Assignment 4
// 
// Written for ICS45 J Fall 2012, by Norman Jacobson, August 2012.
// Revised to more clearly state constructor requirements,
//   by Norman Jacobson, November, 2012.

import java.util.ArrayList;

// The group of smileys that are in the race
interface RacingGroupInterface
{
	
	// public RacingGroup(Color background)
	
	// The constructor builds an ArrayList of racers
	// all racing smileys, each with the same shape and
	// color, with mouths the same color as the background
	// of the screen (the passed-in parameter), moving to
	// the right to start, but each with a different name
	// and name color
	
	// Access the group
	
	public ArrayList<RacingSmiley> getRacers();
	
}
