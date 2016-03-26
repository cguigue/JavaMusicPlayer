package cguigue_B20_A04_Final;


public class Product extends Item
{

	String model;
	String colour;
	int quantity;

	public Product(String title, String company, double cost)
	{
		super(title, company, cost);
		model = "Unknown";
		colour = "Unknown";
		quantity = 0;
		itemNumber = "P" + itemNumber;

	}// Product(title, company, cost) constructor

	public Product(String title, String company, double cost, String mod,
			String col, int quant)
	{
		super(title, company, cost);
		model = mod;
		colour = col;
		quantity = quant;
		itemNumber = "P" + itemNumber;
	}// Product(title, company, cost, mod, col, quant) constructor

	public Product(String num, String title, String company, double cost,
			String mod, String col, int quant)
	{
		super(title, company, cost);
		model = mod;
		colour = col;
		quantity = quant;
		itemNumber = num;

	}
	
	public String getModel()
	{
		return model;
	}
	public String getColour()
	{
		return colour;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public String getItemNumber()
	{
		return itemNumber;
	}

	public String getItemClass()
	{
		return "Product";
	}

	public char getItemType()
	{
		return 'P';

	}

	public String getFileName()
	{
		return "product.txt";
	}

	public String getEndOfRecord()
	{
		return model + "~" + colour + "~" + quantity;
	}

}// Product() class
