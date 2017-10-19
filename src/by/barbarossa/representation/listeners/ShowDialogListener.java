package by.barbarossa.representation.listeners;

import by.barbarossa.representation.dialogs.CommonDialog;
import by.barbarossa.representation.dialogs.DialogsFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowDialogListener implements ActionListener{
    private String menuName;
    public ShowDialogListener(String menuName){
        this.menuName = menuName;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        DialogsFactory dialogsFactory = DialogsFactory.getInstance();
        CommonDialog dialog = dialogsFactory.getDialog(menuName);
        dialog.show();
    }
}
