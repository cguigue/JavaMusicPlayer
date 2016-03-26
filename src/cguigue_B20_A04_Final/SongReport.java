package cguigue_B20_A04_Final;


import CSClasses.Heading;
import javax.swing.JTextArea;
public class SongReport
{

	private JTextArea textArea;
	private Inventory inventory;
	private int totalArtist;
	private int totalAlbum;
	private int totalPlayed;
	private String albumName;
	private String artistName;
	private Music previousItem;
	private Music currentItem;

	public SongReport(JTextArea display)
	{
		textArea = display;
		inventory = new Inventory();

	} // SongReport(JTextArea)

	public void displaySongReport()
	{
		inventory.sortMusic(); // sort music by control fields
		totalArtist = 0;
		totalAlbum = 0;
		totalPlayed = 0; // initialize accumulators

		displayReportHeadings(); // display headings

		currentItem = inventory.getMusicItem(0);

		startNewArtistGroup(currentItem);
		startNewAlbumGroup(currentItem);

		for (int i = 0; i < inventory.getNumMusic(); ++i)
		{
			currentItem = inventory.getMusicItem(i);
			if (currentItem.getArtist().compareTo(previousItem.getArtist()) != 0)
			{
				processAlbumTotals(previousItem);
				startNewAlbumGroup(currentItem);
				processArtistTotals(previousItem);
				startNewArtistGroup(currentItem);
			}// end if
			else
				if (currentItem.getAlbum().compareTo(previousItem.getAlbum()) != 0)
				{
					processAlbumTotals(previousItem);
					startNewAlbumGroup(currentItem);
				}// end if
			
			displayMusicDetailLine(currentItem);
			totalAlbum += currentItem.getTimesPlayed();
			albumName = "";
			artistName = "";
			
		}// end for
		processAlbumTotals(previousItem);
		processArtistTotals(previousItem);
		displayMusicTotals();

	} // displaySongReport()
	
	private void displayMusicTotals()
	{
		textArea.append(String.format("%110s\n", "----------"));
		textArea.append(String.format("%105s%5d\n", "Total Plays:", totalPlayed));
		
	} // displayMusicTotals()

	private void displayMusicDetailLine(Music music)
	{
		textArea.append(String.format("%-25s%-40s%-40s%5s\n", artistName, albumName, music.getName(), music.getTimesPlayed()));
		
	}// displayMusicDetailLine()

	private void startNewArtistGroup(Music music)
	{
		previousItem = music;
		totalArtist = 0;
		totalAlbum = 0;
		artistName = music.getArtist();
		albumName = music.getAlbum();
	}// startNewArtistGroup()
	
	private void startNewAlbumGroup(Music music)
	{
		albumName = music.getAlbum();
		totalAlbum = 0;
		previousItem = music;
	}// startNewAlbumGroup
	
	private void processArtistTotals(Music previous)
	{
	
		textArea.append(String.format("%105s%5d\n\n", "Artist Totals:", totalArtist));
		totalPlayed += totalArtist;
	
	}//processArtistTotals()
	
	private void processAlbumTotals(Music music)
	{
		textArea.append(String.format("%110s\n", "-----"));
		textArea.append(String.format("%105s%5d\n\n", "Album Totals:", totalAlbum));
	 totalArtist += totalAlbum;
	 totalAlbum += music.getTimesPlayed();
	 
	
	}// processAlbumTotals()

	private void displayReportHeadings()
	{
		Heading heading = new Heading(
				"Music Inventory Report~Sorted by Artist and Album", 110);
		textArea.setText(heading.getHeading());
		textArea.append(String.format("%-25s%-40s%-30s%15s\n", "Artist", "Album",
				"Song", "Times Played"));
		textArea.append(String.format("%-25s%-40s%-30s%15s\n",
				"--------------------------",
				"----------------------------------------",
				"------------------------------", "---------------"));
	}// displayReportHeadings()

}// SongReport()
