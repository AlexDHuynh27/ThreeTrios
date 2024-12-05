package cs3500.threetrios.provider.controllerandfeatures;

import cs3500.threetrios.provider.model.Player;

/**
 * Represents player actions that the view emits to the controller.
 */
public interface PlayerActions {
  /**
   * Called when a player selects a card from their hand.
   * @param cardIdx the index of the selected card.
   */
  void onCardSelected(int cardIdx, Player windowOwner, Player cardOwner);

  /**
   * Called when a player attempts to play a card on the board.
   * @param row the row where the card is played
   * @param col the column where the card is played
   */
  void onCellSelected(int row, int col);
}
