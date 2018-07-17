package pitsatellimus;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pitsatellimus {
    public static void main(String[] args) {
        List<Tellimus> allOrders = readOrders("tellimused.txt");
        Collections.sort(allOrders);
        double sum = 0;
        for (Tellimus order : allOrders) {
            sum += order.maksumus();
        }
        System.out.println(Math.round(sum*100)/100.0);
    }

    private static List<Tellimus> readOrders(String fileName){
        List<Tellimus> allOrders = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))){
            String line;
            while((line = br.readLine()) != null){
                allOrders.add(translateLineIntoOrder(line));
            }
        } catch (IOException e) {
            System.out.println("Couldn't read the file!");
            System.exit(-1);
        }
        return allOrders;
    }

    private static Tellimus translateLineIntoOrder(String line){
        String[] parts = line.split(";");
        List<String> orderedPizzasInfo = new ArrayList<>();
        List<Pitsa> orderedPizzas = new ArrayList<>();
        String clientName = "";
        boolean namePart = true;
        for (String part : parts) {
            if(namePart){
                clientName = parts[0];
                namePart = false;
            }else {
                orderedPizzasInfo.add(part);
            }
        }
        for (String pizzaInfo : orderedPizzasInfo) {
            String[] newParts = pizzaInfo.split("/");
            orderedPizzas.add(new Pitsa(newParts[0], Double.parseDouble(newParts[1])));
        }
        return new Tellimus(clientName, orderedPizzas, (int) Math.round(Math.random()*25 + 10));
    }
}
