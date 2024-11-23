package cs3500.threetrios.model.player.strategy;

public class HandGridCoord {
  int handIdx;
  int row;
  int col;

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
