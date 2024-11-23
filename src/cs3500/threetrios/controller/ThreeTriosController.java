package cs3500.threetrios.controller;

import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.player.HumanPlayer;
import cs3500.threetrios.model.player.MachinePlayer;
import cs3500.threetrios.model.player.Player;
import cs3500.threetrios.view.ThreeTriosFeatures;
import cs3500.threetrios.view.ThreeTriosGraphicsView;
import java.awt.Color;

public class ThreeTriosController implements ThreeTriosFeatures {
  ThreeTriosModel model;
  Player player;
  ThreeTriosGraphicsView view;
  int selectedCard;
  CardColor currentTurn;

  public ThreeTriosController(ThreeTriosModel model, Player player, ThreeTriosGraphicsView view) {
    this.model = model;
    this.player = player;
    this.view = view;
    this.selectedCard = -1;
    this.model.addListener(this);
    this.view.addFeaturesListener(this);
  }

  public void goPlay() {
    this.view.makeVisible();
    currentTurn = model.getCurrentPlayerColor();
    checkTurn();
  }


  @Override
  public void setSelected(Color color, int selected) {
    CardColor selectedColor = colorToCardColor(color);
    if (this.model.getCurrentPlayerColor().equals(selectedColor)
        && this.player.getColor().equals(selectedColor)) {
      if (this.selectedCard == selected) {
        this.selectedCard = -1;
      }
      else {
        view.setSelected(selectedColor, selected);
        this.selectedCard = selected;
      }
      view.repaint();
    }
  }

  @Override
  public void playSelectedToGrid(int row, int col) {
    if (!this.model.getCurrentPlayerColor().equals(this.player.getColor())) {
      view.showMessage("Player " + this.player.getColor() + ": It's not your turn.");
    }
    else if (this.selectedCard == -1) {
      view.showMessage("Player " + this.player.getColor() + ": Please select a card first.");
    }
    else {
      try {
        model.playToGrid(this.selectedCard, row, col);
        view.setSelected(this.model.getCurrentPlayerColor(), -1);
        model.battle();
        this.selectedCard = -1;
      } catch (IllegalStateException e) {
        view.showMessage("Player " + this.player.getColor() + "You can't play a card there");
      }
    }
  }

  private void checkTurn() {
    if (!currentTurn.equals(this.model.getCurrentPlayerColor())
        && this.model.getCurrentPlayerColor().equals(this.player.getColor())) {

      if (this.player instanceof HumanPlayer) {
        view.showMessage("Player " + this.player.getColor() + ": It's your turn now");
      }
      else if (this.player instanceof MachinePlayer) {
        ((MachinePlayer) this.player).playAIMove();
      }
    }
    currentTurn = this.model.getCurrentPlayerColor();
  }

  @Override
  public void update() {
    view.setHand(this.model.getCurrentPlayerColor(),
        this.model.getHand(this.model.getCurrentPlayerColor()));
    view.repaint();
    if (this.model.gameOver()) {
      if (this.model.getWinner() == null) {
        view.showMessage("Game resulted in a tie!" + model.getPlayerScore(this.player));
      }
      else {
        view.showMessage("Player " + this.model.getWinner().getColor() + " won with a score of: "
            + model.getPlayerScore(this.model.getWinner()));
      }
    }
    else {
      checkTurn();
    }
  }


  private CardColor colorToCardColor(Color color) {
    if (color == Color.RED) {
      return CardColor.RED;
    }
    else if (color == Color.BLUE) {
      return CardColor.BLUE;
    }
    return null;
  }
}
