package AppProperties;

import java.io.IOException;

public class Test {

    public static void main(String[] args) {
        Propsicl propsicl = new Propsicl();

        try {
            propsicl.createPropsFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
