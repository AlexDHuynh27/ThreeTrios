package cs3500.threetrios.view;

import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.cell.Hole;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class GridPanel extends JPanel {
  List<List<Cell>> grid;

  public GridPanel() {
    this.grid = new ArrayList<List<Cell>>();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.setLayout(new GridLayout(this.grid.size(), this.grid.size()));

    Graphics2D g2d = (Graphics2D) g;
    g2d.setFont(new Font("Times New Roman", Font.PLAIN,
        25));


    for (int i = 0; i < grid.size(); i++) {
      for (int j = 0; j < grid.size(); j++) {
        Cell cell = grid.get(i).get(j);
        int ScaleX = this.getWidth() / grid.size();
        int ScaleY = this.getHeight() / grid.size();
        if (cell instanceof Hole) {
          g2d.setColor(Color.GRAY);
          g2d.fillRect(ScaleX * j + 1,
              ScaleY * i + 1,
              ScaleX - 2,
              ScaleY - 2);

        }
        else if (cell.isEmpty()) {
          g2d.setColor(Color.YELLOW);
          g2d.fillRect(ScaleX * j + 1,
              ScaleY * i + 1,
              ScaleX - 2,
              ScaleY - 2);
        }
        else {
          g2d.setColor(cardToColor(cell.getCard()));
          g2d.fillRect(ScaleX * j + 1,
              ScaleY * i + 1,
              ScaleX - 2,
              ScaleY - 2);
          drawAttacks(g2d, i, j);
        }
      }
    }
  }

  private void drawAttacks(Graphics2D g, int index, int index2) {
    g.setColor(Color.BLACK);
    int scaleX = this.getWidth() / this.grid.size();
    int scaleY = this.getHeight() / this.grid.size();
    ThreeTriosCard card = this.grid.get(index).get(index2).getCard();
    g.drawString(card.attackToString(Direction.WEST),
        scaleX * index2 + 10,
        (scaleY * index) + (scaleY / 2) + 10);
    g.drawString(card.attackToString(Direction.EAST),
        scaleX * (index2 + 1) - 15,
        (scaleY * index) + (scaleY / 2) + 10);
    g.drawString(card.attackToString(Direction.NORTH),
        (scaleX * index2) + (scaleX / 2),
        ((this.getHeight() / grid.size()) * index) + (scaleY / 5) + 10);
    g.drawString(card.attackToString(Direction.SOUTH),
        (scaleX * index2) + (scaleX / 2),
        (scaleY * (index + 1)) - (scaleY / 5) + 10);
  }

  private Color cardToColor(ThreeTriosCard card) {
    if (card.getColor() == CardColor.RED) {
      return Color.RED;
    }
    else {
      return Color.BLUE;
    }
  }

  void setGrid(List<List<Cell>> grid) {
    this.grid = grid;
  }
}
