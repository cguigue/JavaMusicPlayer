package javazoom.jlgui.basicplayer;

import java.io.File;
import java.io.PrintStream;

import java.util.Map;

import javax.swing.JProgressBar;

public class BasicPlayerControl
  implements BasicPlayerListener
{

  private BasicController control;
  private BasicPlayer player = new BasicPlayer();
  private String playList[];
  private int numSongs = 0;
  private int songIndex = 0;
  private long fileLength;
  private int bytesPlayed = 0;
  private JProgressBar progress = null;
  private boolean skipped = false;
  private boolean restarted = false;
  BasicPlayerControlInterface playerUserInterface = null;
  private static double volume = .5;

  public BasicPlayerControl()
  {
    out = System.out;
    // BasicPlayer is a BasicController.
    control = (BasicController) player;
    // Register BasicPlayerTest to BasicPlayerListener events.
    // It means that this object will be notified on BasicPlayer
    // events such as : opened(...), progress(...), stateUpdated(...)
    player.addBasicPlayerListener(this);
  }
  /*
    public BasicPlayerControl(BasicPlayerControlInterface bpci) {
            playerUserInterface = bpci;
            out = System.out;
        // BasicPlayer is a BasicController.
        control = (BasicController) player;
        // Register BasicPlayerTest to BasicPlayerListener events.
        // It means that this object will be notified on BasicPlayer
        // events such as : opened(...), progress(...), stateUpdated(...)
        player.addBasicPlayerListener(this);
    }
    */

  public void addStopListener(BasicPlayerControlInterface bpci)
  {
    playerUserInterface = bpci;
  }

  public void setProgressBar(JProgressBar p)
  {
    progress = p;
    progress.setStringPainted(true);
  }

  private PrintStream out = null;

  /**
   * Entry point.
   * @param args filename to play.

    public static void main(String[] args)
    {
            BasicPlayerTest test = new BasicPlayerTest();
    //              test.play(args[0]);
    }
   */

  public void play(String[] pplayList, int pnumSongs)
  {
    songIndex = 0;
    playList = pplayList;
    numSongs = pnumSongs;
    play(playList[songIndex++]);
  }

  public void play(String filename)
  {
    try
    {

      // Open file, or URL or Stream (shoutcast) to play.
      File file = new File(filename);
      fileLength = file.length();
      if (progress != null)
      {
        progress.setMaximum(Math.round(fileLength * 1.0f));
        progress.setValue(0);
      }
      bytesPlayed = 0;
      //                   BasicPlayer player = new BasicPlayer();
      // BasicPlayer is a BasicController.
      //                   control = (BasicController) player;
      control.open(file);
      // control.open(new URL("http://yourshoutcastserver.com:8000"));

      // Start playback in a thread.
      control.play();

      // Set Volume (0 to 1.0).
      // setGain should be called after control.play().
      control.setGain(volume);

      // Set Pan (-1.0 to 1.0).
      // setPan should be called after control.play().
      control.setPan(0.0);

      // If you want to pause/resume/pause the played file then
      // write a Swing player and just call control.pause(),
      // control.resume() or control.stop().
      // Use control.seek(bytesToSkip) to seek file
      // (i.e. fast forward and rewind). seek feature will
      // work only if underlying JavaSound SPI implements
      // skip(...). True for MP3SPI (JavaZOOM) and SUN SPI's
      // (WAVE, AU, AIFF).

    }
    catch (BasicPlayerException e)
    {
      System.out.println("file name " + filename);
      e.printStackTrace();
    }
  }

  public void stop()
  {
    try
    {
      numSongs = 0;
      control.stop();
      if (progress != null)
        progress.setValue(0);
    }
    catch (BasicPlayerException e)
    {
      e.printStackTrace();
    }
  }

  public void skip()
  {
    try
    {
      restarted = true;
      control.seek(fileLength);
    }
    catch (BasicPlayerException e)
    {
      System.out.println("song index " + songIndex + "num songs " +
                         numSongs);
      for (int i = 0; i < numSongs; i++)
        System.out.println(playList[i]);
      e.printStackTrace();
    }
  }

  public void pause()
  {
    try
    {
      control.pause();
    }
    catch (BasicPlayerException e)
    {
      e.printStackTrace();
    }
  }

  public void resume()
  {
    try
    {
      control.resume();
    }
    catch (BasicPlayerException e)
    {
      e.printStackTrace();
    }
  }

  public void restart()
  {
    try
    {
      restarted = true;
      control.seek(-bytesPlayed);

    }
    catch (BasicPlayerException e)
    {
      e.printStackTrace();
    }
  }

  public void setVolume(double vol)
  {
    try
    {
      volume = vol;
      control.setGain(volume);
    }
    catch (BasicPlayerException e)
    {
      e.printStackTrace();
    }

  }

  /**
   * Open callback, stream is ready to play.
   *
   * properties map includes audio format dependant features such as
   * bitrate, duration, frequency, channels, number of frames, vbr flag,
   * id3v2/id3v1 (for MP3 only), comments (for Ogg Vorbis), ...
   *
   * @param stream could be File, URL or InputStream
   * @param properties audio stream properties.
   */
  public void opened(Object stream, Map properties)
  {
    // Pay attention to properties. It's useful to get duration,
    // bitrate, channels, even tag such as ID3v2.
    //        display("opened : "+properties.toString());
  }

  /**
   * Progress callback while playing.
   *
   * This method is called severals time per seconds while playing.
   * properties map includes audio format features such as
   * instant bitrate, microseconds position, current frame number, ...
   *
   * @param bytesread from encoded stream.
   * @param microseconds elapsed (<b>reseted after a seek !</b>).
   * @param pcmdata PCM samples.
   * @param properties audio stream parameters.
   */
  public void progress(int bytesread, long microseconds, byte[] pcmdata,
                       Map properties)
  {
    // Pay attention to properties. It depends on underlying JavaSound SPI
    // MP3SPI provides mp3.equalizer.

    //           display("progress : "+properties.toString());
    bytesPlayed = bytesread;
    if (progress != null)
    {
      progress.setValue(bytesread);
      //                System.out.println(bytesread + " " + fileLength);
    }
  }

  /**
   * Notification callback for basicplayer events such as opened, eom ...
   *
   * @param event
   */
  public void stateUpdated(BasicPlayerEvent event)
  {
    // Notification of BasicPlayer states (opened, playing, end of media, ...)
    //      display("stateUpdated : "+event.toString()  + " song index " + songIndex);
    //stateUpdated : STOPPED:-1
    if (event.getCode() == BasicPlayerEvent.STOPPED &&
        !restarted) // && !skipped)
    {
      if (songIndex < numSongs)
      {
        //                         System.out.println("stoped, playing" + songIndex);
        play(playList[songIndex++]);
        //                       System.out.println("stoped, playing" + playList[songIndex-1]);

      }
      else
      {
        if (progress != null)
          progress.setValue(0);
        if (playerUserInterface != null)
          playerUserInterface.playerStopped();

      }

    }
    else if (event.getCode() == BasicPlayerEvent.SEEKED)
      restarted = false;
    else if (event.getCode() == BasicPlayerEvent.PLAYING)
    {
      try
      {
        control.setGain(volume);
      }
      catch (BasicPlayerException e)
      {
        e.printStackTrace();
      }
    }

  }

  /**
   * A handle to the BasicPlayer, plugins may control the player through
   * the controller (play, stop, ...)
   * @param controller : a handle to the player
   */
  public void setController(BasicController controller)
  {
    //            display("setController : "+controller);
  }

  public void display(String msg)
  {
    if (out != null)
      out.println(msg);
  }

}
