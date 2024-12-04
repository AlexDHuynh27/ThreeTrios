package cs3500.threetrios.provider.view;

import java.awt.*;

import javax.swing.*;

import cs3500.threetrios.provider.controllerAndFeatures.PlayerActions;
import cs3500.threetrios.provider.model.Player;
import cs3500.threetrios.provider.model.ReadOnlyModel;

/**
 * Represents the GUI-based view for Three Trios.
 * Is composed of 4 components:
 * - the board panel
 * - the red player hand panel
 * - the blue player hand panel
 * - the label displaying whose turn it currently is
 */
public class ThreeTriosSwingView extends JFrame implements SwingView {
  private final BoardPanel boardPanel;
  private final HandPanel playerOneHand;
  private final HandPanel playerTwoHand;
  private final JLabel currentPlayer;

  /**
   * Constructor for the GUI view.
   *
   * @param model is the NON-MUTABLE version of the model that is read from.
   */
  public ThreeTriosSwingView(ReadOnlyModel model, Player windowOwner) {
    setTitle("PLAYER: " + windowOwner.toString());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // init panels
    boardPanel = new BoardPanel(model);
    playerOneHand = new HandPanel(model, Player.RED, windowOwner);
    playerTwoHand = new HandPanel(model, Player.BLUE, windowOwner);

    // display current player turn
    currentPlayer = new JLabel("Current Player: "
            + model.toMove().toString(), SwingConstants.CENTER);
    add(currentPlayer, BorderLayout.NORTH);

    // layout
    add(boardPanel, BorderLayout.CENTER);
    add(playerOneHand, BorderLayout.WEST);
    add(playerTwoHand, BorderLayout.EAST);

    pack();
    setSize(800, 800);
  }

  @Override
  public void updateView() {
    boardPanel.repaint();
    playerOneHand.updateHand();
    playerTwoHand.updateHand();
    playerOneHand.repaint();
    playerTwoHand.repaint();
    currentPlayer.repaint();
    setVisible(true);

  }

  @Override
  public void setPlayerActionListener(PlayerActions listener) {
    boardPanel.setPlayerActionListener(listener);
    playerOneHand.setPlayerActionListener(listener);
    playerTwoHand.setPlayerActionListener(listener);
  }

  @Override
  public void updateCurrentPlayer(Player currentPlayer) {
    this.currentPlayer.setText("Current Player: " + currentPlayer.toString());
  }

  @Override
  public void showMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }
}
