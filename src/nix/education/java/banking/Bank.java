package nix.education.java.banking;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Bank {
    private final String INDUSTRY = "5";
    private final String IIN = "400000";
    private final int CARD_ID_SIZE = 9;

    private Card session = null;

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            if(session == null) {
                unlogged(scanner);
            } else {
                logged(scanner);
            }
        }
    }

    private void logged(Scanner scanner) {
        System.out.println("1. Balance\n" +
                "2. Add income\n" +
                "3. Do transfer\n" +
                "4. Close account\n" +
                "5. Log out\n" +
                "0. Exit\n");
        String input = scanner.nextLine();
        if(input.equals("1")) {
            System.out.println("Balance: " + session.getBalance());
        } else if (input.equals("2")) {
            addIncome(scanner);
        }
        else if(input.equals("3")) {
            transfer(scanner);
        }
        else if(input.equals("4")) {
            BankDB.deleteCard(session);
            session = null;
            System.out.println("Account closed!");
        }
        else if (input.equals("5")) {
            session = null;
            System.out.println("Logged out");
        } else if (input.equals("0")) {
            System.out.println("Bye!");
            System.exit(0);
        } else {
            System.out.println("Wrong command!");
        }

    }
    private void unlogged(Scanner scanner) {
        System.out.println("1. Create an account\n" +
                "2. Log into account\n" +
                "0. Exit\n");
        String input = scanner.nextLine();
        if(input.equals("1")) {
            var card = new Card(INDUSTRY + IIN, CARD_ID_SIZE);
            //cards.add(card);
            BankDB.addCard(card);

            System.out.println("Your card has been created\nYour card number:\n" + card.getCardNumber() +
                    "\nYour card PIN:\n" + card.getPIN());
        } else if (input.equals("2")) {
            login(scanner);

        } else if (input.equals("0")) {
            System.out.println("Bye!");
            System.exit(0);
        }
        else {
            System.out.println("Wrong command");
        }
    }

    private void transfer(Scanner scanner) {
        System.out.println("Enter card number:");
        String cardNumber = scanner.nextLine();
        if(!Card.luhnAlgorithm(cardNumber)) {
            System.out.println("Probably you made a mistake in the card number. Please try again!");
            return;
        }
        //get card
        Card card = BankDB.getCard(cardNumber);
        if(card == null) {
            System.out.println("Such a card does not exist.");
            return;
        }
        if(card.equals(session)) {
            System.out.println("You can't transfer money to the same account!");
            return;
        }

        System.out.println("Enter how much money you want to transfer:");
        int money = scanner.nextInt();
        scanner.nextLine();
        if(money > session.getBalance()) {
            System.out.println("Not enough money!");
            return;
        }

        session.setBalance(session.getBalance() - money);
        card.setBalance(card.getBalance() + money);
        BankDB.updateCard(session);
        BankDB.updateCard(card);
        System.out.println("Money transfered!");
    }

    private void addIncome(Scanner scanner) {
        System.out.println("Enter income: ");
        int income = scanner.nextInt();
        scanner.nextLine();
        session.setBalance(session.getBalance() + income);
        BankDB.updateCard(session);
    }


    private void login(Scanner scanner) {
        System.out.println("Enter your card number: ");
        String cardID = scanner.nextLine();
        System.out.println("Enter your PIN: ");
        String cardPIN = scanner.nextLine();
        //Card card = cards.stream().filter(c -> c.getCardNumber().equals(cardID)).findFirst().orElse(null);
        Card card = BankDB.getCard(cardID);
        if(card != null) {
            if(card.getPIN().equals(cardPIN)) {
                session = card;
                System.out.println("You have successfully logged in!");
            } else {
                System.out.println("PIN is wrong");
            }
        } else {
            System.out.println("Card not found");
        }
    }

}
