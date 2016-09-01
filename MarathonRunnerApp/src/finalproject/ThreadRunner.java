/**
 * 
 */
package finalproject;

import java.util.Random;

/**
 * @author zeeshansajid
 *
 */
public class ThreadRunner extends Thread {

	private String runnersName;
	private double restPercentage; 
	private double runnersSpeed; 	
	private double distanceCovered = 0;
	private static boolean raceFinished = false;
	
	private static Object obj = new Object();
	
	/**
	 * 
	 */
	public ThreadRunner() {
		// TODO Auto-generated constructor stub
		this.runnersName    = "";
		this.restPercentage = 0;
		this.runnersSpeed   = 0;		
	}
	
	public void startRace(){
		
		raceFinished = false;
	}
	
	public boolean isRaceFinished(){
		
		return raceFinished;
	}
	
	/**
	 * 
	 */
	public ThreadRunner(String name, double percentage, double speed) {
		
		this.runnersName    = name;
		this.restPercentage = percentage;
		this.runnersSpeed   = speed;
	}

	/**
	 * @return the runnersName
	 */
	public String getRunnersName() {
		return runnersName;
	}

	/**
	 * @param runnersName the runnersName to set
	 */
	public void setRunnersName(String runnersName) {
		this.runnersName = runnersName;
	}

	/**
	 * @return the restPercentage
	 */
	public double getRestPercentage() {
		return restPercentage;
	}

	/**
	 * @param restPercentage the restPercentage to set
	 */
	public void setRestPercentage(int restPercentage) {
		this.restPercentage = restPercentage;
	}

	/**
	 * @return the runnersSpeed
	 */
	public double getRunnersSpeed() {
		return runnersSpeed;
	}

	/**
	 * @param runnersSpeed the runnersSpeed to set
	 */
	public void setRunnersSpeed(int runnersSpeed) {
		this.runnersSpeed = runnersSpeed;
	}
/*
 * 
 *The run method of the ThreadRunner class should consist of a loop that repeats until the runner has reached 1000 meters. 
 *Each time through the loop, the thread should decide whether it should run or rest based on a random number and the 
 *percentage passed to the constructor (RestPercentage). If this random number indicates that the runner should run, the class should add the speed value passed to the constructor (RunnersSpeed). The run method should sleep for 100 milliseconds on each repetition of the loop.
• To determine whether a thread should run or rest, calculate a random number between 1 and 100. Then, have the thread rest if the number is less than or equal to the percentage of time that the thread rests (RestPercentage). Otherwise, the thread should run.
 * @see java.lang.Thread#run()
 */
	@Override
	public void run(){
		
        while (distanceCovered < 1000)
        {
        	synchronized (obj)
        	{
        		if(raceFinished == true)
        		{
             	    System.out.println();
        			System.out.println(this.runnersName+" : You beat me fair and square.");
        			break;
        		}
        		
        		Random randomGenerator = new Random();
        	    int randomInt = randomGenerator.nextInt(100);
        	    if(this.restPercentage <= randomInt)
        	    {
        	    	// for restPercentage of 75, it is assumed we will rest until the random value is between 0-75
            	    distanceCovered = distanceCovered + this.runnersSpeed;
            	    if(distanceCovered >= 1000 )
            	    {
            	       raceFinished = true;
                	   System.out.println(this.runnersName+" : I finished!");
                	   System.out.println();
                	   System.out.println("The race is over! The "+this.runnersName+" is the winner.");
                	   
            	    	
            	    }
            	    else
            	    {
            	    	System.out.println(this.runnersName+" : "+ distanceCovered);
            	    }
        	    }
        	}
        	
            try
            {
                Thread.sleep(1000);          // delay one second
            }
            catch (InterruptedException e)
            {}                               // ignore interruptions
        }
		
		
	}
	
	public void waitForRaceResults(){
		
		 while (this.isRaceFinished() == false)
	        {
	            try
	            {
	                wait();
	            }
	            catch (InterruptedException e)
	            {}
	        }
		
	}

}
