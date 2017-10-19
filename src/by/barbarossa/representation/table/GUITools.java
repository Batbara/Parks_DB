package by.barbarossa.representation.table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class GUITools {
    public static DefaultTableModel createTableModel(List<String> header, List<List<String>> rows) {

        DefaultTableModel tableModel = new DefaultTableModel(header.toArray(), 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        for (List<String> row : rows) {
            tableModel.addRow(new Vector(row));
        }
        return tableModel;

    }

    public static Map<JLabel, JTextField> createFields(String[] labelNames) {

        Map<JLabel, JTextField> fields = new LinkedHashMap<>();
        for (String name : labelNames) {
            JLabel label = new JLabel(name);
            JTextField textField = new JTextField(10);
            fields.put(label, textField);

        }
        return fields;
    }

    public static void setColumnWidth(JTable table, int width) {
        for (int col = 0; col < table.getColumnCount(); col++) {

            TableColumn column = table.getColumnModel().getColumn(col);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);

        }
    }


}
