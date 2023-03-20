public class HardDrive extends StorageDeviceStructure {
    private int size;

    public HardDrive (String name, String UUID, int size) {
        super(name, UUID);
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
