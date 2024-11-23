package cs3500.threetrios.model.player;

import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.player.strategy.HandGridCoord;
import cs3500.threetrios.model.player.strategy.ThreeTriosStrategy;
import java.util.List;

public class MachinePlayer implements Player {
  private ThreeTriosStrategy strategy;
  private HumanPlayer humanPlayer;
  private ThreeTriosModel model;

  MachinePlayer(ThreeTriosStrategy strategy, HumanPlayer player, ThreeTriosModel model) {
    super();
    this.strategy = strategy;
    this.humanPlayer = player;
    this.model = model;
  }

  public void playAIMove() {
    HandGridCoord move = this.strategy.chooseMove(this.model, this.getColor());
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
