package cs3500.threetrios.view;

import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.cell.Hole;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

/**
 * A graphical representation of the grid in a ThreeTriosGame.
 * Holes are represented by gray rectangles.
 * Empty CardCells are represented by yellow rectangles.
 * And CardCells that aren't empty are represented by a rectangle of the Color of the card owner,
 * as well as the attack values being displayed on the corresponding direction.
 */
public class GridPanel extends JPanel {
  private List<List<Cell>> grid; // Grid

  /**
   * Constructor for a gridpanel.
   */
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
        int scaleX = this.getWidth() / grid.size();
        int scaleY = this.getHeight() / grid.size();
        if (cell instanceof Hole) {
          g2d.setColor(Color.GRAY);
          g2d.fillRect(scaleX * j + 1, scaleY * i + 1, scaleX - 2,
              scaleY - 2);
        }
        else if (cell.isEmpty()) {
          g2d.setColor(Color.YELLOW);
          g2d.fillRect(scaleX * j + 1, scaleY * i + 1, scaleX - 2,
              scaleY - 2);
        }
        else {
          g2d.setColor(cardToColor(cell.getCard()));
          g2d.fillRect(scaleX * j + 1, scaleY * i + 1, scaleX - 2,
              scaleY - 2);
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

  /**
   * Provide the GridPanel with the current grid
   * of the ThreeTriosModel, presumably to show it.
   *
   * @param grid the grid of a ThreeTriosModel to show
   */
  public void setGrid(List<List<Cell>> grid) {
    this.grid = grid;
  }
}
