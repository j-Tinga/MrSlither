import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class Meter extends Rectangle{
    Graphics g;

    //Constructors
    public Meter(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public Meter(int x, int y) {
        this(x, y, 700, 150);
    }  
    //Functions
    public void reduceBarLength(float reduce){
        super.setWidth(super.getWidth()-reduce); //reduces the witdh of the bar
    }
    
    public void reduceHungerBar(){
         if(this.getWidth() > -1){
            this.reduceBarLength((float) 0.20);//time decay
        }
    }
    
    public void reduceRentBar(){
        if(this.getWidth() > -1){
            this.reduceBarLength((float) 0.30);//time decay
        } 
    }
    
    //Getters and Setters
    public void setHungerBar(Graphics g){
        g.drawString("Hunger ", 340, 20);
        g.setColor(new Color(255,255,0));//color of bar
        g.fill(this);
        
        g.setColor(new Color(255,255,255));//color of bg
        g.draw(this);
    }
    public void setRentBar(Graphics g){
        g.drawString("Rent ", 340, 50);
        g.setColor(new Color(255,0,255)); //color of bar
        g.fill(this);
        
        g.setColor(new Color(255,255,255)); //color of bg
        g.draw(this);
    }
    
    //Getter and Setters
    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

}

