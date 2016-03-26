package cguigue_B20_A04_Final;


/*
 * Author : Christopher Guigue
 * For : Programming Assignment 2
 *  
 */

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import CSClasses.Heading;

public class InventoryFrame extends JFrame implements ActionListener,
		WindowListener
{
	private Inventory inventory;
	private Item item;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu menuReports;
	private JMenu menuFile;
	
	private JMenuItem menuItemExit;
	private JMenu menuSearch;
	private JScrollPane scrollPane;
	private JMenuItem menuItemListMusicFrequency;
	private JMenuItem menuItemFindItem;
	private String fldItemNumber;
	private JMenuItem menuItemListMusic;
	private JMenuItem menuItemListAllItems;
	private JMenuItem menuItemListProducts;
	private JTextArea textArea;
	private SongReport musicReport;
	NumberFormat currency = NumberFormat.getCurrencyInstance();

	/*
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
					InventoryFrame frame = new InventoryFrame();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Create the frame.
	 */
	public InventoryFrame()
	{
		setTitle("Inventory Reports");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 590);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(0, 0, 820, 531);
		contentPane.add(textArea);
		textArea.setEditable(false);
		textArea.setFont(new Font("Courier New", 0, 11));

		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(0, 0, 820, 531);
		contentPane.add(scrollPane);
		inventory = new Inventory();
		musicReport = new SongReport(textArea);
		
		// Create Menu Bar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);		
			menuFile = new JMenu("File");
			menuBar.add(menuFile);
				menuItemExit = new JMenuItem("Exit");
				menuFile.add(menuItemExit);
			menuSearch = new JMenu("Search");
			menuBar.add(menuSearch);
				menuItemFindItem = new JMenuItem("Find Item");
				menuSearch.add(menuItemFindItem);
			menuReports = new JMenu("Reports");
			menuBar.add(menuReports);
				menuItemListAllItems = new JMenuItem("List All Items");
				menuReports.add(menuItemListAllItems);
				menuItemListMusic = new JMenuItem("List Music");
				menuReports.add(menuItemListMusic);
				menuItemListProducts = new JMenuItem("List Products");
				menuReports.add(menuItemListProducts);
				menuItemListMusicFrequency = new JMenuItem("List Music Frequency");
				menuReports.add(menuItemListMusicFrequency);
	
	// Implement Action Listeners
		menuItemListAllItems.addActionListener(this);
		menuItemListMusic.addActionListener(this);
		menuItemListProducts.addActionListener(this);
		menuItemFindItem.addActionListener(this);
		menuItemListMusicFrequency.addActionListener(this);
	
	}// InventoryFrame()

	public void actionPerformed(ActionEvent e)
	{

		if (e.getSource() == menuItemListAllItems)
		{
			menuItemListAllItems_actionPerformed();
		}
		if (e.getSource() == menuItemListMusic)
		{
			menuItemListMusic_actionPerformed();
		}
		if (e.getSource() == menuItemListProducts)
		{
			menuItemListProducts_actionPerformed();
		}
		if (e.getSource() == menuItemFindItem)
		{
			fldDisplayItem_actionPerformed();
		}
		if (e.getSource() == menuItemListMusicFrequency)
		{
			musicReport.displaySongReport();
		}
	}// Action Performed

	private void menuItemListAllItems_actionPerformed()
	{
		if (inventory.getNumMusic() == 0)
		{
			textArea.setText("No Music was found");
		}
		else
		{

			displayAllItemHeadings();
			for (int i = 0; i < inventory.getNumMusic(); ++i)
			{
				item = inventory.getMusicItem(i);
				displayDetailLine(item);
			}
		}

		if (inventory.getNumProducts() == 0)
		{
			textArea.setText("No products were found");
		}
		else
		{
			for (int i = 0; i < inventory.getNumProducts(); ++i)
			{
				item = inventory.getProductItem(i);
				displayDetailLine(item);
			}
		}

	} // List All Items buttom Action Performed ()

	private void menuItemListMusic_actionPerformed()
	{
		if (inventory.getNumMusic() == 0)
		{
			textArea.setText("No Music was found");
		}
		else
		{

			displayMusicHeadings();
			for (int i = 0; i < inventory.getNumMusic(); ++i)
			{
				item = inventory.getMusicItem(i);
				displayMusicDetailLine(item);
			}
		}
	} // Music button Action Performed

	private void menuItemListProducts_actionPerformed()
	{
		if (inventory.getNumProducts() == 0)
		{
			textArea.setText("No products were found");
		}
		else
		{

			displayProductHeadings();
			for (int i = 0; i < inventory.getNumProducts(); ++i)
			{
				item = inventory.getProductItem(i);
				displayProductDetailLine(item);
			}
		}
	} // Products button Action Performed

	private void fldDisplayItem_actionPerformed()
	{
		 fldItemNumber = JOptionPane.showInputDialog(this,
				"Enter product code:", "Find Product", JOptionPane.QUESTION_MESSAGE);
//	if(fldItemNumber = JOptionPane.OK_OPTION)	 { *** if OK option is pressed**
		if (fldItemNumber.charAt(0) == 'M'
				|| fldItemNumber.charAt(0) == 'P')
		{

			if (inventory.find(fldItemNumber) != null)
			{
				item = inventory.find(fldItemNumber);

				if (item.getItemType() == 'M')
				{
					displayMusicItem((Music) item);
				}
				if (item.getItemType() == 'P')
				{
					displayProductItem((Product) item);
				}
			}
			else
			{
				if (fldItemNumber.charAt(0) == 'P')
				{
					JOptionPane.showMessageDialog(this,
							"Product item " + fldItemNumber + " was not found",
							"Invalid Item Number", JOptionPane.OK_OPTION);
					fldItemNumber = "";
				}
				else
					if (fldItemNumber.charAt(0) == 'M')
					{
						JOptionPane.showMessageDialog(this,
								"Music item " + fldItemNumber + " was not found",
								"Invalid Item Number", JOptionPane.OK_OPTION);
						fldItemNumber = "";
					}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Item " + fldItemNumber
					+ " is not a valid item number. It must start with M or P.",
					"Invalid Item Number", JOptionPane.OK_OPTION);
			fldItemNumber = "";
		}
	}
	// }*** end if for OK option

	private void displayAllItemHeadings()
	{
		
		Heading heading = new Heading("Inventory~---------", 110);
		textArea.setText(heading.getHeading());
		textArea.append(String.format("%-10s%-10s%-40s%-40s%10s\n", "Type", "Item",
				"Name", "Artist/Model", "Price"));
		textArea.append(String.format("%-10s%-10s%-40s%-40s%10s\n", "----------",
				"----------", "----------------------------------------",
				"----------------------------------------", "----------"));

	}

	private void displayDetailLine(Item item)
	{
		if (item.getItemClass() == "Music")
		{
			Music music = ((Music) item);

			textArea.append(String.format("%-10s%-10s%-40s%-40s%10s\n",
					item.getItemClass(), item.getItemNumber(), item.getName(),
					music.getArtist(), currency.format(item.getPrice())));
		}

		if (item.getItemClass() == "Product")
		{
			Product product = ((Product) item);

			textArea.append(String.format("%-10s%-10s%-40s%-40s%10s\n",
					item.getItemClass(), item.getItemNumber(), item.getName(),
					product.getModel(), currency.format(item.getPrice())));
		}
	}

	private void displayMusicHeadings()
	{
		Heading heading = new Heading("Music Inventory~---------------", 110);
		textArea.setText(heading.getHeading());
		textArea.append(String.format("%-10s%-15s%-30s%-40s%10s\n", "Item",
				"Genre", "Artist", "Title", "Price"));
		textArea.append(String.format("%-10s%-15s%-30s%-40s%10s\n", "----------",
				"---------------", "------------------------------",
				"----------------------------------------", "----------"));

	}

	private void displayMusicDetailLine(Item item)
	{
		Music music = ((Music) item);

		textArea.append(String.format("%-10s%-15s%-30s%-40s%10s\n",
				item.getItemNumber(), music.getGenre(music.getGenreCode()),
				music.getArtist(), item.getName(), currency.format(item.getPrice())));
	}

	private void displayProductHeadings()
	{

		Heading heading = new Heading("Product Inventory~-----------------", 110);
		textArea.setText(heading.getHeading());
		textArea.append(String.format("%-10s%-40s%-25s%-15s%10s%15s\n", "Item",
				"Name", "Model", "Colour", "Quantity", "Price"));
		textArea.append(String.format("%-10s%-40s%-25s%-15s%10s%15s\n",
				"----------", "----------------------------------------",
				"-------------------------", "---------------", "----------",
				"---------------"));

	}

	private void displayProductDetailLine(Item item)
	{
		Product product = ((Product) item);
		textArea.append(String.format("%-10s%-40s%-25s%-15s%10s%15s\n",
				item.getItemNumber(), item.getName(), product.getModel(),
				product.getColour(), product.getQuantity(),
				currency.format(item.getPrice())));

	}

	private void displayMusicItem(Music music)
	{

		textArea.setText(String.format("%16s%-20s\n", " ", "Music"));
		textArea.append(String.format("%16s%-20s\n\n", " ", "-----"));
		textArea.append(String.format("%-20s%-30s\n", "Item Number:",
				music.getItemNumber()));
		textArea
				.append(String.format("%-20s%-30s\n", "Artist:", music.getArtist()));
		textArea.append(String.format("%-20s%-30s\n", "Title:",
				((Item) music).getName()));
		textArea.append(String.format("%-20s%-30s\n", "Album:", music.getAlbum()));
		textArea.append(String.format("%-20s%-30s\n", "Genre:",
				music.getGenre(music.genreCode)));
		textArea.append(String.format("%-20s%-30s\n", "Company:",
				((Item) music).getCompanyName()));
		textArea.append(String.format("%-20s%-30s\n", "Price:",
				currency.format(music.getPrice())));
		textArea.append(String.format("%-20s%-30s\n", "Music File:",
				music.getFileName()));
	}

	private void displayProductItem(Product product)
	{
		textArea.setText(String.format("%16s%-20s\n", " ", "Product"));
		textArea.append(String.format("%16s%-20s\n\n", " ", "-------"));
		textArea.append(String.format("%-20s%-30s\n", "Item Number:",
				product.getItemNumber()));
		textArea.append(String.format("%-20s%-30s\n", "Company:",
				((Item) product).getCompanyName()));
		textArea.append(String.format("%-20s%-30s\n", "Product:",
				((Item) product).getName()));
		textArea
				.append(String.format("%-20s%-30s\n", "Model:", product.getModel()));
		textArea.append(String.format("%-20s%-30s\n", "Colour:",
				product.getColour()));
		textArea
				.append(String.format("%-20s%-30s\n", "Price:", product.getPrice()));
		textArea.append(String.format("%-20s%-30s\n", "Quantity on hand:",
				product.getQuantity()));
	}

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
