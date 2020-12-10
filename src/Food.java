/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sam
 */
public class Food extends FieldObjects{
    private int foodValue;
    private int foodCost;
    
    public Food(){
        foodValue = 0;
        foodCost = 0;
    }
    
    public int getFVal(){
        return foodValue;
    }
    
    public void setFVal(int fval){
        this.foodValue = fval;
    }
    
    public int getFCost(){
        return foodCost;
    }
    
    public void setRent(int fcost){
        this.foodCost = fcost;
    }
}
