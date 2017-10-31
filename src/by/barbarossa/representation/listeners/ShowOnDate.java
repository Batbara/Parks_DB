package by.barbarossa.representation.listeners;

import by.barbarossa.controller.ControllerFactory;
import by.barbarossa.controller.impl.ScheduleController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ShowOnDate extends Observable implements ActionListener {
    public ShowOnDate(){
        ControllerFactory factory = ControllerFactory.getInstance();
        ScheduleController controller =(ScheduleController) factory.getController("График");
        addObserver(controller);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        setChanged();
        notifyObservers(e.getActionCommand());
    }
}
