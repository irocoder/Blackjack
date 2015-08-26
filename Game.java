import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Thomas on 11/26/2014.
 */
public class Game
{
    private JFrame frame;
    private SpriteManager spriteManager;

    private JButton hit, stand, playAgain;

    ArrayList<Card> deck = new ArrayList<Card>(52);

    Player player = new Player();
    Player house = new Player();

    Timer timer = new Timer(800, new OnTimer());

    private boolean win = false, lose = false;


    public Game()
    {
        Resource.loadCards(); //load the card image file

        loadDeck(); //populate and shuffle the deck


        //set up the buttons and panels
        hit = new JButton("Hit me!");
        hit.addActionListener(new HitButtonPressed());

        stand = new JButton("Stand!");
        stand.addActionListener(new StandButtonPressed());

        playAgain = new JButton("Play Again!");
        playAgain.addActionListener(new RestartButtonPressed());

        hit.setBounds(320, 350, 80, 30);
        stand.setBounds(400, 350, 80, 30);
        playAgain.setBounds(320, 300, 160, 30);
        playAgain.setVisible(false);

        frame = new JFrame("Graphical Blackjack");

        spriteManager = new SpriteManager(this);
        spriteManager.setLayout(null); //use absolute positioning

        spriteManager.setBackground(new Color(0x397228));
        spriteManager.setForeground(new Color(0xffffff));

        spriteManager.add(hit);
        spriteManager.add(stand);
        spriteManager.add(playAgain);
        spriteManager.setPreferredSize(new Dimension(800, 600));

        frame.getContentPane().add(spriteManager);
        frame.pack();

        dealCard(house, true);
        dealCard(house, false);
        dealCard(player, true);
        dealCard(player, true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void loadDeck()
    {
        deck.clear();

        for(int j = 0; j < 4; j++) //populate the deck
        {
            for(int k = 1; k <= 13; k++)
            {
                deck.add(new Card(j, k));
            }
        }

        Collections.shuffle(deck); // shuffle the deck
    }

    public boolean getWin()
    {
        return win;
    }

    public boolean getLose()
    {
        return lose;
    }

    public void dealCard(Player p, boolean faceUp)
    {
        //deal a card to a player
        Card c = deck.remove(deck.size() - 1);
        if(faceUp == false)
            c.setFaceUp(false);

        p.addCard(c);
    }

    public void compareScores()
    {
        if(player.getScore() > house.getScore() || house.getScore() > 21)
        {
            win();
        }
        else if(player.getScore() <= house.getScore() && house.getScore() < 22)
        {
            lose();
        }

        spriteManager.repaint();
    }

    public void display()
    {
        frame.setVisible(true);
    }

    private void win()
    {
        win = true;
    }

    private void lose()
    {
        //set the lose condition and appropriate buttons' visibility
        lose = true;
        hit.setVisible(false);
        stand.setVisible(false);
        playAgain.setVisible(true);
    }

    private class HitButtonPressed implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            dealCard(player, true);

            spriteManager.repaint();

            if(player.getScore() > 21)
                lose();
        }
    }

    private class RestartButtonPressed implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //restart the game

            lose = false;
            win = false;

            player.reset();
            house.reset();
            hit.setVisible(true);
            stand.setVisible(true);

            loadDeck();

            dealCard(house, true);
            dealCard(house, false);
            dealCard(player, true);
            dealCard(player, true);

            spriteManager.repaint();

            playAgain.setVisible(false);
        }
    }

    private class StandButtonPressed implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            house.flipCards();
            hit.setVisible(false);
            stand.setVisible(false);
            spriteManager.repaint(); //repaint otherwise card won't flip immediately

            //start the house's card dealing timer
            timer.restart();
        }
    }

    private class OnTimer implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(house.getScore() < 17) //house can only hit if score is less than 17
            {
                dealCard(house, true);
                spriteManager.repaint();
            }
            else
            {
                playAgain.setVisible(true);

                //compare scores and stop timer
                compareScores();

                timer.stop();
            }
        }
    }
}