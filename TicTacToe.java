import java.util.*;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Game obj = new Game();

        while (true) {
            System.out.println("Welcome to Tic Tac Toe Game!");
            System.out.println("\nHere's the board\n");
            obj.board();

            System.out.println("\nInstruction: You play as X and I'll play as O.\n To make a move, just type the number of the space where you would like to place your X");
            System.out.println("You can start!");
            obj.gam();

            while (true) {
                System.out.print("\nWould you like to play again (yes/no): ");
                String str = sc.next();
                if (str.equalsIgnoreCase("no")) {
                    System.out.println("Thank you for playing!");
                    return;
                } else if (str.equalsIgnoreCase("yes")) {
                    break;
                } else {
                    System.out.println("\nInvalid response, enter again");
                }
            }
        }
    }
}

class Game {
    static char[][] arr;

    public Game() {
        resetBoard();
    }

    public void resetBoard() {
        arr = new char[][]{
            {'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'}
        };
    }

    public void board() {
        for (char[] row : arr) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public void gam() {
        int moves = 0;
        Scanner sc = new Scanner(System.in);
        Random ran = new Random();
        List<Integer> availableMoves = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            availableMoves.add(i);
        }

        while (true) {
            System.out.print("\nPlease enter your move: ");
            int userMove = sc.nextInt();

            if (!availableMoves.contains(userMove)) {
                System.out.println("\nInvalid or occupied move. Try again.");
                continue;
            }

            placeMove(userMove, 'X');
            availableMoves.remove(Integer.valueOf(userMove));

            System.out.println("\nHere's the updated board\n");
            board();

            if (checkWin('X')) {
                System.out.println("\nYou won!");
                break;
            }

            if (availableMoves.isEmpty()) {
                System.out.println("\nBoard is full. It's a tie!");
                break;
            }

            int compMove = availableMoves.get(ran.nextInt(availableMoves.size()));
            placeMove(compMove, 'O');
            availableMoves.remove(Integer.valueOf(compMove));

            System.out.printf("\nI'll place my O in %d position%n", compMove);
            System.out.println("\nHere's the updated board\n");
            board();

            if (checkWin('O')) {
                System.out.println("\nI won!");
                break;
            }
        }
        resetBoard();
    }

    private void placeMove(int move, char player) {
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;
        arr[row][col] = player;
    }

    private boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if (arr[i][0] == player && arr[i][1] == player && arr[i][2] == player){
                return true;
            } 
            if (arr[0][i] == player && arr[1][i] == player && arr[2][i] == player){
                return true;
            }
        }
        if (arr[0][0] == player && arr[1][1] == player && arr[2][2] == player){
            return true;
        }
        if (arr[0][2] == player && arr[1][1] == player && arr[2][0] == player){
             return true;
        }
        return false;
    }
}
