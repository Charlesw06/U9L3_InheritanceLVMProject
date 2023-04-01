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

    public String toString(LVMStructureOperator operator) {
        String info = super.getName() + ": [" + getName() + "G]";
        for (VG vg : operator.getVGList()) {
            if (vg.getPVList().contains(this)) {
                info += " [" + vg.getName() + "]";
            }
        }
        return info + " [" + super.getUUID() + "]";
    }
}
