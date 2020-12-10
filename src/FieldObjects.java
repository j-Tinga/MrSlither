
import java.util.Random;

public class FieldObjects {
    private Position ObjectPosition;
    private Random r;
    
    public FieldObjects(){
        r = new Random();
        ObjectPosition = new Position(r.nextInt(250),r.nextInt(300));
    }
    
    public Position getPos(){
        return ObjectPosition;
    }
    
    public void setPos(Position op){
        this.ObjectPosition = op;
    }
    
    public void generateObject(){
    }
    
}
