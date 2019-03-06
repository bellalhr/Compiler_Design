/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Alamin
 */
public class TestCode {
     static String[] operators={"ADD","SUB","MULT","DIV","POW","ASSIGN","PRINT"};
    static String[] variables={"X","Y","Z"};
    static int x=0,y=0,z=0;
    static String identifiers="",selectedToken="",pointZ="";
    static int identifierValue;
    static boolean isValid=false,isAssignmentOperator=true;
    static ArrayList<String> commandList=new ArrayList<>();
    static ArrayList<String> identifiersList=new ArrayList<>();
    static ArrayList<Integer> identifiersValue=new ArrayList<>();
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan=new Scanner(System.in);
        System.out.println("WRITE THE OPERATION : ");
        OUTER:
        while(true)
        {
            
            String operation=scan.nextLine();
            String[] s1=operation.split("[ ,:]");
            //System.out.println("Laxical Analysis");
            for(int i=0;i<s1.length;i++)
            {

                int tokenResult=checkedToken(s1[i]);
                if(tokenResult==1)
                {
                    selectedToken=s1[i];
                    commandList.add(s1[i]);
                }                   
                else if(tokenResult==2)
                {                
                     identifiers=s1[i];
                     identifiersList.add(s1[i]);
                     
                }
                else if(tokenResult==3)
                {                   
                    identifierValue=Integer.valueOf(s1[i]);
                    identifiersList.add(s1[i]);
                }
                else
                {
                    System.out.println("UNEXPECTED COMMAND");                   
                    continue OUTER;
                }                               
            } 
            int commandListLength=commandList.size();
            for(int i=0;i<commandList.size();i++)
            {
                System.out.println(commandList.get(commandListLength-1));
                commandListLength--;
                
                if(commandList.get(commandListLength-1).equals("ASSIGN"))
                {
                    assignValue();
                    if(identifiersList.size()==1)
                    {
                        System.out.println("Operation is not possible");
                    }
                    else if(identifiersList.size()==2)
                    {
                        String value1=identifiersList.get(0);
                        String value2=identifiersList.get(1);
                        int tokenResult1=checkedToken(value1);
                        int tokenResult2=checkedToken(value2);
                        if(tokenResult1==2) //if the variable is string
                        {
                            
                        }
                        else{
                            System.out.println("IT'S DOES NOT ASSIGN BECAUSE IT'S INTEGER VALUE");
                        }
                        
                        
                    }
                    else if(identifiersList.size()>2)
                    {
                        
                    }
                }
                else if(commandList.get(commandListLength-1).equals("ADD"))
                {
                     processExperssion("ADD");
                }
                else if(commandList.get(commandListLength-1).equals("SUB"))
                {
                    processExperssion("SUB");
                }
                else if(commandList.get(commandListLength-1).equals("MULT"))
                {
                    processExperssion("MULT");
                }
                else if(commandList.get(commandListLength-1).equals("DIV"))
                {
                    processExperssion("DIV");
                }
                else if(commandList.get(commandListLength-1).equals("POW"))
                {
                    processPowerStmt("POW");
                }
                else if(commandList.get(commandListLength-1).equals("PRINT"))
                {
                    printStatement();
                }      
                
            }
            for(int i=0;i<identifiersList.size();i++)
            {
                
            }
            
            
            
            
            
            
            if(selectedToken.equals("ASSIGN"))
            {
                assignValue();
            }
            else if(selectedToken.equals("ADD"))
            {
                 processExperssion("ADD");
            }
            else if(selectedToken.equals("SUB"))
            {
                processExperssion("SUB");
            }
            else if(selectedToken.equals("MULT"))
            {
                processExperssion("MULT");
            }
            else if(selectedToken.equals("DIV"))
            {
                processExperssion("DIV");
            }
            else if(selectedToken.equals("POW"))
            {
                processPowerStmt("POW");
            }
            else if(selectedToken.equals("PRINT"))
            {
                printStatement();
            }      
        }
    }
    
    private static void processExperssion(String command)
    {
        if(identifiers.equals("X"))
             {
                 if(x!=0)
                 {
                     //x=x-identifierValue;
                     executeStatementToX(command);
                     System.out.println("SUCCESS");
                 }
                 else{
                     System.out.println("NO VAL ASSIGN TO X");
                 }
             }
             else if(identifiers.equals("Y"))
             {
                 if(y!=0)
                 {
                     //y=y-identifierValue;
                     executeStatementToY(command);
                     System.out.println("SUCCESS");
                 }
                 else{
                     System.out.println("NO VAL ASSIGN TO Y");
                 }
             }
             else if(identifiers.equals("Z"))
             {
                 if(z!=0)
                 {
                     //z=z-identifierValue;
                     executeStatementToZ(command);
                     System.out.println("SUCCESS");
                 }
                 else{
                     System.out.println("NO VAL ASSIGN TO Z");
                 }
             }
    }

    private static void processPowerStmt(String powerStmt)
    {
         if(identifiers.equals("X"))
             {
                 if(x!=0)
                 {
                     //x=x-identifierValue;
                     x=(int)Math.pow(x, identifierValue);
                     System.out.println("SUCCESS");
                 }
                 else{
                     System.out.println("NO VAL ASSIGN TO X");
                 }
             }
             else if(identifiers.equals("Y"))
             {
                 if(y!=0)
                 {
                     //y=y-identifierValue;
                     y=(int)Math.pow(y, identifierValue);
                     System.out.println("SUCCESS");
                 }
                 else{
                     System.out.println("NO VAL ASSIGN TO Y");
                 }
             }
             else if(identifiers.equals("Z"))
             {
                 if(z!=0)
                 {
                     //z=z-identifierValue;
                     z=(int)Math.pow(z, identifierValue);
                     System.out.println("SUCCESS");
                 }
                 else{
                     System.out.println("NO VAL ASSIGN TO Z");
                 }
             }
    }

    private static void executeStatementToX(String stmt)
    {
        if(stmt.equals("ADD"))
            x=x+identifierValue;
        else if(stmt.equals("SUB"))
            x=x-identifierValue;
        else if(stmt.equals("MULT"))
            x=x*identifierValue;
        else if(stmt.equals("DIV"))
            x=x/identifierValue;
        else if(stmt.equals("ASSIGN"))
            x=identifierValue;          
    }
    private static void executeStatementToY(String stmt)
    {
        if(stmt.equals("ADD"))
            y=y+identifierValue;
        else if(stmt.equals("SUB"))
            y=y-identifierValue;
        else if(stmt.equals("MULT"))
            y=y*identifierValue;
        else if(stmt.equals("DIV"))
            y=y/identifierValue;
        else if(stmt.equals("ASSIGN"))
            y=identifierValue;          
    }
    private static void executeStatementToZ(String stmt)
    {
        if(stmt.equals("ADD"))
            z=z+identifierValue;
        else if(stmt.equals("SUB"))
            z=z-identifierValue;
        else if(stmt.equals("MULT"))
            z=z*identifierValue;
        else if(stmt.equals("DIV"))
            z=z/identifierValue;
        else if(stmt.equals("ASSIGN"))
            z=identifierValue;
    }

    private static void assignValue()
    {
        if(identifiers.equals("X"))
         {
             if(x==0)
             {
                 x=identifierValue;
                 System.out.println("SUCCESS");
             }
         }
         else if(identifiers.equals("Y"))
         {
             if(y==0)
             {
                 y=identifierValue;
                 System.out.println("SUCCESS");
             }
         }
         else if(identifiers.equals("Z"))
         {
             if(z==0)
             {
                 z=identifierValue;
                 System.out.println("SUCCESS");
             }

         }
    }

    private static int checkedToken(String token)
    {
     for(int i=0;i<operators.length;i++)
     {
         if(token.equals(operators[i]))
         {
             return 1;
         }
     }
     for(int i=0;i<variables.length;i++)
     {
         if(token.equals(variables[i]))
         {
             return 2;
         }
     }
     //Todo if the token is integer type
     if(token.matches("-?\\d+(\\.\\d+)?"))
         return 3;

     return 0;

 }

    private static void printStatement()
     {
         if(identifiers.equals("X"))
         {
             System.out.println("PRINTING X "+x);
         }
         else if(identifiers.equals("Y"))
         {
             System.out.println("PRINTING Y "+y);
         }
         else if(identifiers.equals("Z"))
         {
             System.out.println("PRINTING Z "+z);
         }
     }

    
    
}
