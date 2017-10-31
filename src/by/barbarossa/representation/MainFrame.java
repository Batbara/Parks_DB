package by.barbarossa.representation;

import by.barbarossa.representation.listeners.ShowSpeciesInfo;
import by.barbarossa.representation.table.TableView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class MainFrame extends Observable{
    private JFrame mainFrame;
    private static MainFrame instance = new MainFrame();
    public static MainFrame getInstance(){return instance;}
    private TableView tableView;
    private JLabel tableName;
    private Map<String, JButton> controlButtons;
    private MainFrame(){

        initFrame();
        tableView = new TableView();
        tableName = new JLabel();

        initButtons();
        addButtonsListeners();
        mainFrame.setJMenuBar(createMenuBar());
        mainFrame.add(tableName, BorderLayout.PAGE_START);
        mainFrame.add(tableView.getHolder(), BorderLayout.CENTER);
        mainFrame.add(getButtonsPanel(),BorderLayout.PAGE_END);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);

    }

    private void addButtonsListeners() {
        for(String buttonName : controlButtons.keySet()){
            switch (buttonName){
                case "Насаждения":
                    JButton button = controlButtons.get(buttonName);
                    button.addActionListener(new ShowSpeciesInfo());
            }
        }
    }

    private JPanel getButtonsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
        for(String key : controlButtons.keySet()){
            panel.add(controlButtons.get(key));
        }
        return panel;
    }

    private void initFrame(){
        mainFrame = new JFrame("Парки и зоны отдыха");
        mainFrame.setLayout(new BorderLayout(0,10));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }
    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        String[] menuNames = {"Фирма", "Парки и зоны", "Растения","График"};
        for (String menuName : menuNames){
            menuBar.add(new Menu(menuName).getMenu());
        }

        JMenu workers = new JMenu("Работники");
        String[] subMenuNames = {"Служитель", "Декораторы парка"};
        for (String subMenuName : subMenuNames){
            workers.add(new Menu(subMenuName).getMenu());
        }
        menuBar.add(workers);
        return menuBar;
    }

    public JTable getTable() {
        return tableView.getTable();
    }
    public void setTable(JTable table){
        tableView.setTable(table);
    }
    public void setTableNameLabel(String text){
        tableName.setText(text);
    }

    public TableView getTableView() {
        return tableView;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
    private void initButtons(){
        controlButtons = new HashMap<>();
        String[] buttonsNames = {"Насаждения","Сотрудники на дату","Растения на дату"};
        for(String name : buttonsNames){
            controlButtons.put(name, new JButton(name));
        }
    }
}
