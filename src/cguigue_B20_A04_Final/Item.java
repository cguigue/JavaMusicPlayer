package cguigue_B20_A04_Final;


import java.io.*;
import java.util.Scanner;

/*
 * Author: Christopher Guigue
 * 
 */

public abstract class Item
{

	private static int nextItemNumber;
	private static String itemNumberFileName = "itemNumber.txt";
	private static boolean nextItemNumberIsInitialized = false;
	protected String itemNumber, name, companyName;
	protected double price;

	public static void readNextItemNumber(String fileName)
			throws FileNotFoundException
	{

		itemNumberFileName = fileName;

		readNextItemNumber();

	}

	public static void readNextItemNumber() throws FileNotFoundException
	{

		Scanner fileScanner = new Scanner(new File(itemNumberFileName));

		nextItemNumber = Integer.parseInt(fileScanner.next());

		nextItemNumberIsInitialized = true;

		fileScanner.close();

	}

	public static void rewriteNextItemNumber() throws IOException
	{
		File numberFile = new File(itemNumberFileName);
		FileWriter numberWriter = new FileWriter(numberFile);
		numberWriter.write(String.valueOf(nextItemNumber));
		numberWriter.close();
	}

	public Item(String title, String company, double cost)
	{

		super();
		name = title;
		companyName = company;
		price = cost;
		setItemNumber();
	}
	public Item(String num, String title, String company, double cost)
	{
		super();
		name = title;
		companyName = company;
		price = cost;
		itemNumber = num;
	}

	protected void setItemNumber()
	{

		if (nextItemNumberIsInitialized)
		{
			itemNumber = getItemType() + String.valueOf(nextItemNumber);
			++nextItemNumber;

		}
	}
	
	public static void setNextItemNumber(int nextNumber)
	{

		nextItemNumber = nextNumber;
		nextItemNumberIsInitialized = true;
	}

	public String getFileRecord()
	{

		String finalRecord = itemNumber + "~" + name + "~" + companyName + "~"
				+ price + "~" + getEndOfRecord() + "\r\n";

		return finalRecord;
	}
	
	public String getItemNumber()
	{
		return itemNumber;
	}
	public String getName()
	{
		return name;
	}
	public String getCompanyName()
	{
		return companyName;
	}
	public double getPrice()
	{
		return price;
	}
	
	protected abstract char getItemType();

	public abstract String getFileName();

	public abstract String getEndOfRecord();

	public abstract String getItemClass();
	
}// Item()
