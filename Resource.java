import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Thomas on 11/26/2014.
 */
public class Resource
{
    //class for accessing and loading resources such as graphics or sound (no sound here though)

    public static BufferedImage cards;
    public static BufferedImage backside; //backside of card

    public static void loadCards()
    {
        //load resources

        URL url = Resource.class.getClassLoader().getResource("cards.png");
        URL url2 = Resource.class.getClassLoader().getResource("back.png");
        try
        {
            cards = ImageIO.read(url);
            backside = ImageIO.read(url2);
        } catch (IOException e)
        {
            System.exit(0);
            e.printStackTrace();
        }
    }
}
