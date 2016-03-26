package cguigue_B20_A04_Final;

import java.io.IOException;

public class MusicStore
{
	private static int PLAYLIST = 16;
	private int lastSong;
	private int playableSongs;
	private Music currentPlayList[];
	private boolean isChosen[];
	private Inventory inventory;

	public MusicStore()
	{
		inventory = new Inventory();
		isChosen = new boolean[PLAYLIST];
		for (int i = 0; i < PLAYLIST; i++)
			isChosen[i] = false;
	} // PlayerControl()

	public void close()
	{
		try
		{
			inventory.rewriteMusic();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	} // close()
	
	public Music getSong()
	{
		Music music = null;
		while (true)
		{
			if (getPlayableSongs() == 0)
				break;
			for (int i = lastSong; i < playableSongs; ++i)
			{
				if (isChosen(i))
				{
					lastSong = i + 1;
					music = currentPlayList[i];
					break;
				}
			}
			if (music == null)
				lastSong = 0;
			else
				break;
		}
		return music;
	}// getSong()

	public int currentPlayList(String genre)
	{
		playableSongs = 0;
		lastSong = 0;
		currentPlayList = new Music[PLAYLIST];
		for (int i = 0; i < isChosen.length; ++i)
		{
			isChosen[i] = false;
		}
		for (int i = 0; i < inventory.getNumMusic(); ++i)
		{
			Music music = inventory.getMusicItem(i);
			if (playableSongs >= PLAYLIST)
				break;
			if (music.getGenre(music.genreCode).equalsIgnoreCase(genre))
			{
				currentPlayList[playableSongs] = music;
				++playableSongs;
			}
		}
		return playableSongs;
	} // currentPlayList(String)

	public boolean isChosen(int i)
	{
		return isChosen[i];
	}// isChosen(i)

	public void setSelected(int i)
	{
		isChosen[i] = true;
	}//isSelected(i)

	public void setNotChosen(int i)
	{
		isChosen[i] = false;
	}//setNotChosen(i)

	public Music getPlaylist(int i)
	{
		return currentPlayList[i];
	}//getPlayList

	public int getPlayableSongs()
	{
		int c = 0;
		for (int i = 0; i < isChosen.length; ++i)
		{
			if (isChosen(i))
			{
				++c;
			}
		}
		return c;
	}

} // PlayerControl
