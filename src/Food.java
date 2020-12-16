
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Food extends FieldObjects {
   
    private int foodValue = 20;
    private int foodCost = 10;
    float delay = 15;

    //Contructors

    public Food() throws SlickException {
        objectPosition = new Position(352, 352);
        objectImage = new Image("assets/apple.png");
        objectHitbox =new Rectangle((float)objectPosition.getX(),(float)objectPosition.getY(), 16, 16);
    }
    
    //Function
     public void increaseHungerLength( Meter hungerbar){
         
        hungerbar.setWidth(hungerbar.getWidth()+ this.getFoodVal());

   }
    public void decreaseMoney(Money money){
   
        money.setMoney(money.getMoney() - this.getFoodCost());
            
    }
        
    //Getter and Setters
    public int getFoodVal(){
        return foodValue;
    }
    
    public void setFoodVal(int fval){
        this.foodValue = fval;
    }
    
    public int getFoodCost(){
        return foodCost;
    }
    
    public void setFoodCost(int fcost){
        this.foodCost = fcost;
    }
}


