import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class LVMRunner {
    public static void main(String[] args) {
        LVMStructureSaver saver = new LVMStructureSaver();

        Scanner s = new Scanner(System.in);
        System.out.print("Data loaded.\nWelcome to the LVM system.\ncmb#: ");
        String input = s.nextLine();

        while (!input.equals("exit")) {
            String[] inputParts = input.split(" ");

            if (inputParts[0].equals("install-drive")) {
                installDrive(inputParts[1], inputParts[2], saver);
            }
            else if (inputParts[0].equals("list-drives")) {
                for (HardDrive drive : saver.getHardDriveList()) {
                    System.out.println(drive.getName() + " [" + drive.getSize() + "] [" + drive.getUUID() + "]");
                }
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

            System.out.print("cmb#: ");
            input = s.nextLine();
        }
    }

    public static void installDrive(String name, String size, LVMStructureSaver saver) {
        boolean alreadyInstalled = false;
        for (HardDrive hd : saver.getHardDriveList()) {
            if (hd.getName().equals(name)) {alreadyInstalled = true;}
        }
        if (!alreadyInstalled) {
            String uuid = (UUID.randomUUID()).toString();
            size = size.substring(0, size.length()-1);
            HardDrive drive = new HardDrive(name, uuid, Integer.parseInt(size));
            saver.extendHardDriveList(drive);
            System.out.println("Drive " + name + " installed");
        }
        else {
            System.out.println("Drive " + name + " is already installed");
        }
    }

    public static void installPV(String name, HardDrive drive, LVMStructureSaver saver) {
        boolean notInstallable = false;
        for (PV pv : saver.getPvList()) {
            if (pv.getName().equals(name)) {notInstallable = true;}
            if (pv.getAssociatedDrive() == drive) {notInstallable = true;}
        }
        if (!saver.getHardDriveList().contains(drive)) {notInstallable = true;}
        if (!notInstallable) {
            String uuid = (UUID.randomUUID()).toString();
            PV pv = new PV(name, uuid, drive);
            saver.extendPVList(pv);
            System.out.println("PV " + name + " installed");
        }
        else {

        }
    }



}
