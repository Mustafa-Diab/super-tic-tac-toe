# Super Tic-Tac-Toe  

Version 1.0

## 📖 Overview  

Super Tic-Tac-Toe is an exciting twist on the classic Tic-Tac-Toe game, adding a strategic layer by incorporating a 3x3 grid of mini boards.  
- Each square on the main board contains another Tic-Tac-Toe board, resulting in 9 mini boards.  
- The objective is to win three mini boards in a row (horizontally, vertically, or diagonally) on the main grid.  

---

## 🛠️ Features  

- **Interactive Gameplay**: Players take turns navigating mini boards and the main board strategically.  
- **Dynamic Rules**: Turns direct players to specific mini boards, creating a unique challenge.  
- **Victory Conditions**: Win by dominating the main grid with three mini boards in a row.  
- **Tie Scenarios**: Handle tied boards seamlessly and track the overall game state.  

---

## 🎮 How to Play  

### Game Overview:  
- Super Tic-Tac-Toe is played on a 3x3 grid, where each square contains another 3x3 mini board.  
- The goal is to win three mini boards in a row on the main grid (horizontally, vertically, or diagonally).  

### Starting the Game: 
- Player X starts by choosing any position on any of the 9 mini boards.  
- Example: If Player X picks the top-right corner of the top-right mini board, Player O must play in that mini board.  

### Turns and Board Navigation: 
- After Player X's move, Player O plays in the mini board corresponding to Player X’s last move.  
- Example: If Player X selects position 7 in a mini board, Player O must play in the mini board located at position 7 of the main grid.  
- If the required mini board has already been won or tied, the player can choose any available position.  

### Winning a Mini Board:
- A mini board is won by achieving three symbols in a row (horizontally, vertically, or diagonally).  
- Once a mini board is won, it is marked and cannot be played again.  

### Winning the Game:
- A player wins the game by winning three mini boards in a row on the main 3x3 grid.  
- The winning row can be horizontal, vertical, or diagonal.  

### Ties: 
- If a mini board ends in a tie, it remains unclaimed.  
- If all mini boards are tied or claimed and no player has won three in a row, the game ends in a tie.  

---

## 🚀 Getting Started  

### Prerequisites  
- Java Development Kit (JDK) 11 or higher.  
- A Java IDE (e.g., IntelliJ IDEA, Eclipse, or VS Code).  

### Installation  

1. Clone the repository:  
   ```bash  
   git clone https://github.com/Mustafa-Diab/super-tic-tac-toe.git
