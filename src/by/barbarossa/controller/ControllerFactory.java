package by.barbarossa.controller;

import by.barbarossa.controller.impl.FirmController;
import by.barbarossa.controller.impl.ParkDecoratorController;
import by.barbarossa.controller.impl.ParksController;
import by.barbarossa.controller.impl.PlantController;
import by.barbarossa.controller.impl.PlantWorkerController;
import by.barbarossa.controller.impl.ScheduleController;
import by.barbarossa.entity.ParkDecorator;

public class ControllerFactory {
    private static final ControllerFactory instance = new ControllerFactory();
    public static final ControllerFactory getInstance(){return instance;}

    private static Controller firmController = new FirmController();
    private static Controller plantController = new PlantController();
    private static Controller parksController = new ParksController();
    private static Controller parkDecoratorController = new ParkDecoratorController();
    private static Controller pWorkerController = new PlantWorkerController();
    private static Controller scheduleController = new ScheduleController();
    private ControllerFactory(){}
    public Controller getController(String name){
        switch (name){
            case "Фирма":
                return firmController;
            case "Растения":
                return plantController;
            case "Парки и зоны":
                return parksController;
            case "Декораторы парка":
                return parkDecoratorController;
            case "Служитель":
                return pWorkerController;
            case "График":
                return scheduleController;
        }
        return null;
    }

}
