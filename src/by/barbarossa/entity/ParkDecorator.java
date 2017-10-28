package by.barbarossa.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkDecorator extends Employee {

    private String almaMater;
    private String category;
    private String education;

    public String getAlmaMater() {
        return almaMater;
    }

    public void setAlmaMater(String almaMater) {
        this.almaMater = almaMater;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
    public List<String> getInfo(){
        List<String> info = new ArrayList<>();
        info.add(Integer.toString(this.id));
        info.add(this.name);
        Address address = this.address;
        info.add(address.getCity());
        info.add(address.getStreet());
        info.add(Integer.toString(address.getBuildingNum()));
        info.add(education);
        info.add(almaMater);

        info.add(this.phoneNum);
        info.add(category);
        return info;
    }
    public void setInfo(Map<String, String> infoMap){
        for (String key : infoMap.keySet()){
            switch (key){
                case "Имя":
                    this.name = infoMap.get(key);
                    break;
                case "Город":
                    this.address.setCity(infoMap.get(key));
                    break;
                case "Улица":
                    this.address.setStreet(infoMap.get(key));
                    break;
                case "Номер здания":
                    this.address.setBuildingNum(Integer.parseInt(infoMap.get(key)));
                    break;
                case "Образование":
                    education = infoMap.get(key);
                    break;
                case "Альма-матер":
                    almaMater = infoMap.get(key);
                    break;
                case "Телефон":
                    phoneNum = infoMap.get(key);
                    break;
                case "Категория":
                    category = infoMap.get(key);
                    break;
            }
        }
    }
}
