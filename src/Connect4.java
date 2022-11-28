import java.util.Scanner;
public class Connect4 {

    private Board board;
    private int turn;
    private String winner;
    private static final int ROWS = 6;
    private static final int COLS = 7;

    public Connect4() {
        board = new Board(ROWS, COLS);
        turn = 0;
    }

    public void playGame(){
        printGreeting();
        printBoard();
        do {
            playRound();
            printBoard();
            turn++;
        } while (!isOver());
        printWinner();
    }

    public void playRound() {
        Scanner s = new Scanner(System.in);
        int col;
        do {
            System.out.println("Enter a Column: ");
            col = s.nextInt();
            col--;
        } while (col < 0 || col >= COLS || board.isFull(col));

        board.add(col, turn);
    }

    public void printWinner() {
        if (winner.equals("TIE"))
            System.out.println("It's a tie!");
        else
            System.out.println(winner + " WINS!");
    }

    public void printGreeting() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Welcome to Connect4!");
        System.out.println();
        System.out.println("This is a game for two players, RED and YELLOW.");
        System.out.println("You can play at a location by entering its row and column numbers.");
        System.out.println("RED goes first. Good luck!");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
    }

    public boolean isOver() {
        if (board.hasVerticalWin() || board.hasHorizontalWin() || board.hasDiagonalWin()) {
            if (turn % 2 == 1)
                winner = "RED";
            else
                winner = "YELLOW";
            return true;
        }
        if (board.isFull()) {
            winner = "TIE";
            return true;
        }
        return false;
    }

    public void printBoard() {
        System.out.println(board);
    }

    public static void main(String[] args) {
        Connect4 c = new Connect4();
        c.playGame();
    }
}
