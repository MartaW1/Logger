package model;

public class Request {

    private String testName;
    private String account;
    private String password;
    private String locationId;
    private String deviceId;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Request(){
    }

    public Request(String testName, String account, String password, String locationId, String deviceId) {
        this.testName = testName;
        this.account = account;
        this.password = password;
        this.locationId = locationId;
        this.deviceId = deviceId;
    }
}

