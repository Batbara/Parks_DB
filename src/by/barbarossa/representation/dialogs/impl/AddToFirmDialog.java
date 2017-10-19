package by.barbarossa.representation.dialogs.impl;

import by.barbarossa.representation.dialogs.CommonDialog;
import by.barbarossa.representation.listeners.AddRecordListener;
import by.barbarossa.representation.table.GUITools;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class AddToFirmDialog extends CommonDialog{
    public AddToFirmDialog(){
        super();
        initFields();

        dialog.setLayout(new BorderLayout());
        dialog.add(getFieldsPanel());
        dialog.add(confirmButton, BorderLayout.PAGE_END);
        addButtonListener();
        dialog.pack();
    }
    private void initFields(){
        String[] labels = {"Название", "Город", "Улица", "Номер здания"};

        fields = GUITools.createFields(labels);
    }
    @Override
    public void show() {
        this.dialog.setVisible(true);
    }

    @Override
    public void hide() {
        this.dialog.setVisible(false);
    }

    @Override
    public void addButtonListener() {
        this.confirmButton.addActionListener(new AddRecordListener("Фирма"));
    }

    private JPanel getFieldsPanel(){

        JPanel container = new JPanel(new GridLayout(fields.size(),2));
        for (JLabel label : fields.keySet()){
            container.add(label);
            container.add(fields.get(label));
        }
        return container;

    }

    public Map<JLabel, JTextField> getFields() {
        return fields;
    }

    public void setFields(Map<JLabel, JTextField> fields) {
        this.fields = fields;
    }
}
