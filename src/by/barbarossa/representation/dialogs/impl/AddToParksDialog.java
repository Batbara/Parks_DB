package by.barbarossa.representation.dialogs.impl;

import by.barbarossa.representation.dialogs.CommonDialog;
import by.barbarossa.representation.listeners.AddRecordListener;
import by.barbarossa.representation.table.GUITools;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddToParksDialog extends CommonDialog{

    private Map<JLabel,JComboBox> comboBoxMap;
    public AddToParksDialog(){
        super();
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

    public void setParksInfo(List<String> parksInfo) {
        JComboBox box= getBox();
        if(parksInfo.isEmpty()){
            return;
        }
        for(String zoneName : parksInfo){
            box.addItem(zoneName);
        }
    }
    private JComboBox getBox(){
        JComboBox box = new JComboBox();
        for(JLabel key : comboBoxMap.keySet()){
            box = comboBoxMap.get(key);
        }
        return box;
    }
    private void initFields(){
        String[] labels = {"Зона"};

        fields = GUITools.createFields(labels);
    }
    private void initComboBox(){
        comboBoxMap = new HashMap<>();
        JLabel label = new JLabel("Парк");
        JComboBox box = new JComboBox();
        box.setEditable(true);
        comboBoxMap.put(label,box);
    }
    private JPanel getPanel(){

        JPanel container = new JPanel(new GridLayout(fields.size()+comboBoxMap.size(),2));

        for (JLabel label : fields.keySet()){
            container.add(label);
            container.add(fields.get(label));
        }
        for (JLabel label : comboBoxMap.keySet()){
            container.add(label);
            container.add(comboBoxMap.get(label));
        }
        return container;

    }
    @Override
    public void addButtonListener() {
        this.confirmButton.addActionListener(new AddRecordListener("Парки и зоны"));
    }
}
