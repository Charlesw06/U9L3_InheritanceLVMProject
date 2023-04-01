import java.util.Scanner;

public class LVMRunner {
    public static void main(String[] args) {
        LVMStructureOperator o = new LVMStructureOperator();
        o.loadData();

        Scanner s = new Scanner(System.in);
        System.out.print("Welcome to the LVM system.\ncmb#: ");
        String input = s.nextLine();

        while (!input.equals("exit")) {
            String[] inputParts = input.split(" ");

            if (inputParts[0].equals("install-drive")) {
                o.installDrive(inputParts[1], inputParts[2]);
            }
            else if (inputParts[0].equals("list-drives")) {
                o.listDrives();
            }
            else if (inputParts[0].equals("pvcreate")) {
                o.createPV(inputParts[1], inputParts[2]);
            }
            else if (inputParts[0].equals("pvlist")) {
                o.listPVs();
            }
            else if (inputParts[0].equals("vgcreate")) {
                o.createVG(inputParts[1], inputParts[2]);
            }
            else if (inputParts[0].equals("vgextend")) {
                o.extendVG(inputParts[1], inputParts[2]);
            }
            else if (inputParts[0].equals("vglist")) {
                o.listVGs();
            }
            else if (inputParts[0].equals("lvcreate")) {
                o.createLV(inputParts[1], inputParts[2], inputParts[3]);
            }
            else if (inputParts[0].equals("lvlist")) {
                o.listLVs();
            }

            System.out.print("cmb#: ");
            input = s.nextLine();
        }
        o.saveData();
    }

}
