package by.barbarossa.controller;

import by.barbarossa.controller.impl.FirmController;

public class ControllerFactory {
    private static final ControllerFactory instance = new ControllerFactory();
    public static final ControllerFactory getInstance(){return instance;}

    private static FirmController firmController = new FirmController();
    private ControllerFactory(){}
    public Controller getController(String name){
        switch (name){
            case "Фирма":
                return firmController;
        }
        return null;
    }

}
