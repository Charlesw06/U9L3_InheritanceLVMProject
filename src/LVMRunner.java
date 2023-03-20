import java.util.ArrayList;
import java.util.Scanner;

public class LVMRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Data loaded.\nWelcome to the LVM system.\ncmb#: ");
        String input = s.nextLine();

        while (!input.equals("exit")) {
            String[] inputParts = input.split(" ");

            if (inputParts[0].equals("install-drive")) {

            }
        }
    }
}
