// SmileyAnimationInterface.java
// 
// ICS 45J : Lab Assignment 4
// 
// Written for ICS45J Fall 2012, by Norman Jacobson, August 2012.

import java.util.ArrayList;

// A SmileyAnimation represents an animation in which a collection of
// smiley faces that race one another, each completing a set number of laps.
public interface RacingAnimationInterface
{
	
	// Your program should work for anywhere from one up to tens of smileys.
	// Be sure to size the smileys so that they all fit on the screen and do
	// not overlap each other. You can pick a number to use in your program,
	// but when grading your program, I may well change your code to use
	// different numbers between 1 and 30, and your program should still meet
	// these requirements.
	// 
	// All smiley faces must have the mouth set to the background color of
	// the SmileyDisplay and should initially be constructed so that they're
	// facing to the right (i.e., their mouth is on the right-hand side of
	// the face so it looks like an open mouth, with only the right eye visible).
	
	
	
	// RacingAnimation(RacingGroup g, RacingDisplay d)
	
	// Implement this constructor in your class that implements this interface
	// (changing its name to match the name of your class, if necessary). It
	// creates an animation that races the smileys in the given group, showing
	// the animation of the race on the given display.
	
	
	
	// animate() is called once, from RacerFrame, to show the running race when
	// the GO! button is pressed; your code should not call it.
	// 
	// For each tick until all theRacers have completed all of their laps, animate()
	// moves the theRacers forward the distance they should go in that tick,
	// based on their current speeds. In more detail, animate() follows this logic:
	// 
	//     Until all theRacers have finished the race...
	//     Each time through the loop is one 'tick' of the race clock
	//     {   For each racer in the list of theRacers...
	//            If the racer has not yet finished the race...
	//                Move the racer forward one clock tick
	//         Repaint the screen to show the movement made this tick
	//         Pause to slow the animation to a visible speed
	//     }
	//     Race done!  Compute the statistics
	
	void animate();
	
	
	
	// Accessors -- used by the graphics routine to obtain
	// the information that it needs to display
	
	// getRacers() returns all the theRacers (with their information).
	public ArrayList<RacingSmiley> getRacers();
	
	// getStatisticsTitle() returns the title that should be shown in the
	// statistics area of the window.
	public String getStatisticsTitle();
	
	// getAverageTicks() returns the average time, in ticks, that each
	// smiley spent completing the race.
	public double getAverageTicks();
	
	// getFewestTicks() returns the number of ticks spent by the fastest
	// smiley in completing the race.
	public int getFewestTicks();
	
	// getMostTicks() returns the number of ticks spent by the slowest
	// smiley in completing the race.
	public int getMostTicks();
	
	// getFastestSmileyName() returns the name of the fastest smiley.
	public String getFastestSmileyName();
	
	// getSlowestSmileyName() returns the name of the slowest smiley.
	public String getSlowestSmileyName();
	
}
