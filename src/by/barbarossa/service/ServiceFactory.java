package by.barbarossa.service;

import by.barbarossa.service.impl.FirmService;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private static final ParksAndRecService firmService = new FirmService();

    private ServiceFactory() {}

    public ParksAndRecService getService(String name) {
        switch (name) {
            case "firm":
                return firmService;
        }
        return null;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

}