package nix.education.java.tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe ticTacToe = new TicTacToe();

        TicTacToe.Cell player = TicTacToe.Cell.X;
        while(true) {
            System.out.println(ticTacToe.toString());
            // coordinates input
            int x = 0, y = 0;
            System.out.print("[Player " + player + "] Enter the coordinates: ");
            try {
                x = scanner.nextInt();
                y = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next(); // костыль для сканнера :)
                scanner.next();
                System.out.println("You should enter numbers!");
                continue;
            }
            try {
                ticTacToe.place(player, x, y);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
            // game state check
            var state = ticTacToe.check();
            if(state.equals(TicTacToe.GameState.X_WINS)) {
                System.out.println("X wins");
                break;
            }
            if(state.equals(TicTacToe.GameState.O_WINS)) {
                System.out.println("O wins");
                break;
            }
            if(state.equals(TicTacToe.GameState.DRAW)) {
                System.out.println("Draw!");
                break;
            }
            // change player after turn
            if(player.equals(TicTacToe.Cell.X)) {
                player = TicTacToe.Cell.O;
            }
            else {
                player = TicTacToe.Cell.X;
            }
        }
    }
}
