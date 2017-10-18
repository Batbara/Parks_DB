package by.barbarossa.entity;

import java.io.Serializable;
import java.util.List;

public class Park implements Serializable{
    private int id;
    private List<ParkZone> zones;
    private String parkName;
}
