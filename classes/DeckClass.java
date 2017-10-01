// The "DeckClass" class.

import java.util.*;
public class DeckClass
{
    protected Vector deck = new Vector (0, 1);

    // constructors
    public DeckClass ()  // initializes as a standard deck (52 cards)
    {
	for (int s = 0 ; s < 4 ; s++) // creates the suits
	{
	    for (int v = 0 ; v < 13 ; v++) // creates the values
	    {
		CardClass card = new CardClass ();
		card.setSuit (s + 1);
		card.setValue (v + 1);
		addCard (card, 13 * s + v);
	    } // value
	} // suit
    }


    // methods
    public void addCard (CardClass cardToAdd, int Pos)  // add a card top a certain position in the deck
    {
	if (Pos == 0 || deck.size () == 0)
	{
	    deck.addElement (cardToAdd);
	}
	else if (Pos > deck.size ())
	{
	    deck.insertElementAt (cardToAdd, deck.size () - 1);
	}
	else
	{
	    deck.insertElementAt (cardToAdd, Pos);
	}
    }


    public int cardsLeft ()  // returns the number of cards left in the deck
    {
	return deck.size ();
    }


    public CardClass viewCard (int Pos)  // returns a card
    {
	return (CardClass) deck.get (Pos);
    }


    public CardClass dealTopCard ()  // returns the top card and removes it from the deck
    {
	if (deck.size () > 0)
	{
	    return (CardClass) deck.remove (0);
	}
	else
	{
	    return null;
	}
    }


    public CardClass dealCard (int Pos)  // returns a card and removes it from the deck
    {
	return (CardClass) deck.remove (Pos);
    }


    public void shuffle ()  // shuffles the deck
    {
	Random randInt = new Random ();
	int randPos;

	for (int i = 0 ; i < deck.size () ; i++) // places every card in the deck to a random position
	{
	    randPos = randInt.nextInt (deck.size ());
	    CardClass tempCard = dealCard (i);

	    addCard (tempCard, randPos);
	}
    }
} // DeckClass class
