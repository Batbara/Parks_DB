package by.barbarossa.representation.listeners;


import by.barbarossa.controller.Controller;
import by.barbarossa.controller.ControllerFactory;
import by.barbarossa.controller.impl.FirmController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class ViewTableListener extends Observable implements ActionListener {
    private String menuName;
    public ViewTableListener(String menuName) {
        ControllerFactory factory = ControllerFactory.getInstance();
        if (menuName.equals("Фирма")) {
        FirmController firmController = (FirmController)factory.getController("firm");
        this.menuName = menuName;
        this.addObserver(firmController);
    }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("lalalla");
        setChanged();
        notifyObservers("select");
    }
}
