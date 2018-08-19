package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
@Entity
public class Event {
    @Id
    private String id;
    private LocalDateTime date;
    private String source;
    private String type;
    private String name;
    private String value;
    private String user;
    private String displayedText;
    private String deviceId;



    public Event(){
    }

    public Event(LocalDateTime date, String source, String type, String name, String value, String user,
                 String displayedText, String deviceId) {
        this.date = date;
        this.source = source;
        this.type = type;
        this.name = name;
        this.value = value;
        this.user = user;
        this.displayedText = displayedText;
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "Event{" +
                ", date=" + date +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", user='" + user + '\'' +
                ", displayedText='" + displayedText + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDisplayedText() {
        return displayedText;
    }

    public void setDisplayedText(String displayedText) {
        this.displayedText = displayedText;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
