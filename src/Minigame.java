/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.RotateTransition;

/**
 *
 * @author jbp
 */
public class Minigame extends BasicGameState {
    private final int xres = 900;
    private final int yres = 600;
    private boolean clicked = false;
    private ArrayList<WireClass> wires;
    
    private ArrayList<WireConnectionClass> connections;
    private ArrayList<Vector2f> slots;
    private int selected = -1;
    
    private int numberOfWires;
    private int finished = 0;
    private ColorRandomizer rcolorizer;
    
    public static int testval = 0;
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        numberOfWires = 4;
        wires = new ArrayList<>();
        rcolorizer = new ColorRandomizer();
        finished = 0;
        clicked = false;
        testval = 0;
        
        connections = new ArrayList<>();
        slots = new ArrayList<>();
        
        float delta = yres/(numberOfWires+1);
        float change = delta;
        for(int i = 0; i<numberOfWires*2; i++){
            if(i<=numberOfWires-1){
                slots.add(new Vector2f(xres/4,delta));
                delta+=change;
                if(i==numberOfWires-1){
                    delta=change;
                }
            } else {
                slots.add(new Vector2f(xres/4+xres/2,delta));
                delta+=change;
            }
        }
        for(int i = 0, j=0; i<numberOfWires; i++,j+=2){
            ArrayList<Shape> stuff = new ArrayList<>();
            if(slots.size()!=2){
                int num1 = new Random().nextInt((slots.size()/2));
                int num2 = new Random().nextInt((slots.size()/2))+(slots.size()/2);
                Vector2f random1 = slots.get(num1);
                Vector2f random2 = slots.get(num2);
                stuff.add(new Circle(random1.x,random1.y,15));
                stuff.add(new Circle(random2.x,random2.y,15));
                
                slots.remove(random1);
                slots.remove(random2);
            } else {
                stuff.add(new Circle(slots.get(0).x,slots.get(0).y,15));
                stuff.add(new Circle(slots.get(1).x,slots.get(1).y,15));
                
                slots.clear();
            }
            WireConnectionClass stuff2 = new WireConnectionClass(stuff, rcolorizer.getLRandomColor(),j,j+1);
            connections.add(stuff2);
        }
        
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
        Input input = gc.getInput();
        
        
        if(input.isMousePressed(0)){
            int x = input.getMouseX();
            int y = input.getMouseY();
            connections.forEach((pair) -> {
                for(Shape c: pair.getPair()){
                    if(c.contains(x, y)&&pair.getBool()==false){ //If circle contains mouse x and y and is not already connected
                        int shapenum = pair.whichShapeIsThisBro(c);
                        if(clicked){ //if currently holding wire and the circle clicked is not what wire is attached to
                            if(pair.getColor().equals(wires.get(selected).getColor())&&pair.getBool()==false&&shapenum!=wires.get(selected).getNum()){ //if circle pair is equal to wire color
                                renderWire(c.getCenterX(),c.getCenterY());
                                pair.setBool(true);
                                finished++;
                                if(finished==numberOfWires){
                                    sbg.enterState(3,new EmptyTransition(),new RotateTransition());
                                }
                            } else {
                                wires.remove(selected);
                            }
                        } else {
                            WireClass wc = new WireClass(c.getCenterX(),c.getCenterY(),x,y,pair.getColor(),pair.whichShapeIsThisBro(c));
                            wires.add(wc);
                            selected=wires.size()-1; ///GET LAST ADDED WIRE
                        }
                        clicked=!clicked;
                    }
                }
            });
        }
        
        if(clicked){
            renderWire(input.getMouseX(),input.getMouseY());
        }
        
        if(input.isMousePressed(1)){ //reset wires
            finished=0;
            clicked=false;
            wires.clear();
            for(WireConnectionClass w: connections){
                w.setBool(false);
            }
            selected=-1;
        }
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.white);
        
        wires.forEach((w) -> {
            g.setColor(w.getColor());
            g.fill(w.getShape());
        });
        
        connections.forEach((pair) -> {
            Color r = pair.getColor();
            g.setColor(r);
            pair.getPair().forEach((c) ->{
                g.fill(c);
            });
        });
        
    }
    
    @Override
    public int getID() {
        return 4;
    }
    
    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        System.out.println("Entered");
        this.init(gc, sbg);
    }
    
    public void renderWire(float x, float y){
        Line sel1 = wires.get(selected).getLine();
        sel1.set(sel1.getX1(), sel1.getY1(), x, y);
            
        Rectangle sel2 = wires.get(selected).getRectangle();
        sel2.setWidth(sel1.length());
        sel2.setCenterX(sel1.getCenterX());
        sel2.setCenterY(sel1.getCenterY());
        double ycal = y-sel1.getY1();
        double xcal = x-sel1.getX1();
        double angle = Math.asin(ycal/sel1.length());
        if(xcal<0){
            angle = -angle;
        }
            
        Shape sel3 = sel2.transform(Transform.createRotateTransform((float) angle, sel1.getCenterX(), sel1.getCenterY()));
        wires.get(selected).setShape(sel3);
    }
}
