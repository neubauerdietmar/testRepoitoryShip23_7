package GameUtilities.Field;

/**
 * 
 * @author Schoenegger / Purkart / Koch
 *
 */
public class FieldElement
{
	private FieldState fieldState;
	private boolean isTaken;
	private boolean isOwnFieldElement = false;
	private boolean isAvailableToSetShip = false;
	private boolean isPossibleField = false;

	public FieldElement(boolean isOwnFieldElement)
	{
		this.isOwnFieldElement = isOwnFieldElement;
	}

	/**
	 * get the field state
	 * 
	 * @return FieldState
	 */
	public FieldState getFieldState()
	{
		return fieldState;
	}

	/**
	 * is on this field position a ship
	 * 
	 * @return boolean
	 */
	public boolean isTaken()
	{
		return this.isTaken;
	}

	public boolean isOwn()
	{
		return isOwnFieldElement;
	}

	public boolean isAvailableToSetShip()
	{
		return isOwnFieldElement & isAvailableToSetShip & isPossibleField;
	}

	public boolean isAvailableToAttac()
	{
		return !isOwnFieldElement & (fieldState == fieldState.UNKNOWN)
				& isPossibleField;
	}

	public void setisPossibleField(boolean isPossible)
	{
		isPossibleField = isPossible;
	}

	/**
	 * set the field state
	 * 
	 * @param fieldState
	 */
	public void setFieldState(FieldState fieldState)
	{
		this.fieldState = fieldState;
	}

	/**
	 * set a ship on this position
	 */
	public void setTaken()
	{
		this.isTaken = true;
	}
}
