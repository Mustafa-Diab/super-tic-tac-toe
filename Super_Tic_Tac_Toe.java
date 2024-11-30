import java.util.Scanner;

public class Super_Tic_Tac_Toe 
{
	// Track the number of wins for Player X, Player O, and the number of ties
    public static int playerXWin = 0;
    public static int playerOWin = 0;
    public static int tie = 0;
	
	// 2D array representing the available positions on each board in the 3x3 grid
    public static String[][] placeOption = {
        {"1", "2", "3", "4", "5", "6", "7", "8", "9"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9"},
        {"1", "2", "3", "4", "5", "6", "7", "8", "9"}
    };
    
    // Variable to store current board won by the player
    public static int saveBoardWin = 0;
    
    // Array to save which board has been won
    public static int[] saveWinBoard = new int[placeOption.length];
    
    // Integer to store the board user chooses
    public static int boardChoice = 0;    
    
    // Integer to store the position user chooses
    public static int positionChoice = 0;
    
    // Boolean to determine if the game has ended
    public static boolean gameEnd = false;
	
    // A 2D Array to store all chosen positions to ensure there are no duplicates
    public static int[][] positionsChosen = new int[9][9];
                
    // Scanner object for user input throughout the game
    public static Scanner scanner = new Scanner(System.in);
    
	public static void main(String[] args) 
	{
		MAIN_MENU();
	}
	
	// Display the main menu
    public static void MAIN_MENU() 
    {
        // Display the game's title
    	DISPLAY_TITLE();
        
        // Prompt the user to choose between tutorial and play
        System.out.print("\n\t\t\t\t\t\tWelcome to Super Tic Tac Toe!!!\n\n\t\t\t\t\t      Choose one of the options below: \n\t\t\t\t\t \t\t - Tutorial\n\t\t\t\t\t\t\t - Play\n\n");
    
        // Read the user's choice
        String choice = scanner.nextLine().toLowerCase().trim();
    
        // Validate user input
        while (!(choice.equals("tutorial") || choice.equals("play")))
        {
            System.out.print("\nInvalid choice. Please choose one of the options. ");
            choice = scanner.nextLine().toLowerCase().trim();
        }
    
        // Call the appropriate method based on user's choice
        if (choice.equals("tutorial")) 
        {
        	CLEAR_SCREEN();
        	TUTORIAL();
        } 
        if (choice.equals("play"))
        {
        	CLEAR_SCREEN();
            PLAY_GAME();
        }
    }

    // Display the game tutorial
    public static void TUTORIAL() 
    {
        System.out.println("Welcome to Super Tic-Tac-Toe!");
        System.out.println("Here's how to play:\n");
        
        System.out.println("1. **Game Overview**");
        System.out.println("   - Super Tic-Tac-Toe is a variant of Tic-Tac-Toe played on a 3x3 grid.");
        System.out.println("   - Each square in this grid contains another Tic-Tac-Toe board, making a total of 9 mini boards.");
        System.out.println("   - The objective is to win three mini boards in a row (horizontally, vertically, or diagonally).\n");
                
        System.out.println("2. **Starting the Game**");
        System.out.println("   - Player X starts by choosing any position on any of the 9 mini boards.");
        System.out.println("   - For example, if Player X chooses the top-right corner of the top-right mini board, Player O must play in that mini board.\n");
                
        System.out.println("3. **Turns and Board Navigation**");
        System.out.println("   - After Player X makes a move, Player O must play in the mini board that corresponds to the chosen position.");
        System.out.println("   - For instance, if Player X chooses position 7 in the top-right mini board, Player O must play in the mini board that is in the position 7 of the overall grid.");
        System.out.println("   - If a player is forced to play in a mini board that has already been won or tied, they can choose any available position.\n");
                
        System.out.println("4. **Winning a Mini Board**");
        System.out.println("   - A mini board is won when a player gets three of their symbols in a row (horizontally, vertically, or diagonally) on that board.");
        System.out.println("   - Once a mini board is won, it is marked as won and cannot be played again by either player.\n");
                
        System.out.println("5. **Winning the Game**");
        System.out.println("   - The game is won when a player wins three mini boards in a row on the main 3x3 grid.");
        System.out.println("   - This can be accomplished in any direction: horizontally, vertically, or diagonally.\n");
                
        System.out.println("6. **Ties**");
        System.out.println("   - If a mini board ends in a tie (no player wins), it remains unclaimed.");
        System.out.println("   - If all mini boards are filled and no player has won three in a row on the main grid, the game is a tie.\n");
                
        System.out.println("To play the game, you'll need to:");
        System.out.println("   - Choose a mini board to play on (1-9).");
        System.out.println("   - Choose a position within the selected mini board (1-9).");
        System.out.println("   - Continue until a player wins or the game ends in a tie.\n");
                
        System.out.println("Press Enter to return to the main menu.");
        scanner.nextLine();
        CLEAR_SCREEN();
        MAIN_MENU();
    }
	
	public static void PLAY_GAME()
	{
		DISPLAY_BOARD();
		
		WAITING_FOR_BOARD_SELECTION("X"); // Player X starts the game
		
		// Game loop continues until the game ends
        while (!gameEnd) 
        {
        	WAITING_FOR_POSITION_SELECTION("X"); // Player X's turn
            
        	if (gameEnd) break; // Exit loop if game ends

            WAITING_FOR_POSITION_SELECTION("O"); // Player O's turn
        }
        
        // Ask the user if they want to play again
        System.out.print("\nWould you like to play again? (yes/no) ");
        
        String choicePlayAgain = scanner.nextLine().toLowerCase().trim();

        // Validate the user's input
        while (!(choicePlayAgain.startsWith("y") || choicePlayAgain.startsWith("n"))) 
        {
            System.out.print("\nInvalid input. Please enter yes or no. ");
            choicePlayAgain = scanner.nextLine().toLowerCase().trim();
        }

        // Handle the user's decision
        if (choicePlayAgain.startsWith("y")) 
        {
            RESET_GAME(); // Reset all game variables for a new game
            CLEAR_SCREEN(); // Clear the console
            MAIN_MENU(); // Restart the game
        } 
        else 
        {
            DISPLAY_STATISTICS(); // Show final game statistics
            System.out.println("\nHave a great day."); // Exit message
            System.exit(0); // Exit the program
        }
	}
	
	public static void WAITING_FOR_BOARD_SELECTION(String playerSymbol)
	{   
		while (true)
		{
			System.out.println("\nPlayer " + playerSymbol + ": Select a board to play on (1-9): ");
			
			try 
	        {
				boardChoice = Integer.parseInt(scanner.nextLine().trim()) - 1;
	        } 
	        catch (NumberFormatException e) 
	        {
	            System.out.println("\nInvalid input. Please enter a number between 1 and 9.");
	            continue;
	        }
			
			if (boardChoice < 0 || boardChoice > 8)
            {
            	System.out.println("\nInvalid input. Please enter a number between 1 and 9.");
            	continue;
            }
			
			// Ensure the chosen board hasn't been won already
			if (saveWinBoard[boardChoice] == 1)
			{
				System.out.println("\nBoard " + (boardChoice + 1) + " has already been won. Please choose another board.");
				
				WAITING_FOR_BOARD_SELECTION(playerSymbol);
			}
			
			break;
		}				
	}
	
	public static void WAITING_FOR_POSITION_SELECTION(String playerSymbol)
	{
		while (true)
		{
			System.out.println("\nPlayer " + playerSymbol + ": Select a position to play on (1-9) in Board " + (boardChoice + 1) + ": ");
			
			try 
	        {
				positionChoice = Integer.parseInt(scanner.nextLine().trim()) - 1;
	        } 
	        catch (NumberFormatException e) 
	        {
	        	CLEAR_SCREEN();
	        	DISPLAY_BOARD();
	            System.out.println("Invalid input. Please enter a number between 1 and 9.");
	            continue;
	        }
			
			if (positionChoice < 0 || positionChoice > 8)
            {
				CLEAR_SCREEN();
				DISPLAY_BOARD();
            	System.out.println("Invalid input. Please enter a number between 1 and 9.");
            	continue;
            }
			
			// Ensure the position hasn't been chosen already
			if (positionsChosen[boardChoice][positionChoice] == 1)
			{
				CLEAR_SCREEN();
				DISPLAY_BOARD();
				System.out.println("This position has already been chosen. Please choose another one.");
				continue;
			}
			
			positionsChosen[boardChoice][positionChoice] = 1;
			
			placeOption[boardChoice][positionChoice] = playerSymbol;
			
			// The board for the next player to play on is the position just chosen
			boardChoice = positionChoice;
			
	        break; // Exit the loop if the board hasn't been won
		}
		
		if (CHECK_FOR_SINGULAR_BOARD_WIN(playerSymbol))
		{
			if (CHECK_FOR_GAME_WIN(playerSymbol))
			{
				for (int i = 0; i < 9; i++) 
	                placeOption[saveBoardWin][i] = playerSymbol;
				
				CLEAR_SCREEN();
				DISPLAY_BOARD();
				
				System.out.println("\nPlayer " + playerSymbol + " wins the game!!!");
                
				if (playerSymbol.equals("X")) 
                    playerXWin++;
                else 
                    playerOWin++;
                
				gameEnd = true;
			}
			
			else if (CHECK_FOR_GAME_TIE(playerSymbol))
			{
				CLEAR_SCREEN();
				DISPLAY_BOARD();
				
				System.out.println("\nThe game is a tie!");
                tie++;
                gameEnd = true;
			}
			
			else
			{
				for (int i = 0; i < 9; i++) 
	                placeOption[saveBoardWin][i] = playerSymbol;
				
				CLEAR_SCREEN();
				DISPLAY_BOARD();
				
				System.out.println("\nPlayer " + playerSymbol + " has won Board " + (saveBoardWin + 1) + "!");
				
				return;
			}
		}
		else if (saveWinBoard[boardChoice] == 1) // Check if the new board has already been won 
        {
			CLEAR_SCREEN();
			DISPLAY_BOARD();
            System.out.println("The next board (Board " + (boardChoice + 1) + ") has already been won. Please select another board.");
            
            // Redirect the player to choose a new board that hasn't been won yet
            if (playerSymbol.equals("X"))
                WAITING_FOR_BOARD_SELECTION("O");
            else
                WAITING_FOR_BOARD_SELECTION("X");
        }
		else
		{
			CLEAR_SCREEN();
			DISPLAY_BOARD();
		}
	}
	
	// Check if a player has won a singular board 
	public static boolean CHECK_FOR_SINGULAR_BOARD_WIN(String playerSymbol)
	{
		for (int i = 0; i < placeOption.length; i++)
		{
			if (saveWinBoard[i] == 0) // Only check boards that haven't been won 
			{
				for (int j = 0; j < placeOption[i].length; j++)
				{
					if ((placeOption[i][0].equals(playerSymbol) && placeOption[i][1].equals(playerSymbol) && placeOption[i][2].equals(playerSymbol)) || 
					    (placeOption[i][3].equals(playerSymbol) && placeOption[i][4].equals(playerSymbol) && placeOption[i][5].equals(playerSymbol)) || 
					    (placeOption[i][6].equals(playerSymbol) && placeOption[i][7].equals(playerSymbol) && placeOption[i][8].equals(playerSymbol)) || 
					    (placeOption[i][0].equals(playerSymbol) && placeOption[i][3].equals(playerSymbol) && placeOption[i][6].equals(playerSymbol)) || 
					    (placeOption[i][1].equals(playerSymbol) && placeOption[i][4].equals(playerSymbol) && placeOption[i][7].equals(playerSymbol)) || 
					    (placeOption[i][2].equals(playerSymbol) && placeOption[i][5].equals(playerSymbol) && placeOption[i][8].equals(playerSymbol)) || 
					    (placeOption[i][0].equals(playerSymbol) && placeOption[i][4].equals(playerSymbol) && placeOption[i][8].equals(playerSymbol)) || 
					    (placeOption[i][2].equals(playerSymbol) && placeOption[i][4].equals(playerSymbol) && placeOption[i][6].equals(playerSymbol)))
					{
						saveBoardWin = i; // Save the index of the won board
						saveWinBoard[i] = 1; // Mark this board as won
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	// Check if a player has won the entire game 
	public static boolean CHECK_FOR_GAME_WIN(String playerSymbol) 
	{
	    // Check rows for a win
	    if ((saveWinBoard[0] == 1 && saveWinBoard[1] == 1 && saveWinBoard[2] == 1) ||
	        (saveWinBoard[3] == 1 && saveWinBoard[4] == 1 && saveWinBoard[5] == 1) ||
	        (saveWinBoard[6] == 1 && saveWinBoard[7] == 1 && saveWinBoard[8] == 1)) 
	        return true;

	    // Check columns for a win
	    if ((saveWinBoard[0] == 1 && saveWinBoard[3] == 1 && saveWinBoard[6] == 1) ||
	        (saveWinBoard[1] == 1 && saveWinBoard[4] == 1 && saveWinBoard[7] == 1) ||
	        (saveWinBoard[2] == 1 && saveWinBoard[5] == 1 && saveWinBoard[8] == 1)) 
	        return true;

	    // Check diagonals for a win
	    if ((saveWinBoard[0] == 1 && saveWinBoard[4] == 1 && saveWinBoard[8] == 1) ||
	        (saveWinBoard[2] == 1 && saveWinBoard[4] == 1 && saveWinBoard[6] == 1)) 
	        return true;

	    return false;
	}

	// Check if game ends with a tie
	public static boolean CHECK_FOR_GAME_TIE(String playerSymbol)
	{
		// If all positions on all boards are filled, it's a tie 
        for (int i = 0; i < 9; i++) 
        {
            for (int j = 0; j < 9; j++) 
            {
                if (!(placeOption[i][j].equals("X") || placeOption[i][j].equals("O"))) 
                    return false;
            }
        }
        
        return true;
	}
    
	// Resets all variables to play again
	public static void RESET_GAME() 
    {
        // Reset the placeOption array to its initial state
        for (int i = 0; i < placeOption.length; i++) 
        {
            for (int j = 0; j < placeOption[i].length; j++) 
                placeOption[i][j] = Integer.toString(i * 9 + j + 1); // Reset to initial numbers (1-9)
        }
        
        // Reset the saveWinBoard array
        for (int i = 0; i < saveWinBoard.length; i++) 
            saveWinBoard[i] = 0; // 0 indicates that the board has not been won
        
        // Reset the positionsChosen array
        for (int i = 0; i < positionsChosen.length; i++) 
        {
            for (int j = 0; j < positionsChosen[i].length; j++) 
                positionsChosen[i][j] = 0; // 0 indicates that the position has not been chosen
        }

        // Reset the game state variables
        saveBoardWin = 0;
        boardChoice = 0;
        positionChoice = 0;
        gameEnd = false;
    }
	
	// Clear the screen
    public static void CLEAR_SCREEN()
    {
        for (int i = 0; i < 50; i++)
        	System.out.println();
    }
	
    // Display final statistics after the game ends
    public static void DISPLAY_STATISTICS() 
    {
        System.out.println("\nGame Statistics:");
        System.out.println("Player X wins: " + playerXWin);
        System.out.println("Player O wins: " + playerOWin);
        System.out.println("Ties: " + tie);
    }
    
    // Display the game's title
    public static void DISPLAY_TITLE() 
    {
    	System.out.println("\t\t\t\t  ::::::::   :::   :::  :::::::::   ::::::::::  ::::::::: ");
    	System.out.println("\t\t\t\t :+:    :+:  :+:   :+:  :+:    :+:  :+:         :+:    :+:");
    	System.out.println("\t\t\t\t +:+         +:+   +:+  +:+    +:+  +:+         +:+    +:+");
    	System.out.println("\t\t\t\t +#++:++#++  +#+   +#+  +#++:++#+   +#++:++#    +#++:++#: ");
    	System.out.println("\t\t\t\t        +#+  +#+   +#+  +#          +#+         +#+    +#+");
    	System.out.println("\t\t\t\t #+#    #+#  #+#   #+#  #+#         #+#         #+#    #+#");
    	System.out.println("\t\t\t\t  ########    ### ###   ###         ##########  ###    ###");
        System.out.println();
    	System.out.println("\t::::::::::: :::::::::::  ::::::::       :::::::::::      :::       ::::::::       :::::::::::  ::::::::   ::::::::::");
        System.out.println("\t    :+:         :+:     :+:    :+:          :+:       :+:   :+:   :+:    :+:         :+:      :+:    :+:  :+:       ");
        System.out.println("\t    +:+         +:+     +:+                 +:+      +:+     +:+  +:+                +:+      +:+    +:+  +:+       ");
        System.out.println("\t    +#+         +#+     +#+                 +#+      +#++:++#++:  +#+                +#+      +#+    +:+  +#++:++#  ");
        System.out.println("\t    +#+         +#+     +#+                 +#+      +#+     +#+  +#+                +#+      +#+    +#+  +#+       ");
        System.out.println("\t    #+#         #+#     #+#    #+#          #+#      #+#     #+#  #+#    #+#         #+#      #+#    #+#  #+#       ");
        System.out.println("\t    ###     ###########  ########           ###      ###     ###   ########          ###       ########   ##########");
        
        System.out.println("        ___________________________________________________________________________________________________________");
    }
    
    // Display the 3x3 grid of 3x3 boards
    public static void DISPLAY_BOARD()
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