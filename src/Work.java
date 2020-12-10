/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sam
 */
public class Work extends FieldObjects{
    private int Salary;
    
    public Work(){
        Salary = 0;
    }
    
    public int getSal(){
        return Salary;
    }
    
    public void setSel(int sal){
        this.Salary = sal;
    }
    
    public int earnSal(){
        return Salary+=50; //50 is just a test number, will combine w pasana's game later im just testing
    }
    
}
