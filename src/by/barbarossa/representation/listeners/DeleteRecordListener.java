package by.barbarossa.representation.listeners;

import by.barbarossa.controller.Controller;
import by.barbarossa.controller.ControllerFactory;
import by.barbarossa.representation.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class DeleteRecordListener extends Observable implements ActionListener {
    public DeleteRecordListener(String menuName){
        ControllerFactory factory = ControllerFactory.getInstance();
        Controller controller = factory.getController(menuName);

        if (menuName.equals("Фирма") || menuName.equals("Растения") || menuName.equals("Парки и зоны")) {
            // FirmController firmController = (FirmController) factory.getController("firm");
            //this.menuName = menuName;
            this.addObserver(controller);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String s =  JOptionPane.showInputDialog(
                MainFrame.getInstance().getMainFrame(),
                "Введите id записи, которую хотите удалить",
                "Удалить запись",
                JOptionPane.PLAIN_MESSAGE);
        setChanged();
        notifyObservers(Integer.parseInt(s));

    }
}
