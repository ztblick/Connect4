public class Board {

    private int[][] board;
    private static final int EMPTY = 0;
    private static final int RED = 1;
    private static final int YELLOW = 2;
    private int ROWS;
    private int COLS;
    private int lastRow;
    private int lastCol;
    private int lastPlay;


    public Board(int r, int c) {
        // Declare and initialize the board.
        ROWS = r;
        COLS = c;
        board = new int[ROWS][COLS];
        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLS; j++) {
                board[i][j] = EMPTY;
            }
        }
    }


    public boolean isFull(int col) {
        return board[0][col] != EMPTY;
    }

    public boolean isFull() {
        for (int[] row : board)
            for (int i : row)
                if (i == EMPTY)
                    return false;
        return true;
    }

    public String toString() {
        String out = "";
        for (int i = 0; i < COLS; i++) {
            out += " " + (i + 1);
        }
        out += "\n";
        for (int i = 0; i < COLS; i++)
            out += "--";
        out += "-\n";

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                out += " ";
                if(board[i][j]==EMPTY)
                    out += "_";
                else if (board[i][j]==RED)
                    out += "R";
                else
                    out += "Y";
            }
            out += "\n";
        }
        return out;
    }

    public void add(int col, int turn) {
        int row = 0;
        while (row < ROWS && board[row][col] == EMPTY) {
            row++;
        }
        row--;
        if (turn % 2 == 0)
            board[row][col] = RED;
        else
            board[row][col] = YELLOW;
        lastRow = row;
        lastCol = col;
        lastPlay = board[row][col];
    }

    public int nextSquare(int count, int row, int col, String direction) {
        if (count == 4)
            return count;
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS)
            return count;
        if (board[row][col] == lastPlay) {
            if (direction.contains("UP"))
                row--;
            else if (direction.contains("DOWN"))
                row++;
            if (direction.contains("LEFT"))
                col--;
            else if (direction.contains("RIGHT"))
                col++;
            return nextSquare(++count, row, col, direction);
        }
        return count;
    }
    public boolean hasVerticalWin() {
        return nextSquare(0, lastRow, lastCol, "UP") +
                nextSquare(0, lastRow, lastCol, "DOWN") > 4;
    }
    public boolean hasHorizontalWin() {
        return nextSquare(0, lastRow, lastCol, "RIGHT") +
                nextSquare(0, lastRow, lastCol, "LEFT") > 4;
    }

    public boolean hasDiagonalWin() {
        return nextSquare(0, lastRow, lastCol, "UP RIGHT") +
                nextSquare(0, lastRow, lastCol, "DOWN LEFT") > 4 ||
                nextSquare(0, lastRow, lastCol, "UP LEFT") +
                nextSquare(0, lastRow, lastCol, "DOWN RIGHT") > 4;
    }
}