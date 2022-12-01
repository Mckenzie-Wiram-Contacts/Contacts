

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


//Show all your contacts
//        Add a new contact
//        Search a contact by her name
//        Delete an existing contact
//The text file should contain one contact per line.
//        When the application starts, the contact list should be read from the file.
//        Before the application exits, the contacts file should be rewritten.
//        The user interface for your application should include a main menu like the following, where the user will need to enter a number between 1 and 5:
public class PhoneBook {

    public static boolean checkName(String name, Path dataFile) throws IOException {


        List<String> contactsList = Files.readAllLines(dataFile);
        for (String line : contactsList) {
            System.out.println(line);


                if (line.contains(name)) {
                    return true;
                }

            }

        return false;
    }


    public static String formatNum(String num) {
        if (num.length() == 7) {
           String first = num.substring(0, 3);
           String second = num.substring(3, 7);
           String result = first + "-" + second;
           return result;
        }
        else if (num.length() == 10) {
            String first = num.substring(0, 3);
            String second = num.substring(3, 6);
            String third = num.substring(6, 10);
            String result = "(" + first + ")-" + second + "-" + third;
            return result;
        }
        return "Invalid Number";
    }
public static void getContacts() throws IOException {
    String directory = "src";
    String filename = "contacts.txt";

    Path dataDirectory = Paths.get(directory);
    Path dataFile = Paths.get(directory, filename);

    if (Files.notExists(dataDirectory)) {
        Files.createDirectories(dataDirectory);
    }

    if (!Files.exists(dataFile)) {
        Files.createFile(dataFile);
    }



    Input contact = new Input(new Scanner(System.in));
    System.out.println("1. View contacts.");
    System.out.println("2. Add a new contact.");
    System.out.println("3. Search a contact by name.");
    System.out.println("4. Delete an existing contact.");
    System.out.println("5. Exit.");
    System.out.println("Enter an option (1, 2, 3, 4 or 5): ");
    int choice = contact.getInt();

    if (choice == 1) {
        List<String> printListFromFile = Files.readAllLines(dataFile);
//            come back here to do print format
        System.out.printf("Name             |  Phone Number          |\n" );
        System.out.println("-------------------------------------------");
        for (int i = 0; i < printListFromFile.size(); i++) {
            int num = printListFromFile.get(i).indexOf("|") - 1;
            String first = printListFromFile.get(i).substring(0, num);
            String second = printListFromFile.get(i).substring(num + 3);



            System.out.printf("%-16s | %-22s |\n", first, second);

        }
        System.out.println("\n");
        getContacts();
    } else if (choice == 2) {
        Input something = new Input(new Scanner(System.in));
        System.out.println("What is the Name of the Contact You Would Like To Add?");
        String name = something.getString();
        System.out.println("What is the Phone Number for " + name);
        String phoneNumber = something.getString();
        phoneNumber = formatNum(phoneNumber);


        int index = 0;
        boolean bool = false;
        List<String> contactsList = Files.readAllLines(dataFile);
        for (int i = 0; i < contactsList.size(); i++) {
            if (contactsList.get(i).contains(name)){
                        System.out.println("Name is already in contacts want to overwrite?");
                        bool = something.yesNo();
                        index = i;


                    }
                }
        if (bool == true) {
            contactsList.set(index, name + " | " + phoneNumber);
            Files.write(dataFile, contactsList);
            getContacts();
        } else {
            System.out.println("Contact Added");
            Files.write(dataFile, Arrays.asList(name + " | " + phoneNumber), StandardOpenOption.APPEND);
            getContacts();

        }






    } else if (choice == 3) {
        Input name = new Input(new Scanner(System.in));
        System.out.println("Which Contact are you looking for?");
        String search = name.getString();
        List<String> contactsList = Files.readAllLines(dataFile);
        for (String line : contactsList) {
            try {
                String something = line.substring(0, search.length());
                if (something.equalsIgnoreCase(search)) {
                    System.out.println(line);
                }
            } catch (Exception e) {
            }
        }
        getContacts();
    }else if (choice == 4) {
        System.out.println("Which contact do you want to delete?");
        Input name = new Input(new Scanner(System.in));
        String search = name.getString();
        List<String> contactsList = Files.readAllLines(dataFile);
        int deleteSomething = 0;
        for (String line : contactsList) {
            try {


                String something = line.substring(0, search.length());
                if (something.equalsIgnoreCase(search)) {
                    deleteSomething = contactsList.indexOf(line);
                }
            } catch (Exception e) {
            }
        }
        contactsList.remove(deleteSomething);
        Files.write(dataFile, contactsList);
        getContacts();
    }
}
    public static void main(String[] args) throws IOException {
       getContacts();
    }
}
