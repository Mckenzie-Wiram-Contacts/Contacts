import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    public static void main(String[] args) throws IOException {
        String directory = "src";
        String filename = "contacts.txt";

        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);

        if (Files.notExists(dataDirectory)) {
            Files.createDirectories(dataDirectory);
        }

        if (! Files.exists(dataFile)) {
            Files.createFile(dataFile);
        }

        List<String> groceryList = new ArrayList<>();

        String item1 = "loaf of bread";

        groceryList.add(item1);
        groceryList.add("breakfast cereal");
        groceryList.add("cat food");
        groceryList.add("whole chicken");
        groceryList.add("asparagus (we are fancy)");

        Files.write(dataFile, groceryList);

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
            for (int i = 0; i < printListFromFile.size(); i++) {
                System.out.println(printListFromFile.get(i));
            }

        }
    }
}