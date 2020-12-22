
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Work extends FieldObjects{
    private int Salary = 50;
    
    public Work() throws SlickException {
      objectPosition = new Position(400, 352);
      objectImage = new Image("assets/work.png");
      objectHitbox =new Rectangle((float)objectPosition.getX(),(float)objectPosition.getY(), 16, 16);
    }
    
    public int getSal(){
        return Salary;
    }
    
    public void setSel(int sal){
        this.Salary = sal;
    }
    
    public int earnSal(){
        return Salary+=50; //50 is just a test number, will combine w pasana's game later im just testing
    }
    
}
