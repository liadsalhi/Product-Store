package Liadsalhi;

public class Product {
    private static int serialCounter = 1;
    private final int ID;
    protected String name;
    protected double price;
    protected enum eCategory {Children, Electronics, Office, Clothing}
    protected eCategory theCategory;

    public Product(String name, double price, int categoryOrdinal) {
        this.ID = serialCounter++;
        this.name = name;
        this.price = price;
        this.theCategory = eCategory.values()[categoryOrdinal];
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    public double getPrice() {
        return price;
    }

    public int getSerialNumber() {
        return ID;
    }

    public boolean setPrice(double price) {
        this.price = price;
        return true;
    }

    public eCategory getCategory() {
        return theCategory;
    }

    public boolean setCategory(eCategory category) {
        theCategory = category;
        return true;
    }

    public String toString() {
        return "ID: "+ ID + ", Name: " + name + ", Category: " + theCategory + ", Price: " + price;
    }

    public String toStringNoCategory(){
        return "ID: "+ ID + " Product Name: " + name + " Price: " + price;
    }
}
