package by.barbarossa.dao.command;

import java.util.HashMap;
import java.util.Map;

public class TablesDirector {
    private Map<String, Command> commandMap = new HashMap<>();
    public TablesDirector(){
        commandMap.put("firm", new FirmTable());
        commandMap.put("plant", new PlantTable());
        commandMap.put("parks", new ParksTable());
    }
    public Command getCommand(String tableName){
        return commandMap.get(tableName);
    }
}
