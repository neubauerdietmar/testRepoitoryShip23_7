package view.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import GameUtilities.ShipType;
import GameUtilities.Field.Field;
import GameUtilities.Field.FieldElement;
import GameUtilities.Field.FieldState;

public class DrawingPanelGameFields extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Graphics2D graphic;
	private BufferedImage image;
	private Field ownField;
	private Field enemyField;
	private int mouseCourserX = 0;
	private int mouseCourserY = 0;
	private ShipType currShipType = ShipType.AIRCARRIER;
	private String align = "";

	private final Color NOT_VALID_COLOR = Color.gray;
	private final Color VALID_COLOR = Color.green;
	private final Color DEFAULT_FOREGROUND = new Color(0, 0, 255);
	private final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 20);

	public DrawingPanelGameFields(Field ownField, Field enemyField)
	{
		setBackground(new Color(169, 169, 169));
		this.ownField = ownField;
		this.enemyField = enemyField;
		startRunningText(10);
		// this.setBackground(new Color(255, 255, 255));

		this.setForeground(DEFAULT_FOREGROUND);
		this.setFont(new Font("Arial", Font.BOLD, 20));
		this.repaint();
		this.revalidate();
	}

	private void startRunningText(int i)
	{
		// graphic.drawString(
	}

	private void setMouseCourser(int x, int y, ShipType type, String align)
	{
		mouseCourserX = x;
		mouseCourserY = y;
		currShipType = type;
		this.align = align;
	}

	@Override
	public void paintComponent(Graphics graph)
	{

		graphic = (Graphics2D) graph.create();

		createFieldView(ownField, (1.0 / 12.0), (1.0 / 6.0), (1.0 / 30.0),
				"OWN FIELD", true);
		createFieldView(enemyField, (7.0 / 12.0), (1.0 / 6.0), (1.0 / 30.0),
				"ENEMY FIELD", false);

	}

	//
	// private void creatFioeldView(double factorStartX, double factorStartY,
	// double factorRect)
	// {
	// double widthReck = this.getWidth() * factorRect;
	// double heightReck = widthReck;
	// double startX = this.getWidth() * factorStartX;
	// double startY = this.getHeight() * factorStartY;
	//
	// graphic.drawString("PanelWidht" + this.getWidth(), (int) startX,
	// (int) startY - 20);
	//
	// for (int i = 0; i < 10; i++)
	// {
	// // change coloum
	// for (int j = 0; j < 10; j++)
	// {
	//
	// graphic.draw(new Rectangle2D.Double(startX, startY, widthReck,
	// heightReck));
	//
	// startX += widthReck;
	// }
	// startX = this.getWidth() * factorStartX;
	// startY += heightReck;
	// }
	// }

	private void createFieldView(Field gameField, double factorStartX,
			double factorStartY, double factorRect, String fieldName,
			boolean isOwmField)
	{

		double widthReck = this.getWidth() * factorRect;
		double heightReck = widthReck;
		double startX = this.getWidth() * factorStartX;
		double startY = this.getHeight() * factorStartY;

		graphic.setPaint(DEFAULT_FOREGROUND);
		graphic.drawString(fieldName, (int) startX, (int) startY - 20);

		for (int i = 0; i < 10; i++)
		{
			// change coloum
			for (int j = 0; j < 10; j++)
			{
				if (courserIsInField(startX, startY, widthReck, heightReck))
				{
					// gameField.setPossibleFields();
				}
				if (false)
				{
					graphic.setPaint(NOT_VALID_COLOR);
					graphic.fill(new Rectangle2D.Double(startX, startY,
							widthReck, heightReck));
				}
				else
				{
					graphic.draw(new Rectangle2D.Double(startX, startY,
							widthReck, heightReck));
				}

				BufferedImage image = getImageByFildType(gameField
						.getFieldElement(i, j));

				if (image != null)
				{
					// 25 = half of iconWidth
					graphic.drawImage(image,
							(int) (startX + (widthReck / 2 - 25)),
							(int) (startY + (heightReck / 2 - 25)), this);
				}

				startX += widthReck;
			}
			startX = this.getWidth() * factorStartX;
			startY += heightReck;
		}
	}

	private boolean courserIsInField(double startX, double startY,
			double widthReck, double heightReck)
	{
		boolean isInField = true;
		isInField &= ((mouseCourserX > (int) startX) && (mouseCourserX < ((int) startX + widthReck)));
		isInField &= ((mouseCourserY > (int) startY) && (mouseCourserY < ((int) startY + heightReck)));
		return isInField;
	}

	private boolean setElementPositionByMouseCouser(Field gameField)
	{

		return false;
	}

	private BufferedImage getImageByFildType(FieldElement fieldElement)
	{
		BufferedImage image = null;
		String path = "";

		if (fieldElement.isTaken())
		{
			if (fieldElement.getFieldState() == FieldState.STRIKE_SHIP)
			{
				path = "src\\img\\ship_dest.png";// strike ship
			}
			else
			{
				path = "src\\img\\ship.png";
			}
		}

		if (!fieldElement.isTaken())
		{
			if (fieldElement.getFieldState() == FieldState.UNKNOWN
					&& !fieldElement.isOwn())
			{

				path = "src\\img\\questm.png";
			}
			else
			{
				path = "src\\img\\wave.png";
			}
		}

		try
		{
			image = ImageIO.read(new File(path));
		}
		catch (IOException e)
		{
			System.out.println("Fuck you 'Image");
			e.printStackTrace();
		}

		return image;
	}

	public void setEnemyField(Field enemyField)
	{
		this.enemyField = enemyField;
	}
}
