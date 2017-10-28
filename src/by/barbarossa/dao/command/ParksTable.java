package by.barbarossa.dao.command;

public class ParksTable implements Command {
    private String[] parkColumns = {"parkName"};
    @Override
    public String formUpdateStatement(String arg) {
        if(arg.equalsIgnoreCase("zoneName")){
            return "UPDATE parks.zone SET zoneName = ? WHERE parks.zone.idZone = ?";
        } else{
            return "UPDATE parks.park SET parkName = ? WHERE parks.park.idPark = ?";
        }
    }

    private boolean isParkColumn(String arg) {
        for (String name : parkColumns){
            if(name.equalsIgnoreCase(arg))
                return true;
        }
        return false;
    }

    @Override
    public String formDeleteStatement(String arg) {
        return "DELETE FROM parks.zone WHERE parks.zone.idZone = "+arg;
    }

    @Override
    public String formIDStatement(String arg, Object primaryID) {

        if(isParkColumn(arg)){
            return "SELECT parks.park.idPark FROM parks.park INNER JOIN parks.zone ON " +
                    "parks.park.idPark = parks.zone.idPark WHERE parks.zone.idZone = "+primaryID.toString();
        }
        return null;
    }

}
