package Liadsalhi;

public class SomethingWentWrongException extends Exception {
    public SomethingWentWrongException(String msg){
        super(msg);
    }

    public SomethingWentWrongException(){
        super("\nSomething Went Wrong...please try again.");
    }
}
