package nix.education.java.chatbot;

import java.util.Scanner;

public class ChatBot {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        String botName = "Bob";
        int birthYear = 2021;
        System.out.println("Hello! My name is " + botName + "\nI was created in " + birthYear);

        System.out.println("Please, enter your name: ");
        String yourName = scanner.nextLine();
        System.out.println("What a great name, " + yourName + "!");

        int remainder3, remainder5, remainder7;
        System.out.println("Let me guess your age.\n" +
                "Enter remainders of dividing your age by 3, 5 and 7.");
        remainder3 = scanner.nextInt();
        remainder5 = scanner.nextInt();
        remainder7 = scanner.nextInt();
        int yourAge = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;
        System.out.println("Your age is " + yourAge + "; that's a good time to start programming!");

        System.out.println("Now I`ll prove I can count to any number you want.");
        int number = scanner.nextInt();
        for (int i = 0; i <= number; i++) {
            System.out.println(i + " !");
        }

        System.out.println("Imagine there's a java class Human. How to create an object of that class?\n" +
                "1. Human human = Human();\n2. Human human = new Human();\n3. Human human = new(Human);\n" +
                "4. Human = new Human();");
        while(scanner.nextInt() != 2) {
            System.out.println("Please try again!");
        }
        System.out.println("Great, you right!");
    }
}
