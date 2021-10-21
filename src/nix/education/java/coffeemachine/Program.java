package nix.education.java.coffeemachine;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine machine = new CoffeeMachine();
        while (true) {
            if(machine.getState() == CoffeeMachine.State.MENU) {
                System.out.println("Write action (buy, fill, take, remaining, exit):");
            }
            machine.command(scanner.nextLine());
        }
    }
}
