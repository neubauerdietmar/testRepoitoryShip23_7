package GameUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Ship
 * 
 * @author Schoenegger / Purkart / Koch
 *
 */
public class Ship
{
	// static final ALIGN_VERTICAL = "VERTICAL";
	// static final ALIGN_HORIZONTAL = "HORIZONTAL";

	private ShipType shipType;
	private ShipPosition shipPosition;
	private String align;
	private int countSector;
	private int number;
	private boolean isAlive = true;
	private List<int[]> coordinatesReservatedFields;

	/**
	 * Ship - define a ship with the parameters
	 * 
	 * @param shipPosition
	 * @param type
	 * @param ShipNumber
	 */
	public Ship(ShipPosition shipPosition, ShipType type, int ShipNumber)
	{

		this.shipPosition = shipPosition;
		this.shipType = type;
		this.number = ShipNumber;

		switch (this.shipType)
		{
		case AIRCARRIER :
			countSector = 5;
			break;
		case DESTROYER :
			countSector = 4;
			break;
		default :
			countSector = 3;
			break;
		}

	}

	/**
	 * generate the transfere strigt
	 * 
	 * @return String
	 */
	public String toTransferString()
	{
		String type = "";
		switch (this.shipType)
		{
		case AIRCARRIER :
			type = "AIRCARRIER";
			break;
		case DESTROYER :
			type = "DESTROYER";
			break;
		case YELLOW_SUBMARINE :
			type = "YELLOW_SUBMARINE";
			break;
		default :
			break;
		}

		return "-" + Integer.toString(number) + "," + type + ","
				+ shipPosition.toString();
	}

	/**
	 * Ship is alive
	 * 
	 * @return boolean
	 */
	public boolean isAlive()
	{
		return isAlive;
	}

	/**
	 * cont this sector
	 * 
	 * @return integer
	 */
	public int getCountSector()
	{
		return this.countSector;
	}

	public ShipPosition getShipPosition()
	{
		return this.shipPosition;
	}

	public ShipType getType()
	{
		return this.shipType;
	}

	public List<int[]> getListOfReservatedFields()
	{

		List<int[]> list = new ArrayList<int[]>();

		if (align.toLowerCase().equals("horizontal"))
		{
			int y = (int) shipPosition.getXyPosition().getY();

			for (int x = (int) shipPosition.getXyPosition().getX(); x < this.countSector; x++)
			{

				addToListReservedFieldsAroundPosition(list, y, x);

			}

		}
		else
		{
			int x = (int) shipPosition.getXyPosition().getX();

			for (int y = (int) shipPosition.getXyPosition().getY(); y < this.countSector; y++)
			{

				addToListReservedFieldsAroundPosition(list, y, x);

			}
		}
		return list;
	}

	private void addToListReservedFieldsAroundPosition(List<int[]> list, int y,
			int x)
	{
		if (isInFieldPosition(x - 1, y - 1))
		{
			list.add(new int[]
			{ x - 1, y - 1 });
		}
		if (isInFieldPosition(x + 1, y + 1))
		{
			list.add(new int[]
			{ x + 1, y + 1 });
		}
		if (isInFieldPosition(x + 1, y))
		{
			list.add(new int[]
			{ x + 1, y });
		}
		if (isInFieldPosition(x, y + 1))
		{
			list.add(new int[]
			{ x, y + 1 });
		}
		if (isInFieldPosition(x - 1, y))
		{
			list.add(new int[]
			{ x - 1, y });
		}
		if (isInFieldPosition(x, y - 1))
		{
			list.add(new int[]
			{ x, y - 1 });
		}
		if (isInFieldPosition(x - 1, y + 1))
		{
			list.add(new int[]
			{ x - 1, y + 1 });
		}
		if (isInFieldPosition(x + 1, y - 1))
		{
			list.add(new int[]
			{ x + 1, y - 1 });
		}
		if (isInFieldPosition(x, y))
		{
			list.add(new int[]
			{ x, y });
		}
	}

	private boolean isInFieldPosition(int x, int y)
	{
		if (x >= 0 && x <= 9 && y >= 0 && y <= 9)
		{
			return true;
		}
		return false;
	}
}
