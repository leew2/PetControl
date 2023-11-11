/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package framework.pet;

import java.util.ArrayList;

/**
 *
 * @author WangL
 */
public class CustomError extends Exception{
    public CustomError(String s){
        super(s);
    }
    
    public static void checkMax(ArrayList<petObj> collection) throws CustomError{
        if(collection.size() == 5){
            throw new CustomError("Not Enough Space");
        }
    }
    
    public static void checkAge(int age) throws CustomError{
        if(age > 20 || age < 1){
            throw new CustomError("Pet Age cannot be over 20 OR under 1");
        }
    }
    
    public static void checkValue(String[] i) throws CustomError{
        if(i.length == 2){
            try{
                int j = Integer.parseInt(i[1]);
            }catch(NumberFormatException e){
                throw new CustomError("Error Detected!\nNeeds Number for Age\n Example: tom 2");
            }
        }else{
            if("done".equals((i[0].toLowerCase()))){
                //  nothing should happen since this is our exit
                //  BTW if this is checked after the escape, this is not needed BUT incase it is not in future code
            }else{
                //  if input not 'done' then any other input is incorrect (format wise)
                throw new CustomError("Error Detected!\nNeeds Name AND Age\nExample: tom 2");
            }
        }
    }
    
    //  Some Reason, despite NOT being in the Array, it stills passess ...i>collect.size... SOOOOOOOOO we just going to run the code and catch the outofbound to throw our custom
    public static void checkID(int i, ArrayList<petObj> collect) throws CustomError{
        System.out.println(collect.size());
        i++;
        try{
            if(collect.size() >= i && i >= 0){
                //  Nothing                
            }else{throw new IndexOutOfBoundsException();}
            
        }catch(IndexOutOfBoundsException e){
            i--;
            throw new CustomError( "Id "+i + " is not in Array" );
        }
    }
    
}
