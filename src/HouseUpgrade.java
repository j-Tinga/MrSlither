import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class HouseUpgrade extends FieldObjects{
    private final int houseCost = 100;
    
    public HouseUpgrade() throws SlickException {
      objectPosition = new Position(256, 320);
      objectImage = new Image("assets/house.png");
      objectHitbox =new Rectangle((float)objectPosition.getX(),(float)objectPosition.getY(), 16, 16);
    }

    public int getHouseCost() {
        return houseCost;
    }

}
