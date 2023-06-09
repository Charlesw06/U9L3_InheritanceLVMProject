public class HardDrive extends StorageDeviceStructure{
    private int size;

    public HardDrive (String name, String uuid, int size) {
        super(name, uuid);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        return super.getName() + " [" + size + "G] [" + super.getUUID() + "]";
    }
}
