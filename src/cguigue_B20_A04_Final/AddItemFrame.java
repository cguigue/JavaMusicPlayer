package cguigue_B20_A04_Final;


import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AddItemFrame extends JFrame implements WindowListener,
		ActionListener
{

	protected char itemTypeFlag;
	protected Inventory inventory;
	private Item item;
	private JPanel contentPane;
	private JTextField fldName;
	private JTextField fldCompany;
	private JTextField fldPrice;
	private JTextField fldMusicFileName;
	private JTextField fldArtist;
	private JTextField fldAlbum;
	private JTextField fldModel;
	private JTextField fldColour;
	private JTextField fldQuantity;
	private JComboBox cmbxGenre = new JComboBox();
	private JLabel lblColour = new JLabel("Colour:");
	private JLabel lblQuantity = new JLabel("Quantity:");
	private JLabel lblArtist = new JLabel("Artist:");
	private JLabel lblGenre = new JLabel("Genre:");
	private JLabel lblAlbum = new JLabel("Album:");
	private JLabel lblModel = new JLabel("Model:");
	private JLabel lblMusicFilename = new JLabel("Music Filename:");
	private JLabel lblPrice = new JLabel("Price:");
	private JLabel lblName = new JLabel("Name:");
	private JLabel lblCompany = new JLabel("Company:");
	private JButton btnNewMusic = new JButton("New Music");
	private JButton btnNewProduct = new JButton("New Product");
	private JButton btnExit = new JButton("Exit");
	private JButton btnSubmit = new JButton("Submit");
	private JButton btnClear = new JButton("Clear");

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
					AddItemFrame frame = new AddItemFrame();
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
	public AddItemFrame()
	{
		setTitle("Heritage Online Music Store - Add Item");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 465, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		inventory = new Inventory();

		lblName.setBounds(27, 11, 41, 14);
		contentPane.add(lblName);

		fldName = new JTextField();
		fldName.setBounds(67, 8, 336, 20);
		contentPane.add(fldName);
		fldName.setColumns(10);

		lblCompany.setBounds(9, 42, 59, 14);
		contentPane.add(lblCompany);

		fldCompany = new JTextField();
		fldCompany.setBounds(67, 39, 336, 20);
		contentPane.add(fldCompany);
		fldCompany.setColumns(10);

		lblPrice.setBounds(31, 73, 37, 14);
		contentPane.add(lblPrice);

		fldPrice = new JTextField();
		fldPrice.setBounds(67, 70, 86, 20);
		contentPane.add(fldPrice);
		fldPrice.setColumns(10);

		btnNewMusic.setBounds(52, 117, 118, 23);
		contentPane.add(btnNewMusic);
		btnNewMusic.addActionListener(this);

		btnNewProduct.setBounds(236, 117, 118, 23);
		contentPane.add(btnNewProduct);
		btnNewProduct.addActionListener(this);

		lblMusicFilename.setBounds(22, 151, 101, 14);
		contentPane.add(lblMusicFilename);

		lblModel.setBounds(236, 151, 46, 14);
		contentPane.add(lblModel);

		fldMusicFileName = new JTextField();
		fldMusicFileName.setBounds(22, 167, 199, 20);
		contentPane.add(fldMusicFileName);
		fldMusicFileName.setColumns(10);

		lblArtist.setBounds(22, 189, 46, 14);
		contentPane.add(lblArtist);

		fldArtist = new JTextField();
		fldArtist.setBounds(22, 207, 199, 20);
		contentPane.add(fldArtist);
		fldArtist.setColumns(10);

		lblAlbum.setBounds(22, 229, 46, 14);
		contentPane.add(lblAlbum);

		fldAlbum = new JTextField();
		fldAlbum.setBounds(22, 249, 199, 20);
		contentPane.add(fldAlbum);
		fldAlbum.setColumns(10);

		fldModel = new JTextField();
		fldModel.setBounds(236, 167, 199, 20);
		contentPane.add(fldModel);
		fldModel.setColumns(10);

		lblGenre.setBounds(22, 272, 46, 14);
		contentPane.add(lblGenre);

		lblColour.setBounds(236, 189, 46, 14);
		contentPane.add(lblColour);

		lblQuantity.setBounds(236, 229, 59, 14);
		contentPane.add(lblQuantity);

		fldColour = new JTextField();
		fldColour.setBounds(236, 207, 199, 20);
		contentPane.add(fldColour);
		fldColour.setColumns(10);

		fldQuantity = new JTextField();
		fldQuantity.setBounds(236, 249, 199, 20);
		contentPane.add(fldQuantity);
		fldQuantity.setColumns(10);

		cmbxGenre.setModel(new DefaultComboBoxModel(new String[] {"Select a Genre", "Blues", "Classical", "Country", "Folk", "Jazz", "R&B/Soul", "Rap/Hip-Hop", "Rock", "Pop", "Other"}));
		cmbxGenre.setBounds(22, 290, 199, 20);
		contentPane.add(cmbxGenre);

		btnSubmit.setBounds(34, 333, 89, 23);
		contentPane.add(btnSubmit);
		btnSubmit.addActionListener(this);

		btnClear.setBounds(177, 333, 89, 23);
		contentPane.add(btnClear);
		btnClear.addActionListener(this);

		btnExit.setBounds(303, 333, 89, 23);
		contentPane.add(btnExit);
		btnExit.addActionListener(this);

		enableNewItemFields(true);
		enableProductFields(false);
		enableMusicFields(false);

		try
		{
			Item.readNextItemNumber();
		}
		catch (FileNotFoundException e)
		{

			if (JOptionPane.showConfirmDialog(this,
					"Do you want to initialize the next number?",
					"Number initialization error", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
			{
				System.exit(-1);
			}
			else
			{
				int nextNumber = Integer.parseInt(JOptionPane.showInputDialog(this,
						"Please enter the next item number:", "Input next item number",
						JOptionPane.PLAIN_MESSAGE));
				Item.setNextItemNumber(nextNumber);
			}
		}
	}

	public void actionPerformed(ActionEvent e)
	{

		if (e.getSource() == btnExit)
		{
			btnExit_actionPerformed();
		}
		if (e.getSource() == btnNewMusic)
		{
			btnNewMusic_actionPerformed();
		}
		if (e.getSource() == btnNewProduct)
		{
			btnNewProduct_actionPerformed();
		}
		if (e.getSource() == btnClear)
		{
			clearInputFields();
		}
		if (e.getSource() == btnSubmit)
		{
			btnSubmit_actionPerformed();
		}

	}// action performed

	private void btnExit_actionPerformed()
	{
		try
		{
			Item.rewriteNextItemNumber();
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(this,
					"Could not write item number to file", "Number file error",
					JOptionPane.ERROR_MESSAGE);
		}
		this.dispose();
	} // btnExit action performed

	private void btnSubmit_actionPerformed()
	{
		if (validateInput())
		{
			boolean created = false;
			if (itemTypeFlag == 'M')
			{
				created = createMusicItem();
			}

			if (itemTypeFlag == 'P')
			{
				created = createProductItem();
			}
			if (created)
			{
				inventory.write(item);
				clearInputFields();
				// add meesage here to say you successfully created your item!********************
			}
		}

	} // btnSubmit action performed

	private void btnNewProduct_actionPerformed()
	{
		if (validateInput())
		{
			btnNewProduct.setEnabled(false);
			btnNewMusic.setEnabled(true);
			fldMusicFileName.setText("");
			fldArtist.setText("");
			fldAlbum.setText("");
			//	cmbxGenre.setModel(default);  Can I set the combo box back to default??? ******************
			enableMusicFields(false);
			enableProductFields(true);
			itemTypeFlag = 'P';
		}
	} // btnNewProduct action performed

	private void btnNewMusic_actionPerformed()
	{
		if (validateInput())
		{
			btnNewMusic.setEnabled(false);
			btnNewProduct.setEnabled(true);
			fldModel.setText("");
			fldColour.setText("");
			fldQuantity.setText("");
			enableProductFields(false);
			enableMusicFields(true);
			itemTypeFlag = 'M';
		}

	} // btnNewMusic action performed

	public void clearInputFields()
	{
		fldName.setText("");
		fldCompany.setText("");
		fldPrice.setText("");
		fldMusicFileName.setText("");
		fldArtist.setText("");
		fldAlbum.setText("");
		fldModel.setText("");
		fldColour.setText("");
		fldQuantity.setText("");
		enableMusicFields(false);
		enableProductFields(false);
		enableNewItemFields(true);
		cmbxGenre.setSelectedIndex(0);

	} // clearInputFields()

	public boolean createMusicItem()
	{
		if (validateMusicInputFields())
		{
			item = new Music(fldName.getText().toString(), fldCompany.getText()
					.toString(), Double.parseDouble(fldPrice.getText()),
					fldMusicFileName.getText(), fldAlbum.getText(), fldArtist.getText(),
					cmbxGenre.getSelectedItem().toString());
			return true;
		}
		return false;

	} // createMusicItem()

	public boolean createProductItem()
	{
		if (validateProductFields())
		{
			item = new Product(fldName.getText(), fldCompany.getText(),
					Double.parseDouble(fldPrice.getText()), fldModel.getText(), fldColour
							.getText().toLowerCase(), Integer.parseInt(fldQuantity.getText()));
			return true;
		}
		return false;

	}// createProductItem

	public boolean validateInput()
	{

		String price = fldPrice.getText().trim();

		if (fldName.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "You must enter a name.",
					"Name missing", JOptionPane.OK_OPTION);
			return false;
		}

		if (fldCompany.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "You must enter a company.",
					"Company missing", JOptionPane.OK_OPTION);
			return false;
		}

		if (fldPrice.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "You must enter a price.",
					"Price missing", JOptionPane.OK_OPTION);
			return false;
		}
		else
		{
			int numPeriods = 0;
			for (int count = 0; count < price.length(); ++count)
			{
				if (!Character.isDigit(price.charAt(count)))
				{
					if (price.charAt(count) == '.')
					{
						++numPeriods;
					}
					if (price.charAt(count) != '.' || numPeriods > 1)
					{
						JOptionPane.showMessageDialog(this,
								"Price must be a number that has max 1 period", "Price error",
								JOptionPane.OK_OPTION);
						fldPrice.setText("");
						return false;
					}

				}

			}

		}

		return true;

	}// validateInput()

	public boolean validateMusicInputFields()
	{

		if (fldMusicFileName.getText().isEmpty())
		{
			if (JOptionPane.showConfirmDialog(this,
					"No file name was entered, set to name.mp3?", "Filename missing",
					JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
				return false;
			else
			{
				fldMusicFileName.setText("name.mp3");
				return false;
			}
		}
		if (fldArtist.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "You must enter an artist",
					"Artist name Missing", JOptionPane.OK_OPTION);
			return false;
		}
		else
			if (fldAlbum.getText().isEmpty())
			{
				if (JOptionPane.showConfirmDialog(this,
						"No album name was entered, set it to N/A?", "Album name missing",
						JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
				{
					return false;
				}
				else
				{
					fldAlbum.setText("n/a");
				}
			}
			else
				if (fldMusicFileName.getText().indexOf('.') == -1)
				{
					if (JOptionPane
							.showConfirmDialog(
									this,
									"Music file name has no extension, would you like to set it to .mp3?",
									"Extension Missing", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
					{
						return false;
					}
					else
						fldMusicFileName.setText(fldMusicFileName.getText() + ".mp3");
				}

		return true;

	} // validateMusicInputFields()

	public boolean validateProductFields()
	{
		String quantity = fldQuantity.getText().trim();
		String colour = fldColour.getText().trim();

		if (fldModel.getText().isEmpty())
		{
			if (JOptionPane.showConfirmDialog(this,
					"No model was entered, set it to N/A?", "Model missing",
					JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
			{
				return false;
			}
			else
			{
				fldModel.setText("n/a");
				return false;
			}
		}

		if (fldColour.getText().isEmpty())
		{
			if (JOptionPane.showConfirmDialog(this,
					"No colour was entered, set it to black?", "Colour missing",
					JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)

			{
				return false;
			}
			else
			{
				fldColour.setText("black");
			}
		}

		if (fldQuantity.getText().isEmpty())
		{
			if (JOptionPane.showConfirmDialog(this,
					"No quantity was entered, set it to 1?", "Quantity missing",
					JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
			{
				return false;
			}
			else
			{
				fldQuantity.setText("1");
			}
		}
		for (int count = 0; count < colour.length(); ++count)
		{
			if (!Character.isAlphabetic(colour.charAt(count)))
			{
				JOptionPane.showMessageDialog(this, "Colour should be alphabetic",
						"Colour error", JOptionPane.OK_OPTION);
				fldColour.setText("");
				return false;
			}
		}
		for (int count = 0; count < quantity.length(); ++count)
		{

			if (!Character.isDigit(quantity.charAt(count)))
			{
				JOptionPane.showMessageDialog(this, "Quantity must be an integer",
						"Quantity error", JOptionPane.OK_OPTION);
				fldQuantity.setText("");
				return false;
			}
		}
		return true;

	}// validateProductFields

	public void writeItemToFile()
	{

		inventory.write(item);

	} // writeItemToFile()

	public void enableMusicFields(boolean enabled)
	{

		lblMusicFilename.setEnabled(enabled);
		fldMusicFileName.setEnabled(enabled);
		lblArtist.setEnabled(enabled);
		fldArtist.setEnabled(enabled);
		lblAlbum.setEnabled(enabled);
		fldAlbum.setEnabled(enabled);
		lblGenre.setEnabled(enabled);
		cmbxGenre.setEnabled(enabled);
		btnSubmit.setEnabled(enabled);

	}// enableMusicFields

	public void enableProductFields(boolean enabled)
	{

		lblModel.setEnabled(enabled);
		fldModel.setEnabled(enabled);
		lblColour.setEnabled(enabled);
		fldColour.setEnabled(enabled);
		lblQuantity.setEnabled(enabled);
		fldQuantity.setEnabled(enabled);
		btnSubmit.setEnabled(enabled);
		cmbxGenre.setSelectedIndex(0);

	}// enableProductFields

	public void enableNewItemFields(boolean enabled)
	{

		btnSubmit.setEnabled(!enabled);
		btnNewMusic.setEnabled(enabled);
		btnNewProduct.setEnabled(enabled);

	}// enableNewitemFields()

	public void windowActivated(WindowEvent arg0)
	{

	}

	public void windowClosed(WindowEvent arg0)
	{

	}

	public void windowClosing(WindowEvent arg0)
	{

		btnExit_actionPerformed();

	}

	public void windowDeactivated(WindowEvent arg0)
	{

	}

	public void windowDeiconified(WindowEvent arg0)
	{

	}

	public void windowIconified(WindowEvent arg0)
	{

	}

	public void windowOpened(WindowEvent arg0)
	{

	}

}
