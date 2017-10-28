package by.barbarossa.representation.listeners;

import by.barbarossa.controller.Controller;
import by.barbarossa.controller.ControllerFactory;
import by.barbarossa.representation.dialogs.CommonDialog;
import by.barbarossa.representation.dialogs.DialogsFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class ShowDialogListener extends Observable implements ActionListener{
    private String menuName;
    public ShowDialogListener(String menuName){
        this.menuName = menuName;
        ControllerFactory factory = ControllerFactory.getInstance();
        Controller controller = factory.getController(menuName);

        if (menuName.equals("Фирма") || menuName.equals("Растения") || menuName.equals("Парки и зоны") ||
        menuName.equals("Декораторы парка")) {
            // FirmController firmController = (FirmController) factory.getController("firm");
            //this.menuName = menuName;
            this.addObserver(controller);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        DialogsFactory dialogsFactory = DialogsFactory.getInstance();
        CommonDialog dialog = dialogsFactory.getDialog(menuName);

        setChanged();
        notifyObservers();
        dialog.show();
    }
}
