public class LV extends StorageDeviceStructure {
    private int size;
    private VG associatedVG;

    public LV (String name, String UUID, int size, VG associatedVG) {
        super(name, UUID);
        this.size = size;
        this.associatedVG = associatedVG;
    }

    public int getSize() {
        return size;
    }

    public VG getAssociatedVG() {
        return associatedVG;
    }
}
