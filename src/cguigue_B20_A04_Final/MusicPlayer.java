package cguigue_B20_A04_Final;

import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Color;
import java.awt.event.*;
import javazoom.jlgui.basicplayer.*;
import java.awt.Font;

public class MusicPlayer extends JFrame implements ItemListener,
		ChangeListener, ActionListener, BasicPlayerControlInterface
{
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmExit;
	private JMenu mnHelp;
	private JMenuItem mntmAbout;
	private JPanel genrePanel;
	private JPanel playListPanel;
	private JProgressBar songProgress;
	private JSlider volSlider;
	private JLabel lblCurrentSong;
	private JLabel lblNeg;
	private JLabel labelPos;

	private ButtonGroup genreGroup;
	private JPanel btnPanel;
	private String btnLabels[] = { "\u21A0", "\u21DD", "||", "\u25A0", "|\u25BA",
			"\u21BA" };
	private JButton btnArr[] = new JButton[7];
	private final int SKIP = 0;
	private final int PLAY = 1;
	private final int PAUSE = 2;
	private final int STOP = 3;
	private final int RESUME = 4;
	private final int REWIND = 5;

	private static String genres[] = new String[Music.genreName.length - 1];
	private JRadioButton rdBtnGenre[] = new JRadioButton[genres.length];
	private JCheckBox chkBox[] = new JCheckBox[10];
	private BasicPlayerControl musicPlayer;
	private MusicStore store;
	private int currentStatus;
	private Music songPlaying;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MusicPlayer frame = new MusicPlayer();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public MusicPlayer()
	{
		store = new MusicStore();
		musicPlayer = new BasicPlayerControl();
		musicPlayer.setVolume(0.25);
		musicPlayer.addStopListener(this);

		setTitle("Heritage Music Player");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);

		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 484, 21);
		contentPane.add(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(this);

		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		mntmAbout.addActionListener(this);

		genrePanel = new JPanel();
		genrePanel.setBorder(null);
		genrePanel.setBounds(10, 32, 464, 106);
		contentPane.add(genrePanel);
		genrePanel.setLayout(null);

		playListPanel = new JPanel();
		playListPanel.setBounds(10, 141, 464, 185);
		contentPane.add(playListPanel);
		playListPanel.setLayout(null);

		songProgress = new JProgressBar();
		songProgress.setForeground(new Color(0, 0, 139));
		songProgress.setBounds(10, 337, 464, 21);
		contentPane.add(songProgress);
		songProgress.setStringPainted(true);
		musicPlayer.setProgressBar(songProgress);

		lblNeg = new JLabel("-");
		lblNeg.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNeg.setBounds(20, 360, 26, 14);
		contentPane.add(lblNeg);

		labelPos = new JLabel("+");
		labelPos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelPos.setBounds(448, 362, 26, 14);
		contentPane.add(labelPos);

		volSlider = new JSlider();
		volSlider.setBounds(30, 362, 415, 23);
		contentPane.add(volSlider);
		volSlider.addChangeListener(this);
		

		lblCurrentSong = new JLabel("");
		lblCurrentSong.setBounds(0, 441, 484, 21);
		contentPane.add(lblCurrentSong);

		btnPanel = new JPanel();
		btnPanel.setBounds(10, 393, 464, 44);
		contentPane.add(btnPanel);

		for (int i = 0; i < btnLabels.length; ++i)
		{
			btnArr[i] = new JButton(btnLabels[i]);
			btnArr[i].addActionListener(this);
			btnPanel.add(btnArr[i]);
		}// for

		genrePanel.setBorder(BorderFactory.createTitledBorder("Genre"));
		genrePanel.setLayout(new GridLayout(3, 3));
		genreGroup = new ButtonGroup();
		for (int i = 0; i < genres.length; i++)
		{
			rdBtnGenre[i] = new JRadioButton(Music.genreName[i + 1]);
			rdBtnGenre[i].addItemListener(this);
			genrePanel.add(rdBtnGenre[i]);
			genreGroup.add(rdBtnGenre[i]);
		}

		playListPanel.setBorder(BorderFactory.createTitledBorder("Songs"));
		playListPanel.setLayout(new GridLayout(8, 2));
		for (int i = 0; i < 10; i++)
		{
			chkBox[i] = new JCheckBox();
			chkBox[i].addChangeListener(this);
			playListPanel.add(chkBox[i]);
			chkBox[i].setVisible(false);
		}

		btnArr[RESUME].setEnabled(false);
		btnArr[PLAY].setEnabled(true);
		btnArr[PAUSE].setEnabled(false);
		btnArr[RESUME].setEnabled(false);
		btnArr[SKIP].setEnabled(false);
		btnArr[STOP].setEnabled(false);
	} // MusicPlayerFrame()

	private void close()
	{
		musicPlayer.stop();
		currentStatus = 3;
		store.close();
		this.dispose();
	} // close()

	public void itemStateChanged(ItemEvent e)
	{
		int index = -1;
		for (int i = 0; i < rdBtnGenre.length; i++)
		{
			if (e.getSource() == rdBtnGenre[i])
				index = i;
		}
		showCurrentGenre(rdBtnGenre[index].getText());
	} // itemStateChanged()

	public void stateChanged(ChangeEvent e)
	{
		if (e.getSource() == volSlider)
		{
			musicPlayer.setVolume((double) volSlider.getValue()
					/ volSlider.getMaximum());
		}
		else
		{
			for (int i = 0; i < chkBox.length; ++i)
			{
				if (chkBox[i].isSelected())
					store.setSelected(i);
				else
					store.setNotChosen(i);
			}
		}
	} // stateChanged()

	private void showCurrentGenre(String currentGenre)
	{
		int count = store.currentPlayList(currentGenre);
		for (int i = 0; i < chkBox.length; i++)
		{
			chkBox[i].setSelected(false);
			chkBox[i].setVisible(false);
		}
		for (int i = 0; i < count; i++)
		{
			chkBox[i].setVisible(true);
			chkBox[i].setText(store.getPlaylist(i).getName());
		}
	} // showCurrentGenre()

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnArr[RESUME])
		{
			btnResume_actionPerformed();
		}
		else
			if (e.getSource() == btnArr[PLAY])
			{
				btnPlay_actionPerformed();
			}
			else
				if (e.getSource() == btnArr[PAUSE])
				{
					btnPause_actionPerformed();
				}
				else
					if (e.getSource() == btnArr[RESUME])
					{
						btnResume_actionPerformed();
					}
					else
						if (e.getSource() == btnArr[SKIP])
						{
							btnSkip_actionPerformed();
						}
						else
							if (e.getSource() == btnArr[STOP])
							{
								btnStop_actionPerformed();
							}
							else
								if (e.getSource() == btnArr[REWIND])
								{
									btnRewind_actionPerformed();
								}
								else
									if (e.getSource() == mntmExit)
									{
										mntmExit_actionPerformed();
									}
									else
										if (e.getSource() == mntmAbout)
										{
											mntmAbout_actionPerformed();
										}
	} // actionPerformed(ActionEvent)

	private void mntmAbout_actionPerformed()
	{
		JOptionPane.showMessageDialog(this, new AboutPanel(), "About",
				JOptionPane.PLAIN_MESSAGE);
	}

	private void mntmExit_actionPerformed()
	{

		close();

	}

	private void btnPlay_actionPerformed()
	{
		playNextSong();
		enableGenreList(false);
		btnArr[RESUME].setEnabled(true);
		btnArr[PLAY].setEnabled(false);
		btnArr[PAUSE].setEnabled(true);
		btnArr[RESUME].setEnabled(false);
		btnArr[SKIP].setEnabled(true);
		btnArr[STOP].setEnabled(true);
		currentStatus = 1;
		updateStatusBar();
	}

	private void btnStop_actionPerformed()
	{
		currentStatus = 3;
		enableGenreList(true);
		btnArr[PLAY].setEnabled(true);
		btnArr[PAUSE].setEnabled(false);
		btnArr[RESUME].setEnabled(false);
		btnArr[SKIP].setEnabled(false);
		btnArr[STOP].setEnabled(false);
		musicPlayer.stop();
		updateStatusBar();
	}

	private void btnResume_actionPerformed()
	{
		musicPlayer.resume();
		btnArr[RESUME].setEnabled(true);
		btnArr[PLAY].setEnabled(false);
		btnArr[PAUSE].setEnabled(true);
		btnArr[RESUME].setEnabled(false);
		btnArr[SKIP].setEnabled(true);
		btnArr[STOP].setEnabled(true);
		currentStatus = 4;
		updateStatusBar();
	}

	private void btnPause_actionPerformed()
	{
		musicPlayer.pause();
		btnArr[RESUME].setEnabled(false);
		btnArr[PLAY].setEnabled(false);
		btnArr[PAUSE].setEnabled(false);
		btnArr[RESUME].setEnabled(true);
		btnArr[SKIP].setEnabled(false);
		btnArr[STOP].setEnabled(true);
		currentStatus = 2;
		updateStatusBar();
	}

	private void btnSkip_actionPerformed()
	{
		btnArr[RESUME].setEnabled(true);
		btnArr[PLAY].setEnabled(false);
		btnArr[PAUSE].setEnabled(true);
		btnArr[RESUME].setEnabled(false);
		btnArr[SKIP].setEnabled(true);
		btnArr[STOP].setEnabled(true);
		currentStatus = 0;
		musicPlayer.stop();
		playNextSong();
	}

	private void btnRewind_actionPerformed()
	{
		musicPlayer.restart();
		btnArr[PLAY].setEnabled(false);
		btnArr[PAUSE].setEnabled(true);
		btnArr[RESUME].setEnabled(false);
		btnArr[SKIP].setEnabled(true);
		btnArr[STOP].setEnabled(true);
		currentStatus = 5;
		updateStatusBar();
	}

	private void playNextSong()
	{
		songPlaying = store.getSong();
		if (songPlaying != null)
		{
			String current = "mp3Files/" + songPlaying.getMusicFilename();
			musicPlayer.play(current);
			currentStatus = 1;
		}
		else
		{
			btnArr[PLAY].setEnabled(true);
			currentStatus = 3;
		}
		updateStatusBar();
	} // playNextSong()

	private void updateStatusBar()
	{
		if (songPlaying != null)
		{
			if (currentStatus == 0)
			{
				lblCurrentSong.setText(songPlaying.getName() + " by"
						+ songPlaying.getArtist() + " was skipped");
			}
			else
				if (currentStatus == 1)
				{
					lblCurrentSong.setText(songPlaying.getName() + " by "
							+ songPlaying.getArtist() + " is now playing.");
				}
				else
					if (currentStatus == 2)
					{
						lblCurrentSong.setText(songPlaying.getName() + " by "
								+ songPlaying.getArtist() + " is on pause.");
					}
					else
						if (currentStatus == 3)
						{
							lblCurrentSong.setText(songPlaying.getName() + " by "
									+ songPlaying.getArtist() + " is stopped.");
						}
						else
							if (currentStatus == 4)
							{
								lblCurrentSong.setText(songPlaying.getName() + " by "
										+ songPlaying.getArtist() + " is now playing.");
							}
							else
								if (currentStatus == 5)
								{
									lblCurrentSong.setText(songPlaying.getName() + " by "
											+ songPlaying.getArtist() + " is now playing.");
									
								}
		}
	} // updateStatusBar()

	public void playerStopped()
	{
		if (currentStatus == 1 || currentStatus == 4)
		{
			songPlaying.incrementTimesPlayed();
		}
		if (currentStatus != 2 && currentStatus != 3)
		{
			playNextSong();
		}
	} // playerStopped()

	public void enableGenreList(boolean set)
	{
		for (int i = 0; i < rdBtnGenre.length; i++)
			rdBtnGenre[i].setEnabled(set);
		for (int j = 0; j < chkBox.length; j++)
			chkBox[j].setEnabled(set);
	} // enableGenreList()
} // MusicPlayerFrame