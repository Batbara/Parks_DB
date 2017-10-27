package by.barbarossa.entity;

import java.io.Serializable;
import java.util.Map;

public class ParkZone implements Serializable{
    private int id;
    private String zoneName;
    private String parkName;

    public int getId() {
        return id;
    }

    public void setInfo(Map<String, String> infoMap){
        for(String info : infoMap.keySet()){
            switch (info){
                case "Парк":
                    parkName = infoMap.get(info);
                    break;
                case "Зона":
                    zoneName = infoMap.get(info);
                    break;
            }
        }
    }
    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
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
