import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class PayRent extends FieldObjects{
    private int payRent = 20;
    float delay = 15;

    public PayRent() throws SlickException {
      objectPosition = new Position(250, 352);
      objectImage = new Image("assets/rent.png"); // please change to PAYRENT
      objectHitbox =new Rectangle((float)objectPosition.getX(),(float)objectPosition.getY(), 16, 16);
    }
    
      public int getPayRent() {
        return payRent;
    }

    public void setPayRent(int payRent) {
        this.payRent = payRent;
    }
}