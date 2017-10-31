package by.barbarossa.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlantWorker extends Employee {
    private Date workDate;
    public List<String> getInfo(){
        List<String> info = new ArrayList<>();
        info.add(Integer.toString(this.id));
        info.add(this.name);
        Address address = this.address;
        info.add(address.getCity());
        info.add(address.getStreet());
        info.add(Integer.toString(address.getBuildingNum()));

        info.add(this.phoneNum);
        return info;
    }

}
