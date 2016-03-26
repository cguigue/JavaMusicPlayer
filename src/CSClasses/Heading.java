package CSClasses;


/**
 * Title:       Heading Class
 * Description: Displays standard report heading with date and page number on
 *              first line and title centred on next lines. Double spaces 
 *              after heading output.
 * Pre-conditions: A title and page width are supplied
 * Post-conditions: A report heading formatted is output in the following 
 *              layout:
 * mmm dd, yyyy                                                        Page #
 *                           title line 1
 *                           title line 2
 *                            ...
*/

import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.util.StringTokenizer;

public class Heading
{
	private String title;
	private String reportDate;
	private int pageNumber;
	private int lineSize;

	// public Heading() and public Heading(String t) constructors go here

	/**
	 * Initializes the title, width, page number and date
	 * @param t The Title of the report. Separate title lines are separated by ~
	 *
	 */
	public Heading(String t)
	{
		title = t;
		lineSize = 80;
		setDate();
		pageNumber = 1;
	} // Heading()

	/**
	* Initializes the title, width, page number and date
	* @param t The Title of the report. Separate title lines are separated by ~
	* @param w The column width of the report
	*
	*/
	public Heading(String t, int w)
	{
		title = t;
		lineSize = w;
		setDate();
		pageNumber = 1;
	} // Heading()

	// set methods go here

	/** Formats and returns a multi-line header. The first line contains the 
	 * date and page number. Subsequent lines contain the report title. A blank
	 * line follows the heading
	 * @return a multi-line formatted report heading
	 */
	public String getHeading()
	{
		StringBuffer head = new StringBuffer();
		StringTokenizer headingLine = new StringTokenizer(title, "~");
		
    // First line of heading contains date and page number
		int endOfPageNum = lineSize - reportDate.length();
		head.append(String.format("%s%" + endOfPageNum + "s\n",reportDate,
				"Page " + pageNumber));
		while (headingLine.hasMoreTokens())
		{
			String titleToken = headingLine.nextToken();
			int endOfTitle = (lineSize / 2) + (titleToken.length() / 2);
			head.append(String.format("%" + endOfTitle + "s\n", titleToken));
		}
		// Double space after headings
		head.append("\n");
		// Increment for next page
		++pageNumber;

		return head.toString();
	} // getHeading()

	/**
	 * Retrieves the System date and formats it as mon dd, yyyy. The formatted
	 * date is stored in the reportDate instance variable
	 */
	private void setDate()
	{
		// get today's date
		GregorianCalendar today = new GregorianCalendar();
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
		reportDate = dateFormat.format(today.getTime());
	} // setDate()
} // Heading class
