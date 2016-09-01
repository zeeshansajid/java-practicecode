/**
 * 
 */
package finalproject;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author zeeshansajid
 *
 */
public class RunnerInfoFromXmlFile {

	private Path runnersPath = null;
    private ArrayList<ThreadRunner> runners = null;

	/**
	 * 
	 */
	public RunnerInfoFromXmlFile() 
	{
		// TODO Auto-generated constructor stub
	     runnersPath = Paths.get("Resources/runners.xml");
	     runners = this.getRunnersInformation();
	}
	
	protected ArrayList<ThreadRunner> getRunnersList(){
		return runners;
	}


	public ArrayList<ThreadRunner> getRunnersInformation()
	{
	    // if the XML file has already been read, don't read it again
	    if (runners != null)
	        return runners;        
	
	    runners = new ArrayList<>();        
	    ThreadRunner tr = null;        
	    if (Files.exists(runnersPath))  // prevent the FileNotFoundException
	    {
	        // create the XMLInputFactory object
	        XMLInputFactory inputFactory = XMLInputFactory.newFactory();
	        try
	        {
	            // create a XMLStreamReader object
	            FileReader fileReader =
	                new FileReader(runnersPath.toFile());
	            XMLStreamReader reader =
	                inputFactory.createXMLStreamReader(fileReader);
	
	            // read the runners from the file
	            while (reader.hasNext())
	            {
	                int eventType = reader.getEventType();
	                switch (eventType)
	                {
	                    case XMLStreamConstants.START_ELEMENT:
	                        String elementName = reader.getLocalName();
	                        if (elementName.equals("Runner"))
	                        {
	                            tr = new ThreadRunner();
	                            String name = reader.getAttributeValue(0);
	                            tr.setRunnersName(name);
	                            
	                        }
	                        
	                        if (elementName.equals("RunnersMoveIncrement"))
	                        {
	                            String moveInc = reader.getElementText();
	                            tr.setRunnersSpeed(Integer.parseInt(moveInc));
	                        }
	                        
	                        if (elementName.equals("RestPercentage"))
	                        {
	                            String restPercentage = reader.getElementText();
	                            tr.setRestPercentage(Integer.parseInt(restPercentage));
	                        }
	                        break;
	                        
	                    case XMLStreamConstants.END_ELEMENT:
	                        elementName = reader.getLocalName();
	                        if (elementName.equals("Runner"))
	                        {
	                            runners.add(tr);
	                        }
	                        break;
	                    default:
	                        break;
	                }
	                reader.next();
	            }
	        }
	        catch (IOException | XMLStreamException e)
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

