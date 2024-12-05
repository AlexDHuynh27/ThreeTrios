package cs3500.threetrios.provider.model;

/**
 * Represents a move in ThreeTrios.
 */
public class Move {
  int row;
  int col;
  int cardIdx;
  Player player;

  /**
   * Constructor.
   * @param row 0-indexed row to play to
   * @param col 0-indexed row to play to
   * @param cardIdx 0-indexed index in hand
   * @param player the player who is to play this move
   */
  public Move(int row, int col, int cardIdx, Player player) {
    this.row = row;
    this.col = col;
    this.cardIdx = cardIdx;
    this.player = player;
  }

  /**
   * Return 0-indexed row to play to.
   * @return 0-indexed row to play to.
   */
  public int getRow() {
    return row;
  }

  /**
   * Return 0-indexed column to play to.
   * @return 0-indexed column to play to.
   */
  public int getCol() {
    return col;
  }

  /**
   * Return 0-indexed card in hand.
   * @return 0-indexed card in hand.
   */
  public int getCardIdx() {
    return cardIdx;
  }

  /**
   * Return the player who would play this move.
   * @return the player who would play this move.
   */
  public Player getPlayer() {
    return player;
  }
}
