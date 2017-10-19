package by.barbarossa.dao.command;

public interface Command {
    String formUpdateStatement(String arg);
    String formDeleteStatement(String arg);
    String formInsertStatement(String arg);

}
