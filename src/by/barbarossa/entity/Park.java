package by.barbarossa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Park implements Serializable{
    private int id;
    private List<ParkZone> zones;
    private String parkName;

    public void setInfo(Map<String, String> infoMap){
        for(String info : infoMap.keySet()){
            switch (info){
                case "Парк":
                    parkName = infoMap.get(info);
                    break;
                case "Зона":
                    ParkZone zone = new ParkZone();
                    zone.setZoneName(infoMap.get(info));
                    zones.add(zone);
                    break;
            }
        }
    }
    public Park(){
        zones = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ParkZone> getZones() {
        return zones;
    }

    public void setZones(List<ParkZone> zones) {
        this.zones = zones;
    }
    public void addZone(ParkZone zone){
        zones.add(zone);
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }
}
