package by.barbarossa.representation;

import by.barbarossa.representation.listeners.ViewTableListener;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Menu {
    private JMenu menu;
    private Map<String, JMenuItem> menuItems;
    public Menu(String name){
        initMenu(name);
        initMenuItems();
        addSubMenus();
        addListeners(name);
    }
    private void initMenu(String name){
        menu = new JMenu(name);

    }
    private void initMenuItems(){
        menuItems = new HashMap<>();
        String[] itemNames = {"Показать", "Добавить", "Редактировать","Удалить"};
        for(String itemName : itemNames){
            menuItems.put(itemName,new JMenuItem(itemName));
        }
    }
    private void addSubMenus(){
        for(String itemKey: menuItems.keySet()){
            JMenuItem menuItem = menuItems.get(itemKey);
            menu.add(menuItem);
        }
    }
    private void addListeners(String menuName){
        menuItems.get("Показать").addActionListener(new ViewTableListener(menuName));
    }
    public JMenu getMenu() {
        return menu;
    }
}
