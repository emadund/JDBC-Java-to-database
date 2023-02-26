package model;

/**
 *
 * @author Nikola Maksimovic
 */
public class City {
    
    private String zipCode;
    private String name;

    public City() {
    }

    public City(String zipCode, String name) {
        this.zipCode = zipCode;
        this.name = name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    @Override
    public String toString() {
        return "Mesto{" + "ptt=" + zipCode + ", naziv=" + name + '}';
    }
    
    
    
}
