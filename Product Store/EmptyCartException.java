package Liadsalhi;

public class EmptyCartException extends Exception{
    public EmptyCartException(String msg){
        super(msg);
    }

    public EmptyCartException(){
        super("\nThis cart is empty. Please add products and try again.");
    }
}
