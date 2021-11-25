package nix.education.java.dinnerparty;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Double> friends = new HashMap<String, Double>();

        int number = getIntInput("Enter the number of friends joining (including you):", scanner);
        checkNumberOfGuests(number);

        addGuests(scanner, friends, number);

        int amount = getIntInput("Enter the total amount:", scanner);
        // calculate value for each friend
        calculatePrice(friends, number, amount);

        whoIsLucky(scanner, friends, number, amount);

        System.out.println(friends);
    }

    private static void whoIsLucky(Scanner scanner, Map<String, Double> friends, int number, int amount) {
        System.out.println("Do you want to use the \"Who is lucky?\" feature? Write Yes/No:");
        String answer = scanner.nextLine();
        if(answer.equals("Yes")) {
            calculateLucky(friends, amount, number);
        }
        else {
            System.out.println("No one is going to be lucky");
        }
    }

    private static void addGuests(Scanner scanner, Map<String, Double> friends, int number) {
        System.out.println("Enter the name of every friend (including you), each on a new line:");
        for (int i = 0; i < number; i++) {
            friends.put(scanner.nextLine(), 0.0);
        }
    }

    private static void checkNumberOfGuests(int number) {
        if(number <= 0) {
            System.out.println("No one is joining for the party");
            System.exit(0);
        }
    }

    /**
     * Gets integer input from scanner and skips the new line symbol
     */
    private static int getIntInput(String message, Scanner scanner) {
        System.out.println(message);
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }
    /**
     * rounds double to 2 decimal places
     */
    private static double round2(double number) {
        return Math.round(number * 100.0) / 100.0;
    }

    private static void calculatePrice(Map<String, Double> friends, double number, int amount) {
        for(var friend : friends.entrySet()) {
            friend.setValue(round2(amount / number));
        }
    }

    private static void calculateLucky(Map<String, Double> friends, int moneyAmount, int friendsNumber) {
        String lucky = (String)friends.keySet().toArray()[new Random().nextInt(friends.size())];
        System.out.println(lucky + " is the lucky one!");
        for(var friend : friends.entrySet()) {
            if(friend.getKey().equals(lucky)) {
                friend.setValue(0.0);
                continue;
            }
            friend.setValue(round2(moneyAmount / (double)(friendsNumber - 1)));
        }
    }
}