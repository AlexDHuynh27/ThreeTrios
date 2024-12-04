package cs3500.threetrios.model.player.strategy;

/**
 * Represents the required index's for a move in the game ThreesTrios.
 * The card in hand idx and the row idx column idx for the grid.
 */
public class HandGridCoord {
  private int handIdx; // Idx-0
  private int row; // Idx-0
  private int col; // Idx-0

  /**
   * HandGridCoord constructor for HandGridCoord class, throws IAE if arguments are negative.
   */
  public HandGridCoord(int handIdx, int row, int col) {
    this.handIdx = handIdx;
    this.row = row;
    this.col = col;
  }

  /**
   * Gets the handIdx of this HandGridCoord.
   */
  public int getHandIdx() {
    return handIdx;
  }

  /**
   * Gets the rowIdx of this HandGridCoord.
   */
  public int getRow() {
    return row;
  }

  /**
   * Gets the colIdx of this HandGridCoord.
   */
  public int getCol() {
    return col;
  }
}
