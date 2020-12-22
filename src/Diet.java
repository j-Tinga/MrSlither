
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Diet extends FieldObjects{
        
    private int dietCost = 35;
    float delay = 15;
    
    public Diet() throws SlickException {
        objectPosition = new Position(582, 192);
        objectImage = new Image("assets/GreenApple.png");
        objectHitbox =new Rectangle((float)objectPosition.getX(),(float)objectPosition.getY(), 16, 16);
    }
    
    
    //Getters and Setters
    public int getDietCost() {
        return dietCost;
    }

    public void setDietCost(int dietCost) {
        this.dietCost = dietCost;
    }
   
}


