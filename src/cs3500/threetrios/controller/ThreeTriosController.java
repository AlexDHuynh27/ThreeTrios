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
  private ThreeTriosModel model;
  private Player player;
  private ThreeTriosGraphicsView view;
  private int selectedCard;
  private boolean gameOverAnnounced;

  public ThreeTriosController(ThreeTriosModel model, Player player, ThreeTriosGraphicsView view) {
    this.model = model;
    this.player = player;
    this.view = view;
    this.selectedCard = -1;
    this.model.addListener(this);
    this.view.addFeaturesListener(this);
    gameOverAnnounced = false;
  }

  /**
   * Displays the view of the controller and announces who's turn it is.
   */
  public void goPlay() {
    this.view.makeVisible();
    checkTurnAndWinner();
  }


  @Override
  public void setSelected(Color color, int selected) {
    CardColor selectedColor = colorToCardColor(color);
    if (!gameOverAnnounced) {
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
    else {
      winnerMessage();
    }

  }

  @Override
  public void playSelectedToGrid(int row, int col) {
    if (!gameOverAnnounced) {
      if (!this.model.getCurrentPlayerColor().equals(this.player.getColor())) {
        view.showMessage("Player " + this.player.getColor() + ": It's not your turn.");
      }
      else if (this.selectedCard == -1) {
        view.showMessage("Player " + this.player.getColor() + ": Please select a card first.");
      }
      else {
        try {
          model.playToGrid(this.selectedCard, row, col);
          view.setHand(this.model.getCurrentPlayerColor(),
              this.model.getHand(this.model.getCurrentPlayerColor()));
          view.setSelected(this.model.getCurrentPlayerColor(), -1);
          this.selectedCard = -1;
          model.battle();
        } catch (IllegalStateException e) {
          view.showMessage("Player " + this.player.getColor() + "You can't play a card there");
        }
      }
    }
    else {
      winnerMessage();
    }
  }

  @Override
  public void checkTurnAndWinner() {
    gameOver();
    if (this.model.getCurrentPlayerColor().equals(this.player.getColor()) &&
        !gameOverAnnounced) {
      if (this.player instanceof HumanPlayer) {
        view.showMessage("Player " + this.player.getColor() + ": It's your turn now");
      }
      else if (this.player instanceof MachinePlayer) {
        ((MachinePlayer) this.player).playAIMove();
        this.model.battle();
      }
    }
  }

  @Override
  public void update() {
    view.setHand(CardColor.BLUE,
        this.model.getHand(CardColor.BLUE));
    view.setHand(CardColor.RED,
        this.model.getHand(CardColor.RED));
    view.setGrid(this.model.getGrid());
    view.repaint();
  }

  private void gameOver() {
    if (this.model.gameOver() && !gameOverAnnounced) {
      winnerMessage();
      gameOverAnnounced = true;
    }
  }

  private void winnerMessage() {
    if (this.model.getWinner() == null) {
      view.showMessage("Game resulted in a tie!" + model.getPlayerScore(this.player));
    }
    else {
      view.showMessage("Player " + this.model.getWinner().getColor() + " won with a score of: "
          + model.getPlayerScore(this.model.getWinner()));
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
