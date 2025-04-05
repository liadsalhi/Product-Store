package Liadsalhi;


public class Order {
    private Date date;
    private Product[] products ;
    private int numProducts;
    private double price;
    private Buyer buyer;

    public Order() {
        products = new Product[2];
        numProducts = 0;
        price = 0;
    }

    public Order(Order other) { //copy constructor
        this.date = new Date(other.date);
        this.products = other.products;
        this.numProducts = other.numProducts;
        this.price = other.price;
        this.buyer = other.buyer;
    }

    public Date getDate() {
        return date;
    }

    public boolean setDate(Date date) {
        this.date = date;
        return true;
    }

    public Product[] getProducts() {
        return products;
    }

    public boolean setProducts(Product[] products) {
        this.products = products;
        return true;
    }

    public double getPrice() {
        return price;
    }

    public boolean setPrice(double price) {
        this.price = price;
        return true;
    }

    public int getNumProducts() {
        return numProducts;
    }

    public boolean setNumProducts(int numProducts) {
        this.numProducts = numProducts;
        return true;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public boolean setBuyer(Buyer buyer) {
        this.buyer = buyer;
        return true;
    }

    public StringBuffer showOrder() {
        StringBuffer sb = new StringBuffer();
        sb.append("Order date: ").append(date.toString());
        sb.append("\nProducts: ").append(showOrderProducts());
        return sb;
    }

    public boolean addProduct(Product product) {
        if (numProducts == products.length) {
            Product[] listNew = new Product[products.length * 2];
            System.arraycopy(products, 0, listNew, 0, products.length);
            products = listNew;
        }
        products[numProducts] = product;
        price += product.getPrice();
        numProducts++;
        return true;
    }

    public StringBuffer showOrderProducts(){
        StringBuffer sb = new StringBuffer();
        if (numProducts<=0){
            sb.append("\nShopping cart is empty");
            return sb;
        }
        for (int i = 0; i<numProducts; i++) {
            sb.append("\n").append(products[i].toString());
        }
        return sb;
    }
}

