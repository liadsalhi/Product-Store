package Liadsalhi;

public class SpecialProduct extends Product {
    private double packagingPrice;
    private boolean packingStatus;

    public SpecialProduct(String name, double price, int category, double packagingPrice) {
        super(name, price, category);
        this.packingStatus = false;
        this.packagingPrice = packagingPrice;
    }

    @Override
    public String toString() {
        return super.toString() + ", Packing price:"+ packagingPrice + ", Special Packing Status: " + (packingStatus ? "Yes" : "No");
    }

    public boolean isPackingStatus() {
        return packingStatus;
    }

    @Override
    public double getPrice(){
        return price+packagingPrice;
    }

    public double getPackagingPrice() {
        return packagingPrice;
    }

    public boolean packingProduct() {
        if (!packingStatus) {
            packingStatus = true;
        }
        return packingStatus;
    }
}
