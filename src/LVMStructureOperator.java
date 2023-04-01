import java.util.ArrayList;
import java.util.UUID;
import java.io.*;
import static java.util.Arrays.asList;
import java.io.IOException;

public class LVMStructureOperator implements Serializable{
    private ArrayList<LV> lvList;
    private ArrayList<PV> pvList;
    private ArrayList<HardDrive> hardDriveList;
    private ArrayList<VG> vgList;

    public LVMStructureOperator() {
        lvList = new ArrayList<LV>();
        pvList = new ArrayList<PV>();
        hardDriveList = new ArrayList<HardDrive>();
        vgList = new ArrayList<VG>();
    }

    public ArrayList<LV> getLVList() {
        return lvList;
    }

    public ArrayList<PV> getPVList() {
        return pvList;
    }

    public ArrayList<HardDrive> getHardDriveList() {
        return hardDriveList;
    }

    public ArrayList<VG> getVGList() {
        return vgList;
    }

    public void installDrive(String name, String size) {
        boolean alreadyInstalled = false;
        for (HardDrive hd : hardDriveList) {
            if (hd.getName().equals(name)) {alreadyInstalled = true;}
        }
        if (!alreadyInstalled) {
            String uuid = (UUID.randomUUID()).toString();
            size = size.substring(0, size.length()-1);
            HardDrive drive = new HardDrive(name, uuid, Integer.parseInt(size));
            hardDriveList.add(drive);
            System.out.println("Drive " + name + " installed");
        }
        else {
            System.out.println("Drive " + name + " is already installed");
        }
    }

    public void listDrives() {
        for (HardDrive drive : hardDriveList) {
            System.out.println(drive);
        }
    }

    public void createPV(String name, String driveName) {
        boolean notInstallable = false;
        for (PV pv : pvList) {
            if (pv.getName().equals(name)) {
                System.out.println("PV " + name + " already exists");
                notInstallable = true;
            }
            if (pv.getAssociatedDrive().getName().equals(driveName)) {
                System.out.println("Hard drive " + driveName + " is associated with another PV");
                notInstallable = true;
            }
        }
        String[] driveNames = new String[hardDriveList.size()];
        for (int i = 0; i < hardDriveList.size(); i++) {
            driveNames[i] = hardDriveList.get(i).getName();
        }
        if (!asList(driveNames).contains(driveName)) {
            System.out.println("Hard drive " + driveName + " does not exist");
            notInstallable = true;
        }
        if (!notInstallable) {
            String uuid = (UUID.randomUUID()).toString();
            int index = asList(driveNames).indexOf(driveName);
            PV pv = new PV(name, uuid, hardDriveList.get(index));
            pvList.add(pv);
            System.out.println(name + " created");
        }
    }

    public void listPVs() {
        for (PV pv : pvList) {
            System.out.println(pv);
        }
    }

    public void createVG(String name, String pvName) {
        boolean notInstallable = false;
        String[] allPVNames = new String[pvList.size()];
        for (int i = 0; i < pvList.size(); i++) {
            allPVNames[i] = pvList.get(i).getName();
        }
        if (!asList(allPVNames).contains(pvName)) {
            System.out.println("PV " + pvName + " does not exist");
            notInstallable = true;
        }
        for (VG vg : vgList) {
            for (PV pv : vg.getPVList()) {
                if (pv.getName().equals(pvName)) {
                    System.out.println("PV " + pvName + " is part of another VG");
                    notInstallable = true;
                }
            }
        }
        for (VG vg : vgList) {
            if (vg.getName().equals(name)) {
                System.out.println("VG " + name + " already exists");
                notInstallable = true;
            }
        }
        if (!notInstallable) {
            String uuid = (UUID.randomUUID()).toString();
            int index = asList(allPVNames).indexOf(pvName);
            VG vg = new VG(name, uuid, pvList.get(index));
            vgList.add(vg);
            System.out.println(name  + " created");
        }
    }

    public void extendVG(String vgName, String pvName) {
        boolean notExtendable = false;
        String[] allPVNames = new String[pvList.size()];
        for (int i = 0; i < pvList.size(); i++) {
            allPVNames[i] = pvList.get(i).getName();
        }
        if (!asList(allPVNames).contains(pvName)) {
            System.out.println("PV " + pvName + " does not exist");
            notExtendable = true;
        }
        for (VG vg : vgList) {
            for (PV pv : vg.getPVList()) {
                if (pv.getName().equals(pvName)) {
                    System.out.println("PV " + pvName + " is part of another VG");
                    notExtendable = true;
                }
            }
        }
        if (!notExtendable) {
            for (VG vg : vgList) {
                if (vg.getName().equals(vgName)) {
                    int index = 0;
                    for (int i = 0; i < pvList.size(); i++) {
                        if (pvList.get(i).getName().equals(pvName)) {
                            index = i;
                        }
                    }
                    vg.addPV(pvList.get(index));
                }
            }
            System.out.println(pvName + " added to " + vgName);
        }
    }

    public void listVGs() {
        for (VG vg : vgList) {
            System.out.println(vg);
        }
    }

    public void createLV(String name, String size, String vgName) {
        int sizeNum = Integer.parseInt(size.substring(0, size.length() - 1));
        boolean notInstallable = false;
        String[] allLVNames = new String[lvList.size()];
        for (int i = 0; i < lvList.size(); i++) {
            allLVNames[i] = lvList.get(i).getName();
        }
        if (asList(allLVNames).contains(name)) {
            System.out.println("LV " + name + " already exists");
            notInstallable = true;
        }
        String[] allVGNames = new String[vgList.size()];
        for (int i = 0; i < vgList.size(); i++) {
            allVGNames[i] = pvList.get(i).getName();
        }
        if (!asList(allVGNames).contains(vgName)) {
            System.out.println("VG " + vgName + " does not exist");
            notInstallable = true;
        }
        else {
            VG vg = vgList.get(asList(allVGNames).indexOf(vgName));
            if (vg.getSpaceLeft() < sizeNum) {
                System.out.println("VG " + vgName + " does not have enough space");
                notInstallable = true;
            }
        }
        if (!notInstallable) {
            String uuid = (UUID.randomUUID()).toString();
            int index = asList(allVGNames).indexOf(vgName);
            LV lv = new LV(name, uuid, sizeNum, vgList.get(index));
            lvList.add(lv);
            System.out.println(name + " " + sizeNum + " " + vgName + "\n" + name + " created");
        }
    }

    public void listLVs() {
        for (LV lv : lvList) {
            System.out.println(lv);
        }
    }

    public void saveData() {
        try {
            FileOutputStream writeData = new FileOutputStream("src/LVSaver");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(lvList);

            writeData = new FileOutputStream("src/PVSaver");
            writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(pvList);

            writeData = new FileOutputStream("src/DriveSaver");
            writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(hardDriveList);

            writeData = new FileOutputStream("src/VGSaver");
            writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(vgList);

            writeStream.flush();
            writeStream.close();

            System.out.println("Data saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        try {
            FileInputStream readData = new FileInputStream("src/LVSaver");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            if (new File("src/LVSaver").length() != 0) {
                lvList = (ArrayList<LV>) readStream.readObject();
            }

            readData = new FileInputStream("src/PVSaver");
            readStream = new ObjectInputStream(readData);

            if (new File("src/PVSaver").length() != 0) {
                pvList = (ArrayList<PV>) readStream.readObject();
            }
            readData = new FileInputStream("src/DriveSaver");
            readStream = new ObjectInputStream(readData);

            if (new File("src/DriveSaver").length() != 0) {
                hardDriveList = (ArrayList<HardDrive>) readStream.readObject();
            }

            readData = new FileInputStream("src/VGSaver");
            readStream = new ObjectInputStream(readData);

            if (new File("src/VGSaver").length() != 0) {
                vgList = (ArrayList<VG>) readStream.readObject();
            }

            System.out.println("Data loaded.");
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
