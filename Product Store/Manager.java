package Liadsalhi;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Manager {
    Seller[] sellers;
    int numSellers;
    Buyer[] buyers;
    int numBuyers;

    public Manager() {
        sellers = new Seller[2];
        numSellers = 0;
        buyers = new Buyer[2];
        numBuyers = 0;
    }

    public Seller[] getSellers() {
        return sellers;
    }

    public int getNumSellers() {
        return numSellers;
    }

    public int getNumBuyers() {
        return numBuyers;
    }

    public Buyer[] getBuyers() {
        return buyers;
    }

    public boolean isBuyerExists(String name) {
        if (numBuyers <= 0) {
            return false;
        }
        for (int i=0 ; i<numBuyers ; i++) {
            if (buyers[i].getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }


    public boolean isSellerExists(String name) {
        if (numSellers <= 0) {
            return false;
        }
        for (int i=0 ; i<numSellers ; i++) {
            if (sellers[i].getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public Buyer getBuyer(String name) throws NoSuchElementException {
        for (int i = 0; i < numBuyers; i++) {
            if (buyers[i].getName().equalsIgnoreCase(name)) {
                return buyers[i];
            }
        }
        throw new NoSuchElementException("Buyer not found: " + name);
    }

    public Seller getSeller(String name) throws NoSuchElementException {
        for (int i=0 ; i<numSellers ; i++) {
            if (sellers[i].getName().equalsIgnoreCase(name)) {
                return sellers[i];
            }
        }
        throw new NoSuchElementException("Seller not found: " + name);
    }

    public boolean addBuyer(String name, String password, Address address) {
        if (numBuyers == buyers.length) {
            Buyer[] listNew = new Buyer[buyers.length * 2];
            System.arraycopy(buyers, 0, listNew, 0, buyers.length);
            buyers = listNew;
        }
        buyers[numBuyers] = new Buyer(name, password, address);
        numBuyers++;
        return true;
    }

    public boolean addSeller(String name, String password) {
        if (numSellers == sellers.length) {
            Seller[] listNew = new Seller[sellers.length * 2];
            System.arraycopy(sellers, 0, listNew, 0, sellers.length);
            sellers = listNew;
        }
        sellers[numSellers] = new Seller(name, password);
        numSellers++;
        return true;
    }

    public boolean addSpecialProductToSeller(String sellerName ,String productName,double productPrice,int category,double packingPrice) {
        Seller seller = getSeller(sellerName);
        return (seller.addProduct(new SpecialProduct(productName, productPrice, category, packingPrice)));
    }

    public boolean addProductToSeller(String sellerName ,String productName,double productPrice,int category) {
        Seller seller = getSeller(sellerName);
        return (seller.addProduct(new Product(productName, productPrice, category)));
    }

    public boolean areProductsAvailable(String sellerName) {
        Seller seller = getSeller(sellerName);
        return seller.getNumProduct()>0;
    }

    public StringBuffer showProducts(String sellerName) {
        ;
        Seller seller = getSeller(sellerName);
        return seller.stringProducts();
    }

    public Product productChosen(String productChosen, String sellerName) {
        Seller seller = getSeller(sellerName);
        for(int i=0 ; i<seller.getNumProduct() ; i++){
            if (seller.getProducts()[i].getName().equalsIgnoreCase(productChosen)) {
                return seller.getProducts()[i];
            }
        }
        return null;
    }

    public boolean isProductExists(String sellerName, String productChosen) {
        Seller seller = getSeller(sellerName);
        for(int i=0 ; i<seller.getNumProduct() ; i++){
            if (seller.getProducts()[i].getName().equalsIgnoreCase(productChosen)) {
                return true;
            }
        }
        return false;
    }

    public boolean addToCart(String buyerName, Product productChosen) {
        Buyer buyer = getBuyer(buyerName);
        if (buyer.getName().equalsIgnoreCase(buyerName)) {
            return buyer.addToBuyerCart(productChosen);
        }
        return false;
    }

    public boolean isCartEmpty(Buyer buyer) {
        return buyer.getShoppingCart().getNumProducts()<=0;
    }

    public boolean isOrderHistoryEmpty(Buyer buyer) {
        return buyer.getNumOfOrderHistory()<=0;
    }

    public double orderPrice(Buyer buyer) {
        return buyer.getShoppingCart().getPrice();
    }

    public boolean payForOrder(Buyer buyer) {
        if (buyer.getShoppingCart().getNumProducts()==0) {
            return false;
        }
        return (buyer.addOrderToOrderHistory());
    }

    public StringBuffer showAllBuyers() {
        StringBuffer stringBuyers = new StringBuffer();
        if (numBuyers<=0){
            stringBuyers.append("No buyers available yet");
            return stringBuyers;
        }
        if(numBuyers>1) {
            Arrays.sort(buyers); //by name
        }
        for (int i=0 ; i<numBuyers ; i++) {
            stringBuyers.append(buyers[i].toString()).append("\n\n");
        }
        return stringBuyers;
    }

    public StringBuffer showAllSellers() {
        StringBuffer stringSellers = new StringBuffer();
        if (numSellers<=0){
            stringSellers.append("No Sellers available yet");
            return stringSellers;
        }
        if (numSellers>1) {
            Arrays.sort(sellers); //from the seller with the most products to the one with the least
        }
        for (int i=0 ; i<numSellers ; i++) {
            stringSellers.append(sellers[i].toString()).append("\n\n");
        }
        return stringSellers;
    }

    public StringBuffer showProductsByCategory(int categoryOrdinal) {
        StringBuffer sb = new StringBuffer();
        Product[] p;
        for (int i=0 ; i<numSellers ; i++) {
            boolean foundProduct = false;
            p=sellers[i].getProducts();
            for(int j=0; j<sellers[i].getNumProduct();j++)
                if (p[j].getCategory().ordinal() == categoryOrdinal) {
                    if (!foundProduct) {
                        sb.append("\nSeller Name: ").append(sellers[i].getName());
                        foundProduct = true;
                    }
                    sb.append("\n").append(p[j].toStringNoCategory());
                }
        }
        if(sb.isEmpty()){
            sb.append("No products available in this category");
        }
        return sb;
    }

    public StringBuffer showOrderHistory(Buyer buyer){
        StringBuffer sb = new StringBuffer();
        sb.append(buyer.showOrderHistory());
        return sb;
    }

    public Order chosenOrder(int numOrder,Buyer buyer){
        return buyer.chosenOrder(numOrder);
    }

    public Boolean replaceShoppingCart(Order order,Buyer buyer){
        return buyer.setNewShoppingCart(order);
    }
}