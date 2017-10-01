// The "CardClass" class.
import java.awt.*;
import java.io.IOException;
import java.awt.image.*;
import javax.imageio.*;

public class CardClass extends ShapeClass
{
    // encapsulated data
    protected int iSuit = 1; // suit value (spades, hearts, clubs, diamonds)
    protected int iValue = 1; // card value (Ace, 2, 3, 4...)
    protected boolean isCovered = false; // true if another card is on top of this card
    protected boolean isSelected = false; // true when user clicks on card
    protected boolean isRemoved = false; // true when card is removed
    protected boolean drawFlag = true; // only draw card when true
    protected CardClass ptrLeft; // bottom left
    protected CardClass ptrRight; // bottom right


    // constructors
    public CardClass ()
    {
    }


    public CardClass (int uValue, int uSuit)
    {
	setSuit (uSuit);
	setValue (uValue);
    }


    // communicator methods
    public void setSuit (int iNewSuit)  // sets a integer for suit value
    {
	iSuit = iNewSuit;
    }


    public void setValue (int iNewValue)  // sets a integer for card value
    {
	iValue = iNewValue;
    }


    public void setIsCovered (boolean bCovered)  // sets to check if card is covered
    {
	isCovered = bCovered;
    }


    public void setIsSelected (boolean bSelected)  // sets to check if card is selected
    {
	isSelected = bSelected;
    }


    public void setDrawFlag (boolean bDrawFlag)  // sets the draw flag
    {
	drawFlag = bDrawFlag;
    }


    public void setPtrs (CardClass leftCard, CardClass rightCard)  // sets the pointers for both sides
    {
	ptrLeft = leftCard;
	ptrRight = rightCard;
    }


    public int getSuit ()  // returns suit value
    {
	return iSuit;
    }


    public int getValue ()  // returns card value
    {
	return iValue;
    }


    public boolean getIsCovered ()      // returns true if card is covered, and vice versa
    {
	return isCovered;
    }


    public boolean getIsSelected ()  // returns true if card is selected, and vice versa
    {
	return isSelected;
    }


    public boolean getIsRemoved ()  // returns boolean isRemoved
    {
	return isRemoved;
    }


    public boolean getDrawFlag ()  // returns the drawFlag of card
    {
	return drawFlag;
    }


    public CardClass getPtrLeft ()  // returns the left pointer
    {
	return ptrLeft;
    }


    public CardClass getPtrRight ()  // returns the right pointer
    {
	return ptrRight;
    }


    // methods
    public void draw (Graphics g, int iValue, int iSuit, int iCentreX, int iCentreY)  // draw with given int values
    {
	BufferedImage img = null;
	BufferedImage img2 = null;
	try
	{
	    img = ImageIO.read (getClass ().getResource ("images/cards/" + iValue + iSuit + ".gif")); // get the image according the the value and suit
	    img2 = ImageIO.read (getClass ().getResource ("images/cards/selectedRing.png")); // get the image of the ring around the card
	}
	catch (IOException e)
	{
	}
	if (drawFlag == true && isSelected == false && isRemoved == false) // if the card is not selected but can be drawn
	{
	    g.drawImage (img, iCentreX, iCentreY, getWidth (), getHeight (), null); // just draw the card
	}
	else if (drawFlag == true && isSelected == true) // if the card is selected and can be drawn
	{
	    g.drawImage (img, iCentreX, iCentreY, getWidth (), getHeight (), null); // draw the card
	    g.drawImage (img2, iCentreX, iCentreY, getWidth (), getHeight (), null); // draw the ring on top of the card
	}
    } // draw method


    public void draw (Graphics g, CardClass card, int iCentreX, int iCentreY)  // draw with given CardClass and location
    {
	if (!card.isRemoved ()) // draw if the card is not removed
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
	    if (drawFlag == true && isSelected == false) // if card can be drawn and is not selected
	    {
		g.drawImage (img, iCentreX, iCentreY, getWidth (), getHeight (), null); // draw just the card
	    }
	    else if (drawFlag == true && isSelected == true) // if the card can be drawn and is selected
	    {
		g.drawImage (img, iCentreX, iCentreY, getWidth (), getHeight (), null); // draw the card
		g.drawImage (img2, iCentreX, iCentreY, getWidth (), getHeight (), null); // draw the ring around the card
	    }
	}
    } // draw method


    public boolean isPointInside (int X, int Y)
    {
	boolean inX = false;
	boolean inY = false;
	if (X >= iCentreX && X <= iCentreX + getWidth ()) // checks if X is inside X coords of card
	{
	    inX = true;
	}
	if (Y >= iCentreY && Y <= iCentreY + getHeight ()) // checks if Y is inside Y coords of card
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


    public boolean isitCovered ()  // checks if card is covered by bottom 2 cards
    {
	if (ptrLeft == null && ptrRight == null)
	{
	    return false;
	}
	else if (ptrLeft.isRemoved == true && ptrRight.isRemoved == true)
	{
	    return false;
	}
	else
	{
	    return true;
	}

    }


    public boolean isRemoved ()
    {
	return isRemoved;
    }
} // CardClass class

