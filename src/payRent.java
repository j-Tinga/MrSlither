/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sam
 */
public class payRent extends FieldObjects{
    private int Rent;
    
    public payRent(){
        Rent = 0;
    }
    
    public int getRent(){
        return Rent;
    }
    
    public void setRent(int rent){
        this.Rent = rent;
    }
    
    public int decreaseAmt(){
        return Rent-=10; //10 is just a test number, will test out more later 
    }
}
