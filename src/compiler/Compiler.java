/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author WCS
 */
public class Compiler {
   static String[] operators={"ADD","SUB","MULT","DIV","POW","ASSIGN","PRINT"};
    static String[] variables={"X","Y","Z"};
    static int x=0,y=0,z=0;
    static int assignTagX=0,assignTagY=0,assignTagZ=0;
    static String identifiers="",selectedToken="",pointZ="";
    static int identifierValue;
    static boolean isValid=false,isAssignmentOperator=true;
    static ArrayList<String> commandList=new ArrayList<>();
    static ArrayList<String> identifiersList=new ArrayList<>();
    static ArrayList<Integer> identifiersValue=new ArrayList<>();
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan=new Scanner(System.in);
        System.out.println("WRITE THE OPERATION  : ");
        OUTER:
        while(true)
        {
            
            String operation=scan.nextLine();
            String[] s1=operation.split("[ ,:]");
            //Todo laxical anlysis to this block
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
                    commandList.clear();
                    identifiersList.clear();
                    continue OUTER;
                }                               
            } 
            //End of laxical analysis
            
            //Todo this block check the command then perform for this operation
            int commandListLength=commandList.size();
            for(int i=0;i<commandList.size();i++)
            {
               
                if(commandList.get(commandListLength-1).equals("ASSIGN"))
                {
                    //assignValue();
                    if(identifiersList.size()==1)
                    {
                        System.out.println("OPERATION IS NOT POSSIBLE !!");
                    }
                    else if(identifiersList.size()>=2)
                    {
                        String value1=identifiersList.get(0);
                        String value2=identifiersList.get(1);
                        int tokenResult1=checkedToken(value1);
                        int tokenResult2=checkedToken(value2);
                        if(tokenResult1==2) //if the variable is string
                        {
                            assignNewValue(value1,value2); 
                        }
                        else{
                            System.out.println("IT'S DOES NOT ASSIGN BECAUSE IT'S INTEGER VALUE");
                        }                
                    }
              
                }
                
                else if(commandList.get(commandListLength-1).equals("ADD"))
                {
                   executeOfOperation("ADD");
                }
                else if(commandList.get(commandListLength-1).equals("SUB"))
                {
                   executeOfOperation("SUB");
                }
               else if(commandList.get(commandListLength-1).equals("DIV"))
                {
                   executeOfOperation("DIV");
                }
               else if(commandList.get(commandListLength-1).equals("MULT"))
                {
                    executeOfOperation("MULT");
                }
                else if(commandList.get(commandListLength-1).equals("POW"))
                {
                  executeOfOperation("POW");
                }
                else if(commandList.get(commandListLength-1).equals("PRINT"))
                {
                    printStatement();
                }   
                
                commandListLength--;  
            }
            //End this block check the command then perform for this operation
            
            //Clear the list when execute the operation
            commandList.clear();
            identifiersList.clear();       
        }
    }
    
    private static void executeOfOperation(String command)
    {
         
        if(identifiersList.size()==1)
        {
            System.out.println("OPERATION IS NOT POSSIBLE !!");
        }
        else if(identifiersList.size()>=2)
        {
            String value1=identifiersList.get(0);
            String value2=identifiersList.get(1);
            int tokenResult1=checkedToken(value1);
            int tokenResult2=checkedToken(value2);
            if(tokenResult1==2) //if the variable is string
            {
                if(command.equals("ADD"))
                     addNewValue(value1,value2,"ADD"); 
                else if(command.equals("SUB"))
                     addNewValue(value1,value2,"SUB");
                else if(command.equals("MULT"))
                     addNewValue(value1,value2,"MULT");
                else if(command.equals("DIV"))
                     addNewValue(value1,value2,"DIV");
                else if(command.equals("POW"))
                     addNewValue(value1,value2,"POW");
            }
            else{
                System.out.println("IT'S DOES NOT ASSIGN BECAUSE IT'S INTEGER VALUE");
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
         int checkIdentifier=checkedToken(identifiersList.get(0));
         if(identifiersList.get(0).equals("X"))
         {
             System.out.println("PRINTING X "+x);
         }
         else if(identifiersList.get(0).equals("Y"))
         {
             System.out.println("PRINTING Y "+y);
         }
         else if(identifiersList.get(0).equals("Z"))
         {
             System.out.println("PRINTING Z "+z);
         }
         else if(checkIdentifier==3)
         {
             System.out.println("PRINTING  "+identifiersList.get(0));
         }
     }

    //added new code
    private static void assignNewValue(String value1, String value2) {
         if(value1.equals("X"))
         {
             assignTagX=1; //If assign any value to x then its have to tag is 1
             int variableType=checkedToken(value2);
             if(variableType==1 || variableType==2 || variableType==3) 
             {
                 identifiersList.remove(0);
                 identifiersList.remove(0);
                 identifiersList.add(0, "X");
             }
             if(variableType==3)
             {
                 x=Integer.valueOf(value2);
                 System.out.println("SUCCESS");
             }
             if(value2.equals("X"))
             {
                 //remove first two item from the identifier list
                
                 x=x;
                 System.out.println("SUCCESS");
             }
             else if(value2.equals("Y"))
             {
               
                 if(assignTagY==1)
                 {
                     x=y;
                    System.out.println("SUCCESS");
                 }
                 else{
                     System.out.println("NO VALUE ASSIGN TO Y");
                 }
             }
             else if(value2.equals("Z"))
             {
                 
                 if(assignTagZ==1)
                 {
                     x=z;
                    System.out.println("SUCCESS");
                 }
                 else{
                     System.out.println("NO VALUE ASSIGN TO Z");
                 }
             }           
         }
         else if(value1.equals("Y"))
         {
             assignTagY=1; //If assign any value to x then its have to tag is 1
             int variableType=checkedToken(value2);
             if(variableType==1 || variableType==2 || variableType==3) 
             {
                 identifiersList.remove(0);
                 identifiersList.remove(0);
                 identifiersList.add(0, "Y");
             }
             if(variableType==3)
             {
      
                 y=Integer.valueOf(value2);
                 System.out.println("SUCCESS");
             }
         
             if(value2.equals("X"))
             {
                 if(assignTagX==1)
                 {
                     y=x;
                     System.out.println("SUCCESS");
                 }
                 else{
                      System.out.println("NO VALUE ASSIGN TO X");
                 }
             }
             else if(value2.equals("Y"))
             {
                 y=y;
                 System.out.println("SUCCESS");
             }
             else if(value2.equals("Z"))
             {
                 
                 
                 if(assignTagZ==1)
                 {
                     y=z;
                    System.out.println("SUCCESS");
                 }
                 else{
                      System.out.println("NO VALUE ASSIGN TO Z");
                 }
             }            
         }
         else if(value1.equals("Z"))
         {
             assignTagZ=1; //If assign any value to x then its have to tag is 1
             int variableType=checkedToken(value2);
             if(variableType==1 || variableType==2 || variableType==3) 
             {
                 identifiersList.remove(0);
                 identifiersList.remove(0);
                 identifiersList.add(0, "Z");
             }
             if(variableType==3)
             {
      
                 z=Integer.valueOf(value2);
                 System.out.println("SUCCESS");
             }
            if(value2.equals("X"))
             {
                 
                 //System.out.println("SUCCESS");
                 
                 if(assignTagX==1)
                 {
                     z=x;
                     System.out.println("SUCCESS");
                 }
                 else{
                      System.out.println("NO VALUE ASSIGN TO X");
                 }
                 
             }
             else if(value2.equals("Y"))
             {
                 
                 //System.out.println("SUCCESS");
                 
                 if(assignTagY==1)
                 {
                    z=y;
                    System.out.println("SUCCESS");
                 }
                 else{
                     System.out.println("NO VALUE ASSIGN TO Y");
                 }
             }
             else if(value2.equals("Z"))
             {
                 
                 //System.out.println("SUCCESS");
                 
                 if(assignTagZ==1)
                 {
                    z=z;
                    System.out.println("SUCCESS");
                 }
                 else{
                      System.out.println("NO VALUE ASSIGN TO Z");
                 }
             }
         }
    }

    private static void addNewValue(String value1, String value2,String command) {
         if(value1.equals("X"))
         {
             int variableType=checkedToken(value2);
             if(variableType==1 || variableType==2 || variableType==3) 
             {
                 identifiersList.remove(0);
                 identifiersList.remove(0);
                 identifiersList.add(0, "X");
             }
             if(variableType==3)
             {
                 if(assignTagX==1)
                 {
                      //assignNewToX(command,x);
                    if(command.equals("ADD"))
                       x+=Integer.valueOf(value2);
                    else if(command.equals("SUB"))
                        x-=Integer.valueOf(value2);
                    else if(command.equals("MULT"))
                        x*=Integer.valueOf(value2);
                    else if(command.equals("DIV"))
                        x/=Integer.valueOf(value2);
                    else if(command.equals("POW"))
                        x=(int)Math.pow(x, Integer.valueOf(value2));
                    //assignNewValue
                    System.out.println("SUCCESS");
                 }
                 else{
                     System.out.println("NO VAL ASSIGN TO X");
                 }
             }
             if(value2.equals("X"))
             {
                 //remove first two item from the identifier list
                 if(assignTagX==1)
                 {
                    if(command.equals("ADD"))
                        x+=x;
                     else if(command.equals("SUB"))
                         x-=x;
                     else if(command.equals("MULT"))
                         x*=x;
                     else if(command.equals("DIV"))
                         x/=x;
                    else if(command.equals("POW"))
                        x=(int)Math.pow(x,x);
                     System.out.println("SUCCESS");
                 }
                 else{
                     System.out.println("NO VAL ASSIGN TO X");
                 }
             }
             else if(value2.equals("Y"))
             {
                 if(assignTagX==1 && assignTagY==1)
                 {
                    if(command.equals("ADD"))
                    x+=y;
                    else if(command.equals("SUB"))
                        x-=y;
                    else if(command.equals("MULT"))
                        x*=y;
                    else if(command.equals("DIV"))
                        x/=y;
                    else if(command.equals("POW"))
                        x=(int)Math.pow(x,y);
                   // System.out.println("SUCCESS");
                    //x+=y;
                    System.out.println("SUCCESS");
                 }
                 
                 else{
                     System.out.println("NO VAL ASSIGN TO X OR Y");
                 }                
             }
         
             else if(value2.equals("Z"))
             {
                 if(assignTagZ==1 && assignTagX==1)
                 {
                     if(command.equals("ADD"))
                        x+=z;
                     else if(command.equals("SUB"))
                         x-=z;
                     else if(command.equals("MULT"))
                         x*=z;
                     else if(command.equals("DIV"))
                         x/=z;
                     else if(command.equals("POW"))
                        x=(int)Math.pow(x,z);
                     //x+=z;
                     System.out.println("SUCCESS");
                 }
                 else{
                     System.out.println("NO VAL ASSIGN TO X OR Z");
                 }
             }            
         }
         else if(value1.equals("Y"))
         {
             int variableType=checkedToken(value2);
             if(variableType==1 || variableType==2 || variableType==3) 
             {
                 identifiersList.remove(0);
                 identifiersList.remove(0);
                 identifiersList.add(0, "Y");
             }
             if(variableType==3)
             {
                 if(assignTagY==1)
                 {
                    if(command.equals("ADD"))
                        y+=Integer.valueOf(value2);
                    else if(command.equals("SUB"))
                        y-=Integer.valueOf(value2);
                    else if(command.equals("MULT"))
                        y*=Integer.valueOf(value2);
                    else if(command.equals("DIV"))
                        y/=Integer.valueOf(value2);
                    else if(command.equals("POW"))
                        y=(int)Math.pow(y,Integer.valueOf(value2));
                    //y+=Integer.valueOf(value2);
                    System.out.println("SUCCESS");
                 }
                  else{
                     System.out.println("NO VAL ASSIGN TO Y");
                 }
             }
         
             if(value2.equals("X"))
             {
                 if(assignTagY==1 && assignTagX==1)
                 {
                     if(command.equals("ADD"))
                        y+=x;
                    else if(command.equals("SUB"))
                        y-=x;
                    else if(command.equals("MULT"))
                        y*=x;
                    else if(command.equals("DIV"))
                        y/=x;
                     else if(command.equals("POW"))
                        y=(int)Math.pow(y,x);
                 //y+=x;
                 System.out.println("SUCCESS");
                 }
                  else{
                     System.out.println("NO VAL ASSIGN TO X OR Y");
                 }
             }
             else if(value2.equals("Y"))
             {
                 if(assignTagY==1)
                 {
                     if(command.equals("ADD"))
                        y+=y;
                     else if(command.equals("SUB"))
                         y-=y;
                     else if(command.equals("MULT"))
                         y*=y;
                     else if(command.equals("DIV"))
                         y/=y;
                     else if(command.equals("POW"))
                        y=(int)Math.pow(y,y);
                     //y+=y;
                     System.out.println("SUCCESS");
                 }
                  else{
                     System.out.println("NO VAL ASSIGN TO Y");
                 }                
             }
             else if(value2.equals("Z"))
             {
                 if(assignTagY==1 && assignTagZ==1)
                 {
                     if(command.equals("ADD"))
                        y+=z;
                     else if(command.equals("SUB"))
                         y-=z;
                     else if(command.equals("MULT"))
                         y*=z;
                     else if(command.equals("DIV"))
                         y/=z;
                     else if(command.equals("POW"))
                        y=(int)Math.pow(y,z);
                     //y+=z;
                     System.out.println("SUCCESS");
                 }
                  else{
                     System.out.println("NO VAL ASSIGN TO Y");
                 }
             }             
         }
         else if(value1.equals("Z"))
         {
             int variableType=checkedToken(value2);
             if(variableType==1 || variableType==2 || variableType==3) 
             {
                 identifiersList.remove(0);
                 identifiersList.remove(0);
                 identifiersList.add(0, "Z");
             }
             if(variableType==3)
             {
                 if(assignTagZ==1)
                 {
                    if(command.equals("ADD"))
                        z+=Integer.valueOf(value2);
                     else if(command.equals("SUB"))
                         z-=Integer.valueOf(value2);
                     else if(command.equals("MULT"))
                         z*=Integer.valueOf(value2);
                     else if(command.equals("DIV"))
                         z/=Integer.valueOf(value2);
                    else if(command.equals("POW"))
                        z=(int)Math.pow(z,Integer.valueOf(value2));

                     //z+=Integer.valueOf(value2);
                     System.out.println("SUCCESS");
                     }
                  else{
                     System.out.println("NO VAL ASSIGN TO Z");
                 }
             }
            if(value2.equals("X"))
             {
                  if(assignTagZ==1 && assignTagX==1)
                  {
                     if(command.equals("ADD"))
                        z+=x;
                     else if(command.equals("SUB"))
                         z-=x;
                     else if(command.equals("MULT"))
                         z*=x;
                     else if(command.equals("DIV"))
                         z/=x;
                     else if(command.equals("POW"))
                        z=(int)Math.pow(z,x);
                    // z+=x;
                     System.out.println("SUCCESS");
                  }
                  else{
                     System.out.println("NO VAL ASSIGN TO Z OR X");
                 }
             }
             else if(value2.equals("Y"))
             {
                 if(assignTagZ==1 && assignTagY==1)
                  {
                     if(command.equals("ADD"))
                        z+=y;
                     else if(command.equals("SUB"))
                         z-=y;
                     else if(command.equals("MULT"))
                         z*=y;
                     else if(command.equals("DIV"))
                         z/=y;
                     else if(command.equals("POW"))
                        z=(int)Math.pow(z,y);
                     //z+=y;
                     System.out.println("SUCCESS");
                  }
                  else{
                     System.out.println("NO VAL ASSIGN TO Z OR Y");
                 }
             }
             else if(value2.equals("Z"))
             {
                 if(assignTagZ==1)
                  {
                     if(command.equals("ADD"))
                        z+=z;
                     else if(command.equals("SUB"))
                         z-=z;
                     else if(command.equals("MULT"))
                         z*=z;
                     else if(command.equals("DIV"))
                         z/=z;
                     else if(command.equals("POW"))
                        z=(int)Math.pow(z,z);
                    // z+=z;
                     System.out.println("SUCCESS");
                  }
                  else{
                     System.out.println("NO VAL ASSIGN TO Z");
                 }
             }
         }
    }

}
