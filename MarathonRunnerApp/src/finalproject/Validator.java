package finalproject;

import java.util.Scanner;

public class Validator
{
	private Scanner sc;
	
	// constructor for OOValidator class
	public Validator(Scanner sc)
	{
		setSc(sc);	
	}

	// get function for the private object.
	protected Scanner getSc() {
		return sc;
	}

	// set function for the private object.
	private void setSc(Scanner sc) {
		this.sc = sc;
	}
	
	 // This getInt method forces the user to enter a valid integer value. Uses hasNextInt to test validity of double.
	protected int getInt(String prompt)
	{
		int i = 0;
		boolean isValid = false;
		while (isValid == false)
		{
			System.out.print(prompt);
			if (this.sc.hasNextInt())
			{
				i = this.sc.nextInt();
				isValid = true;
			}
			else
			{
				System.out.println("Error! Invalid integer value. Try again.");
			}
			this.sc.nextLine();  // discard any other data entered on the line
		}
		return i;
	}

	 // This getIntWithinRange uses the getInt function to get the user input and then checks if the integer lies within the range.
	 // it continues to ask the user to input number until a valid integer is input.
	protected int getIntWithinRange(String prompt, int min, int max)
	{
		int i = 0;
		boolean isValid = false;
		while (isValid == false)
		{
			i = getInt(prompt);
			if (i <= min)
				System.out.println(
					"Error! Number must be greater than " + min);
			else if (i > max)
				System.out.println(
					"Error! Number must be less than " + max);
			else
				isValid = true;
		}
		return i;
	}

	 // This getDouble method forces the user to enter a valid number value. Uses hasNextDouble to test validity of double.
	private double getDouble(String prompt)
	{
		double d = 0;
		boolean isValid = false;
		while (isValid == false)
		{
			System.out.print(prompt);
			if (this.sc.hasNextDouble())
			{
				d = this.sc.nextDouble();
				isValid = true;
			}
			else
			{
				System.out.println("Error! Invalid decimal value. Try again.");
			}
			sc.nextLine();  // discard any other data entered on the line
		}
		return d;
	}

	 // This getDoubleWithinRange uses the getInt function to get the user input and then checks if the number lies within the range.
	 // it continues to ask the user to input number until a valid number is input.
	protected double getDoubleWithinRange(String prompt, double min, double max)
	{
		double d = 0;
		boolean isValid = false;
		while (isValid == false)
		{
			d = getDouble(prompt);
			if (d < min)
				System.out.println(
					"Error! Number must be greater than " + min);
			else if (d > max)
				System.out.println(
					"Error! Number must be less than " + max);
			else
				isValid = true;
		}
		return d;
	}
	
    // This getRequiredString method forces the user to enter a non empty string.
    protected String getRequiredString(String prompt)
    {
        String s = "";
        boolean isValid = false;
        while (isValid == false)
        {
            System.out.print(prompt);
            s = this.getSc().nextLine();
            if (s.equals(""))
            {
                System.out.println("Error! This entry is required. Try again.");
            }
            else
            {
                isValid = true;
            }
        }
        return s;
    }

    // This getChoiceString method forces the user to enter one of two strings s1 or s2. It uses the getRequiredString function to 
    // get the value and then uses the built-in equalsIgnoreCase function to check if the string is correct or not.
    protected String getChoiceString(String prompt, String s1, String s2)
    {
        String s = "";
        boolean isValid = false;
        while (isValid == false)
        {
            s = getRequiredString(prompt);
            if (
                !s.equalsIgnoreCase(s1) &&
                !s.equalsIgnoreCase(s2)
                )
            {
                System.out.println("Error! Entry must be '"+
                    s1 +"' or '"+ s2 +"'. Try again.");
            }
            else
            {
                isValid = true;
            }
        }
        return s;
    }


}