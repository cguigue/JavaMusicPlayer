package cguigue_B20_A04_Final;


public class Music extends Item
{

	private String musicFileName, album;
	int genreCode;
	String artist;
	private int timesPlayed;
	public static String genreName[] = { "Other", "Blues", "Classical", "Country", "Folk",
			"Jazz", "R&B/Soul", "Rap/Hip-Hop", "Rock", "Pop" };

	public Music(String title, String company, double cost)
	{
		super(title, company, cost);
		musicFileName = "Unknown";
		album = "Unknown";
		artist = "Unknown";
		itemNumber = "M" + itemNumber;
	}

	public Music(String title, String company, double cost, String musicFile,
			String alb, String singer, int code)
	{
		super(title, company, cost);
		musicFileName = musicFile;
		album = alb;
		artist = singer;
		genreCode = 99;
		timesPlayed = 0;
		itemNumber = "M" + itemNumber;
	}

	public Music(String title, String company, double cost, String musicFile,
			String alb, String singer, String genre)
	{
		super(title, company, cost);
		musicFileName = musicFile;
		album = alb;
		artist = singer;
		setGenreCode(genre);
		timesPlayed = 0;
		itemNumber = "M" + itemNumber;
	}

	public Music(String num, String title, String company, double cost,
			String musicFile, String singer, String alb, int genre, int played)
	{
		super(title, company, cost);
		musicFileName = musicFile;
		album = alb;
		artist = singer;
		genreCode = setGenreCode(getGenre(genre));
		timesPlayed = played;
		itemNumber = num;
	}

	public String getArtist()
	{
		return artist;
	}
	public int getTimesPlayed()
	{
		return timesPlayed;
	}
	
	public int getGenreCode()
	{
		return genreCode;
	}

	public String getItemClass()
	{
		return "Music";
	}

	public void incrementTimesPlayed()
	{
		++timesPlayed;
	}

	public String getItemNumber()
	{
		return itemNumber;
	}

	public int setGenreCode(String genre)
	{
		for (int i = 0; i < genreName.length; ++i)
		{
			if (genre.equalsIgnoreCase(genreName[i]))
			{
				return genreCode = i;
			}

		}
		return -1;
	}

	public String getGenre(int g)
	{
		for (int i = 0; i < genreName.length; ++i)
		{
			if (g == i)
			{
				return genreName[i];
			}
		}
		return null;
	}

	public char getItemType()
	{
		return 'M';
	}

	public String getFileName()
	{
		return "music.txt";
	}
	
	public String getMusicFilename()
	{
		return musicFileName;
	} // getMusicFileName()

	public String getEndOfRecord()
	{

		String endOfRecord = musicFileName + "~" + album + "~" + artist + "~"
				+ genreCode + "~" + timesPlayed;

		return endOfRecord;
	}

	public String getAlbum()
	{
		return album;
	}

}
