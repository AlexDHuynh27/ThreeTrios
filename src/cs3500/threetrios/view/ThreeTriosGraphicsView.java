package cs3500.threetrios.view;

import cs3500.threetrios.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.Cell;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ThreeTriosGraphicsView extends JFrame implements IView {
  private ReadOnlyThreeTriosModel model;
  private HandPanel redHandPanel;
  private HandPanel blueHandPanel;
  private GridPanel gridPanel;

  public ThreeTriosGraphicsView(ReadOnlyThreeTriosModel model) {
    super();
    this.model = model;
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
  public void setHand(CardColor color, List<ThreeTriosCard> hand) {
    switch (color) {
      case RED:
        redHandPanel.setHand(hand);
        break;
      case BLUE:
        blueHandPanel.setHand(hand);
        break;
    }
  }

  @Override
  public void setGrid(List<Cell> grid) {
    this.gridPanel.setGrid(this.model.getGrid());
  }

  @Override
  public void setSelected(CardColor color, int selected) {
    switch (color) {
      case RED:
        redHandPanel.setSelected(selected);
        blueHandPanel.setSelected(-1);
        break;
      case BLUE:
        blueHandPanel.setSelected(selected);
        redHandPanel.setSelected(-1);
        break;
    }
  }
}
