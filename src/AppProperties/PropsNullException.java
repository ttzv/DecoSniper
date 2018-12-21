package AppProperties;

public class PropsNullException extends RuntimeException{

    public PropsNullException(){
        System.out.println("Properties empty or key not found");
    }

}
