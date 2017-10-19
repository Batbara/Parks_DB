package by.barbarossa.service;

import by.barbarossa.service.impl.FirmService;
import by.barbarossa.service.impl.PlantService;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private static final ParksAndRecService firmService = new FirmService();
    private static final ParksAndRecService plantService = new PlantService();

    private ServiceFactory() {}

    public ParksAndRecService getService(String name) {
        switch (name) {
            case "firm":
                return firmService;
            case "plant":
                return plantService;
        }
        return null;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

}