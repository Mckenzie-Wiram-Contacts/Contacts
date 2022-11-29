

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Input {

    private Scanner scanner;

    public Input(Scanner scanner) {
        this.scanner = scanner;
    }
    public String getString() {

        return scanner.nextLine();
    }
    private boolean yesNo;
    public boolean yesNo() {
        System.out.println("Continue [Yes/No]: ");
        Pattern regex = Pattern.compile("^(y|yes)$", Pattern.CASE_INSENSITIVE);
        Matcher equals = regex.matcher(scanner.nextLine());
        boolean bool = equals.find();
        return bool;
    }

    public int getInt(int min, int max) {
        System.out.println("Give me a number between " +  min + " and " + max);
        int num = scanner.nextInt();
        if (num < min || num > max) {
            return getInt(min, max);
        } else {
            System.out.println(num);
            return num;
        }


    }

    public int getInt(){
        System.out.println("Give me a number");
        int num = scanner.nextInt();
        System.out.println(num);
        return num;
    }

    public double getDouble(double min, double max) {
        System.out.println("Give me a number between " +  min + " and " + max);
        double num = scanner.nextDouble();
        if (num < min || num > max) {
            return getDouble(min, max);
        } else {
            System.out.println(num);
            return num;
        }
    }

    public double getDouble(){
        System.out.println("Give me a number");
        double num = scanner.nextDouble();
        System.out.println(num);
        return num;
    }








    public static void main(String[] args) {
        Input input = new Input(new Scanner(System.in));

        input.getInt(1, 10);
        input.yesNo();
        input.getInt();
        input.getDouble(1.0, 10.0);
        input.getDouble();

    }


}
