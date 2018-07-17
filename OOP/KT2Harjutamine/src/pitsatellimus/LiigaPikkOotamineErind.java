package pitsatellimus;

import java.io.*;

public class LiigaPikkOotamineErind extends RuntimeException{
    private String slowOrdersFileName = "liigaAeglased.txt";

    LiigaPikkOotamineErind(Tellimus slowOrder){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(slowOrdersFileName)))) {
            bw.write(orderIntoString(slowOrder));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File can't be accessed!");
            System.exit(-1);
        }
    }

    private String orderIntoString(Tellimus order){
        String orderString = "";
        orderString += order.getClientName();
        orderString += ";";
        for (Pitsa pizza : order.getPizzaList()) {
            orderString += pizza.getPizzaName();
            orderString += "/";
            orderString += pizza.getPizzaPrice();
        }
        return orderString;
    }
}
