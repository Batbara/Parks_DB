package by.barbarossa.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Schedule {
    private int id;
    private PlantWorker plantWorker;
    private Date date;
    private Plant plant;

    public List<String> getInfo(){
        List<String> info = new ArrayList<>();
        info.add(Integer.toString(id));
        info.add(plantWorker.getName());
        info.add(date.toString());
        info.add(plant.getSpecies());
        return info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlantWorker getPlantWorker() {
        return plantWorker;
    }

    public void setPlantWorker(PlantWorker plantWorker) {
        this.plantWorker = plantWorker;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}
