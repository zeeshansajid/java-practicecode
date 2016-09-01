/**
 * 
 */
package finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author zeeshansajid
 *
 */
public class RunnerInfoFromDerbyDB {

	 private ArrayList<ThreadRunner> runners = null;
	/**
	 * 
	 */
	public RunnerInfoFromDerbyDB() {
		// TODO Auto-generated constructor stub
		runners = this.getRunnersInformation();
	}

	protected ArrayList<ThreadRunner> getRunnersList(){
		return runners;
	}
	
	private static Connection connect()
 	{
        try
        {         	
        	//keep relative path to this project
            String dbDirectory = "Resources";
            System.setProperty("derby.system.home", dbDirectory);

        	// use the DriverManager to create a Connection object
            String dbUrl = "jdbc:derby:FinalDB";
            
            String username = "";
            String password = "";
            Connection connection = DriverManager.getConnection(dbUrl, username, password);

            return connection;

        }
        catch(SQLException e)
        {
           // e.printStackTrace();
            return null;
        }
    }

    public ArrayList<ThreadRunner> getRunnersInformation()
    {
        try
        {
            Connection connection = connect();
            ArrayList<ThreadRunner> runners = new ArrayList<ThreadRunner>();
            String query = "SELECT Name, RunnersSpeed, RestPercentage FROM RunnersStats";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                String Name 			= rs.getString("Name");
                double RunnersSpeed 	= rs.getDouble("RunnersSpeed");
                double RestPercentage 	= rs.getDouble("RestPercentage");

                ThreadRunner tr = new ThreadRunner( Name, RestPercentage, RunnersSpeed);
                runners.add(tr);
            }
            rs.close();
            ps.close();
            connection.close();
            return runners;
            
        }
        catch(SQLException sqle)
        {
            //sqle.printStackTrace();
            return null;
        }
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
