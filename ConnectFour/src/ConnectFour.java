import java.util.Scanner;

public class ConnectFour {

    // This will print the board.
    public static void printBoard(char[][] array) {
        for (int rowIndex = array.length - 1; rowIndex >= 0; rowIndex--) {
            for (int columnIndex = 0; columnIndex < array[rowIndex].length; columnIndex++) {
                System.out.print(array[rowIndex][columnIndex] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // This will set each spot in the array to “-”.
    public static void initializeBoard(char[][] array) {
        for (int rowIndex = 0; rowIndex < array.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < array[rowIndex].length; columnIndex++) {
                array[rowIndex][columnIndex] = '-';
            }
        }
    }

    // Places the token in the column that the user has chosen. Will find the next available spot in that column
    // if there are already tokens there. The row that the token is placed in is returned.
    public static int insertChip(char[][] array, int col, char chipType) {
        for (int columnIndex = 0; columnIndex < array.length; columnIndex++) {
            if (array[columnIndex][col] == '-') {
                array[columnIndex][col] = chipType;
                return columnIndex;
            }
        }
        return 'k';
    }

    // After a token is added, checks whether the token in this location, of the specified chip type, creates
    // four in a row. Will return true if someone won, and false otherwise.
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {
        int count = 0;

        for (int rowIndex = 0; rowIndex < array.length; rowIndex++) {
            if (array[rowIndex][col] == chipType) {
                count++;
                if (count == 4) {return true;}
            }
            else {count = 0;}
        }

        count = 0;

        for (int columnIndex = 0; columnIndex < array[row].length; columnIndex++) {
            if (array[row][columnIndex] == chipType) {
                count++;
                if (count == 4) {return true;}
            }
            else {count = 0;}
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int row, col;

        System.out.print("What would you like the height of the board to be? ");
        row = input.nextInt();

        System.out.print("What would you like the length of the board to be? ");
        col = input.nextInt();

        char board[][] = new char[row][col];
        initializeBoard(board);
        printBoard(board);

        System.out.println("Player 1: x");
        System.out.println("Player 2: o");
        System.out.println();

        boolean player1 = true;
        char player;
        int choiceCol = 0;
        int result = 0;
        boolean done = false;
        int totalPlay = 0;

        while (true) {
            if (player1) {
                System.out.print("Player 1: ");
                player = 'x';
            } else {
                System.out.print("Player 2: ");
                player = 'o';
            }

            System.out.print("Which column would you like to choose? ");
            choiceCol = input.nextInt();

            result = insertChip(board, choiceCol, player);
            printBoard(board);
            done = checkIfWinner(board, choiceCol, result, player);

            if (done) {
                if (player1) {
                    System.out.print("Player 1 won the game! \n");
                } else {
                    System.out.print("Player 2 won the game! \n");
                }
                break;
            }

            player1 = !player1;
            totalPlay++;

            if (totalPlay == row * col) {
                System.out.println("Draw. Nobody wins. \n");
                break;
            }
        }
    }
}
