/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleslickgame;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author jbp
 */
public class WireClass {
    private Line wire;
    private Rectangle temp;
    private Shape wiredraw;
    private Color color;
    
    public WireClass(float x1, float y1, float x2, float y2){
        wire = new Line(x1,y1,x2,y2);
        temp = new Rectangle(x1,y1+5,0,10);
        wiredraw = temp;
    }
    
    public WireClass(float x1, float y1, float x2, float y2, Color c){
        wire = new Line(x1,y1,x2,y2);
        color = c;
        temp = new Rectangle(x1,y1+5,0,10);
        wiredraw = temp;
    }
    
    public Rectangle getRectangle(){
        return this.temp;
    }
    
    public Shape getShape(){
        return this.wiredraw;
    }
    
    public Line getLine(){
        return this.wire;
    }
    
    public Color getColor(){
        return this.color;
    }
    
    public void setRectangle(Rectangle r){
        this.temp = r;
    }
    
    public void setShape(Shape s){
        this.wiredraw = s;
    }
    
    public void setLine(Line l){
        this.wire = l;
    }
    
    public void setColor(Color c){
        this.color = c;
    }
}
