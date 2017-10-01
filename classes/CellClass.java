import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class CellClass extends CardClass
{
    // encapsulated data
    protected Stack Cell = new Stack ();
    protected CardClass card = new CardClass ();
    protected boolean drawFlag = false; // only draw card when true
    // constructors

    public CellClass ()
    {
    }


    // communicator methods
    public void setDrawFlag (boolean bDrawFlag)  // sets the draw flag
    {
	drawFlag = bDrawFlag;
    }


    public boolean isEmpty ()  // returns true if cell is empty and vice versa
    {
	return Cell.empty ();
    }


    // methods
    public CardClass pushFromDeck (DeckClass deck)  // pushes to the cell from the top of the deck
    {
	return (CardClass) Cell.push (deck.dealTopCard ());
    }


    public CardClass popFromCell ()  // pop a card from the cell
    {
	return (CardClass) Cell.pop ();
    }


    public CardClass peek ()  // peek at the top card of the cell
    {
	return (CardClass) Cell.peek ();
    }


    public boolean isPointInside (int X, int Y)
    {
	boolean inX = false;
	boolean inY = false;
	if (X >= 160 && X <= 160 + card.getWidth ()) // checks if X is inside X coords of card
	{
	    inX = true;
	}
	if (Y >= 120 && Y <= 120 + card.getHeight ()) // checks if Y is inside Y coords of card
	{
	    inY = true;
	}
	if (inX && inY) // if both are true, return true, else return false
	{
	    return true;
	}
	else
	{
	    return false;
	}
    } // isPointInside


    public void draw (Graphics g, CardClass card, int iCentreX, int iCentreY) // draw with given CardClass and location
    {
	if (card != null && drawFlag) // if card can be drawn
	{
	    BufferedImage img = null;
	    BufferedImage img2 = null;
	    try
	    {
		img = ImageIO.read (getClass ().getResource ("images/cards/" + card.getValue () + card.getSuit () + ".gif")); // get the image according the the value and suit of the card
		img2 = ImageIO.read (getClass ().getResource ("images/cards/selectedRing.png")); // get the image of the ring around the card
	    }
	    catch (IOException e)
	    {
	    }
	    if (card.isSelected) // if card is selected
	    {
		g.drawImage (img, iCentreX, iCentreY, card.getWidth (), card.getHeight (), null); // draw the card
		g.drawImage (img2, iCentreX, iCentreY, card.getWidth (), card.getHeight (), null); // draw the ring on top of the card
	    }
	    else
	    {
		g.drawImage (img, iCentreX, iCentreY, card.getWidth (), card.getHeight (), null); // only draw the card
	    }
	}
    } // draw method
}// CellClass
