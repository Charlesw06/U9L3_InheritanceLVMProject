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
        String info = super.getName() + ": [" + getSize() + "G]";
        for (VG vg : LVMStructureOperator.getVGList()) {
            if (vg.getPVList().contains(this)) {
                info += " [" + vg.getName() + "]";
            }
        }
        return info + " [" + super.getUUID() + "]";
    }
}
