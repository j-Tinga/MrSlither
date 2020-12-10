/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleslickgame;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author jbp
 */
public class WireConnectionClass {
    private ArrayList<Shape> pair;
    private Color color;
    private int slot1;
    private int slot2;
    private boolean connected = false;
    
    public WireConnectionClass(Shape s1, Shape s2, Color c){
        pair = new ArrayList<>();
        pair.add(s1);
        pair.add(s2);
        this.color = c;
    }
    
    public WireConnectionClass(ArrayList s, Color c, int s1, int s2){
        this.pair = s;
        this.color = c;
        this.slot1 = s1;
        this.slot2 = s2;
    }
    
    public Color getColor(){
        return this.color;
    }
    
    public ArrayList<Shape> getPair(){
        return pair;
    }
    
    public Shape getShape1(){
        return this.pair.get(0);
    }
    
    public Shape getShape2(){
        return this.pair.get(1);
    }
    
    public void setColor(Color c){
        this.color = c;
    }
    
    public void setShape1(Shape s){
        pair.set(0, s);
    }
    
    public void setShape2(Shape s){
        pair.set(1, s);
    }
    
    public void setShapes(ArrayList s){
        this.pair = s;
    }
    
    public void setBool(boolean b){
        this.connected = b;
    }
    
    public boolean getBool(){
        return this.connected;
    }
    
    public int whichShapeIsThisBro(Shape s){
        if(s.equals(this.pair.get(0))){
            return slot1;
        } else if(s.equals(this.pair.get(1))){
            return slot2;
        } else {
            return -1;
        }
    }
}
