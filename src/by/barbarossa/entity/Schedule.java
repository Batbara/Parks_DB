package by.barbarossa.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Schedule {
    private int id;
    private PlantWorker plantWorker;
    private Date date;
    private Plant plant;

    public Schedule() {
        plantWorker = new PlantWorker();
        date = new Date();
        plant = new Plant();
    }

    public List<String> getInfo(String type) {

        List<String> info = new ArrayList<>();
        if (type.equals("workersondate")) {

            info.add(plantWorker.getName());
            info.add(plantWorker.phoneNum);
            info.add(date.toString());
            return info;
        }

        if(type.equals("plantsondate")){
            info.add(plant.getSpecies());
            info.add(plant.getWatering().getPeriodicity());
            int waterNorm = plant.getWatering().getWaterNorm();
            info.add(Integer.toString(waterNorm));
            info.add(date.toString());
            return info;
        }
        if(type.equals("select")) {
            info.add(Integer.toString(id));
            info.add(plantWorker.getName());
            info.add(date.toString());
            info.add(plant.getSpecies());
            return info;
        }
        return null;
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
