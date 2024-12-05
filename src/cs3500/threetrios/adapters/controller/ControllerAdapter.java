package cs3500.threetrios.adapters.controller;

import cs3500.threetrios.provider.controllerAndFeatures.ModelStatus;
import cs3500.threetrios.provider.controllerAndFeatures.PlayerActions;
import cs3500.threetrios.provider.model.Model;
import cs3500.threetrios.provider.model.Player;
import cs3500.threetrios.provider.view.SwingView;

public class ControllerAdapter implements ModelStatus, PlayerActions {
  SwingView view;
  Model model;
  int cardIdx;
  Player thisPlayer;
  Player currentPlayer;

  public ControllerAdapter(SwingView view, Model model, Player player) {
    model.setModelStatusListener(this);
    view.setPlayerActionListener(this);
    this.view = view;
    this.model = model;
    this.view.updateView();
    this.cardIdx = -1;
    this.thisPlayer = player;
  }

  @Override
  public void onTurnChanged(Player currentPlayer) {
    view.updateView();
    if (currentPlayer == thisPlayer) {
      view.updateCurrentPlayer(currentPlayer);
    }
    this.currentPlayer = currentPlayer;
  }

  @Override
  public void onGameOver(Player winner) {
    String s = "Winner is " + winner.toString() + "With a score of" + model.getWinningScore();
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
    if (currentPlayer == thisPlayer) {
      try {
        model.playToBoard(row, col, this.cardIdx);
      }
      catch (IllegalArgumentException e) {
        view.showMessage("Select a card first");
      }
    }
  }
}
