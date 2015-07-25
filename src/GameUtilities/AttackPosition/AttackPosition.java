package GameUtilities.AttackPosition;

import java.awt.Point;

/**
 * Attack Position
 * 
 * @author Schoenegger / Purkart / Koch
 *
 */
public class AttackPosition
{
	private Point xyPosition;

	/**
	 * Attack Position
	 * 
	 * @param point
	 */
	public AttackPosition(Point point)
	{
		this.setXyPosition(point);
	}

	/**
	 * get xy Position
	 * 
	 * @return Point
	 */
	public Point getXyPosition()
	{
		return xyPosition;
	}

	/**
	 * set xy Position
	 * 
	 * @param xyPosition
	 */
	public void setXyPosition(Point xyPosition)
	{
		this.xyPosition = xyPosition;
	}

	/**
	 * Convert x and y position to string x,y
	 * 
	 * @return String
	 */
	public String toString()
	{
		return Integer.toString(xyPosition.x) + "," + Integer.toString(xyPosition.y);
	}
}
