package by.barbarossa.representation.listeners;

import by.barbarossa.controller.Controller;
import by.barbarossa.controller.ControllerFactory;
import by.barbarossa.controller.impl.PlantController;
import by.barbarossa.representation.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ShowSpeciesInfo extends Observable implements ActionListener{
    public ShowSpeciesInfo(){

        ControllerFactory factory = ControllerFactory.getInstance();
        PlantController controller =(PlantController) factory.getController("Растения");
        addObserver(controller);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        setChanged();
        notifyObservers();

    }
}
