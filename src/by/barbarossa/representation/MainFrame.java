package by.barbarossa.representation;

import by.barbarossa.representation.table.TableView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

public class MainFrame extends Observable{
    private JFrame mainFrame;
    private static MainFrame instance = new MainFrame();
    public static MainFrame getInstance(){return instance;}
    private TableView tableView;
    private JLabel tableName;
    private MainFrame(){

        initFrame();
        String[] headers = {"lol","kek","cheburek"};
        tableView = new TableView(Arrays.asList(headers));
        tableName = new JLabel();

        mainFrame.setJMenuBar(createMenuBar());
        mainFrame.add(tableName, BorderLayout.PAGE_START);
        mainFrame.add(tableView.getHolder(), BorderLayout.CENTER);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);

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
        String[] menuNames = {"Фирма", "Парки", "Растения"};
        for (String menuName : menuNames){
            menuBar.add(new Menu(menuName).getMenu());
        }

        JMenu workers = new JMenu("Работники");
        String[] subMenuNames = {"Cлужитель", "Декоратор парка"};
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
}
