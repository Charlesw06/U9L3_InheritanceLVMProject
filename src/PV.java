public class PV extends StorageDeviceStructure {
    private HardDrive associatedDrive;

    public PV (String name, String uuid, HardDrive associatedDrive) {
        super(name, uuid);
        this.associatedDrive = associatedDrive;
    }

    public HardDrive getAssociatedDrive() {
        return associatedDrive;
    }

    public int getSize() {
        return associatedDrive.getSize();
    }

    public String toString() {
        return super.toString() + " " + associatedDrive;
    }
}
