package nix.education.java.tictactoe;

public class TicTacToe {
    public enum Cell {
        NULL, X, O
    }
    public enum GameState {
        NOT_FINISHED, DRAW, X_WINS, O_WINS
    }
    private Cell[][] field;
    public TicTacToe() {
        field = new Cell[][] {
            {
                Cell.NULL, Cell.NULL, Cell.NULL
            },
            {
                Cell.NULL, Cell.NULL, Cell.NULL
            },
            {
                Cell.NULL, Cell.NULL, Cell.NULL
            },
        };
    }

    /**
     * Place X or O to the field
     * @param cell X or O
     * @param x row
     * @param y column
     */
    public void place(Cell cell, int x, int y) {
        if(cell.equals(Cell.NULL)) {
            throw new IllegalArgumentException("Wrong player type");
        }
        if(x < 1 || x > 3 || y < 1 || y > 3) {
            throw new IllegalArgumentException("Coordinates should be from 1 to 3");
        }
        if(!field[x-1][y-1].equals(Cell.NULL)) {
            throw new IllegalArgumentException("This cell is occupied");
        }
        field[x-1][y-1] = cell;
    }

    /**
     * Check the state of the game
     * @return Game state
     */
    public GameState check() {
        if(winCheck(Cell.X)) {
            return GameState.X_WINS;
        }
        if(winCheck(Cell.O)) {
            return GameState.O_WINS;
        }
        // check if there's any free cell
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(field[i][j].equals(Cell.NULL)) {
                    return GameState.NOT_FINISHED;
                }
            }
        }
        return GameState.DRAW;
    }
    private Boolean rowCheck(Cell first, Cell second, Cell third) {
        return first != Cell.NULL && first == second && second == third;
    }
    private Boolean winCheck(Cell type) {
        if(type.equals(Cell.NULL)) {
            throw new IllegalArgumentException("Wrong player type");
        }
        //horizontal
        for(int i = 0; i < 3; i++)
        {
            if(rowCheck(field[i][0], field[i][1], field[i][2]))
            {
                return field[i][0].equals(type);
            }
        }
        //vertical
        for (int i = 0; i < 3; i++)
        {
            if (rowCheck(field[0][i], field[1][i], field[2][i]))
            {
                return field[0][i].equals(type);
            }
        }
        //diagonal
        if(rowCheck(field[0][0], field[1][1], field[2][2]))
        {
            return field[0][0].equals(type);
        }
        if (rowCheck(field[2][0], field[1][1], field[0][2]))
        {
            return field[2][0].equals(type);
        }
        return false;
    }
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("---------\n");
        for(int i = 0; i < 3; i++) {
            output.append("|");
            for(int j = 0; j < 3; j++) {
                if(field[i][j].equals(Cell.NULL)) {
                    output.append(" ");
                }
                else if(field[i][j].equals(Cell.O)) {
                    output.append("O");
                }
                else if(field[i][j].equals(Cell.X)) {
                    output.append("X");
                }
                else {
                    throw new IllegalStateException("Field contains unknown values");
                }
                if(j < 2) {
                    output.append("  "); // make the field more square-ish
                }
            }
            output.append("|\n");
        }
        output.append("---------");
        return output.toString();
    }
}
