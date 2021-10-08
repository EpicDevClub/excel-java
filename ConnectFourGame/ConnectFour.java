/* The game that keeps track of all the information, such as
 * the grid and which player's turn */
public class ConnectFour {
   public final static int GRID_HEIGHT = 6;     // The default height of the grid (DO NOT CHANGE)
   public final static int GRID_WIDTH  = 7;     // The default width of the grid (DO NOT CHANGE)
   
   private Disc[][] grid;                       // The grid of the game. A 2D array of Disc objects
   private Player[] allPlayers;                 // An array that stores all the player's object
   private Player currPlayer;                   // Keeps track of the currently playing player
   
   //======================= CONSTRUCTOR =======================//
   public ConnectFour() {

   }
   
   //====================== PRIVATE METHOD =======================//
  
   
   //====================== PUBLIC METHOD =======================//
   /* It returns the 2D grid to the caller
    * @return - The 2D grid containing Disc objects */
   public Disc[][] GetGrid() {
      return null;  // Dummy return value
   }
   
   /* Resets the grid so it contains nothing */
   public void ResetGrid() {
      
   }
   
   /* Check if the grid is full
    * @return - Returns true, if the grid is full.
    *           Returns false, if the grid is not full. */    
   public boolean IsGridFull() {
      return true;  // Dummy return value
   }   
   
   /* Inserts a disc in the specified column and will determine
    * if it successfully inserted or not
    * @param col - The column that the player wants to insert the disc
    * @return    - Return true, if the disc is inserted successfully.
    *              Return false, if the column is full */    
   public boolean InsertDisc(int col) {
      return true;  // Dummy return value
   }   
   
   /* Get the number of empty blocks in the grid
    * @return - The number of empty blocks */
   public int NumEmptyBlock() {
      return 0;  // Dummy return value
   }   
   
   /* Gets all the players 
    * @return - The array that contains all the players' object */
   public Player[] GetAllPlayers() {
      return null;  // Dummy return value
   }
   
   /* Returns the current player, represented by a number value
    * @return - The value that represents the player.
    *           Returns 0 if current player is X
    *           Returns 1 if current player is O */
   public Player GetCurrPlayer() {
      return null;  // Dummy return value
   }
   
   /* Set who will be the current playing 
    * @param playerVal - The value that represents the player */
   public void SetCurrPlayer(int playerVal) {
      
   }
 
   /* Get the current player's name (either X or O)
    * @return - The player's name, either "X" or "O" */   
   public String GetCurrPlayerStr() {
      return null;  // Dummy return value
   }
    
   /* Switch player turn to the next player (If current player is X, then it 
    * switches to O. If current player is O, then it switches to X) */   
   public void SwitchPlayer() {
      
   }
   
   /* Check is there is a winner 
    * @return - Returns true, if there is a winner.
    *           Returns false, if there is no winner */      
   public boolean HasRoundWinner() {
      return true;  // Dummy return value
   }
   
   /* Determine the winner by checking the player's score. 
    * @return - The value that represents the player who wins.
    *           If it's a tie, returns -1 */
   public int GameWinner() {
      return 0;  // Dummy return value
   }
}