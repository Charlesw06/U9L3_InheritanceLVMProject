import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import static java.util.Arrays.asList;

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
                    System.out.println(drive.getName() + " [" + drive.getSize() + "G] [" + drive.getUUID() + "]");
                }
            }
            else if (inputParts[0].equals("pvcreate")) {
                createPV(inputParts[1], inputParts[2], saver);
            }
            else if (inputParts[0].equals("pvlist")) {
                for (PV pv : saver.getPVList()) {
                    System.out.print(pv.getName() + ": [" + pv.getAssociatedDrive().getSize() + "G] ");
                    for (VG vg : saver.getVGList()) {
                        if (asList(vg.getPVList()).contains(pv)) {
                            System.out.print("[" + vg.getName() + "] ");
                        }
                    }
                    System.out.println("[" + pv.getUUID() + "]");
                }
            }
            else if (inputParts[0].equals("vgcreate")) {
                createVG(inputParts[1], inputParts[2], saver);
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

    public static void createPV(String name, String driveName, LVMStructureSaver saver) {
        boolean notInstallable = false;
        for (PV pv : saver.getPVList()) {
            if (pv.getName().equals(name)) {
                System.out.println("PV " + name + " already exists");
                notInstallable = true;
            }
            if (pv.getAssociatedDrive().getName().equals(driveName)) {
                System.out.println("Hard drive " + driveName + " is associated with another PV");
                notInstallable = true;
            }
        }
        String[] driveNames = new String[saver.getHardDriveList().size()];
        for (int i = 0; i < saver.getHardDriveList().size(); i++) {
            driveNames[i] = saver.getHardDriveList().get(i).getName();
        }
        if (!asList(driveNames).contains(driveName)) {
            System.out.println("Hard drive " + driveName + " does not exist");
            notInstallable = true;
        }
        if (!notInstallable) {
            String uuid = (UUID.randomUUID()).toString();
            int index = asList(driveNames).indexOf(driveName);
            PV pv = new PV(name, uuid, saver.getHardDriveList().get(index));
            saver.extendPVList(pv);
            System.out.println(name + " created");
        }
    }

    public static void createVG(String name, String pvName, LVMStructureSaver saver) {
        boolean notInstallable = false;
        String[] allPVNames = new String[saver.getPVList().size()];
        for (int i = 0; i < saver.getPVList().size(); i++) {
            allPVNames[i] = saver.getPVList().get(i).getName();
        }
        if (!asList(allPVNames).contains(pvName)) {
            System.out.println("PV " + pvName + " does not exist");
            notInstallable = true;
        }
        for (VG vg : saver.getVGList()) {
            for (PV pv : vg.getPVList()) {
                if (pv.getName().equals(pvName)) {
                    System.out.println("PV " + pvName + " is part of another VG");
                    notInstallable = true;
                }
            }
        }
        for (VG vg : saver.getVGList()) {
            if (vg.getName().equals(name)) {
                System.out.println("VG " + name + " already exists");
                notInstallable = true;
            }
        }
        if (!notInstallable) {
            String uuid = (UUID.randomUUID()).toString();
            int index = asList(allPVNames).indexOf(pvName);
            VG vg = new VG(name, uuid, saver.getPVList().get(index));
            saver.extendVGList(vg);
            System.out.println(name  + " created");
        }
    }

    public static void extendVG(String vgName, )

}
