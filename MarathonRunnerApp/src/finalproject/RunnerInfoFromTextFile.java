/**
 * 
 */
package finalproject;
import java.util.*;
import java.io.*;
import java.nio.file.*;
/**
 * @author zeeshansajid
 *
 */
public class RunnerInfoFromTextFile {

    private ArrayList<ThreadRunner> runners = null;
    private Path runnersPath = null;
    private File runnersFile = null;
    private final String FIELD_SEP = "\t";

    /**
	 * 
	 */
	public RunnerInfoFromTextFile() {
		// TODO Auto-generated constructor stub
		runnersPath = Paths.get("Resources/runners.txt");
		runnersFile = runnersPath.toFile();
		runners = this.getRunnersInformation();
	}
	
	protected ArrayList<ThreadRunner> getRunnersList(){
		return runners;
	}


    public ArrayList<ThreadRunner> getRunnersInformation()
    {
        // if the runners file has already been read, don't read it again
        if (runners != null)
            return runners;        

        runners = new ArrayList<>();        
        
        if (Files.exists(runnersPath))  // prevent the FileNotFoundException
        {
            try (BufferedReader in = 
                     new BufferedReader(
                     new FileReader(runnersFile)))
            {
                // read all runners info stored in the file
                // into the array list
                String line = in.readLine();
                while(line != null)
                {
                    String[] columns = line.split(FIELD_SEP);
                    String name 	= columns[0];
                    int speed 		= Integer.parseInt(columns[1]);
                    int percent 	= Integer.parseInt(columns[2]);

                    ThreadRunner tr = new ThreadRunner(name, percent, speed);
                    runners.add(tr);
                    line = in.readLine();                    
                }
            }
            catch(IOException e)
            {
                System.out.println(e);
                return null;
            }
        }
        return runners;            
    }

    public void printInfo()
    {
    	System.out.println("Data Read is : ");
        for (ThreadRunner r : runners)
        {
            System.out.println(r.getRunnersName() + "\t\t" + r.getRestPercentage() + "\t\t" + r.getRunnersSpeed());
        }
    }
}