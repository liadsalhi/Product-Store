
//Liad salhi - 314919697 keren
package Liadsalhi;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {//Liad salhi
    static Scanner scanner = new Scanner(System.in);

    public static void menu() {
        System.out.println("\nMENU:");
        System.out.println("0. Exit");
        System.out.println("1. Add Seller");
        System.out.println("2. Add Buyer");
        System.out.println("3. Add Product to Seller");
        System.out.println("4. Add Product to Buyer");
        System.out.println("5. Payment for Buyer");
        System.out.println("6. Show All Buyers");
        System.out.println("7. Show All Sellers");
        System.out.println("8. Show products by category");
        System.out.println("9. Create New Cart from Order History");
        System.out.print("Choose an option: ");
    }

    public static void addSeller(Manager m) throws SomethingWentWrongException {
        System.out.println("Enter seller name:");
        String sellerName = scanner.nextLine();
        while (m.isBuyerExists(sellerName) || m.isSellerExists(sellerName)) {
            System.out.println("This name already exists, try another name:");
            sellerName = scanner.nextLine();
        }
        System.out.println("Enter seller password:");
        String sellerPassword = scanner.nextLine();
        if (m.addSeller(sellerName, sellerPassword)) {
            System.out.println("seller added successfully");
        } else {
            throw new SomethingWentWrongException();
        }
    }

    public static void addBuyer(Manager m) throws Exception {
        boolean inputValid = false;
        System.out.println("Enter buyer name:");
        String buyerName = scanner.nextLine();
        while (m.isBuyerExists(buyerName) || m.isSellerExists(buyerName)) {
            System.out.println("This name already exists, try another name:");
            buyerName = scanner.nextLine();
        }
        System.out.println("Enter buyer password:");
        String buyerPassword = scanner.nextLine();
        System.out.println("Enter buyer address");
        System.out.println("country: ");
        String country = scanner.nextLine();
        System.out.println("city: ");
        String city = scanner.nextLine();
        System.out.println("street: ");
        String street = scanner.nextLine();
        while (!inputValid) {
            try {
                System.out.println("houseNumber: ");
                int houseNumber = scanner.nextInt();
                scanner.nextLine();
                inputValid = true;
                Address buyerAddress = new Address(country, city, street, houseNumber);
                if (m.addBuyer(buyerName, buyerPassword, buyerAddress)) {
                    System.out.println("Buyer added successfully");
                } else {
                    throw new SomethingWentWrongException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid house number.");
                scanner.nextLine();
            }
        }
    }

    public static void addProductToSeller(Manager m) throws Exception {
        boolean inputValid = false;
        int category = -1;
        double productPrice = -1;
        double packingPrice = -1;
        if (m.numSellers <= 0) {
            System.out.println("\nNo sellers available yet.");
        } else {
            System.out.print("Enter seller name to add product to: ");
            String sellerName = scanner.nextLine();
            while (!m.isSellerExists(sellerName)) {
                System.out.println("Seller does not exist, try another name:");
                sellerName = scanner.nextLine();
            }
            while (!inputValid) {  // Validate product category input
                try {
                    System.out.print("Enter product category number (0-Children, 1-Electronics, 2-Office, 3-Clothing): ");
                    category = scanner.nextInt();
                    if (category < 0 || category > 3) {
                        throw new InputMismatchException();
                    }
                    inputValid = true;
                } catch (InputMismatchException e) {
                    System.out.println("Category number must be between 0 and 3");
                    scanner.nextLine(); // clear buffer
                }
            }
            scanner.nextLine(); // clear buffer
            System.out.print("Enter product name: ");
            String productName = scanner.nextLine();
            while (m.isProductExists(sellerName, productName)) {
                System.out.print("This product already exists, try another name: ");
                productName = scanner.nextLine();
            }
            inputValid = false; // Validate product price input
            while (!inputValid) {
                try {
                    System.out.print("Enter product price: ");
                    productPrice = scanner.nextDouble();
                    inputValid = true;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid price.");
                    scanner.nextLine(); // clear buffer
                }
            }
            scanner.nextLine(); // clear buffer
            System.out.print("Is your product need a special packing? (Yes/No): ");
            String specialPacking = scanner.nextLine();
            switch (specialPacking.toLowerCase()) {
                case "yes":
                    // Validate packing price input
                    inputValid = false;
                    while (!inputValid) {
                        try {
                            System.out.print("Please enter the packing price:");
                            packingPrice = scanner.nextDouble();
                            inputValid = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Input must be a valid price");
                            scanner.nextLine(); // clear buffer
                        }
                    }
                    scanner.nextLine(); // clear buffer
                    if (m.addSpecialProductToSeller(sellerName, productName, productPrice, category, packingPrice)) {
                        System.out.println("\nProduct added and packed successfully.");
                    } else {
                        throw new SomethingWentWrongException();
                    }
                    break;
                case "no":
                    if (m.addProductToSeller(sellerName, productName, productPrice, category)) {
                        System.out.println("\nProduct added successfully.");
                    } else {
                        throw new SomethingWentWrongException();
                    }
                    break;
                default:
                    System.out.println("Invalid option. Returning to the main menu");
                    break;
            }
        }
    }

    public static void addProductToBuyer(Manager m) throws Exception {
        if (m.numBuyers <= 0) {
            System.out.println("\nNo buyers available yet");
        } else {
            System.out.print("Enter buyer name to add product to: ");
            String buyerName = scanner.nextLine();
            while (!m.isBuyerExists(buyerName)) {
                System.out.println("Buyer does not exist,try another name: ");
                buyerName = scanner.nextLine();
            }
            System.out.print("Enter seller name to buy from: ");
            String sellerName = scanner.nextLine();
            while (!m.isSellerExists(sellerName)) {
                System.out.println("Seller does not exist, try another name: ");
                sellerName = scanner.nextLine();
            }
            if (m.areProductsAvailable(sellerName)) {
                System.out.print("\n" + sellerName + " products list: " + m.showProducts(sellerName));
                System.out.print("\nEnter product name add to the cart: ");
                String productChosen = scanner.nextLine();
                if (m.isProductExists(sellerName, productChosen)) {
                    if (m.addToCart(buyerName, m.productChosen(productChosen, sellerName))) {
                        System.out.print("\nProduct added to the cart\n");
                    } else {
                        throw new SomethingWentWrongException();
                    }
                } else {
                    System.out.print("\nProduct is not exist\n");
                }
            } else {
                System.out.print("There are no products available for " + sellerName);
            }
        }
    }

    public static void paymentForBuyer(Manager m) throws Exception {
        if (m.numBuyers <= 0) {
            System.out.println("\nNo buyers available yet");
        } else {
            System.out.print("Enter buyer name for payment: ");
            String buyerName = scanner.nextLine();
            while (!m.isBuyerExists(buyerName)) {
                System.out.println("Buyer does not exist");
                System.out.print("Enter buyer name for payment: ");
                buyerName = scanner.nextLine();
            }
            Buyer buyer = m.getBuyer(buyerName);
            if (m.isCartEmpty(buyer)) {
                throw new EmptyCartException();
            } else {
                System.out.print("Your order costs " + m.orderPrice(buyer) + ", would you like to pay?(yes/no) ");
                String payment = scanner.nextLine();
                switch (payment.toLowerCase()) {
                    case "yes":
                        if (m.payForOrder(buyer)) {
                            System.out.println("\nYour order has been placed successfully");
                        } else {
                            throw new SomethingWentWrongException();
                        }
                        break;
                    case "no":
                        System.out.println("\nPayment cancelled.");
                        break;
                    default:
                        System.out.println("Invalid option. Returning to the main menu");
                        break;
                }
            }
        }
    }

    public static void showAllBuyers(Manager m) {
        System.out.println("List of all buyers:\n");
        System.out.println(m.showAllBuyers());
    }

    public static void showAllSellers(Manager m) {
        System.out.println("List of all sellers:\n");
        System.out.println(m.showAllSellers());
    }

    public static void showProductsByCategory(Manager m) throws InputMismatchException {
        boolean inputValid = false;
        while (!inputValid) {
            try {
                System.out.println("Enter product category to show:\n0.Children\n1.Electronics\n2.Office\n3.Clothing");
                int categoryNum = scanner.nextInt();
                scanner.nextLine(); //clear buffer
                if (categoryNum < 0 || categoryNum > 3) {
                    throw new InputMismatchException();
                }
                System.out.println(m.showProductsByCategory(categoryNum));
                inputValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Category number must be between 0 and 3");
                scanner.nextLine();
            }
        }
    }

    public static void createCartFromOrderHistory(Manager m) throws Exception {
        int orderNum = -1;
        if (m.numBuyers <= 0) {
            System.out.println("\nNo buyers available yet");
        } else {
            System.out.print("Enter buyer name: ");
            String buyerName = scanner.nextLine();
            while (!m.isBuyerExists(buyerName)) {
                System.out.println("Buyer does not exist");
                System.out.print("Enter buyer name: ");
                buyerName = scanner.nextLine();
            }
            Buyer buyer = m.getBuyer(buyerName);
            if (m.isOrderHistoryEmpty(buyer)) {
                System.out.print("\nYour order history is empty\n");
            } else {
                System.out.print(m.showOrderHistory(buyer));
                System.out.print("Choose order number from your history orders: ");
                boolean inputValid = false;
                while (!inputValid) {
                    try {
                        orderNum = scanner.nextInt();
                        if (orderNum < 0 || buyer.getNumOfOrderHistory() - 1 < orderNum) {
                            throw new InputMismatchException();
                        }
                        inputValid = true;
                    } catch (InputMismatchException e) {
                        System.out.println("No order was found with that order number. Please try again:");
                        scanner.nextLine();//clear buffer

                    }
                }
                scanner.nextLine(); //clear buffer
                Order chosenOrder = m.chosenOrder(orderNum, buyer);
                if (!m.isCartEmpty(buyer)) {
                    System.out.println("You have items in your cart. Would you like to replace them with the order you selected?(yes/no)");
                    String cartChoose = scanner.nextLine();
                    switch (cartChoose.toLowerCase()) {
                        case "yes":
                            if (m.replaceShoppingCart(chosenOrder, buyer)) {
                                System.out.println("Your order has been replaced successfully");
                            } else {
                                throw new SomethingWentWrongException();
                            }
                            break;
                        case "no":
                            System.out.println("Okay, your cart has been left as it is.");
                            break;
                        default:
                            System.out.println("Invalid option.");
                            break;
                    }
                } else {
                    if (m.replaceShoppingCart(chosenOrder, buyer)) {
                        System.out.println("Your order has been replaced successfully");
                    } else {
                        throw new SomethingWentWrongException();
                    }
                }
            }
        }
    }

    public static void runMenu(Manager m) {
        boolean exit = false;
        String choice;
        do {
            try {
                menu();
                choice = scanner.nextLine();
                switch (choice) {
                    case "0":
                        exit = true;
                        break;
                    case "1":
                        addSeller(m);
                        break;
                    case "2":
                        addBuyer(m);
                        break;
                    case "3":
                        addProductToSeller(m);
                        break;
                    case "4":
                        addProductToBuyer(m);
                        break;
                    case "5":
                        paymentForBuyer(m);
                        break;
                    case "6":
                        showAllBuyers(m);
                        break;
                    case "7":
                        showAllSellers(m);
                        break;
                    case "8":
                        showProductsByCategory(m);
                        break;
                    case "9":
                        createCartFromOrderHistory(m);
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!exit);
        scanner.close();
        System.out.println("Thank you for visiting in our shop. \n goodbye :)");
    }

    public static void main(String[] args) {
        Manager manager = new Manager();

        // Adding predefined sellers
        //manager.addSeller("Seller1", "password1");
        //    manager.addSeller("Seller2", "password2");

        // Adding predefined buyers
        //    manager.addBuyer("bbb", "password1", new Address("Country1", "City1", "Street1", 1));
        //manager.addBuyer("aaa", "password2", new Address("Country2", "City2", "Street2", 2));

        // Adding predefined products to sellers
        // manager.addProductToSeller("Seller1", "Product1", 10.0, 0);
        // manager.addProductToSeller("Seller1", "Product2", 20.0, 0);
        //  manager.addSpecialProductToSeller("Seller2", "Product3", 20.0, 3, 10.0);
        //  manager.addProductToSeller("Seller2", "Product4", 30.0, 0);
        //  manager.addSpecialProductToSeller("Seller2", "Product5", 40.0, 3, 10.0);

        runMenu(manager);
    }
}




