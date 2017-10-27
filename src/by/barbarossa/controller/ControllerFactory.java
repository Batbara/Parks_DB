package by.barbarossa.controller;

import by.barbarossa.controller.impl.FirmController;
import by.barbarossa.controller.impl.ParksController;
import by.barbarossa.controller.impl.PlantController;

public class ControllerFactory {
    private static final ControllerFactory instance = new ControllerFactory();
    public static final ControllerFactory getInstance(){return instance;}

    private static FirmController firmController = new FirmController();
    private static PlantController plantController = new PlantController();
    private static ParksController parksController = new ParksController();
    private ControllerFactory(){}
    public Controller getController(String name){
        switch (name){
            case "Фирма":
                return firmController;
            case "Растения":
                return plantController;
            case "Парки и зоны":
                return parksController;
        }
        return null;
    }

}
