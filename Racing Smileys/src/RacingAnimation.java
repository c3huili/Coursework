/*
 * RacingAnimation.java
 * ICS 45J : Lab Assignment 4
 * 
 * Completed by: Thomas Liu & Ce Hui Li 
 * UCInetiD:     thomal3 & cel
 * ID:           75716746 & 75935876
 * Modified:     May 27, 2015
 * 
 */

import java.util.ArrayList;

//A SmileyAnimation represents an animation in which a collection of
//smiley faces that race one another, each completing a set number of laps.
public class RacingAnimation implements RacingAnimationInterface{
	
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
	
	private RacingGroup racingGroup;
	private ArrayList<RacingSmiley> racers;
	private RacingDisplay racingDisplay;
	private String statTitle = "Race Statistics";
	
	// Implement this constructor in your class that implements this interface
	// (changing its name to match the name of your class, if necessary). It
	// creates an animation that races the smileys in the given group, showing
	// the animation of the race on the given display.
	RacingAnimation(RacingGroup g, RacingDisplay d) {
		this.racingGroup = g;
		this.racers = g.getRacers();
		this.racingDisplay = d;
	}
	
	
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
	public void animate() {
		if(racingGroup.getNumberOfRacers() > 0) {			
			int numOfFinishedRacers;
			do{
				numOfFinishedRacers = 0;
				for(RacingSmiley r : racers) {
					//check the number of ticks
					if(!r.finishedRace()){
						moveCntSmiley(r);						
					}
					else {
						numOfFinishedRacers++;
					}
				}
				racingDisplay.repaint();
				pause(10);
			} while(numOfFinishedRacers != racers.size()); 
		}
	}
	
	// pause(): pause the animation for the given number of milliseconds
	//	DO NOT CHANGE
	private void pause(int millisecs) {
		try {
			Thread.sleep(millisecs);
		}
		catch (InterruptedException e) {
		}
	}
	
	// moveCntSmiley: Continue to move smiley until it hits
	// an edge; when that happens, change direction
	private void moveCntSmiley(RacingSmiley cntSmiley){
		cntSmiley.raceForOneTick();
	}
	
	// getRacers() returns all the theRacers (with their information).
	public ArrayList<RacingSmiley> getRacers() {
		return this.racers;
	}

	// getStatisticsTitle() returns the title that should be shown in the
	// statistics area of the window.
	public String getStatisticsTitle() {
		return this.statTitle;
	}

	// getAverageTicks() returns the average time, in ticks, that each
	// smiley spent completing the race.
	public double getAverageTicks() {
		int totalTicks = 0;
		if(racingGroup.getNumberOfRacers() > 0) {			
			for(RacingSmiley r: racers) {
				totalTicks += r.getTicks();
			}
			return totalTicks/racers.size();
		}
		else{
			return totalTicks;
		}
	}

	// getFewestTicks() returns the number of ticks spent by the fastest
	// smiley in completing the race.
	public int getFewestTicks() {
		if(racingGroup.getNumberOfRacers() > 0) {
			int fewestTicks = racers.get(0).getTicks();
			for(RacingSmiley r: racers) {
				if(r.getTicks() < fewestTicks){
					fewestTicks = r.getTicks();
				}
			}
			return fewestTicks;
		}	
		else{
			return 0;
		}
	}

	// getMostTicks() returns the number of ticks spent by the slowest
	// smiley in completing the race.
	public int getMostTicks() {
		if(racingGroup.getNumberOfRacers() > 0) {
			int mostTicks = racers.get(0).getTicks();
			for(RacingSmiley r: racers) {
				if(r.getTicks() > mostTicks){
					mostTicks = r.getTicks();
				}
			}
			return mostTicks;
		}	
		else{
			return 0;
		}
	}
	
	// getFastestSmileyName() returns the name of the fastest smiley.
	public String getFastestSmileyName() {
		if(racingGroup.getNumberOfRacers() > 0) {
			RacingSmiley fastestRacer = racers.get(0);
			for(RacingSmiley r: racers) {
				if(r.getTicks() < fastestRacer.getTicks()){
					fastestRacer = r;
				}
			}
			return fastestRacer.getSmileyName();
		}	
		else{
			return null;
		}
	}

	// getSlowestSmileyName() returns the name of the slowest smiley.
	public String getSlowestSmileyName() {
		if(racingGroup.getNumberOfRacers() > 0) {
			RacingSmiley slowestRacer = racers.get(0);
			for(RacingSmiley r: racers) {
				if(r.getTicks() > slowestRacer.getTicks()){
					slowestRacer = r;
				}
			}
			return slowestRacer.getSmileyName();
		}
		else{
			return null;
		}
	}
}
