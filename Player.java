import java.util.ArrayList;

/**
 * Created by Thomas on 11/30/2014.
 */
public class Player {
    //this class can represent either the house or the player
    private ArrayList<Card> cards = new ArrayList<Card>(); //the player's cards

    public Player()
    {

    }

    public void addCard(Card c)
    {
        cards.add(c);
    }

    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public void flipCards()
    {
        //set all cards to face up
        //really a function like this isn't needed, but I wanted to make the program as flexible as possible

        for(int i = 0; i < cards.size(); i++)
        {
            cards.get(i).setFaceUp(true);
        }
    }

    public void reset()
    {
        cards.clear();
    }

    public int getScore()
    {
        int score = 0;
        int numAces = 0;

        for(int i = 0; i < cards.size(); i++)
        {
            if(cards.get(i).getValue() == 1)
                numAces++;

            score += cards.get(i).getValue();
        }

        if(numAces == 1 && score < (12)) //aces can represent either a 1 or an 11 depending on the player's score
            score += 10;

        return score;
    }
}
