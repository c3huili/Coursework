/*
 * RacingGroup.java
 * ICS 45J : Lab Assignment 4
 * 
 * Completed by: Thomas Liu & Ce Hui Li 
 * UCInetiD:     thomal3 & cel
 * ID:           75716746 & 75935876
 * Modified:     May 27, 2015
 * 
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;


public class RacingGroup implements RacingGroupInterface{
	// private member variables that store the name, number of racers, max speed that the smileys can go, etc
	private static final String names[] = {"Adam", "Belle", "Calvin", "Diana", "Ethan", "Fiona", "George", "Hilary", "Issac", "Jaime", "Kevin",
										   "Liana", "Mark", "Nicole", "Omar", "Penelope", "Queen", "Rosanna", "Steven", "Tracy","Ulysseus", 
										   "Venessa", "Wilson", "XinXin", "Yu", "Zack", "Thomas", "Ce", "Bob", "Charles", "Pikachu", "Lando"};
	private static final int NUM_OF_RACERS = 5;
	private static final int MAX_SPEED = 10; // can be changed to make the smileys go faster or slower 
	private static final int FRAME_HEIGHT = 400; // because frame height wasn't inherited, I gave it my own frame height to scale the smiles
	private ArrayList<RacingSmiley> racers;
	private AnimatedSmiley animatedSmiley;
	private static Random r;

	
	// Constructor to build an array of racers
	public RacingGroup(Color background)
	{
		if(NUM_OF_RACERS > 0){
			racers = new ArrayList<RacingSmiley>();
			r = new Random();
			
			for(int i = 0; i < NUM_OF_RACERS; i++)
			{
				// Create animated smiley as to pass as parameter			
				animatedSmiley = new AnimatedSmiley(r.nextInt(MAX_SPEED)+1, 0); // random generate the speed using the max speed + 1 to include max speed and exclude 0
				animatedSmiley.getFace().setAttributes(Color.YELLOW, findCenter(0), findCenter(i), scaleRacer()-3, scaleRacer()-3); // function to scale the face calling the center function of a smiley and scaling it
				animatedSmiley.getLeftEye().setAttributes(Color.YELLOW, findCenter(0) - findCenter(0) / 3, findCenter(i) - findCenter(0) / 3, scaleRacer() / 8, scaleRacer() / 8); // function call to scale the eyes of the smiley relative to size and position of the face
				animatedSmiley.getRightEye().setAttributes(Color.RED, findCenter(0) + findCenter(0) / 3, findCenter(i) - findCenter(0) / 3, scaleRacer() / 8, scaleRacer() / 8); // function call to scale the eyes of the smiley relative to size and position of the face
				animatedSmiley.getSmile().setAttributes(background, findCenter(0) + findCenter(0), findCenter(i) + findCenter(0) / 2, scaleRacer(), scaleRacer() / 8); // function call to scale the smile of the smiley relative to size and position of the face
				
				// Create racing smiley and add into an array
				RacingSmiley raceSmiley  = createRacer(animatedSmiley, names[i]);
				racers.add(raceSmiley);
			}
		}
	} 
	// function to find the center of the racing smiley to build 
	private int findCenter(int racerNum)
	{
		//first smiley center is half the height of the smiley and the rest is half + height of a smiley
		if(racerNum == 0)
			return scaleRacer() / 2;
		return racerNum * scaleRacer() + (scaleRacer() / 2);
	}
	// function that returns the height or size of each smiley
	private int scaleRacer()
	{
		return FRAME_HEIGHT / NUM_OF_RACERS;
	}
	// Return array of racers
	public ArrayList<RacingSmiley> getRacers()
	{
		return racers;
	}
	// function that returns the number of smileys at the race
	public int getNumberOfRacers() {
		return NUM_OF_RACERS;
	}

	// Create a singly racing smiley to add into an array giving it a name from the array and a random generated color
	private RacingSmiley createRacer(AnimatedSmiley animatedSmiley, String name)
	{
		RacingSmiley racingSmiley = new RacingSmiley(animatedSmiley, name, new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()));
		return racingSmiley;
	}
}
