package cguigue_B20_A04_Final;


import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class AboutPanel extends JPanel
{
	private JLabel lblTitle;
	private GridBagLayout gridBagLayout;
	private JLabel lblAuthor;
	private GridBagConstraints gbc_lblAuthor;
	private GridBagConstraints gbc_lblTitle;
	private JLabel lblCopyright;
	private GridBagConstraints gbc_lblCopyright;
	private JLabel lblCompany;
	private GridBagConstraints gbc_lblCompany;
	private JLabel lblSchool;
	private GridBagConstraints gbc_lblSchool;

	/**
	 * Create the panel.
	 */
	public AboutPanel()
	{
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
		setLayout(gridBagLayout);

		lblTitle = new JLabel("Heritage Music Player");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		add(lblTitle, gbc_lblTitle);

		lblAuthor = new JLabel("Christopher Guigue");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gbc_lblAuthor = new GridBagConstraints();
		gbc_lblAuthor.insets = new Insets(0, 0, 5, 0);
		gbc_lblAuthor.gridx = 0;
		gbc_lblAuthor.gridy = 1;
		add(lblAuthor, gbc_lblAuthor);

		lblCopyright = new JLabel("April, 2014");
		lblCopyright.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gbc_lblCopyright = new GridBagConstraints();
		gbc_lblCopyright.insets = new Insets(0, 0, 5, 0);
		gbc_lblCopyright.gridx = 0;
		gbc_lblCopyright.gridy = 2;
		add(lblCopyright, gbc_lblCopyright);

		lblCompany = new JLabel("Heritage College");
		lblCompany.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gbc_lblCompany = new GridBagConstraints();
		gbc_lblCompany.gridx = 0;
		gbc_lblCompany.gridy = 3;
		add(lblCompany, gbc_lblCompany);

		lblSchool = new JLabel("Computer Science");
		lblSchool.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gbc_lblSchool = new GridBagConstraints();
		gbc_lblSchool.gridx = 0;
		gbc_lblSchool.gridy = 4;
		add(lblSchool, gbc_lblSchool);

	}

}
