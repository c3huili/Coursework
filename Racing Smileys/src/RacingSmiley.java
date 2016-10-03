/*
 * Racing Smiley.java
 * ICS 45J : Lab Assignment 4
 * 
 * Completed by: Thomas Liu & Ce Hui Li 
 * UCInetiD:     thomal3 & cel
 * ID:           75716746 & 75935876
 * Modified:     May 27, 2015
 * 
 */

import java.awt.Color;
import java.util.Random;

public class RacingSmiley extends AnimatedSmiley implements RacingSmileyInterface{
	private static final int REVERSE_DIRECTION = -1;
	private static final int LAPS_TO_COMPLETE = 2;
	private String myName;
	private Color myColor;
	private int lapCount;
	private int tickCount; 
	private int maxSpeed;
	private static Random r;
	
	// default constructor that constructs a racing smiley by creating an animated Smiley and assigning a name and color to it 
	public RacingSmiley(AnimatedSmiley existingSmiley, String name, Color nameColor)
	{
		// calls the super constructor and initializes the racing smiley's name and color
		super(existingSmiley);
		myName = name;
		myColor = nameColor;
		tickCount = 0;
		lapCount = 0;
		
		// random generator to determine if the smiley slows down, stays the same, or gradually increases it speed throughout the race
		r = new Random();
		switch(r.nextInt(3))
		{
			case 0:
				maxSpeed = 1;
				break;
			case 1:
				maxSpeed = existingSmiley.getCurrentXMovement();
				break;
			case 2:
				maxSpeed = existingSmiley.getCurrentXMovement() * 2;
		}
	}
	
	// overloaded constructor that constructs a racing smiley from an existing racing smiley but given different name and attributes
	public RacingSmiley(RacingSmiley original, String name, Color nameColor)
	{
		super(original);
		myName = name;
		myColor = nameColor;
		tickCount = 0;
		lapCount = 0;
		
		r = new Random();
		switch(r.nextInt(3))
		{
			case 0:
				maxSpeed = 1;
				break;
			case 1:
				maxSpeed = original.getCurrentXMovement();
				break;
			case 2:
				maxSpeed = original.getCurrentXMovement() * 2;
		}
	}
	
	// boolean function that returns whether a racing smiley has completed the number of laps for the race
	public boolean finishedRace()
	{
		return (LAPS_TO_COMPLETE == lapCount);
	}
	
	// void function that moves the smiley one frame and determines whether or not it has hit a wall what 
	// to do in case of going out of frame. It will handle wall collisions and be responsible for flipping
	// its face when it hits a wall and also for speed changes.
	public void raceForOneTick()
	{
		int XMovement = this.getCurrentXMovement();
		
		// If wall is hit, flipface, increment lap count, and reverse direction
		if(RacingDisplay.LEFT_EDGE >= this.getLeftEdge() + XMovement || RacingDisplay.RIGHT_EDGE <= this.getRightEdge() + XMovement) {
			// If next move is beyond right wall's edge, change the movement to hit the wall exactly for 1 tick
			if(RacingDisplay.LEFT_EDGE > this.getLeftEdge() + XMovement) {
				int tempXMovement = this.getLeftEdge() * -1;
				this.setCurrentXMovement(tempXMovement);
			}
			else if(RacingDisplay.RIGHT_EDGE < this.getRightEdge() + XMovement) {
				int tempXMovement = RacingDisplay.RIGHT_EDGE - this.getRightEdge();
				this.setCurrentXMovement(tempXMovement);
			}
			// after it has determined how far the edge is from the wall, it will move that distance without passing the wall
			moveIt();
			this.flipFace();
			this.incrementLapCount();
			
			// increasing/decreasing/do nothing to racing smiley speed
			if(maxSpeed == 1 && XMovement > 1) {
				this.setCurrentXMovement((XMovement - 1) * REVERSE_DIRECTION);
			}
			else if(maxSpeed == 1 && XMovement < -1) {
				this.setCurrentXMovement((XMovement + 1) * REVERSE_DIRECTION);
			}
			else if(maxSpeed == XMovement || maxSpeed == (XMovement * -1)) {
				this.setCurrentXMovement(XMovement * REVERSE_DIRECTION);
			}
			else if(maxSpeed > XMovement && XMovement > 0) {
				this.setCurrentXMovement((XMovement + 1) * REVERSE_DIRECTION);
			}
			else if(maxSpeed > XMovement && XMovement < 0) {
				this.setCurrentXMovement((XMovement - 1) * REVERSE_DIRECTION);
			}			
		}
		else{
			moveIt();
		}
		// increments the tick count for the smiley
		tickCount++;
	}
	// function to return the tick count for the scoreboard at the end
	public int getTicks()
	{
		return tickCount;
	}
	// function to return the name of the racing smiley for displaying
	public String getSmileyName()
	{
		return myName;
	}
	// function to get the color of the name of the racing smiley
	public Color getSmileyNameColor()
	{
		return myColor;
	}
	// function that returns the number of laps the smiley has completed that will be displayed as the smiley races
	public int getLapsCompleted()
	{
		return lapCount;
	}
	// function to increment the racing smiley's lap count after it hits a wall 
	public void incrementLapCount()
	{
		lapCount++;
	}
	// function that flips the racing smileys face after hitting a wall
	private void flipFace() {
		// if the eye is the same color has the face then the smiley is facing right so it flips is so the smiley is 
		// facing left, setting the left to red, the right to face color and translates the mouth to the other side.
		if(getLeftEye().getColor() == getFace().getColor())
		{
			getLeftEye().setColor(Color.RED);
			getRightEye().setColor(getFace().getColor());
			getSmile().translate((int)getSmile().getXLength() * -1, 0);
		}
		else
		{
			getLeftEye().setColor(getFace().getColor());
			getRightEye().setColor(Color.RED);
			getSmile().translate((int)getSmile().getXLength(), 0);
		}
	}
}
