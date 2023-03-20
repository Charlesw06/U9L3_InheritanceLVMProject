public class PV extends StorageDeviceStructure {
    private HardDrive associatedDrive;

    public PV (String name, String UUID, HardDrive associatedDrive) {
        super(name, UUID);
        this.associatedDrive = associatedDrive;
    }

    public HardDrive getAssociatedDrive() {
        return associatedDrive;
    }

    public int getSize() {
        return associatedDrive.getSize();
    }
}
