package Liadsalhi;

public class Address {
    private String state;
    private String city;
    private String street;
    private int houseNumber;

    public Address(String state, String city, String street, int houseNumber) {
        this.state = state;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public boolean setState(String state) {
        this.state = state;
        return true;
    }

    public boolean setCity(String city) {
        this.city = city;
        return true;
    }

    public boolean setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
        return true;
    }

    public boolean setStreet(String street) {
        this.street = street;
        return true;
    }

    @Override
    public String toString() {
        return street +" "+ houseNumber +", "+ city +", "+ state;
    }
}
