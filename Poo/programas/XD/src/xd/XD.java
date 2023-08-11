/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package xd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class XD {

    public static ArrayList<Integer> agregarTop(ArrayList<Integer> top10,int a){
        ArrayList<Integer> Aux = new ArrayList<Integer>();
        if (top10.size()==0){
            Aux.add(a);
          }
          else{   
            for(int t : top10){
                
                if(Aux.size()<10){
                
                if(t > a&&!Aux.contains(a)){
                    Aux.add(a);
                }
                if(!Aux.contains(t))    
                Aux.add(t);
                }
               if(Aux.size()>10)
                    Aux.remove(10);
                
        }
            if(Aux.size()<10){
                System.out.println(!Aux.contains(a));
                if(!Aux.contains(a)){
                    Aux.add(a);
                }
            }
            
         
    }
        return Aux;
 }
         
        
    public static void main(String[] args) {
          ArrayList<Integer> top=new ArrayList<Integer>();
          int a = 10;
          top =agregarTop(top,a);
          
          top =agregarTop(top,25);
          
          top =agregarTop(top,14);
          top =agregarTop(top,1);
          top =agregarTop(top,8);
          top =agregarTop(top,24);
          top =agregarTop(top,3);
          top =agregarTop(top,5);
          top =agregarTop(top,12);
          top =agregarTop(top,13);
          top =agregarTop(top,81);
           top =agregarTop(top,2);
          for(Integer t : top){
              System.out.println(t);
          }
          
      
    }
    
}
