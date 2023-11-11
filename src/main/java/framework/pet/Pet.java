/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package framework.pet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WangL
 */
public class Pet {
    private static ArrayList<petObj> collection = new ArrayList(); 
    private static String fileName = "petfile"; //  .txt is added in code
    
    public static void main(String[] args) throws CustomError {
        Scanner menuScan = new Scanner(System.in);
        int user;
        loadFile();
        
        while(true){
            showMenu();
            System.out.print("Your Choice: ");
            user = menuScan.nextInt();
            
            if(user == 7){
                break;
            }
            switch(user){
                case 1 -> showPet();
                case 2 -> addPet();
                //case 3 -> updatePet();
                case 4 -> removePet();
                //case 5 -> searchPetName();
                //case 6 -> searchPetAge();
                default -> System.out.println("Not An Option");
            }
            
            
        }   //  End of (Main)While loop
        saveFile();
    }   //  End of Main class
    
    public static void addPet() throws CustomError{
        Scanner input = new Scanner(System.in);
        petObj pet;
        
        System.out.println("Enter 'Done' to exit");
        while(true){
            
            System.out.print("(Name) (Age): ");
            
            //  Checks if there is enough space to add
            try{
                String petName = input.nextLine();
                String[] nameAge = petName.split(" ");
                if("done".equals(nameAge[0])){
                    break;
                }
                CustomError.checkValue(nameAge);
                CustomError.checkMax(collection);                               //  Checks for max size
                CustomError.checkAge(Integer.parseInt(nameAge[1]));                               //  Checks for Min-Max age
                
                
                //  Does the Process
                pet = new petObj(nameAge[0], Integer.parseInt(nameAge[1]));
                collection.add(pet);
            }catch(CustomError e){
                System.out.println(e);
            }catch(ArrayIndexOutOfBoundsException e){
                throw new CustomError("You need to Add a (age)\nExample: tom 2");
            }catch(NumberFormatException e){
                throw new CustomError(" Pet Needs An Age\nExample: tom 2");
            }
            
        }
    }
    
    public static void saveFile(){  //  THE FILE GETS REPLACED AND OVERWRITE SO ANY ONFO ON THE PREVS FILE WILL GET LOST
        try{    
            File file = new File(fileName+".txt");
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
            PrintWriter write = new PrintWriter(fileName+".txt");
            for(var i: collection){
                write.print(i.getName() + " " + i.getAge()+"\n");
            }
            write.close();
            
        }catch(IOException e){
            System.out.println("Saving To File");
        }
    }
    
    public static void loadFile() throws CustomError{
        String[] nameAge;
        try{
            File file = new File(fileName + ".txt");
            Scanner reader = new Scanner(file);
            
            int fileSize = 0;
            
            while(reader.hasNext()){
                fileSize++;
                System.out.println("loading to file");
                nameAge = (reader.nextLine()).split(" ");
                
                CustomError.checkValue(nameAge);
                CustomError.checkAge(Integer.parseInt(nameAge[1]));
                
                
                petObj pet = new petObj(nameAge[0], Integer.parseInt(nameAge[1]));
                collection.add(pet);
                
                //  This checks the size while writing to array, will stop at 5
                if(fileSize >= 6){
                    throw new CustomError("File Too Large, More then 5 pets Detected");
                }
            }
            
            reader.close();
            
        }catch(FileNotFoundException e){
            System.out.println("File: " + fileName + ".txt is Not Found");
        }catch(CustomError e){
            System.out.println(e);
        }catch(ArrayIndexOutOfBoundsException e){
            throw new CustomError("You need to Add a (age)\nExample: tom 2");
        }catch(NumberFormatException e){
            throw new CustomError(" Pet Needs An Age\nExample: tom 2");
        }
        
    }
    
    public static void updatePet(){
        Scanner input = new Scanner(System.in);
        Scanner nameInput = new Scanner(System.in);
        showPet();
        System.out.print("Enter The 'ID' Of The Pet You Want To Rename: ");
        int id = input.nextInt();
        System.out.print("Enter The New Name And Age: \n(Name) (Age): ");
        String pet = nameInput.nextLine();
        String[] nameAge = pet.split(" ");
        System.out.println("Changing "+ collection.get(id).getName() + " " + collection.get(id).getAge() + " To " + nameAge[0] + " " + nameAge[1]);
        collection.get(id).setName(nameAge[0]);
        collection.get(id).setAge(Integer.parseInt(nameAge[1]));
    }
    
    public static void removePet(){
        Scanner input = new Scanner(System.in);
        showPet();
        System.out.print("Enter ID Of Pet To Remove: ");
        int id = input.nextInt();
        
        try{
            CustomError.checkID(id, collection);
            
            System.out.println("Removing " + collection.get(id).getName() + " " + collection.get(id).getAge());
            collection.remove(id);
        }catch(CustomError e){
            System.out.println(e);
        }
        
        
    }
    
    public static void searchPetName(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter A Name To Search: ");
        String name = input.nextLine();
        
        showBreaker();
        System.out.printf("%s %2s %s %-10s %s %3s %s", "|","ID","|","Name","|","Age","|");
        showBreaker();
        for(int i =0; i < collection.size(); i++){
            if(collection.get(i).getName().equals(name)){
                System.out.printf("%s %2s %s %-10s %s %3s %s%n", "|",i,"|",collection.get(i).getName(),"|",collection.get(i).getAge(),"|");
            }
        }
        showBreaker();
        
    }
    
    private static void searchPetAge() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter A Name To Search: ");
        int age = input.nextInt();
        
        showBreaker();
        System.out.printf("%s %2s %s %-10s %s %3s %s", "|","ID","|","Name","|","Age","|");
        showBreaker();
        for(int i =0; i < collection.size(); i++){
            if(collection.get(i).getAge() == age){
                System.out.printf("%s %2s %s %-10s %s %3s %s%n", "|",i,"|",collection.get(i).getName(),"|",collection.get(i).getAge(),"|");
            }
        }
        showBreaker();
    }
    
    public static void showMenu(){
        System.out.println("1) View All Pets");
        System.out.println("2) Add More Pets");
        
        System.out.println("4) Remove Existing Pets");
        
        
        System.out.println("7) Exit");
    }    
    
    public static void showPet(){
        showBreaker();
        System.out.printf("%s %2s %s %-10s %s %3s %s%n", "|","ID","|","Name","|","Age","|");
        showBreaker();
        for(int i =0; i < collection.size(); i++){
            System.out.printf("%s %2s %s %-10s %s %3s %s%n", "|",i,"|",collection.get(i).getName(),"|",collection.get(i).getAge(),"|");
        }
        showBreaker();
    }
    
    public static void showBreaker(){
        System.out.printf("%s%23s%s%n", "+","-----------------------","+");
    }

    
}
