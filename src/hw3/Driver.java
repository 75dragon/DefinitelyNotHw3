package hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver
{
	public static Scanner userScanner = new Scanner(System.in);

	// opens a text file for input, returns a Scanner:
	public static Scanner openInputFile()
	{
		String filename;
		Scanner scanner = null;

		System.out.print("Enter the input filename: ");
		filename = userScanner.nextLine();
		File file = new File(filename);

		try
		{
			scanner = new Scanner(file);
		} // end try
		catch (FileNotFoundException fe)
		{
			System.out.println("Can't open input file\n");
			return null; // array of 0 elements
		} // end catch
		return scanner;
	}

	/**
	 * In this method, call the SortedLList2's display() method AND the
	 * displayBackwards() method (please display appropriate descriptions
	 * first). Also, test the SortedLList2's contains(T) and then remove(T)
	 * (TEST THESE IN SEPARATE IF STATEMENTS), passing a NEW Date with the
	 * same month, day and year as in list's LAST node (so call the
	 * getEntry() method passing the length of the list to getEntry(), but
	 * DON'T pass the return value of getEntry to the contains(T) or
	 * remove(T) method!), displaying the messages that indicate if contains
	 * returns true or false (include the Date you're searching for), and if
	 * remove returns true or false (include the Date you're tried to
	 * remove). Then test contains(T) passing a default Date, displaying the
	 * messages that indicate if contains returns true or false AND try to
	 * remove it (again displaying the messages the indicate if it returned
	 * true or false). Call the display and displayBackwards methods again.
	 */
	public static void testSortedLList1(SortedLList2<Date> dateList)
	{
		System.out.println("The sorted list: ");
		dateList.display();
		System.out.println("\nThe sorted list backwards: ");
		dateList.displayBackwards();
		Date hold = dateList.getEntry(dateList.getLength());
		Date newDate = new Date(hold.getMonth(), hold.getDay(), hold.getYear());
		if (dateList.contains(newDate))
		{
			System.out.println("\nThe list contains " + newDate);
		}
		else
		{
			System.out.println("The list does not have " + newDate);
		}
		
		if (dateList.remove(newDate))
		{
			System.out.println("Successfuly removed " + newDate);
		}
		else
		{
			System.out.println("Unsuccessfuly removed " + newDate);
		}
		
		Date emptyDate = new Date();
		if (dateList.contains(emptyDate))
		{
			System.out.println("\nThe list contains " + emptyDate);
		}
		else
		{
			System.out.println("(good) The list does not have " + emptyDate);
		}
		
		if (dateList.remove(emptyDate))
		{
			System.out.println("Successfuly removed " + emptyDate);
		}
		else
		{
			System.out.println("(good) Unsuccessfuly removed " + emptyDate);
		}
		
		System.out.println("\nThe sorted list: ");
		dateList.display();
		System.out.println("\nThe sorted list backwards");
		dateList.displayBackwards();
	}

	
	public static void testSortedLList2(SortedLList2<Date> dateList)
	{
		if (dateList == null || dateList.getLength() < 2)
		{
			System.out.println("\nEither empty or not enough nodes in the list; no testing done\n");
			return;
		}
		Date date1, date2, newDate;

		int middle = dateList.getLength() / 2;

		date1 = dateList.getEntry(middle);
		date2 = dateList.getEntry(middle + 1);

		System.out.println("Testing removing element #" + (middle + 1) + ": " + date2);
		date2 = dateList.remove(middle + 1);
		if (date2 != null)
			System.out.println("Successfully removed: " + date2);
		else
			System.out.println("Error: unable to remove element #" + (middle + 1));

		System.out.println("\nTesting removing element #" + middle + ": " + date1);
		date1 = dateList.remove(middle);
		if (date1 != null)
			System.out.println("Successfully removed: " + date1);
		else
			System.out.println("Error: unable to remove element #" + middle);

		newDate = new Date();
		System.out.println("Testing adding default Date: " + newDate);
		if (dateList.add(dateList.getLength() + 1, newDate))
			System.out.println("Default date successfully added!");
		else
			System.out.println("Error: unable to add default Date");
		System.out.println("\nNow the list has: ");
		dateList.display();
	}

	public static void main(String args[])
	{
		int month, day, year;
		Scanner scan;
		SortedLList2 simu;
		simu = new SortedLList2();
		scan = openInputFile();
		Date hold;
		if (scan == null)
		{
			return;
		}

		while (scan.hasNext())
		{

			month = Integer.parseInt(scan.next());
			// System.out.println(month);
			day = Integer.parseInt(scan.next());
			// System.out.println(day);
			year = Integer.parseInt(scan.next());
			// System.out.println(year);
			hold = new Date();
			if (hold.setDate(month, day, year))
			{
				simu.add(hold);
			}
		}
		testSortedLList1(simu);
		testSortedLList2(simu);
	}
}
