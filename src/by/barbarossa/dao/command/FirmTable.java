package by.barbarossa.dao.command;

public class FirmTable implements Command {
    private String[] addressColumns = {"city","street","buildingNum"};
    @Override
    public String formUpdateStatement(String arg) {
        for(String addrColumn : addressColumns){
            if(arg.equals(addrColumn))
                return "UPDATE parks.address SET " + arg + " = ? WHERE parks.address.idaddress = ?";
        }
        return "UPDATE parks.firm SET " + arg + " = ? WHERE parks.firm.idfirm = ?";
    }

    @Override
    public String formDeleteStatement(String arg) {
        return "DELETE FROM parks.firm WHERE parks.firm.idfirm = "+arg;

    }

    @Override
    public String formInsertStatement(String arg) {
        for(String addrColumn : addressColumns){
            if(arg.equals(addrColumn))
                return "UPDATE parks.address SET " + arg + " = ? WHERE parks.address.idaddress = ?";
        }
        return "UPDATE parks.firm SET " + arg + " = ? WHERE parks.firm.idfirm = ?";
    }
}
