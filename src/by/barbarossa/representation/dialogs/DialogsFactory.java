package by.barbarossa.representation.dialogs;

import by.barbarossa.representation.dialogs.impl.AddToFirmDialog;

public class DialogsFactory {
    private static final DialogsFactory instance = new DialogsFactory();
    private static CommonDialog addToFirmDialog = new AddToFirmDialog();
    public static DialogsFactory getInstance(){return instance;}

    private DialogsFactory(){}
    public CommonDialog getDialog(String name){
        switch (name){
            case "Фирма":
                return addToFirmDialog;
        }
        return null;
    }
}
