public class VG extends StorageDeviceStructure {
    private PV[] PVList;
    private LV[] LVList;

    public VG (String name, String UUID, PV[] PVList, LV[] LVList) {
        super(name, UUID);
        this.PVList = PVList;
        this.LVList = LVList;
    }

    public PV[] getPVList() {
        return PVList;
    }

    public LV[] getLVList() {
        return LVList;
    }

    public int getUsedSpace() {
        int space = 0;
        for (LV lv : LVList) {
            space += lv.getSize();
        }
        return space;
    }

    public int getSpaceLeft() {
        int totalSpace = 0;
        for (PV pv : PVList) {
            totalSpace += pv.getSize();
        }
        return totalSpace - getUsedSpace();
    }
}
