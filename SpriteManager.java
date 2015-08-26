import javax.swing.*;
import java.awt.*;


/**
 * Created by Thomas on 11/26/2014.
 */
public class SpriteManager extends JPanel //handles sprites
{
    Game game;

    public SpriteManager(Game game)
    {
        this.game = game;
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        //draw each of the player's and house's cards

        for(int i = 0; i < game.house.getCards().size(); i++)
        {
            Card c = game.house.getCards().get(i);

            if(c.getFaceUp())
                g.drawImage(c.image, (i * Card.WIDTH / 2) + 364 - ((game.house.getCards().size() - 1) * (Card.WIDTH / 4)), 84, null);
            else
                g.drawImage(Resource.backside, (i * Card.WIDTH / 2) + 364 - ((game.house.getCards().size() - 1) * (Card.WIDTH / 4)), 84, null);
        }

        for(int i = 0; i < game.player.getCards().size(); i++)
        {
            Card c = game.player.getCards().get(i);

            g.drawImage(c.image, (i * Card.WIDTH / 2) + 364 - ((game.player.getCards().size() - 1) * (Card.WIDTH / 4)), 420, null);

        }

        //draw the player's and house's scores
        g.setFont(new Font("Verdana", Font.BOLD, 14));
        g.drawString(Integer.toString(game.player.getScore()), 390, 550);

        if(game.house.getCards().get(1).getFaceUp())
            g.drawString(Integer.toString(game.house.getScore()), 390, 60);

        g.setFont(new Font("Verdana", Font.BOLD, 72));

        if(game.getLose())
            g.drawString("LOSE!", 290, 280);
        else if(game.getWin())
            g.drawString("WIN!", 300, 280);
    }
}
