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

    public void setName(String name) {
        this.name = name;
    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    public String toString() {
        return name + " " + uuid;
    }
}
