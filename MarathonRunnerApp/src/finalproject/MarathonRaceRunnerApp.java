/**
 * 
 */
package finalproject;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author zeeshansajid
 *
 */
public class MarathonRaceRunnerApp {

	/**
	 * 
	 */
	public MarathonRaceRunnerApp() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String args[]) throws IOException, SQLException
	{			
			displayWelcomeMessage();
			while(true)
			{
				printMainMenu();
				getUserOption();
			}
		}

		public static void displayWelcomeMessage()
		{
			System.out.println("---------------------------------------------------------");
			System.out.println("  Welcome to the Marathon Race Runner Program        ");
			System.out.println("---------------------------------------------------------");	
		}

		public static void printMainMenu()
		{
			System.out.println();
			System.out.println("Select your data source : ");
			System.out.println("1. Derby database");
			System.out.println("2. XML file");
			System.out.println("3. Text file");
			System.out.println("4. Default two runners");
			System.out.println("5. Exit");
			System.out.println();
		}		

		public static  void getUserOption() throws IOException, SQLException
		{
			System.out.println();

			Scanner scObj = new Scanner(System.in);
			Validator myVal = new Validator(scObj);
			
			int choice = myVal.getInt("Enter Choice : ");
			
			if(choice == 1)
			{	
				runMarathonUsingDataFromDerbyDB();
			}
			else if(choice == 2)
			{
				runMarathonUsingDataFromXMLFile();
			}
			else if(choice == 3)
			{
				runMarathonUsingDataFromTEXTFile();
			}
			else if(choice == 4)
			{
				runMarathonUsingDefaultData();
			}
			else if(choice == 5)
			{
				System.out.println();
				System.out.println("Thank you for using my Marathon Race Program");
				System.exit(0);
			}
			else
			{
				System.out.println("Invalid Option selected.");
			}
		}
		
		public static void runMarathonUsingDataFromDerbyDB()
		{
			RunnerInfoFromDerbyDB ri = new RunnerInfoFromDerbyDB();
			ri.printInfo();
			
			System.out.println();
			System.out.println("Get set...Go!");
			ri.getRunnersList().get(0).startRace();
			
			for (ThreadRunner r : ri.getRunnersList())
	        {
				r.start();
	        }
			
	//		ri.getRunnersList().get(0).waitForRaceResults();
		}
		
		public static void runMarathonUsingDataFromXMLFile()
		{
			RunnerInfoFromXmlFile ri = new RunnerInfoFromXmlFile();
			ri.printInfo();
			
			System.out.println();
			System.out.println("Get set...Go!");
			ri.getRunnersList().get(0).startRace();
			
			for (ThreadRunner r : ri.getRunnersList())
	        {
				r.start();
	        }
			
		//	ri.getRunnersList().get(0).waitForRaceResults();
		}
		
		public static void runMarathonUsingDataFromTEXTFile()
		{
			     
			RunnerInfoFromTextFile ri = new RunnerInfoFromTextFile();
			ri.printInfo();
			
			System.out.println();
			System.out.println("Get set...Go!");
			ri.getRunnersList().get(0).startRace();
			
			for (ThreadRunner r : ri.getRunnersList())
	        {
				r.start();
	        }

		//	ri.getRunnersList().get(0).waitForRaceResults();
		}
		
		public static void runMarathonUsingDefaultData()
		{
			 ArrayList<ThreadRunner> runners = new ArrayList<>();       
			 ThreadRunner tr1 = new ThreadRunner("Tiger", 60, 90);
			 ThreadRunner tr2 = new ThreadRunner("Cheetah", 50, 75);
			 runners.add(tr1);
			 runners.add(tr2);
			
			for (ThreadRunner r : runners)
	        {
	            System.out.println(r.getRunnersName() + "\t\t" + r.getRestPercentage() + "\t\t" + r.getRunnersSpeed());
	        }
			
			System.out.println();
			System.out.println("Get set...Go!");
			runners.get(0).startRace();
			for (ThreadRunner r : runners)
	        {
				r.start();
	        }
						
		}
		
}
