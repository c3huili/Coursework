// RacingSmileyInterface.java
// 
// ICS 45J : Lab Assignment 4
// 
// Written for ICS45J Fall 2012, by Norman Jacobson, August 2012.
// Revised to more clearly state constructor requirements,
//   by Norman Jacobson, November 2012.

import java.awt.Color;

// A SmileyRacer is a specialized animated smiley that is capable of
// participating in a smiley race and tracking its own progress.

public interface RacingSmileyInterface
{
	
	// 	public RacingSmiley(AnimatedSmiley existingFace, String name, Color nameColor)
	
	// The provided graphics routines expect to have available this
	// copy constructor; it initializes a RacingSmiley
	// from an existing AnimatedSmiley, a given name and name color
	// (Because a racing smiley is also an animated smiley, you can
	// also use an existing RacingSmiley; the result is a new racing
	// smiley that is the same except for a new name and name color)
	
	// finishedRace() returns true if the SmileyRacer
	// has finished the race, false if not.
	
	public boolean finishedRace();
	
	// raceForOneTick() moves the racer forward the distance that
	// it moves for one tick.  Also, it increases the number of ticks
	// that the racer has been in the race.
	
	public void raceForOneTick();
	
	// Accessors: return, respectively,
	// the number of ticks raced so far,
	// the racer's name
	// the racer name's color
	// the number of laps so far completed
	
	public int getTicks();
	
	public String getSmileyName();
	
	public Color getSmileyNameColor();
	
	public int getLapsCompleted();
	
}
