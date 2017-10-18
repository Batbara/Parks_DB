package by.barbarossa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Firm implements Serializable{
    private int id;
    private String name;
    private Address address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    public List<String> getInfo(){
        List<String> infoList = new ArrayList<>();
        infoList.add(Integer.toString(id));
        infoList.add(name);
        infoList.add(address.getCity());
        infoList.add(address.getStreet());
        infoList.add(Integer.toString(address.getBuildingNum()));
        return infoList;
    }
}
