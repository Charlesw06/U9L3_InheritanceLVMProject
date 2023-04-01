public class StorageDeviceStructure {
    private String name;
    private String uuid;

    public StorageDeviceStructure (String name, String uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public String getUUID() {
        return uuid;
    }
}
