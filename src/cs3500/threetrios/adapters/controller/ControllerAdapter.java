package cs3500.threetrios.adapters.controller;

import cs3500.threetrios.adapters.model.AdapterUtilities;
import cs3500.threetrios.adapters.model.ModelProviderAdapter;
import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.provider.controllerandfeatures.ModelStatus;
import cs3500.threetrios.provider.controllerandfeatures.PlayerActions;
import cs3500.threetrios.provider.model.Model;
import cs3500.threetrios.provider.model.Player;
import cs3500.threetrios.provider.view.SwingView;
import cs3500.threetrios.view.ThreeTriosFeatures;
import java.awt.Color;

/**
 * Adapts our group's code with the provider's Controller interfaces (ModelStatus, PlayerActions).
 */
public class ControllerAdapter implements ModelStatus, PlayerActions, ThreeTriosFeatures {
  private final SwingView view;
  private final ThreeTriosModel model;
  private final Model providerModel;
  private int cardIdx;
  private final Player thisPlayer;
  private Player currentPlayer;

  /**
   * Constructor for ControllerAdapter in order to adapt our code to their Controller interfaces.
   *
   * @param view a SwingView instance from the provider's view code
   * @param model a ThreeTriosModel instance from our group's code
   * @param player a Player instance from our group's code
   */
  public ControllerAdapter(SwingView view, ThreeTriosModel model, Player player) {
    this.view = view;
    this.providerModel = new ModelProviderAdapter(model);
    this.model = model;
    this.view.updateView();
    this.cardIdx = -1;
    this.thisPlayer = player;
    this.model.addListener(this);
    this.view.setPlayerActionListener(this);
    this.providerModel.setModelStatusListener(this);
  }

  @Override
  public void onTurnChanged(Player currentPlayer) {
    view.updateView();
    if (currentPlayer == thisPlayer && !model.gameOver()) {
      view.showMessage("Your turn now: " + currentPlayer);
    }
    this.currentPlayer = currentPlayer;
  }

  @Override
  public void onGameOver(Player winner) {
    String s = "Winner is " + winner.toString() + "With a score of"
        + providerModel.getWinningScore();
    view.showMessage(s);
  }

  @Override
  public void onCardSelected(int cardIdx, Player windowOwner, Player cardOwner) {
    if (windowOwner == currentPlayer && windowOwner == cardOwner && thisPlayer == windowOwner) {
      if (this.cardIdx == cardIdx) {
        this.cardIdx = -1;
      }
      else {
        this.cardIdx = cardIdx;
      }
    }
  }

  @Override
  public void onCellSelected(int row, int col) {
    System.out.print(row + ", " + col);
    if (currentPlayer == thisPlayer) {
      try {
        providerModel.playToBoard(row, col, cardIdx);
        this.cardIdx = -1;
      }
      catch (IllegalArgumentException e) {
        view.showMessage("Select a card first");
      }
    }
  }

  @Override
  public void setSelected(Color color, int selected) {
    int i = 1;
  }

  @Override
  public void playSelectedToGrid(int row, int col) {
    int i = 1;
  }

  @Override
  public void checkTurnAndWinner() {
    int i = 1;
  }

  @Override
  public void update() {
    this.onTurnChanged(AdapterUtilities.colorToPlayer(model.getCurrentPlayerColor()));
  }
}
