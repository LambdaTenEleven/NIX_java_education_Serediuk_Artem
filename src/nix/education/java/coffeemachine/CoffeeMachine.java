package nix.education.java.coffeemachine;

import java.util.Objects;
import java.util.Scanner;

public class CoffeeMachine {
    enum State {
        MENU, COFFEE, FILL_WATER, FILL_MILK, FILL_BEANS, FILL_CUPS
    }
    private final Coffee[] coffees;
    private State state = State.MENU;
    private int money = 550;
    private int water = 400;
    private int milk = 540;
    private int coffee = 120;
    private int cups = 9;

    public CoffeeMachine() {
        coffees = new Coffee[]{
                new Coffee("espresso", 4, 250, 0, 16),
                new Coffee("latte", 7, 350, 75, 20),
                new Coffee("cappuccino", 6, 200, 100, 12)
        };
    }

    public void command(String arg) {
        if (state == State.MENU) {
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
                        coffee + " g of coffee beans\n" +
                        cups + " of disposable cups\n" +
                        money + " uah of money");
            } else if (Objects.equals(arg, "exit")) {
                System.exit(0);
            } else {
                System.out.println("Wrong action");
            }
        } else if (state == State.COFFEE) {
            if (Objects.equals(arg, "back")) {
                state = State.MENU;
                return;
            }
            try {
                int choice = Integer.parseInt(arg) - 1;
                if (choice >= 0 && choice <= 2) {
                    var chosen = coffees[choice];
                    // check if can make a coffee
                    if (water < chosen.water()) {
                        System.out.println("Sorry, not enough water");
                    } else if (milk < chosen.milk()) {
                        System.out.println("Sorry, not enough milk");
                    } else if (coffee < chosen.beans()) {
                        System.out.println("Sorry, not enough coffee beans");
                    } else if (cups < 1) {
                        System.out.println("Sorry, not enough coffee cups");
                    } else {
                        System.out.println("I have enough resources, making you a coffee!");
                        water -= chosen.water();
                        milk -= chosen.milk();
                        coffee -= chosen.beans();
                        money += chosen.money();
                        --cups;
                    }
                } else {
                    System.out.println("This coffee does not exist");
                }
            } catch (Exception ex) {
                System.out.println("Wrong action");
            }
            state = State.MENU;
        } else if (state == State.FILL_WATER) {
            int input = 0;
            try {
                input = Integer.parseInt(arg);
                water += input;
            } catch (Exception ex) {
                System.out.println("Wrong input");
            }
            state = State.FILL_MILK;
            System.out.println("Write how many ml of milk you want to add:");
        } else if (state == State.FILL_MILK) {
            int input = 0;
            try {
                input = Integer.parseInt(arg);
                milk += input;
            } catch (Exception ex) {
                System.out.println("Wrong input");
            }
            state = State.FILL_BEANS;
            System.out.println("Write how many grams of coffee beans you want to add:");
        } else if (state == State.FILL_BEANS) {
            int input = 0;
            try {
                input = Integer.parseInt(arg);
                coffee += input;
            } catch (Exception ex) {
                System.out.println("Wrong input");
            }
            state = State.FILL_CUPS;
            System.out.println("Write how many disposable coffee cups you want to add:");
        } else if (state == State.FILL_CUPS) {
            int input = 0;
            try {
                input = Integer.parseInt(arg);
                cups += input;
            } catch (Exception ex) {
                System.out.println("Wrong input");
            }
            state = State.MENU;
        }
    }
    public State getState() {
        return state;
    }
}
