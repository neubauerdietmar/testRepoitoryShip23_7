package GameUtilities;

import java.awt.Point;

/**
 * 
 * @author Schoenegger / Purkart / Koch
 *
 */
public class ShipPosition
{
	private Point xyPosition;
	private String alignment; // HORIZONTAL || VERTICAL

	/**
	 * Defines the ship position
	 * 
	 * @param point
	 * @param alignment
	 */
	public ShipPosition(Point point, String alignment)
	{
		this.xyPosition = point;
		this.alignment = alignment.toLowerCase();
	}

	/**
	 * get the x and y position
	 * 
	 * @return
	 */
	public Point getXyPosition()
	{
		return xyPosition;
	}

	/**
	 * set the x and y position
	 * 
	 * @param xyPosition
	 */
	public void setXyPosition(Point xyPosition)
	{
		this.xyPosition = xyPosition;
	}

	/**
	 * get the alignment of the ship
	 * 
	 * @return
	 */
	public String getAlignment()
	{
		return alignment;
	}

	/**
	 * set the alignment of a ship
	 * 
	 * @param alignment
	 */
	public void setAlignment(String alignment)
	{
		this.alignment = alignment;
	}

	/**
	 * convert the position to a position string
	 */
	public String toString()
	{
		return Integer.toString(xyPosition.x) + ","
				+ Integer.toString(xyPosition.y) + "," + this.alignment;
	}

}
