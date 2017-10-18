package by.barbarossa.entity;

import java.io.Serializable;

public class Plant implements Serializable {
    private int uniqueNum;
    private ParkZone zone;
    private double age;
    private String species;
    private WateringSchedule wateringSchedule;
}
