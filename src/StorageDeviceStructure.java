public class StorageDeviceStructure {
    private String name;
    private String UUID;

    public StorageDeviceStructure (String name, String UUID) {
        this.name = name;
        this.UUID = UUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
}
