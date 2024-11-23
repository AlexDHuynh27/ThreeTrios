package cs3500.threetrios.model.player;

import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.player.strategy.HandGridCoord;
import cs3500.threetrios.model.player.strategy.ThreeTriosStrategy;
import java.util.List;

/**
 * Represents an AI or Machine (Non-Human) Player in the ThreeTriosGame.
 */
public class MachinePlayer implements Player {
  private final ThreeTriosStrategy strategy;
  private final Player humanPlayer;
  private final ThreeTriosModel model;
  
  /**
   * Constructor for MachinePlayer class that creates a MachinePlayer based on the given
   * parameters.
   *
   * @param strategy the strategy being used by the MachinePlayer
   * @param player the current player whose turn it is being represented by the MachinePlayer
   * @param model the current game model state
   */
  public MachinePlayer(ThreeTriosStrategy strategy, Player player, ThreeTriosModel model) {
    this.strategy = strategy;
    this.humanPlayer = player;
    this.model = model;
  }

  /**
   * Executes the AI bot's move using the assigned strategy. Uses the strategy's chooseMove
   * method to place a card on the grid with the HandGridCoord indexes.
   */
  public void playAIMove() {
    HandGridCoord move = this.strategy.chooseMove(this.model, this.humanPlayer);
    this.model.playToGrid(move.getHandIdx(), move.getRow(), move.getCol());
  }


  @Override
  public void addToHand(Card card) {
    this.humanPlayer.addToHand(card);
  }

  @Override
  public Card playFromHand(int idx) {
    return this.humanPlayer.playFromHand(idx);
  }

  @Override
  public List<Card> getHand() {
    return this.humanPlayer.getHand();
  }

  @Override
  public int getCurrentHandSize() {
    return this.humanPlayer.getCurrentHandSize();
  }

  @Override
  public CardColor getColor() {
    return this.humanPlayer.getColor();
  }

  @Override
  public void setColor(CardColor color) {
    this.humanPlayer.setColor(color);
  }
}
