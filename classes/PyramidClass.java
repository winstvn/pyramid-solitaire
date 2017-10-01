import java.awt.*;

public class PyramidClass
{
    // encapsulated data
    protected CardClass[] card = new CardClass [28];

    // constructors
    public PyramidClass (DeckClass deck)  // initialize the pyramid
    {
	deck.shuffle (); // shuffle the deck
	for (int i = 0 ; i < 28 ; i++)
	{
	    card [i] = deck.dealTopCard ();
	    if (i < 21) // the first 6 rows of the deck are covered, the last row is not
	    {
		card [i].setIsCovered (true);
	    }
	}
	// set the pointers of the cards in the pyramid
	card [0].setPtrs (card [1], card [2]);
	card [1].setPtrs (card [3], card [4]);
	card [2].setPtrs (card [4], card [5]);
	card [3].setPtrs (card [6], card [7]);
	card [4].setPtrs (card [7], card [8]);
	card [5].setPtrs (card [8], card [9]);
	card [6].setPtrs (card [10], card [11]);
	card [7].setPtrs (card [11], card [12]);
	card [8].setPtrs (card [12], card [13]);
	card [9].setPtrs (card [13], card [14]);
	card [10].setPtrs (card [15], card [16]);
	card [11].setPtrs (card [16], card [17]);
	card [12].setPtrs (card [17], card [18]);
	card [13].setPtrs (card [18], card [19]);
	card [14].setPtrs (card [19], card [20]);
	card [15].setPtrs (card [21], card [22]);
	card [16].setPtrs (card [22], card [23]);
	card [17].setPtrs (card [23], card [24]);
	card [18].setPtrs (card [24], card [25]);
	card [19].setPtrs (card [25], card [26]);
	card [20].setPtrs (card [26], card [27]);
    }


    // methods
    public void drawPyramid (Graphics g)
    {
	int[] cardCentreX = new int [28];
	int[] cardCentreY = new int [28];

	// set card X coords
	cardCentreX [0] = 800;
	cardCentreX [1] = 800 - card [1].getWidth () / 2;
	cardCentreX [2] = 800 + card [1].getWidth () / 2;
	cardCentreX [3] = cardCentreX [1] - card [1].getWidth () / 2;
	cardCentreX [4] = cardCentreX [2] - card [1].getWidth () / 2;
	cardCentreX [5] = cardCentreX [2] + card [1].getWidth () / 2;
	cardCentreX [6] = cardCentreX [3] - card [1].getWidth () / 2;
	cardCentreX [7] = cardCentreX [4] - card [1].getWidth () / 2;
	cardCentreX [8] = cardCentreX [5] - card [1].getWidth () / 2;
	cardCentreX [9] = cardCentreX [5] + card [1].getWidth () / 2;
	cardCentreX [10] = cardCentreX [6] - card [1].getWidth () / 2;
	cardCentreX [11] = cardCentreX [7] - card [1].getWidth () / 2;
	cardCentreX [12] = cardCentreX [8] - card [1].getWidth () / 2;
	cardCentreX [13] = cardCentreX [9] - card [1].getWidth () / 2;
	cardCentreX [14] = cardCentreX [9] + card [1].getWidth () / 2;
	cardCentreX [15] = cardCentreX [10] - card [1].getWidth () / 2;
	cardCentreX [16] = cardCentreX [11] - card [1].getWidth () / 2;
	cardCentreX [17] = cardCentreX [12] - card [1].getWidth () / 2;
	cardCentreX [18] = cardCentreX [13] - card [1].getWidth () / 2;
	cardCentreX [19] = cardCentreX [14] - card [1].getWidth () / 2;
	cardCentreX [20] = cardCentreX [14] + card [1].getWidth () / 2;
	cardCentreX [21] = cardCentreX [15] - card [1].getWidth () / 2;
	cardCentreX [22] = cardCentreX [16] - card [1].getWidth () / 2;
	cardCentreX [23] = cardCentreX [17] - card [1].getWidth () / 2;
	cardCentreX [24] = cardCentreX [18] - card [1].getWidth () / 2;
	cardCentreX [25] = cardCentreX [19] - card [1].getWidth () / 2;
	cardCentreX [26] = cardCentreX [20] - card [1].getWidth () / 2;
	cardCentreX [27] = cardCentreX [20] + card [1].getWidth () / 2;

	// set card Y coords
	for (int i = 0 ; i < 28 ; i++)
	{
	    cardCentreY [i] = 50;
	    if (i == 1 || i == 2)
	    {
		cardCentreY [i] = cardCentreY [0] + card [1].getHeight () / 2;
	    }
	    else if (i >= 3 && i <= 5)
	    {
		cardCentreY [i] = cardCentreY [1] + card [1].getHeight () / 2;
	    }
	    else if (i >= 6 && i <= 9)
	    {
		cardCentreY [i] = cardCentreY [3] + card [1].getHeight () / 2;
	    }
	    else if (i >= 10 && i <= 14)
	    {
		cardCentreY [i] = cardCentreY [6] + card [1].getHeight () / 2;
	    }
	    else if (i >= 15 && i <= 20)
	    {
		cardCentreY [i] = cardCentreY [10] + card [1].getHeight () / 2;
	    }
	    else if (i >= 21 && i <= 27)
	    {
		cardCentreY [i] = cardCentreY [15] + card [1].getHeight () / 2;
	    }
	}

	for (int i = 0 ; i < 28 ; i++) // sets the X and Y location of the cards
	{
	    if (!card [i].getIsRemoved ())
	    {
		card [i].setCentreY (cardCentreY [i]);
		card [i].setCentreX (cardCentreX [i]);
	    }
	}

	for (int i = 0 ; i < 28 ; i++) // draw the cards in the pyramid
	{
	    if (!card [i].getIsRemoved () && card [i].getDrawFlag ()) // only draw card if drawFlag is true or card is not null
	    {
		card [i].draw (g, card [i], card [i].iCentreX, card [i].iCentreY);
	    }
	}
    }


    public void removeCard (int Pos)  // remove a card in the pyramid
    {
	card [Pos].isRemoved = true;
    }


    public boolean matchCards (CardClass card1, CardClass card2)  // match 2 pairs of cards together
    {
	if (card1.getValue () + card2.getValue () == 13) // if the values of the 2 cards equal 13
	{
	    card1.setDrawFlag (false);
	    card2.setDrawFlag (false);
	    card1.isRemoved = true; // remove the card
	    card2.isRemoved = true; // remove the card
	    for (int i = 0 ; i < 28 ; i++)
	    {
		if (card [i] == card1)
		{
		    card [i] = card1; // remove it from the pyramid array
		}
		else if (card [i] == card2)
		{
		    card [i] = card2;
		}
	    }
	    return true;
	}
	else
	{
	    card1.setIsSelected (false); // deselect the card
	    card2.setIsSelected (false); // deselect the card
	    for (int i = 0 ; i < 28 ; i++)
	    {
		if (card [i] == card1)
		{
		    card [i] = card1; // update the pyramid array
		}
		else if (card [i] == card2)
		{
		    card [i] = card2; // update the pyramid array
		}
	    }
	    return false;
	}
    } // matchCards


    public CardClass[] getCardArray ()  // returns the pyramid array
    {
	return card;
    } // getCardArray
} // PyramidClass
