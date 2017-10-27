package by.barbarossa.representation.dialogs;

import by.barbarossa.representation.dialogs.impl.AddToFirmDialog;
import by.barbarossa.representation.dialogs.impl.AddToPlantDialog;

public class DialogsFactory {
    private static final DialogsFactory instance = new DialogsFactory();
    private static CommonDialog addToFirmDialog = new AddToFirmDialog();
    private static CommonDialog addToPlantDialog = new AddToPlantDialog();
    public static DialogsFactory getInstance(){return instance;}

    private DialogsFactory(){}
    public CommonDialog getDialog(String name){
        switch (name){
            case "Фирма":
                return addToFirmDialog;
            case "Растения":
                return addToPlantDialog;
        }
        return null;
    }
}
