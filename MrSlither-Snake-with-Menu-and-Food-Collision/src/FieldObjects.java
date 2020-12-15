
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public class FieldObjects {
    protected Position objectPosition;
    protected Image objectImage;
    protected Shape objectHitbox;
    
    //Constructors
    public FieldObjects(){

    }
    
    public void moveObject(Position pos){
        objectHitbox.setX(pos.getX());
        objectHitbox.setY(pos.getY());
        objectPosition = pos;
    }
    
    
    //Getters and Setters
    public Position getPos(){
        return objectPosition;
    }
    
    public void setPos(Position op){
        this.objectPosition = op;
    }

    public Position getObjectPosition() {
        return objectPosition;
    }

    public void setObjectPosition(Position objectPosition) {
        this.objectPosition = objectPosition;
    }

    public Image getObjectImage() {
        return objectImage;
    }

    public void setObjectImage(Image objectImage) {
        this.objectImage = objectImage;
    }

    public Shape getObjectHitbox() {
        return objectHitbox;
    }

    public void setObjectHitbox(Shape objectHitbox) {
        this.objectHitbox = objectHitbox;
    }

    
}
