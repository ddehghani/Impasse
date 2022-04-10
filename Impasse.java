public class Impasse {
    public static void main(String[] args) {
        // argument validation

        if (args.length > 4) {
            StdOut.println("Too many arguments.");
            return;
        }

        if (args.length < 4) {
            StdOut.println("Not enough arguments.");
            return;
        }

        int gameMode = 0, guiIndicator = 0, n = 0, k = 0, m = 0;

        if (isInteger(args[0])) {
            gameMode = Integer.parseInt(args[0]);
            if (gameMode > 1 || gameMode < 0) {
                StdOut.println("First input reset to default.");
                gameMode = 0;
            }
        }

        if (isInteger(args[1])) {
            guiIndicator = Integer.parseInt(args[1]);
            if (guiIndicator > 1 || guiIndicator < 0) {
                StdOut.println("Second input reset to default.");
                guiIndicator = 0;
            }
        }

        if (gameMode == 1 || guiIndicator == 1) {
            StdOut.println("Functionality currently not supported.");
            return;
        }

        if (isInteger(args[2])) {
            n = Integer.parseInt(args[2]);
            if (n > 4 || n < 2) {
                StdOut.println("Third input reset to default.");
                n = 2;
            }
        }

        m = calculateM(n);

        if (isInteger(args[3])) {
            k = Integer.parseInt(args[3]);
            if (k > m || k < 3) {
                StdOut.println("Fourth input reset to default.");
                k = 3;
            }
        }


        // GAME START

        final char open = '.', closed = '*', red = 'R', green = 'G', blue = 'B';
        
        // init board
        char[][] gameBoard = new char[m][m];
        for (int row = 0; row < gameBoard.length; row++)
         for (int col = 0; col < gameBoard[0].length; col++)
            gameBoard[row][col] = col == 0 ? open : closed;

        // initial game state displayed
        StdOut.println("The dimension of your board is: " + m + "x" + m); 
        StdOut.println("The length of a blockade is: " + k);
        displayBoard(gameBoard);

        // main loop
        while (!isGameOver(gameBoard)) {
            int move = -1, row = -1, column = -1, color = -1;
            
            // read the move

            boolean isInputValid = false;
            do {
                isInputValid = true;
                StdOut.print("Move: ");
                move = StdIn.readInt();
                if (move < 0 || move > 2)
                    move = -1;
            
                if (move == 2)
                    return;

                // read row and column
                StdOut.print("Row Number: ");
                row = StdIn.readInt();
                if (row < 0 || row > m - 1)
                    row = -1;
              
                StdOut.print("Column Number: ");
                column = StdIn.readInt();
                if (column < 0 || column > m - 1)
                    column = -1;
               
            
                if (move == 1) {
                    StdOut.print("Color: ");
                    color = StdIn.readInt();
                    if (color < 0 || color > n - 1)
                        color = -2;
                }

                if (move == -1) {
                    StdOut.println("Invalid move: Unknown move!");
                    isInputValid = false;
                    continue;
                }
                if (row == -1) {
                    StdOut.println("Invalid move: Outside of board!");
                    isInputValid = false;
                    continue;
                }

                if (column == -1) {
                    StdOut.println("Invalid move: Outside of board!");
                    isInputValid = false;
                    continue;
                }

                if (color == -2) {
                    StdOut.println("Invalid move: Unknown color!");
                    isInputValid = false;
                    continue;
                }

                if (move == 1 && gameBoard[row][column] != open) {
                    StdOut.println("Invalid move: Cell is not open!");
                    isInputValid = false;
                    continue;
                }

                if (move == 0 && (gameBoard[row][column] == open || gameBoard[row][column] == closed)) {
                    StdOut.println("Invalid move: Nothing to delete!");
                    isInputValid = false;
                }
            } while (!isInputValid);
    }
}

    // public static void logInvalidMove
    
    public static boolean isGameOver(char[][] gameBoard) {
        return false;
    }

    public static void displayBoard(char[][] gameBoard) {
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[0].length; col++)
                StdOut.print(gameBoard[row][col]);
            StdOut.println();
        }
    }

    public static int calculateM(int n) {
        switch (n) {
            case 2:
                return 8;
            case 3:
                return 30;
            case 4:
                return 128;
            case 5: 
                return 650;
            case 6:
                return 3912;
        }
        return 0;
    }

    public static boolean isInteger(String str) { 
        try {  
          Integer.parseInt(str);  
          return true;
        } catch(NumberFormatException e) {  
          return false;  
        }  
    }
}