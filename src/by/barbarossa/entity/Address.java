package by.barbarossa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Address implements Serializable {
    private int id;
    private String city;
    private String street;
    private int buildingNum;

    public Address(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(int buildingNum) {
        this.buildingNum = buildingNum;
    }

    public List<String> getInfo(){
        List<String> infoList = new ArrayList<>();
        infoList.add(Integer.toString(id));
        infoList.add(city);
        infoList.add(street);
        infoList.add(Integer.toString(buildingNum));
        return infoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (id != address.id) return false;
        if (buildingNum != address.buildingNum) return false;
        if (!city.equals(address.city)) return false;
        return street.equals(address.street);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + city.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + buildingNum;
        return result;
    }
}
