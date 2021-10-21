package nix.education.java.coffeemachine;

import java.util.Objects;

public class CoffeeMachine {
    enum State {
        MENU, COFFEE, FILL_WATER, FILL_MILK, FILL_BEANS, FILL_CUPS
    }

    private State state = State.MENU;
    private int money = 550;
    private int water = 400;
    private int milk = 540;
    private int beans = 120;
    private int cups = 9;


    public void command(String arg) {
        if (state == State.MENU) {
            menuMain(arg);
        } else if (state == State.COFFEE) {
            menuCoffee(arg);
        } else {
            menuFill(arg);
        }
    }
    // main menu
    private void menuMain(String arg) {
        if (Objects.equals(arg, "buy")) {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back – to " +
                    "main menu:");
            state = State.COFFEE;
        } else if (Objects.equals(arg, "fill")) {
            state = State.FILL_WATER;
            System.out.println("Write how many ml of water you want to add:");
        } else if (Objects.equals(arg, "take")) {
            System.out.println("I gave you " + money + " uah");
            money = 0;
        } else if (Objects.equals(arg, "remaining")) {
            System.out.println("The coffee machine has:\n" +
                    water + " ml of water\n" +
                    milk + " ml of milk\n" +
                    beans + " g of coffee beans\n" +
                    cups + " of disposable cups\n" +
                    money + " uah of money");
        } else if (Objects.equals(arg, "exit")) {
            System.exit(0);
        } else {
            System.out.println("Wrong action");
        }
    }
    // menu when making coffee
    private void menuCoffee(String arg) {
        if (Objects.equals(arg, "back")) {
            state = State.MENU;
            return;
        }
        try {
            int choice = Integer.parseInt(arg) - 1;
            if (choice >= 0 && choice <= 2) {
                var chosen = Coffee.values()[choice];
                // check if can make a coffee
                if (water < chosen.water) {
                    System.out.println("Sorry, not enough water");
                } else if (milk < chosen.milk) {
                    System.out.println("Sorry, not enough milk");
                } else if (beans < chosen.beans) {
                    System.out.println("Sorry, not enough coffee beans");
                } else if (cups < 1) {
                    System.out.println("Sorry, not enough coffee cups");
                } else {
                    System.out.println("I have enough resources, making you a coffee!");
                    water -= chosen.water;
                    milk -= chosen.milk;
                    beans -= chosen.beans;
                    money += chosen.money;
                    --cups;
                }
            } else {
                System.out.println("This coffee does not exist");
            }
        } catch (Exception ex) {
            System.out.println("Wrong action");
        }
        state = State.MENU;
    }
    // menu when filling
    private void menuFill(String arg) {
        int input = 0;
        try {
            input = Integer.parseInt(arg);
        }
        catch (Exception ex) {
            System.out.println("Wrong input");
            return;
        }

        if(state == State.FILL_WATER) {
            System.out.println("Write how many ml of milk you want to add:");
            water += input;
            state = State.FILL_MILK;
        }
        else if (state == State.FILL_MILK) {
            System.out.println("Write how many grams of coffee beans you want to add:");
            milk += input;
            state = State.FILL_BEANS;
        }
        else if (state == State.FILL_BEANS) {
            System.out.println("Write how many disposable coffee cups you want to add:");
            beans += input;
            state = State.FILL_CUPS;
        }
        else if (state == State.FILL_CUPS) {
            cups += input;
            state = State.MENU;
        }
        else throw new IllegalStateException("Wrong Coffee machine state");
    }
    public State getState() {
        return state;
    }
}
