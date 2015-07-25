package UnitTests.GameUtilities.AttackPosition;

import java.awt.Point;

import org.junit.Assert;
import org.junit.Test;

import GameUtilities.AttackPosition.AttackPosition;

public class JAttackPosition
{

	@Test
	public void correctAttackPosition()
	{

		Point point = new Point(2, 2);
		AttackPosition attackPos = new AttackPosition(point);

		Assert.assertEquals("2,2", attackPos);

	}

}
