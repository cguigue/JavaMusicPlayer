package cguigue_B20_A04_Final;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class MainMenuFrame extends JFrame implements ActionListener,
		WindowListener
{
	private JMenuBar menuBar;

	private JMenu menuFile;
	private JMenuItem menuItemNew;
	private JMenuItem menuItemExit;

	private JMenu menuInventory;
	private JMenuItem menuItemReports;

	private JMenu menuMusicPlayer;
	private JMenuItem menuItemPlayer;

	private JMenu menuHelp;
	private JMenuItem menuItemAbout;

	private JPanel contentPane;
	private AddItemFrame addItemFrame;
	private InventoryFrame inventoryFrame;
	private MusicPlayer player;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainMenuFrame frame = new MainMenuFrame();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenuFrame()
	{
		setTitle("Heritage Music System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 152);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuFile = new JMenu("File");
		menuBar.add(menuFile);

		menuItemNew = new JMenuItem("New");
		menuFile.add(menuItemNew);
		menuItemNew.addActionListener(this);

		menuItemExit = new JMenuItem("Exit");
		menuFile.add(menuItemExit);
		menuItemExit.addActionListener(this);

		menuInventory = new JMenu("Inventory");
		menuBar.add(menuInventory);

		menuItemReports = new JMenuItem("Reports");
		menuInventory.add(menuItemReports);
		menuItemReports.addActionListener(this);

		menuMusicPlayer = new JMenu("Music Player");
		menuBar.add(menuMusicPlayer);

		menuItemPlayer = new JMenuItem("Player");
		menuMusicPlayer.add(menuItemPlayer);
		menuItemPlayer.addActionListener(this);

		menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
		addItemFrame = new AddItemFrame();
		menuItemAbout = new JMenuItem("About");
		menuHelp.add(menuItemAbout);
		menuItemAbout.addActionListener(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == menuItemNew)
		{
			menuItemNew_actionPerformed();
		}
		else
			if (e.getSource() == menuItemExit)
			{
				menuItemExit_actionPerformed();
			}
			else
				if (e.getSource() == menuItemReports)
				{
					menuItemReports_actionPerformed();
				}
				else
					if (e.getSource() == menuItemPlayer)
					{
						menuItemPlayer_actionPerformed();
					}
					else
						if (e.getSource() == menuItemAbout)
						{
							menuItemAbout_actionPerformed();
						}

	}

	public void menuItemNew_actionPerformed()
	{
		addItemFrame = new AddItemFrame();
		addItemFrame.setVisible(true);
		addItemFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void menuItemExit_actionPerformed()
	{
		System.exit(0);
	}

	public void menuItemReports_actionPerformed()
	{
		inventoryFrame = new InventoryFrame();
		inventoryFrame.setVisible(true);
		inventoryFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void menuItemPlayer_actionPerformed()
	{
		player = new MusicPlayer();
		player.setVisible(true);
		player.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void menuItemAbout_actionPerformed()
	{
		JOptionPane.showMessageDialog(this, new AboutPanel(), "About",
				JOptionPane.PLAIN_MESSAGE);	}

	public void windowActivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	public void windowClosed(WindowEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	public void windowClosing(WindowEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	public void windowDeactivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	public void windowDeiconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	public void windowIconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	public void windowOpened(WindowEvent arg0)
	{
		// TODO Auto-generated method stub

	}

}
