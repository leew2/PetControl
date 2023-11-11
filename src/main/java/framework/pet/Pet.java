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
            System.out.print("Your Choice: ");
            user = menuScan.nextInt();
            
            if(user == 7){
                break;
            }
            switch(user){
                case 1 -> showPet();
                case 2 -> addPet();
                case 3 -> updatePet();
                case 4 -> removePet();
                case 5 -> searchPetName();
                case 6 -> searchPetAge();
                default -> System.out.println("Not In Option");
            }
            
            
        }   //  End of (Main)While loop
    }   //  End of Main class
    
    public static void addPet(){
        Scanner input = new Scanner(System.in);
        petObj pet;
        
        System.out.println("Enter 'Done' to exit");
        while(true){
            
            System.out.print("(Name) (Age): ");
            String petName = input.nextLine();
            String[] nameAge = petName.split(" ");
            if("done".equals(nameAge[0])){
                break;
            }
            pet = new petObj(nameAge[0], Integer.parseInt(nameAge[1]));
            collection.add(pet);
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
        System.out.println("Removing " + collection.get(id).getName() + " " + collection.get(id).getAge());
        collection.remove(id);
        
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
        System.out.println("3) Update Existing Pets");
        System.out.println("4) Remove Existing Pets");
        System.out.println("5) Search For Pets Using Name");
        System.out.println("6) Search For Pets Using Age");
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
