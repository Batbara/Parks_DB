package by.barbarossa.entity;

import java.io.Serializable;

public class ParkZone implements Serializable{
    private int id;
    private String zoneName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }
}
