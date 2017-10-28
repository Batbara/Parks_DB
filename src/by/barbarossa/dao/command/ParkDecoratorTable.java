package by.barbarossa.dao.command;

public class ParkDecoratorTable implements Command {

    private String[] addressColumns = {"city","street","buildingNum"};
    @Override
    public String formUpdateStatement(String arg) {
        for(String addrColumn : addressColumns){
            if(arg.equals(addrColumn))
                return "UPDATE parks.address SET " + arg + " = ? WHERE parks.address.idaddress = ?";
        }
        return "UPDATE parks.parkdecorator SET " + arg + " = ? WHERE parks.parkdecorator.idparkdecorator = ?";
    }

    @Override
    public String formDeleteStatement(String arg) {
        return "DELETE FROM parks.parkdecorator WHERE parks.parkdecorator.idparkdecorator = "+arg;
    }

    @Override
    public String formIDStatement(String arg, Object primaryID) {
        if(isAddrColumn(arg)){
            return "SELECT parks.address.idAddress FROM parks.parkdecorator INNER JOIN parks.address ON " +
                    "parks.address.idAddress = parks.parkdecorator.idAddress " +
                    "WHERE parks.parkdecorator.idparkdecorator = "+primaryID.toString();
        }
        return null;
    }

    private boolean isAddrColumn(String arg) {
        for(String addrColumn : addressColumns) {
            if (arg.equalsIgnoreCase(addrColumn)) {
                return true;
            }
        }
        return false;
    }
}
