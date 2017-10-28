package by.barbarossa.representation.dialogs.impl;

import by.barbarossa.representation.dialogs.CommonDialog;
import by.barbarossa.representation.listeners.AddRecordListener;
import by.barbarossa.representation.table.GUITools;

import javax.swing.*;
import java.awt.*;

public class AddToParkDecoratorDialog extends CommonDialog {
    public AddToParkDecoratorDialog(){
        super();
        initFields();

        dialog.setLayout(new BorderLayout());
        dialog.add(getFieldsPanel());
        dialog.add(confirmButton, BorderLayout.PAGE_END);
        addButtonListener();
        dialog.pack();
    }
    private void initFields(){
        String[] labels = {"Имя", "Город", "Улица", "Номер здания", "Образование",
                "Альма-матер", "Телефон","Категория"};

        fields = GUITools.createFields(labels);
    }
    private JPanel getFieldsPanel(){

        JPanel container = new JPanel(new GridLayout(fields.size(),2));
        for (JLabel label : fields.keySet()){
            container.add(label);
            container.add(fields.get(label));
        }
        return container;

    }

    @Override
    public void addButtonListener() {
        this.confirmButton.addActionListener(new AddRecordListener("Декораторы парка"));
    }
}
