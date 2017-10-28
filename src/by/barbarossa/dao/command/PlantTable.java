package by.barbarossa.dao.command;

public class PlantTable implements Command {
    private String[] plantColumns = {"age", "idplant", "idWatering", "idZone", "plantingDate", "species"};
    private String[] wateringColumns = {"idwatering", "periodicity", "waterNorm"};
    private String[] zoneColumns = {"zoneName"};
    @Override
    public String formUpdateStatement(String arg) {
        if(isPlantColumn(arg)) {
            return "UPDATE parks.plant SET " + arg + " = ? WHERE parks.plant.idPlant = ?";
        }
        if(isWateringColumn(arg)){
            return "UPDATE parks.watering SET "+arg+" = ? WHERE parks.watering.idWatering = ?";
        }
        if(isZoneColumn(arg)){
            return "UPDATE parks.zone SET "+arg+" = ? WHERE parks.zone.idzone = ?";
        }
        return null;
    }

    public String getIdQuery(String arg, Object plantID){

        if(isWateringColumn(arg)){
            return "SELECT parks.watering.idWatering FROM parks.plant INNER JOIN parks.watering ON " +
                    "parks.plant.idWatering = parks.watering.idwatering WHERE parks.plant.idPlant = "+plantID.toString();
        }
        if(isZoneColumn(arg)){
            return "SELECT parks.zone.idzone FROM parks.plant INNER JOIN parks.zone ON " +
                    "parks.plant.idzone = parks.zone.idzone WHERE parks.plant.idPlant = "+plantID.toString();
        }
        return null;
    }

    @Override
    public String formDeleteStatement(String arg) {
        return "DELETE FROM parks.plant WHERE parks.plant.idplant = "+arg;
    }

    @Override
    public String formIDStatement(String arg, Object primaryID) {
        if(isWateringColumn(arg)){
            return "SELECT parks.watering.idWatering FROM parks.plant INNER JOIN parks.watering ON " +
                    "parks.plant.idWatering = parks.watering.idwatering WHERE parks.plant.idPlant = "+primaryID.toString();
        }
        if(isZoneColumn(arg)){
            return "SELECT parks.zone.idzone FROM parks.plant INNER JOIN parks.zone ON " +
                    "parks.plant.idzone = parks.zone.idzone WHERE parks.plant.idPlant = "+primaryID.toString();
        }
        return null;
    }

    private boolean isPlantColumn(String arg){
        for (String name : plantColumns){
            if(name.equals(arg))
                return true;
        }
        return false;
    }
    private boolean isWateringColumn(String arg){
        for (String name : wateringColumns){
            if(name.equals(arg))
                return true;
        }
        return false;
    }
    private boolean isZoneColumn(String arg){
        for (String name : zoneColumns){
            if(name.equals(arg))
                return true;
        }
        return false;
    }

}
