import java.util.Arrays;
import java.util.ArrayList;

public class VG extends StorageDeviceStructure {
    private ArrayList<PV> pvList;
    private ArrayList<LV> lvList;

    public VG (String name, String uuid, PV pv) {
        super(name, uuid);
        this.pvList = new ArrayList<PV>(Arrays.asList(pv));
        this.lvList = new ArrayList<LV>();
    }

    public ArrayList<PV> getPVList() {
        return pvList;
    }

    public ArrayList<LV> getLVList() {
        return lvList;
    }

    public int getTotalSpace() {
        int totalSpace = 0;
        for (PV pv : pvList) {
            totalSpace += pv.getSize();
        }
        return totalSpace;
    }

    public int getUsedSpace() {
        int space = 0;
        for (LV lv : lvList) {
            space += lv.getSize();
        }
        return space;
    }

    public int getSpaceLeft() {
        return getTotalSpace() - getUsedSpace();
    }

    public void addPV(PV pv) {
        pvList.add(pv);
    }

    public void addLV(LV lv) {
        lvList.add(lv);
    }

    public String toString() {
        String info = super.getName() + ": total:[" + getTotalSpace() + "G] available:[" + getSpaceLeft() + "G] [";
        for (int i = 0; i < pvList.size(); i++) {
            info += pvList.get(i).getName();
            if (i != pvList.size()-1) {info += ",";}
        }
        return info + "] [" + super.getUUID() + "]";
    }
}
