package cguigue_B20_A04_Final;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Inventory
{
	private int numProducts, numMusic;
	private Music[] musicItem;
	private Product[] productItem;
	private Scanner fileReader;
	private boolean sorted;

	public Inventory()
	{
		numProducts = 0;
		numMusic = 0;
		musicItem = new Music[100];
		productItem = new Product[100];
		load("music.txt", 'M');
		load("product.txt", 'P');

	}// Inventory() constructor

	public int getNumMusic()
	{
		return numMusic;
	}

	public int getNumProducts()
	{
		return numProducts;
	}

	public Music getMusicItem(int subscript)
	{

		return musicItem[subscript];
	}

	public Product getProductItem(int subscript)
	{

		return productItem[subscript];
	}

	public Item find(String num)
	{
		if (num.charAt(0) == 'M')
		{
			return findItem(num, musicItem, numMusic);
		}
		else
			if (num.charAt(0) == 'P')
			{
				return findItem(num, productItem, numProducts);
			}
		return null;
	}

	private Item findItem(String num, Item[] item, int numItems)
	{
		int foundIndex = -1;
		int bottom = 0;
		int top = numItems - 1;

		while (bottom <= top && foundIndex == -1)
		{
			int middle = (top + bottom) / 2;

			if (num.equalsIgnoreCase(item[middle].getItemNumber()))
			{
				foundIndex = middle;
			}
			else
				if (num.compareTo(item[middle].getItemNumber()) > 0)
				{
					bottom = middle + 1;
				}
				else
					if (num.compareTo(item[middle].getItemNumber()) < 0)
					{
						top = middle - 1;
					}
		}
		if (foundIndex >= 0)
		{
			return item[foundIndex];

		}
		else
		{
			return null;
		}

	}

	public void sortMusic()
	{
		sorted = false;
		int loopEnd = numMusic - 1;

		while (loopEnd > 0 && !sorted)
		{
			sorted = true;
			for (int i = 0; i < loopEnd; ++i)
			{
				if (musicItem[i].getArtist().compareTo(musicItem[i + 1].getArtist()) > 0)
				{
					switchMusic(i, i + 1);
					sorted = false;
				}// end if
				else
					if (musicItem[i].getArtist().compareTo(musicItem[i + 1].getArtist()) == 0)
					{
						if (musicItem[i].getAlbum().compareTo(musicItem[i + 1].getAlbum()) > 0)
						{
							switchMusic(i, i + 1);
							sorted = false;

						}// end if
					}// end if
			}// end for loop

			--loopEnd;
		} // end while loop

	} // sortMusic()

	private void switchMusic(int first, int second)
	{
		Music temp = musicItem[first];
		musicItem[first] = musicItem[second];
		musicItem[second] = temp;
	}

	private void load(String fileName, char itemType)
	{
		try
		{
			fileReader = new Scanner(new File(fileName));
		}
		catch (FileNotFoundException e)
		{
			System.err.println("ERROR: " + fileName + " was not found");
		}

		while (fileReader.hasNext())
		{
			StringTokenizer inputLine = new StringTokenizer(fileReader.nextLine(),
					"~");

			if (itemType == 'P')
			{
				productItem[numProducts] = new Product(inputLine.nextToken(),
						inputLine.nextToken(), inputLine.nextToken(),
						Double.parseDouble(inputLine.nextToken()), inputLine.nextToken(),
						inputLine.nextToken(), Integer.parseInt(inputLine.nextToken()));

				++numProducts;
			}
			else
				if (itemType == 'M')
				{
					musicItem[numMusic] = new Music(inputLine.nextToken(),
							inputLine.nextToken(), inputLine.nextToken(),
							Double.parseDouble(inputLine.nextToken()), inputLine.nextToken(),
							inputLine.nextToken(), inputLine.nextToken(),
							Integer.parseInt(inputLine.nextToken()),
							Integer.parseInt(inputLine.nextToken()));
					++numMusic;
				}

		}// While fileReader.hasNext()
		fileReader.close();
	}// load(filename, itemtype)

	public void write(Item item)
	{
		try
		{

			File inventory = new File(item.getFileName());
			FileWriter outStream = new FileWriter(inventory, true);
			outStream.write(item.getFileRecord());
			outStream.close();

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}// write()
	public void rewriteMusic() throws IOException
	{
		FileWriter writer = new FileWriter(new File("music.txt"));
		writer.write("");
		for (int i = 0; i < numMusic; i++)
			writer.write(musicItem[i].getItemNumber() + "~" + musicItem[i]);
		writer.close();
	} // rewriteMusic()

} // Class Inventory()
