package Midterm;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class PeopleData {

    private Scanner input = new Scanner(System.in);
    private BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
    private ArrayList<ArrayList<String>> book = new ArrayList<ArrayList<String>>();
    
    private String headerFormat = "| %-6s| %-26s| %-6s|\n";
    private String boxFormat = "+=======+===========================+=======+";
    private String boxFormatTwo = "+-------+---------------------------+-------+";

    public void menu() {
        int choice = 0;
        //boolean isNum = false;
        do {
            do {
                try {
                    System.out.println("+===========================================+");
                    System.out.println("|               PEOPLE'S DATA               |");
                    System.out.println("+===========================================+");
                    System.out.println("| 1) Add Entry                              |");
                    System.out.println("+-------------------------------------------+");
                    System.out.println("| 2) Delete Entry                           |");
                    System.out.println("+-------------------------------------------+");
                    System.out.println("| 3) Search Entry                           |");
                    System.out.println("+-------------------------------------------+");
                    System.out.println("| 4) Update Entry                           |");
                    System.out.println("+-------------------------------------------+");
                    System.out.println("| 5) Delete All Entries                     |");
                    System.out.println("+-------------------------------------------+");
                    System.out.println("| 6) View All Entries                       |");
                    System.out.println("+-------------------------------------------+");
                    System.out.println("| 7) Exit                                   |");
                    System.out.println("+===========================================+");
                    
                    System.out.print("Enter choice: ");
                    String strChoice = input.next();
                    choice = Integer.parseInt(strChoice);
                    
                    if (choice > 7 || choice < 0) {
                        System.err.println("Invalid input. Enter number from 1 to 7.");
                        input.nextLine();
                        System.out.println("");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid input. Enter number from 1 to 7.");
                    input.nextLine();
                    System.out.println("");
                    
                }
            } while (choice > 7 || choice < 0);
            switch (choice) {
                case 1:
                    addEntry();
                    break;
                case 2:
                    deleteEntry();
                    enterToContinue();
                    break;
                case 3:
                    searchEntry("Enter name to be searched: ", "Would you like to search a different data? (y/n): " , 
                            "You can't search at this moment. There are no data stored.", "SEARCH ENTRY");
                    enterToContinue();
                    break;
                case 4:
                    updateEntry();
                    enterToContinue();
                    break;
                case 5:
                    deleteAllEntries();
                    break;
                case 6:
                    viewAllEntries();
                    break;
                case 7:
                    exit();
                    break;
            }
        } while (choice < 7 || choice > 0);
    }

    //==========================ADD ENTRY=================================
    public void addEntry() {
        System.out.println("");
        methodsHeader("ADD ENTRY");
        try {
            System.out.print("Enter name: ");
            String name = dataIn.readLine();
            book.add(new ArrayList());
            book.get(book.size()-1).add(name);
        }
        catch (IOException e){
            System.err.println(e.toString());
        }
        boolean isNum;
        do {
            System.out.print("Enter age: ");
            if (input.hasNextInt()) {
                int age = input.nextInt();
                isNum = true;
                book.get(book.size()-1).add(Integer.toString(age));
                System.out.println("Data has been added successfully.");
            }
            else {
                System.err.println("Invalid input.");
                isNum = false;
                input.next();
            }
        } while (!(isNum));
        enterToContinue();
    }

    //===========================EXIT===============================
    public void exit() {
        String toExit;
        boolean escapeLoop = true;
        do {
            try {
                System.out.print("Are you sure to exit? (y/n): ");
                toExit = dataIn.readLine();
                
                if (toExit.equals("y")) {
                    System.exit(0);
                }
                else if (toExit.equals("n")) {
                    escapeLoop = true;
                    menu();
                }
                else if (!(toExit.equalsIgnoreCase("y")) || !(toExit.equalsIgnoreCase("n"))) {
                    System.err.println("Invalid input.");
                    escapeLoop = false;
                }
            }catch (Exception e) {
                System.err.println(e);
            }
        } while(!(escapeLoop));
    }

    //=========================DELETE ALL============================
    public void deleteAllEntries() {
        if (book.isEmpty()) {
            System.out.println("You can't delete data at this moment. There were no data stored.");
            enterToContinue();
        }
        else {
            //are u sure to delete?
            String toDeleteAll = "n";
            boolean escapeLoop = false;
            do {
                try {
                System.out.print("Are you sure to delete all entries? (y/n): ");
                toDeleteAll = dataIn.readLine();
                
                    if (toDeleteAll.equalsIgnoreCase("y")) {
                        book.clear();
                        System.out.println("All entries have been successfully deleted.");
                        escapeLoop = true;
                    }
                    else if (toDeleteAll.equalsIgnoreCase("n")) {
                        escapeLoop = true;
                        menu();
                    }
                    else {
                        System.out.println("Invalid input.");
                    }
                } 
                catch (IOException e) {
                    System.err.println(e);
                }
            } while (!(escapeLoop));
        enterToContinue();
        }
        
    }

    //=========================VIEW ALL==============================
    public void viewAllEntries() {
        if (book.isEmpty()) {
            System.out.println("There are no data stored. You can't view at this moment.");
        }
        else {
            System.out.println("");
            headerDisplay("VIEW ALL ENTRIES");
            for (int i = 0; i < book.size(); i++) {
                for (int j = 0; i < book.size(); i++) {
                    dataDisplay(i, j);
                }
            }
        }
        enterToContinue();
    }

    //=========================SEARCH==============================
    public int searchEntry(String instruction, String search, String warning, String methodHeader) {
        
        int counter = 0;
        if (book.isEmpty()) {
            System.out.println(warning);
            enterToContinue();
        }
        else {
            methodsHeader(methodHeader);
            String searchAgain = "y";
            while(searchAgain.equalsIgnoreCase("y")) {
                try {
                System.out.print(instruction); //Enter name to be <UPDATED, SEARCHED, DELETED>
                String toSearch = dataIn.readLine();

                for (int i = 0; i < book.size(); i++) {
                    for (int j = 0; i < book.size(); i++) {
                        if (toSearch.equalsIgnoreCase(book.get(i).get(j))) {
                            System.out.println("");
                            headerDisplay("MATCHED ENTRIES");
                            dataDisplay(i, j);
                            counter++;
                        }
                    }
                }
                if (counter == 0) {
                    System.out.println("Data does not exist.");
                    do {
                        System.out.print(search);
                        searchAgain = dataIn.readLine();
                        if ((searchAgain.equalsIgnoreCase("y")) || (searchAgain.equalsIgnoreCase("n"))) {
                            break;
                        }
                    } while (!(searchAgain.equalsIgnoreCase("y")) || !(searchAgain.equalsIgnoreCase("n")));
                }
                else {
                   break; 
                }
            } catch (IOException e) {
                System.err.println(e);
                }   
            }
        }
        return counter;
    }

    //=========================DELETE==============================
    public void deleteEntry() {
        if (book.isEmpty()) {
            System.out.println("You cannot delete at this moment because the data is empty.");
        }
        else {
            int counter = searchEntry("Enter name to be deleted: ", "Would you like to delete a different data? (y/n): ", 
                    "You can't dalete data at this moment. Theer are no data stored.", "DELETE ENTRY");

            //if duplicates exist
            if (counter != 0){
                boolean isNum;
                do {
                    try {
                        int index = 0;
                        do {
                            System.out.print("Enter the index of the item to be deleted: ");
                            index = Integer.parseInt(dataIn.readLine()) - 1;

                            if (index >= book.size()) {
                                System.out.println("There are " + book.size() + " items. Enter the index of the item you want to delete.");
                            }
                            else {
                                break;
                            }
                        } while(index >= book.size());

                        isNum = true;

                        book.remove(index);

                        System.out.println("Data has been successfully deleted!");
                    } catch (IOException e) {
                        System.out.println("Invalid input!");
                        isNum = false;
                        input.nextLine();
                    }
                } while (!(isNum));
            }
            else {
                menu();
            }
        }
    }
    //=========================UPDATE==============================
    public void updateEntry() {
        int counter = searchEntry("Enter name to be updated: ", "Would you like to update another data? (y/n): ", 
                "You can't update data at this moment. There are no data stored.", "UPDATE ENTRY");
        
        boolean isNum;
        if (counter != 0){
            do {
                try {
                    int index = 0;
                    do {
                        System.out.print("Enter the index of the item to be updated: ");
                        index = Integer.parseInt(dataIn.readLine()) - 1;
                        
                        if (index >= book.size()) {
                            System.out.println("There are " + book.size() + " items. Enter the index of the item you want to update.");
                        }
                        else {
                            break;
                        }
                    } while(index >= book.size());
                    isNum = true;

                    System.out.print("Enter updated name: ");
                    String newName = dataIn.readLine();
                    System.out.print("Enter updated age: ");
                    String newAge = dataIn.readLine();

                    book.get(index).set(0, newName);
                    book.get(index).set(1, newAge);

                    System.out.println("Data has been successfully updated!");
                } catch (IOException e) {
                    System.out.println("Invalid input!");
                    isNum = false;
                    input.nextLine();
                }
            } while (!(isNum));
        }
        else {
            System.out.println("");
            menu();
        }
    }
    
 //============================HEADER DISPLAY========================================
    public void headerDisplay (String header) {
        methodsHeader(header);
        System.out.printf(headerFormat, "Index", "Name", "Age");
        System.out.println(boxFormat);
    }
    
    //========================METHODS' HEADER ========================================
    public void methodsHeader (String header) {
        System.out.println("+===========================================+");
        System.out.printf("|                %1$-27s|\n", header);
        System.out.println("+===========================================+");
    }
 //============================DATA DISPLAY========================================
    public void dataDisplay(int i, int j) {
        System.out.printf(headerFormat, (i+1), book.get(i).get(j), book.get(i).get(j+1));
        System.out.println(boxFormatTwo);
    }
    
    //============================DATA DISPLAY========================================
    public void enterToContinue() {
        System.out.println("");
        System.out.print("Press Enter to continue...");
        try {
            System.in.read();
            System.out.println("");
        }
        catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    
    
}
