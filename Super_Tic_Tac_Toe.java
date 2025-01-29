import java.util.Scanner;

public class Super_Tic_Tac_Toe 
{
    // Score tracking and game state variables
    public static int playerXWin = 0, playerOWin = 0, tie = 0, saveBoardWin = 0, boardChoice = 0, positionChoice = 0;
    public static boolean gameEnd = false;    
    public static int[] saveWinBoard = new int[9]; // Tracks which boards are won
    public static int[][] positionsChosen = new int[9][9]; // Tracks positions already chosen in each mini-board
    public static String[][] placeOption = new String[9][9]; // Holds player symbols or numbers for each spot
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) 
    {
        initializeBoard(); // Initialize game board with numbers
        mainMenu(); // Display main menu and start game flow
    }

    // Initialize game board with numbers (1 to 9)
    private static void initializeBoard() 
    {
    	for (int i = 0; i < 9; i++) 
    	{
            for (int j = 0; j < 9; j++) 
                placeOption[i][j] = String.valueOf(j + 1);  // Set each spot with its number
        }
    }

    // Display and handle main menu
    private static void mainMenu() 
    {
        displayTitle(); // Display game title art
        System.out.print("\n\t\t\t\t\t\tWelcome to Super Tic Tac Toe!!!\n\n\t\t\t\t\t      Choose one of the options below: \n\t\t\t\t\t \t\t - Tutorial\n\t\t\t\t\t\t\t - Play\n\n");

        String choice = getUserChoice(new String[]{"tutorial", "play"}); // Get user choice
        clearScreen(); // Clear screen for a clean slate
        
        // Decide whether to show tutorial or start the game
        if (choice.equals("tutorial")) 
            tutorial();
        else 
            playGame();
    }

    // Get a valid user input (checks if the input is one of the valid choices)
    private static String getUserChoice(String[] validChoices) 
    {
        String choice = scanner.nextLine().toLowerCase().trim();
        while (!isValidChoice(choice, validChoices)) 
        {
            System.out.print("\nInvalid choice. Please choose one of the options: ");
            choice = scanner.nextLine().toLowerCase().trim();
        }
        return choice;
    }

    // Check if user input is valid (matches one of the valid choices)
    private static boolean isValidChoice(String choice, String[] validChoices) 
    {
        for (String validChoice : validChoices) 
        {
            if (choice.equals(validChoice)) 
                return true;
        }
        return false;
    }

    // Display tutorial explaining how to play
    private static void tutorial() 
    {
        System.out.println("Welcome to Super Tic-Tac-Toe!\nHere's how to play:\n");
        
        // Tutorial content explaining game rules and mechanics
        System.out.println("1. Game Overview: ...");
        System.out.println("Press Enter to return to the main menu.");
        scanner.nextLine();
        clearScreen();
        mainMenu(); // Return to the main menu after tutorial
    }

    // Main game loop
    private static void playGame() 
    {
        displayBoard(); // Display the current board
        selectBoard("X"); // Player X selects which board to play on

        // Loop until game ends
        while (!gameEnd) 
        {
            selectPosition("X"); // Player X selects a position
            if (gameEnd) break;
            selectPosition("O"); // Player O selects a position
        }

        // Prompt to play again or quit
        String choicePlayAgain = getUserChoice(new String[]{"yes", "no"});
        
        if (choicePlayAgain.startsWith("y")) 
        {
            resetGame(); // Reset game if user wants to play again
            clearScreen();
            mainMenu(); // Return to main menu
        } 
        else 
        {
            displayStatistics(); // Show final scores
            System.out.println("\nHave a great day.");
            System.exit(0); // End the game
        }
    }

    // Let player select a board to play on
    private static void selectBoard(String playerSymbol) 
    {
        // Get a valid board choice from player
        boardChoice = getValidChoice("\nPlayer " + playerSymbol + ": Select a board to play on (1-9): ", 1, 9) - 1;
        
        // Ensure the board is not already won
        while (saveWinBoard[boardChoice] == 1) 
        {
            System.out.println("Board already won, choose another.");
            boardChoice = getValidChoice("\nPlayer " + playerSymbol + ": Select a board to play on (1-9): ", 1, 9) - 1;
        }
    }

    // Let player select a position within the selected board
    private static void selectPosition(String playerSymbol) 
    {
        // Get a valid position choice from player
        positionChoice = getValidChoice("\nPlayer " + playerSymbol + ": Select a position to play on (1-9) in Board " + (boardChoice + 1) + ": ", 1, 9) - 1;
        
        // Ensure the position is not already taken
        while (positionsChosen[boardChoice][positionChoice] == 1) 
        {
            clearScreen();
            displayBoard(); // Show current board
            System.out.println("\nPosition already taken, choose another.");
            positionChoice = getValidChoice("Player " + playerSymbol + ": Select a position to play on (1-9) in Board " + (boardChoice + 1) + ": ", 1, 9) - 1;
        }

        positionsChosen[boardChoice][positionChoice] = 1; // Mark position as chosen
        placeOption[boardChoice][positionChoice] = playerSymbol; // Place the player's symbol

        evaluateMove(playerSymbol); // Evaluate the move (win/tie/continue)

        // Switch to the next board for the opponent's turn
        if (!gameEnd) 
            boardChoice = positionChoice;  // Next board is determined by chosen position
    }

    // Get a valid choice (between min and max) from the player
    private static int getValidChoice(String prompt, int min, int max) 
    {
        int choice = 0;
        
        // Loop until the player enters a valid choice
        while (true) 
        {
            System.out.println(prompt);
            try 
            {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= min && choice <= max) break; // Valid choice
            } 
            catch (NumberFormatException e) 
            {
                continue; // Ignore invalid input and prompt again
            }
            System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
        }
        
        return choice; // Return valid choice
    }

    // Evaluate the result of a player's move (win, tie, or continue playing)
    private static void evaluateMove(String playerSymbol) 
    {
        clearScreen();
        displayBoard(); // Display updated board

        if (checkSingleBoardWin(playerSymbol)) // Check if the current board was won
        {
            if (checkGameWin(playerSymbol)) // Check if the game is won
            {
                for (int i = 0; i < 9; i++) placeOption[saveBoardWin][i] = playerSymbol;
                System.out.println("\nPlayer " + playerSymbol + " wins the game!!!");
                updateScore(playerSymbol); // Update scores
                gameEnd = true; // End the game
            } 
            else if (checkForTie(playerSymbol)) // Check if there's a tie
            {
                System.out.println("\nThe game is a tie!");
                tie++; // Increment tie counter
                gameEnd = true; // End the game
            } 
            else 
            {
                for (int i = 0; i < 9; i++) placeOption[saveBoardWin][i] = playerSymbol;
                System.out.println("\nPlayer " + playerSymbol + " has won Board " + (saveBoardWin + 1) + "!");
            }
        } 
        else if (saveWinBoard[boardChoice] == 1) 
        {
            System.out.println("The next board (Board " + (boardChoice + 1) + ") has already been won. Please select another board.");
            selectBoard(playerSymbol.equals("X") ? "O" : "X"); // If next board is won, prompt to select another board
        }
    }

    // Update scores based on the result (X or O wins)
    private static void updateScore(String playerSymbol) 
    {
        if (playerSymbol.equals("X")) playerXWin++; // Player X wins
        else playerOWin++; // Player O wins
    }

    // Check if a single mini board is won by the player
    private static boolean checkSingleBoardWin(String playerSymbol) 
    {
        for (int i = 0; i < 9; i++) 
        {
            if (saveWinBoard[i] == 0) // Only check boards that are not yet won
            {
                for (int j = 0; j < 9; j++) 
                {
                    if (checkWinningConditions(playerSymbol, i)) // Check if any winning condition is met
                    {
                        saveBoardWin = i; // Save the winning board
                        saveWinBoard[i] = 1; // Mark board as won
                        return true;
                    }
                }
            }
        }
        return false; // No winner found
    }

    // Check winning conditions on a board (horizontal, vertical, and diagonal)
    private static boolean checkWinningConditions(String playerSymbol, int board)
    {
        return (placeOption[board][0].equals(playerSymbol) && placeOption[board][1].equals(playerSymbol) && placeOption[board][2].equals(playerSymbol)) ||
               (placeOption[board][3].equals(playerSymbol) && placeOption[board][4].equals(playerSymbol) && placeOption[board][5].equals(playerSymbol)) ||
               (placeOption[board][6].equals(playerSymbol) && placeOption[board][7].equals(playerSymbol) && placeOption[board][8].equals(playerSymbol)) ||
               (placeOption[board][0].equals(playerSymbol) && placeOption[board][3].equals(playerSymbol) && placeOption[board][6].equals(playerSymbol)) ||
               (placeOption[board][1].equals(playerSymbol) && placeOption[board][4].equals(playerSymbol) && placeOption[board][7].equals(playerSymbol)) ||
               (placeOption[board][2].equals(playerSymbol) && placeOption[board][5].equals(playerSymbol) && placeOption[board][8].equals(playerSymbol)) ||
               (placeOption[board][0].equals(playerSymbol) && placeOption[board][4].equals(playerSymbol) && placeOption[board][8].equals(playerSymbol)) ||
               (placeOption[board][2].equals(playerSymbol) && placeOption[board][4].equals(playerSymbol) && placeOption[board][6].equals(playerSymbol));
    }

    // Check if the game has been won
    private static boolean checkGameWin(String playerSymbol) 
    {
        return (saveWinBoard[0] == 1 && saveWinBoard[1] == 1 && saveWinBoard[2] == 1) ||
               (saveWinBoard[3] == 1 && saveWinBoard[4] == 1 && saveWinBoard[5] == 1) ||
               (saveWinBoard[6] == 1 && saveWinBoard[7] == 1 && saveWinBoard[8] == 1);
    }

    // Check if there is a tie
    private static boolean checkForTie(String playerSymbol) 
    {
        for (int i = 0; i < 9; i++) 
            if (saveWinBoard[i] == 0) return false;
        
        return true;
    }

    // Reset the game to its initial state
    private static void resetGame() 
    {
        // Reset the board to its initial state
        initializeBoard();
        
        // Reset game status variables
        gameEnd = false;
        saveBoardWin = 0;
        
        for (int i = 0; i < 9; i++) 
        {
            saveWinBoard[i] = 0;
            for (int j = 0; j < 9; j++) 
                positionsChosen[i][j] = 0;
        }
        
        // Reset scores
        playerXWin = 0;
        playerOWin = 0;
        tie = 0;
    }

    
    // Display statistics
    private static void displayStatistics() 
    {
        System.out.println("Final Scores:");
        System.out.println("Player X: " + playerXWin + " wins");
        System.out.println("Player O: " + playerOWin + " wins");
        System.out.println("Ties: " + tie);
    }

    // Clear screen 
    private static void clearScreen() 
    {
        for (int i = 0; i < 50; i++) System.out.println();
    }
    
    public static void displayTitle() 
    {
    	System.out.println("\t\t\t\t  ::::::::   :::   :::  :::::::::   ::::::::::  ::::::::: \n\t\t\t\t :+:    :+:  :+:   :+:  :+:    :+:  :+:         :+:    :+: \n\t\t\t\t +:+         +:+   +:+  +:+    +:+  +:+         +:+    +:+ \n\t\t\t\t +#++:++#++  +#+   +#+  +#++:++#+   +#++:++#    +#++:++#: \n\t\t\t\t        +#+  +#+   +#+  +#          +#+         +#+    +#+ \n\t\t\t\t #+#    #+#  #+#   #+#  #+#         #+#         #+#    #+# \n\t\t\t\t  ########    ### ###   ###         ##########  ###    ### \n\n\t::::::::::: :::::::::::  ::::::::       :::::::::::      :::       ::::::::       :::::::::::  ::::::::   :::::::::: \n\t    :+:         :+:     :+:    :+:          :+:       :+:   :+:   :+:    :+:         :+:      :+:    :+:  :+:       \n\t    +:+         +:+     +:+                 +:+      +:+     +:+  +:+                +:+      +:+    +:+  +:+       \n\t    +#+         +#+     +#+                 +#+      +#++:++#++:  +#+                +#+      +#+    +:+  +#++:++#  \n\t    +#+         +#+     +#+                 +#+      +#+     +#+  +#+                +#+      +#+    +#+  +#+       \n\t    #+#         #+#     #+#    #+#          #+#      #+#     #+#  #+#    #+#         #+#      #+#    #+#  #+#       \n\t    ###     ###########  ########           ###      ###     ###   ########          ###       ########   ########## \n\n        ___________________________________________________________________________________________________________");
    }
    
    public static void displayBoard()
    {
    	System.out.println("                               *                                 *                                ");
    	System.out.println("         *         *           *           *         *           *           *         *          ");
    	System.out.println("    " + placeOption[0][0] + "    *    " + placeOption[0][1] + "    *    " + placeOption[0][2] + "      *      " + placeOption[1][0] + "    *    " + placeOption[1][1] + "    *    " + placeOption[1][2] + "      *      " + placeOption[2][0] + "    *    " + placeOption[2][1] + "    *    " + placeOption[2][2] + "     ");
    	System.out.println("         *         *           *           *         *           *           *         *          ");
    	System.out.println(" ***************************   *   ***************************   *   ***************************  ");
    	System.out.println("         *         *           *           *         *           *           *         *          ");
    	System.out.println("    " + placeOption[0][3] + "    *    " + placeOption[0][4] + "    *    " + placeOption[0][5] + "      *      " + placeOption[1][3] + "    *    " + placeOption[1][4] + "    *    " + placeOption[1][5] + "      *      " + placeOption[2][3] + "    *    " + placeOption[2][4] + "    *    " + placeOption[2][5] + "     ");
    	System.out.println("         *         *           *           *         *           *           *         *          ");
    	System.out.println(" ***************************   *   ***************************   *   ***************************  ");
    	System.out.println("         *         *           *           *         *           *           *         *          ");
    	System.out.println("    " + placeOption[0][6] + "    *    " + placeOption[0][7] + "    *    " + placeOption[0][8] + "      *      " + placeOption[1][6] + "    *    " + placeOption[1][7] + "    *    " + placeOption[1][8] + "      *      " + placeOption[2][6] + "    *    " + placeOption[2][7] + "    *    " + placeOption[2][8] + "     ");
    	System.out.println("         *         *           *           *         *           *           *         *          ");
    	System.out.println("                               *                                 *                                ");
    	System.out.println("**************************************************************************************************");
    	System.out.println("                               *                                 *                                ");
    	System.out.println("         *         *           *           *         *           *           *         *          ");
    	System.out.println("    " + placeOption[3][0] + "    *    " + placeOption[3][1] + "    *    " + placeOption[3][2] + "      *      " + placeOption[4][0] + "    *    " + placeOption[4][1] + "    *    " + placeOption[4][2] + "      *      " + placeOption[5][0] + "    *    " + placeOption[5][1] + "    *    " + placeOption[5][2] + "     ");
    	System.out.println("         *         *           *           *         *           *           *         *          ");
    	System.out.println(" ***************************   *   ***************************   *   ***************************  ");
    	System.out.println("         *         *           *           *         *           *           *         *          ");
    	System.out.println("    " + placeOption[3][3] + "    *    " + placeOption[3][4] + "    *    " + placeOption[3][5] + "      *      " + placeOption[4][3] + "    *    " + placeOption[4][4] + "    *    " + placeOption[4][5] + "      *      " + placeOption[5][3] + "    *    " + placeOption[5][4] + "    *    " + placeOption[5][5] + "     ");
    	System.out.println("         *         *           *           *         *           *           *         *          ");
    	System.out.println(" ***************************   *   ***************************   *   ***************************  ");
    	System.out.println("         *         *           *           *         *           *           *         *          ");
    	System.out.println("    " + placeOption[3][6] + "    *    " + placeOption[3][7] + "    *    " + placeOption[3][8] + "      *      " + placeOption[4][6] + "    *    " + placeOption[4][7] + "    *    " + placeOption[4][8] + "      *      " + placeOption[5][6] + "    *    " + placeOption[5][7] + "    *    " + placeOption[5][8] + "     ");
    	System.out.println("         *         *           *           *         *           *           *         *          ");
    	System.out.println("                               *                                 *                                ");
    	System.out.println("**************************************************************************************************");
    	System.out.println("                               *                                 *                                ");
    	System.out.println("         *         *           *           *         *           *           *         *          ");
    	System.out.println("    " + placeOption[6][0] + "    *    " + placeOption[6][1] + "    *    " + placeOption[6][2] + "      *      " + placeOption[7][0] + "    *    " + placeOption[7][1] + "    *    " + placeOption[7][2] + "      *      " + placeOption[8][0] + "    *    " + placeOption[8][1] + "    *    " + placeOption[8][2] + "     ");
    	System.out.println("         *         *           *           *         *           *           *         *          ");
    	System.out.println(" ***************************   *   ***************************   *   ***************************  ");
    	System.out.println("         *         *           *           *         *           *           *         *          ");
    	System.out.println("    " + placeOption[6][3] + "    *    " + placeOption[6][4] + "    *    " + placeOption[6][5] + "      *      " + placeOption[7][3] + "    *    " + placeOption[7][4] + "    *    " + placeOption[7][5] + "      *      " + placeOption[8][3] + "    *    " + placeOption[8][4] + "    *    " + placeOption[8][5] + "     ");
    	System.out.println("         *         *           *           *         *           *           *         *          ");
    	System.out.println(" ***************************   *   ***************************   *   ***************************  ");
    	System.out.println("         *         *           *           *         *           *           *         *          ");
    	System.out.println("    " + placeOption[6][6] + "    *    " + placeOption[6][7] + "    *    " + placeOption[6][8] + "      *      " + placeOption[7][6] + "    *    " + placeOption[7][7] + "    *    " + placeOption[7][8] + "      *      " + placeOption[8][6] + "    *    " + placeOption[8][7] + "    *    " + placeOption[8][8] + "     ");
    	System.out.println("         *         *           *           *         *           *           *         *          ");
    }
}
