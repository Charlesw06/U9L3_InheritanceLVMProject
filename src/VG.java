public class VG extends StorageDeviceStructure {
    private PV[] pvList;
    private LV[] lvList;

    public VG (String name, String uuid, PV[] pvList, LV[] lvList) {
        super(name, uuid);
        this.pvList = pvList;
        this.lvList = lvList;
    }

    public PV[] getPVList() {
        return pvList;
    }

    public LV[] getLVList() {
        return lvList;
    }

    public int getUsedSpace() {
        int space = 0;
        for (LV lv : lvList) {
            space += lv.getSize();
        }
        return space;
    }

    public int getSpaceLeft() {
        int totalSpace = 0;
        for (PV pv : pvList) {
            totalSpace += pv.getSize();
        }
        return totalSpace - getUsedSpace();
    }
}
