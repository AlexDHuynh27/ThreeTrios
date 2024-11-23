package cs3500.threetrios.model.player.strategy;

/**
 * Represents the necessary indexes to play a move in a game of ThreeTrios.
 * The card in the hand idx, the row of the grid idx, and the col of the grid idx.
 */
public class HandGridCoord {
  int handIdx; // idx-0
  int row; // idx-0
  int col; // idx-0

  /**
   * HandGridCoord constructor for HandGridCoord class, throws IAE if arguments are negative.
   */
  public HandGridCoord(int handIdx, int row, int col) {
    if (handIdx < 0 || row < 0  || col < 0) {
      throw new IllegalArgumentException("Arguments cannot be negative!");
    }
    this.handIdx = handIdx;
    this.row = row;
    this.col = col;
  }

  public int getHandIdx() {
    return handIdx;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }
}
