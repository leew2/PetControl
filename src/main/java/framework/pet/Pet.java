/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package framework.pet;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author WangL
 */
public class Pet {
    private static ArrayList<petObj> collection = new ArrayList(); 
    public static void main(String[] args) {
        Scanner menuScan = new Scanner(System.in);
        int user;
        while(true){
            showMenu();
            user = menuScan.nextInt();
            System.out.println("Your Choice: " + user);
            if(user == 7){
                break;
            }
            switch(user){
                case 1:
                    showPet();
                    break;
                case 2:
                    addPet();
                    break;
                default:
                    System.out.println("Not In Option");
            }
            
            
        }   //  End of (Main)While loop
    }   //  End of Main class
    
    public static void addPet(){
        Scanner input = new Scanner(System.in);
        petObj pet;
        
        System.out.println("Enter 'Done' to exit");
        while(true){
            
            System.out.print("(Name) (Age)");
            String petName = input.nextLine();
            String[] nameAge = petName.split(" ");
            if("done".equals(nameAge[0])){
                break;
            }
            pet = new petObj(nameAge[0], Integer.parseInt(nameAge[1]));
            collection.add(pet);
        }
    }
    
    public static void showMenu(){
        System.out.println("1) View All Pets");
        System.out.println("2) Add More Pets");
        System.out.println("7) Exit");
    }
    
    
    public static void showPet(){
        showBreaker();
        System.out.printf("%s %2s %s %-10s %s %3s %s", "|","ID","|","Name","|","Age","|");
        showBreaker();
        for(int i =0; i < collection.size(); i++){
            System.out.printf("%s %2s %s %-10s %s %3s %s%n", "|",i,"|",collection.get(i).getName(),"|",collection.get(i).getAge(),"|");
        }
        
    }
    
    public static void showBreaker(){
        System.out.printf("%n%s%23s%s%n", "+","-----------------------","+");
    }
}
