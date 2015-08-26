import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by Thomas on 11/26/2014.
 */
public class Card //represents a single playing card
{
    BufferedImage image;
    public final static int WIDTH = 72; //height and width of the card image
    public final static int HEIGHT = 96;

    //clubs = 0, spades = 1, hearts = 2, diamonds = 3

    private int rank;
    private boolean faceUp = true;

    public Card(int suite, int rank)
    {
        Card[] card = new Card[5];
        this.rank = rank;
        image = Resource.cards.getSubimage((rank - 1) * WIDTH, suite * HEIGHT, WIDTH, HEIGHT);
    }

    public int getValue()
    {
        //return 1-10 for ace through 10, 10 for everything else
        if (rank > 0 && rank < 11)
            return rank;
        else
            return 10;
    }

    public boolean getFaceUp()
    {
        return faceUp;
    }
    public void setFaceUp(boolean b)
    {
        faceUp = b;
    }
}