package by.barbarossa.service;

import by.barbarossa.service.impl.FirmService;
import by.barbarossa.service.impl.ParkDecoratorService;
import by.barbarossa.service.impl.ParksService;
import by.barbarossa.service.impl.PlantService;
import by.barbarossa.service.impl.PlantWorkerService;
import by.barbarossa.service.impl.ScheduleService;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private static final ParksAndRecService firmService = new FirmService();
    private static final ParksAndRecService plantService = new PlantService();
    private static final ParksAndRecService parksService = new ParksService();
    private static final ParksAndRecService parkDecoratorService = new ParkDecoratorService();
    private static final ParksAndRecService plantWorkerService = new PlantWorkerService();
    private static final ParksAndRecService scheduleService = new ScheduleService();

    private ServiceFactory() {}

    public ParksAndRecService getService(String name) {
        switch (name) {
            case "firm":
                return firmService;
            case "plant":
                return plantService;
            case "parks":
                return parksService;
            case "decorator":
                return parkDecoratorService;
            case "plantworker":
                return plantWorkerService;
            case "schedule":
                return scheduleService;
        }
        return null;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

}