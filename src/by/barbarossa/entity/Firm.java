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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Firm firm = (Firm) o;

        if (id != firm.id) return false;
        if (!name.equals(firm.name)) return false;
        return address.equals(firm.address);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }
}
