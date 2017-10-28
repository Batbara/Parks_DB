package by.barbarossa.representation.dialogs;

import by.barbarossa.representation.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public abstract class CommonDialog{
    protected JDialog dialog;
    protected JButton confirmButton;

    protected Map<JLabel, JTextField> fields;
    public CommonDialog(){
        fields = new LinkedHashMap<>();
        dialog = new JDialog(MainFrame.getInstance().getMainFrame(),  "Добавить запись", Dialog.ModalityType.DOCUMENT_MODAL);
        centerOnScreen();
        confirmButton = new JButton("ОК");
    }

    public void show(){
        dialog.setVisible(true);
    };

    public abstract void addButtonListener();

    public Map<JLabel, JTextField> getFields() {
        return fields;
    }
    public Map<String, String> getStringFields(){
        Map<String,String> mapStringFields = new LinkedHashMap<>();
        for(JLabel label : fields.keySet()){
            mapStringFields.put(label.getText(),fields.get(label).getText());
        }
        return mapStringFields;
    }
    private void centerOnScreen() {
        final int width = dialog.getWidth();
        final int height = dialog.getHeight();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width / 2) - (width / 2);
        int y = (screenSize.height / 2) - (height / 2);

        dialog.setLocation(x, y);
    }
    public void clearTextFields(){
        for (JLabel key: fields.keySet()){
            fields.get(key).setText(null);
        }

    }
    public void setFields(Map<JLabel, JTextField> fields) {
        this.fields = fields;
    }
}
