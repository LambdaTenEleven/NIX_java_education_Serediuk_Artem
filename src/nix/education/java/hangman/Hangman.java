package nix.education.java.hangman;

import java.util.HashSet;
import java.util.Scanner;

public class Hangman {
    private static final int MIN_HEALTH = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("HANGMAN");
        while (true) {
            System.out.println("Type \"play\" to play the game, \"exit\" to quit");
            var input = scanner.nextLine();
            if(input.equals("play")) {
                play();
            }
            else if(input.equals("exit")) {
                System.exit(0);
            } else {
                System.out.println("Wrong command");
            }
        }
    }
    private static void play() {
        Scanner scanner = new Scanner(System.in);

        String[] words = {
                "java",
                "python",
                "javascript",
                "kotlin"
        };
        String word = words[getRandomNumber(0, 4)];
        StringBuilder hint = new StringBuilder(new String(new char[word.length()]).replace('\0', '-'));

        int life = 8;
        HashSet<String> wrongChars = new HashSet<String>();

        while(life > MIN_HEALTH) {
            System.out.println(hint);
            System.out.println("Enter a letter: ");
            // get input char
            String inputString = scanner.nextLine();
            char input = inputString.charAt(0);
            // check if input is wrong
            if(inputString.length() != 1 || !Character.isLowerCase(input) || !(input >= 'a' && input <= 'z')) {
                System.out.println("Please enter a lowercase English letter");
                continue;
            }


            if (word.contains(Character.toString(input)) && hint.toString().contains(Character.toString(input))) {
                System.out.println("You've already guessed this letter");
            }
            else if(word.contains(Character.toString(input))) {
                // if word contains input char replace '-' in hint with this char
                for(int j = 0; j < word.length(); j++) {
                    if((char)input == word.charAt(j)) {
                        hint.setCharAt(j, input);
                    }
                }
            }
            else {
                if(wrongChars.contains(Character.toString(input))) {
                    System.out.println("You've already guessed this letter wrong");
                } else {
                    System.out.println("That letter doesn't appear in the word");
                    wrongChars.add(Character.toString(input));
                    --life;
                }
            }
            if(word.equals(hint.toString())) {
                //win
                System.out.println("You guessed the word " + word + "!\nYou survived!\n");
                break;
            }
        }
        if(life <= MIN_HEALTH) {
            System.out.println("You lost!");
        }
    }
    private static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
