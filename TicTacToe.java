import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    
    // Constructor to initialize the board and starting player
    public TicTacToe() {
        board = new char[3][3];  // Create a 3x3 grid
        currentPlayer = 'X';     // Player 'X' starts
        initializeBoard();
    }

    // Initialize or reset the board with empty cells ('-')
    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print the current state of the board
    public void printBoard() {
        System.out.println("Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Check if the board is full (for a draw scenario)
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // Place a mark ('X' or 'O') on the board
    public boolean placeMark(int row, int col) {
        // Ensure the chosen spot is valid and empty
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    // Switch the current player after each turn
    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Check if the current player has won
    public boolean checkForWin() {
        // Check rows, columns, and diagonals for a win
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    // Check rows for a win
    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    // Check columns for a win
    private boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    // Check diagonals for a win
    private boolean checkDiagonals() {
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer)
            || (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    // Main function to start the game
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        boolean gameWon = false;
        boolean gameDraw = false;

        System.out.println("Tic-Tac-Toe Game!");
        game.printBoard();

        while (!gameWon && !gameDraw) {
            int row, col;
            // Get the player's move
            System.out.println("Player " + game.currentPlayer + ", enter your move (row and column: 0, 1, or 2): ");
            row = scanner.nextInt();
            col = scanner.nextInt();

            // Place the mark and switch player if the move is valid
            if (game.placeMark(row, col)) {
                game.printBoard();
                gameWon = game.checkForWin();
                gameDraw = game.isBoardFull();
                
                if (!gameWon) {
                    game.changePlayer();
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        if (gameWon) {
            System.out.println("Player " + game.currentPlayer + " wins!");
        } else if (gameDraw) {
            System.out.println("It's a draw!");
        }

        scanner.close();
    }
}
