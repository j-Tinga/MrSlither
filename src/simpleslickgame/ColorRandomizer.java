/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleslickgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.newdawn.slick.Color;

/**
 *
 * @author jbp
 */
public class ColorRandomizer {
    private final Color[] colors = {Color.blue,Color.cyan,Color.darkGray,Color.gray,Color.green,
    Color.lightGray,Color.magenta,Color.orange,Color.pink,Color.red,Color.white,Color.yellow};
    private final ArrayList<Color> colorstemp;
    
    public ColorRandomizer(){
        colorstemp = new ArrayList<>();
        colorstemp.addAll(Arrays.asList(colors));
    }
    
    public Color getRandomColor(){
        int num = new Random().nextInt(colors.length);
        return colors[num];
    }
    
    public Color getLRandomColor(){
        int num = new Random().nextInt(colorstemp.size());
        
        Color taken = colorstemp.get(num);
        colorstemp.remove(taken);
        return taken;
    }
}
