package by.barbarossa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Plant implements Serializable {
    private int id;
    private ParkZone zone;
    private int age;
    private Date plantDate;
    private String species;
    private Watering watering;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ParkZone getZone() {
        return zone;
    }

    public void setZone(ParkZone zone) {
        this.zone = zone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getPlantDate() {
        return plantDate;
    }

    public void setPlantDate(Date plantDate) {
        this.plantDate = plantDate;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Watering getWatering() {
        return watering;
    }

    public void setWatering(Watering watering) {
        this.watering = watering;
    }

    public List<String> getInfo(){

        List<String> info = new ArrayList<>();
        info.add(Integer.toString(id));
        info.add(zone.getZoneName());
        info.add(species);
        info.add(Integer.toString(age));
        info.add(plantDate.toString());
        info.add(watering.getPeriodicity());
        info.add(Integer.toString(watering.getWaterNorm()));
        return info;
    }
}
