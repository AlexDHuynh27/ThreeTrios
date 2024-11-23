package cs3500.threetrios.view;

import cs3500.threetrios.model.ReadOnlyThreeTriosGameModel;
import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.Cell;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Represents the graphical view of a Three-Trios game. Has the grid to play on
 * in the middle, the red player's hand on the left side, and the blue player's hand
 * on the right side.
 */
public class ThreeTriosGraphicsView extends JFrame implements IView {
  private final HandPanel redHandPanel; // HandPanel of the Red Player.
  private HandPanel blueHandPanel; // HandPanel of the Blue Player.
  private GridPanel gridPanel; // GridPanel of the game.
  private ReadOnlyThreeTriosModel model;

  /**
   * Constructor for ThreeTriosGraphicsView.
   * @param model A ReadOnlyModel that provides the ThreeTriosGraphicsView information to
   *              display
   */
  public ThreeTriosGraphicsView(ThreeTriosModel model) {
    super();

    this.model = new ReadOnlyThreeTriosGameModel(model);

    this.setTitle("Three Trios Game");
    this.setSize(500, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());

    this.gridPanel = new GridPanel();
    this.gridPanel.setGrid(this.model.getGrid());
    this.gridPanel.setPreferredSize(new Dimension(300, 500));
    this.gridPanel.setBackground(Color.BLACK);
    this.gridPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    this.add(gridPanel, BorderLayout.CENTER);

    this.redHandPanel = new HandPanel(Color.RED);
    this.redHandPanel.setPreferredSize(new Dimension(100, 100));
    this.redHandPanel.setBackground(Color.BLACK);
    this.redHandPanel.setHand(this.model.getHand(CardColor.RED));
    this.add(redHandPanel, BorderLayout.WEST);

    this.blueHandPanel = new HandPanel(Color.BLUE);
    this.blueHandPanel.setPreferredSize(new Dimension(100, 100));
    this.blueHandPanel.setBackground(Color.BLACK);
    this.blueHandPanel.setHand(this.model.getHand(CardColor.BLUE));
    this.add(blueHandPanel, BorderLayout.EAST);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setHand(CardColor color, List<Card> hand) {
    if (color == CardColor.RED) {
      this.redHandPanel.setHand(hand);
    }
    else {
      this.blueHandPanel.setHand(hand);
    }
  }

  @Override
  public void setGrid(List<List<Cell>> grid) {
    this.gridPanel.setGrid(grid);
  }

  @Override
  public void setSelected(CardColor color, int selected) {
    if (color == CardColor.RED) {
      redHandPanel.setSelected(selected);
      blueHandPanel.setSelected(-1);
    }
    else {
      blueHandPanel.setSelected(selected);
      redHandPanel.setSelected(-1);
    }
  }

  @Override
  public void addFeaturesListener(ThreeTriosFeatures featuresListener) {
    this.redHandPanel.addFeaturesListener(featuresListener);
    this.blueHandPanel.addFeaturesListener(featuresListener);
    this.gridPanel.addFeaturesListener(featuresListener);
  }

  /**
   * Displays a pop-up message with the given String.
   * @param message The message to display
   */
  public void showMessage(String message) {
    JOptionPane.showMessageDialog(null, message, "message",
        JOptionPane.INFORMATION_MESSAGE);
  }

}
