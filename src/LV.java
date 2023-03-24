public class LV extends StorageDeviceStructure {
    private int size;
    private VG associatedVG;

    public LV (String name, String uuid, int size, VG associatedVG) {
        super(name, uuid);
        this.size = size;
        this.associatedVG = associatedVG;
    }

    public int getSize() {
        return size;
    }

    public VG getAssociatedVG() {
        return associatedVG;
    }

    public String toString() {
        return super.toString() + " " + size + " " + associatedVG;
    }
}
