/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.util.ArrayList;

/**
 *
 * @author Alamin
 */
public class CheckList {
    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList<>();
        
        list.add("1");
        list.add("3");
        list.add("5");
        list.add("7");
        
        for(int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i));
        }
        list.remove(0);
        list.remove(0);
        list.add(0, "10");
        System.out.println("-----------------");
        for(int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i));
        }
        
    }
    
}
