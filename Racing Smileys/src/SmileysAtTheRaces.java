import java.awt.Color;

// SmileysAtTheRaces.java
// 
// ICS 45J : Lab Assignment 4
// 
// Originally coded by Norm Jacobson, September 2006
// Minor modifications introduced by Alex Thornton, June 2009
// Modified for ICS21 Fall 2009 by Norman Jacobson, September 2009

public class SmileysAtTheRaces
{
	
	public static void main(String[] args)
	{
		// Just build the frame; it controls all else
		RacingFrame f = new RacingFrame("Smileys at the Races");
		
		Color BackgroundColor = Color.BLACK;
		RacingGroup racers = new RacingGroup(BackgroundColor);
		RacingDisplay raceDisplay = new RacingDisplay();
		raceDisplay.addGroup(racers);
		//f.activateAnimation();
	}
	
}
