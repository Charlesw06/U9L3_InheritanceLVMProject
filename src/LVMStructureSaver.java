import java.util.ArrayList;

public class LVMStructureSaver {
    private ArrayList<LV> lvList;
    private ArrayList<PV> pvList;
    private ArrayList<HardDrive> hardDriveList;
    private ArrayList<VG> vgList;

    public LVMStructureSaver() {
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

    public void extendLVList(LV lv) {
        lvList.add(lv);
    }

    public void extendPVList(PV pv) {
        pvList.add(pv);
    }

    public void extendHardDriveList(HardDrive drive) {
        hardDriveList.add(drive);
    }

    public void extendVGList(VG vg) {
        vgList.add(vg);
    }
}
