package cs3500.threetrios.adapters.controller;

import cs3500.threetrios.adapters.model.AdapterUtilities;
import cs3500.threetrios.adapters.model.ModelProviderAdapter;
import cs3500.threetrios.controller.ThreeTriosController;
import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.provider.controllerAndFeatures.ModelStatus;
import cs3500.threetrios.provider.controllerAndFeatures.PlayerActions;
import cs3500.threetrios.provider.model.Model;
import cs3500.threetrios.provider.model.Player;
import cs3500.threetrios.provider.view.SwingView;
import cs3500.threetrios.view.ThreeTriosFeatures;
import java.awt.Color;

public class ControllerAdapter implements ModelStatus, PlayerActions, ThreeTriosFeatures {
  SwingView view;

  ThreeTriosModel model;
  Model providerModel;
  int cardIdx;
  Player thisPlayer;
  Player currentPlayer;

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
