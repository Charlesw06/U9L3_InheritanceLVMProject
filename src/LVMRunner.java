import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class LVMRunner {
    public static void main(String[] args) {


        Scanner s = new Scanner(System.in);
        System.out.println("Data loaded.\nWelcome to the LVM system.\ncmb#: ");
        String input = s.nextLine();

        while (!input.equals("exit")) {
            String[] inputParts = input.split(" ");

            if (inputParts[0].equals("install-drive")) {

            }
            else if (inputParts[0].equals("list-drives")) {

            }
            else if (inputParts[0].equals("pvcreate")) {

            }
            else if (inputParts[0].equals("pvlist")) {

            }
            else if (inputParts[0].equals("vgcreate")) {

            }
            else if (inputParts[0].equals("vgextend")) {

            }
            else if (inputParts[0].equals("vglist")) {

            }
            else if (inputParts[0].equals("lvcreate")) {

            }
            else if (inputParts[0].equals("lvlist")) {

            }

            System.out.println("cmb#: ");
            input = s.nextLine();
        }
    }

    public void installDrive(String name, String size) {
        String uuid = (UUID.randomUUID()).toString();

    }

}
