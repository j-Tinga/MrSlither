import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

import java.awt.Font;

public class Meter extends Rectangle{
    Graphics g;
    Font awtFont = new Font("Courier", Font.BOLD, 24);
    TrueTypeFont font = new TrueTypeFont(awtFont, false);
    private boolean empty = false;


    //Constructors
    public Meter(float x, float y, float width, float height) {
        super(x, y, width, height);
        empty = false;
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
            this.reduceBarLength((float) 0.25);//time decay
        }else{
            empty = true;
        }
    }
    
    public void reduceRentBar(){
        if(this.getWidth() > -1){
            this.reduceBarLength((float) 0.15);//time decay
        }else{
            empty = true;
        }
    }
    
    
    //Getters and Setters
    public void setHungerBar(Graphics g){
        font.drawString(340,10,"Hunger ", Color.black);
        g.setColor(new Color(255,255,0));//color of bar
        g.fill(this);
        
        g.setColor(new Color(255,255,255));//color of bg
        g.draw(this);
    }
    public void setRentBar(Graphics g){
        font.drawString(340, 40,"Rent ", Color.black);
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

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;

    }
}

