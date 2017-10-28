package by.barbarossa.representation.dialogs.impl;

import by.barbarossa.representation.dialogs.CommonDialog;
import by.barbarossa.representation.listeners.AddRecordListener;
import by.barbarossa.representation.table.GUITools;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class AddToPlantDialog extends CommonDialog{
    private List<String> zonesInfo;
    private Map<JLabel,JComboBox> comboBoxMap;
    public AddToPlantDialog(){
        super();
        zonesInfo = new ArrayList<>();
        comboBoxMap = new HashMap<>();
        initFields();
        initComboBox();

        dialog.setLayout(new BorderLayout());
        dialog.add(getPanel());
        dialog.add(confirmButton, BorderLayout.PAGE_END);

        addButtonListener();
        dialog.pack();

    }
    public String getSelectedItem(){
        JComboBox box = getBox();
        String selected = (String)box.getSelectedItem();
        return selected;
    }
    private JComboBox getBox(){
        JComboBox box = new JComboBox();
        for(JLabel key : comboBoxMap.keySet()){
            box = comboBoxMap.get(key);
        }
        return box;
    }
    public void setZonesInfo(List<String> zonesInfo) {
        JComboBox box= getBox();
        if(zonesInfo.isEmpty()){
            return;
        }
        for(String zoneName : zonesInfo){
            box.addItem(zoneName);
        }
    }

    private void initComboBox(){
        comboBoxMap = new HashMap<>();
        JLabel label = new JLabel("Зона");

        comboBoxMap.put(label,new JComboBox());
    }
    private void initFields(){
        String[] labels = {"Вид", "Возраст", "Дата высадки", "Периодичность", "Норма воды"};

        fields = GUITools.createFields(labels);
    }

    @Override
    public void addButtonListener() {
        this.confirmButton.addActionListener(new AddRecordListener("Растения"));
    }

    private JPanel getPanel(){

        JPanel container = new JPanel(new GridLayout(fields.size()+comboBoxMap.size(),2));
        for (JLabel label : comboBoxMap.keySet()){
            container.add(label);
            container.add(comboBoxMap.get(label));
        }
        for (JLabel label : fields.keySet()){
            container.add(label);
            container.add(fields.get(label));
        }
        return container;

    }
}
