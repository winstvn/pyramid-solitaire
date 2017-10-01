// The "PyramidSolitaireApplet" class.
import java.applet.Applet;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.imageio.*;

public class PyramidSolitaireApplet extends Applet implements MouseListener
{
    // instance variables
    Graphics g;
    BufferedImage bgImg; // background image
    DeckClass deck; // deck of 52 cards
    CardClass cardBack; // just a visual of the deck flipped downwards
    CardClass selectedCard; // the card selected by the user
    PyramidClass pyramid; // pyramid of cards
    CardClass[] cardsInPyramid; // cards in the pyramid
    CellClass cell; // holding cell
    CardClass topCell; // the card seen by the user in the cell
    int requiredCard; // the card that is required to form a pair
    String sRequiredCard;
    Font f = new Font ("Dialog", Font.BOLD, 16);


    public void init ()
    {
	g = getGraphics ();
	deck = new DeckClass (); // initializes a deck of 52 unique cards
	cardBack = new CardClass (0, 0); // initializes the picture of the back of the card
	cardBack.setCentre (50, 50); // sets the centre of the card back at 50, 50
	selectedCard = null; // no card selected during initialization
	pyramid = new PyramidClass (deck); // creates the pyramid from the standard deck
	cell = new CellClass (); // initializes the stack of holding cards
	requiredCard = -1; // default value of requiredCard
	addMouseListener (this);

	try // gets background image
	{
	    URL url = new URL (getCodeBase (), "images/background.jpg");
	    bgImg = ImageIO.read (url);
	}
	catch (IOException e)
	{
	}
    } // init method


    public void paint (Graphics g)
    {
	g.drawImage (bgImg, 0, 0, null); // draw background
	if (deck.cardsLeft () > 0)
	{
	    cardBack.draw (g, cardBack, cardBack.getCentreX (), cardBack.getCentreY ()); // draw the "deck"
	}
	g.setFont (f); // sets the font of "cards left" text
	g.setColor (Color.white); // sets the drawing color to white
	g.drawString ("Cards Left: " + deck.cardsLeft (), 50, 45); // draw "cards left" text
	if (requiredCard == 1)
	{
	    sRequiredCard = "A";
	}
	else if (requiredCard == 11)
	{
	    sRequiredCard = "J";
	}
	else if (requiredCard == 12)
	{
	    sRequiredCard = "Q";
	}
	else
	{
	    sRequiredCard = Integer.toString (requiredCard);
	}
	if (requiredCard != -1)
	{
	    g.drawString ("You need a " + sRequiredCard + " to create a pair.", 50, 29);
	}
	g.drawRect (60 + cardBack.getWidth (), 50, cardBack.getWidth (), cardBack.getHeight ()); // draw rectangle for the holding cell

	pyramid.drawPyramid (g); // draw pyramid of cards
	cardsInPyramid = pyramid.getCardArray (); // updates cardsInPyramid to the encapsulated array in the PyramidClass
	cell.draw (g, topCell, 60 + 100, 50); // draw temp card cell
    } // paint method


    public void update (Graphics g)  // gets rid of the flickering caused by repainting
    {
	Image offScreenImage = createImage (getWidth (), getHeight ());

	Graphics offScreenG = offScreenImage.getGraphics ();

	offScreenG.setColor (getBackground ());
	offScreenG.fillRect (getX (), getY (), getWidth (), getHeight ());
	offScreenG.setColor (getForeground ());

	paint (offScreenG);

	g.drawImage (offScreenImage, getX (), getY (), this);
    } // update method


    public boolean action (Event e, Object o)
    {
	return true;
    }


    public void mouseClicked (MouseEvent e)
    {
	for (int i = pyramid.getCardArray ().length - 1 ; i >= 0 ; i--) // goes through all of the cards in the pyramid bottom to top
	{
	    if (cardsInPyramid [i].isPointInside (e.getX (), e.getY ()) && !cardsInPyramid [i].isitCovered () && !cardsInPyramid [i].getIsRemoved () && cardsInPyramid [i].getValue () == 13) // if the card in the pyramid is not covered, not removed, and is a king
	    {
		pyramid.removeCard (i); // remove the king
		repaint ();
		break;
	    }
	    else if (cardsInPyramid [i].isPointInside (e.getX (), e.getY ()) && !cardsInPyramid [i].isitCovered () && !cardsInPyramid [i].getIsRemoved ()) // if card is not covered and not removed
	    {
		if (selectedCard == null) // if no card has been selected yet
		{
		    cardsInPyramid [i].setIsSelected (true); // draw ring around card
		    selectedCard = cardsInPyramid [i]; // set the card to the selectedCard
		    requiredCard = 13 - cardsInPyramid [i].getValue ();
		    repaint ();
		    break;
		}
		else if (selectedCard != null) // if a card has been selected
		{
		    cardsInPyramid [i].setIsSelected (true); // draw ring around card
		    if (!pyramid.matchCards (selectedCard, cardsInPyramid [i])) // match the cards
		    {
			selectedCard = null; // if cards do not match, deselect the cards
			requiredCard = -1;
		    }
		    repaint ();
		    break;
		}
	    }
	}

	if (cardBack.isPointInside (e.getX (), e.getY ())) // if the cardback is clicked
	{
	    if (deck.cardsLeft () > 0)
	    {
		cell.setDrawFlag (true);
		topCell = cell.pushFromDeck (deck); // push a card to the cell from the top of the deck
		repaint ();
	    }
	}

	if (cell.isPointInside (e.getX (), e.getY ()) && !cell.isEmpty () && selectedCard != null) // if cell is clicked when it is not empty and there is already a card selected
	{
	    topCell.setIsSelected (true); // the top card of the cell is selected
	    if (pyramid.matchCards (selectedCard, topCell)) // match the cards
	    {
		cell.popFromCell (); // if the cards match, pop the card from the cell
		selectedCard = null; // deselect the cards
		if (!cell.isEmpty ()) // if the cell is not empty
		{
		    topCell = cell.peek (); // set the top card in the cell to the next card in the cell
		}
		else
		{
		    topCell = null;
		}
	    }
	    else
	    {
		selectedCard = null; // if the cards do not match, deselect the cards
	    }
	    repaint ();
	}
	else if (cell.isPointInside (e.getX (), e.getY ()) && !cell.isEmpty () && topCell.getValue () == 13) // if the cell is clicked when it is not empty and the top card is a king
	{
	    cell.popFromCell (); // pop the top card of the cell
	    topCell = cell.peek (); // set the top card in the cell to the next card in the cell
	    repaint ();
	}
    }


    public void mouseEntered (MouseEvent e)
    {
    }


    public void mouseExited (MouseEvent e)
    {
    }


    public void mousePressed (MouseEvent e)
    {

    }


    public void mouseReleased (MouseEvent e)
    {

    }


    public void mouseDragged (MouseEvent e)
    {
    }


    public void mouseMoved (MouseEvent e)
    {
    }
} // PyramidSolitaireApplet class
