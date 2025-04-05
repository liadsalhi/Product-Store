package Liadsalhi;
import java.time.LocalDate;

public class Buyer extends User implements Comparable<Buyer> {
    private Address address;
    private Order shoppingCart;
    private Order[] orderHistory = new Order[2];
    private int numOfOrderHistory = 0;

    public Buyer(String name, String password, Address address) {
        super(name, password);
        this.address = new Address(address.getState(), address.getCity(), address.getStreet(), address.getHouseNumber());
        shoppingCart = new Order();
    }

    public Address getAddress() {
        return address;
    }

    public Order getShoppingCart() {
        return shoppingCart;
    }

    public boolean setNewShoppingCart(Order order) {
        this.shoppingCart = new Order(order);
        shoppingCart.setDate(null);
        return true;
    }

    public Order[] getOrderHistory() {
        return orderHistory;
    }

    public int getNumOfOrderHistory() {
        return numOfOrderHistory;
    }

    public boolean setAddress(Address address) {
        this.address = address;
        return true;
    }

    public boolean addToBuyerCart(Product product){
        return shoppingCart.addProduct(product);
    }

    public boolean addOrderToOrderHistory(){
        if (numOfOrderHistory == orderHistory.length) {
            Order[] listNew = new Order[orderHistory.length * 2];
            System.arraycopy(orderHistory, 0, listNew, 0, orderHistory.length);
            orderHistory = listNew;
        }
        LocalDate today = LocalDate.now();
        Date date = new Date(today.getYear(),today.getMonthValue(),today.getDayOfMonth());
        if (shoppingCart.setDate(date) && shoppingCart.setBuyer(this)) {
            orderHistory[numOfOrderHistory] = new Order(shoppingCart);
            numOfOrderHistory++;
            shoppingCart = new Order();
            return true;
        }
        return false;
    }

    public StringBuffer showShoppingCart(){
        return shoppingCart.showOrderProducts();
    }

    public StringBuffer showOrderHistory() {
        StringBuffer sb = new StringBuffer();
        if (numOfOrderHistory<=0){
            sb.append("Order history is empty");
            return sb;
        }
        for (int i = 0; i<numOfOrderHistory; i++) {
                sb.append(i).append(".").append(orderHistory[i].showOrder()).append("\n");
       }
        return sb;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nAddress: "+ address.toString() +
                "\nShopping cart:"+ shoppingCart.showOrderProducts() +
                "\nOrder history:\n"+ showOrderHistory();
    }

    public Order chosenOrder(int numOrder){
        return orderHistory[numOrder];
    }

    @Override
    public int compareTo(Buyer o) {
        return getName().compareTo(o.getName());
    }
}


