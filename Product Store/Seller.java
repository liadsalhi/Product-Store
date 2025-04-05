package Liadsalhi;

public class Seller extends User implements Comparable<Seller> {
    private Product[] products;
    private int numProduct;

    public Seller(String name, String password) {
        super(name, password);
        products = new Product[2];
        numProduct = 0;
    }

    public Product[] getProducts() {
        return products;
    }

    public int getNumProduct() {
        return numProduct;
    }

    public boolean addProduct(Product p){
        if (numProduct == products.length) {
            Product[] listNew = new Product[products.length * 2];
            System.arraycopy(products, 0, listNew, 0, products.length);
             products = listNew;
        }
        products[numProduct] = p;
        numProduct++;
        if(p instanceof SpecialProduct){
            ((SpecialProduct)p).packingProduct();
            return true;
        }
        return true;
    }

    public StringBuffer stringProducts(){
        StringBuffer stringProducts = new StringBuffer();
        if(numProduct<=0){
            stringProducts.append("No products available");
            return stringProducts;
        }
        for (int i = 0; i < numProduct; i++) {
            stringProducts.append("\n").append(i + 1).append(".").append(products[i].toString());
        }
        return stringProducts;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nProducts:"+ stringProducts();
    }

    @Override
    public int compareTo(Seller o) {
        if (numProduct<o.numProduct){
            return 1;
        }
        if (numProduct>o.numProduct){
            return -1;
        }
        return 0;
    }
}




