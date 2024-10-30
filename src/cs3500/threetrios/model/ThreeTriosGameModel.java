package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.cell.CardCell;
import cs3500.threetrios.model.player.Player;

public class ThreeTriosGameModel implements ThreeTriosModel {
  private List<List<Cell>> grid;
  private List<ThreeTriosCard> deck;
  private Player redPlayer;
  private Player bluePlayer;
  private CardColor colorTurn;
  private boolean battled;
  private boolean playedToGrid;
  private boolean gameStarted;
  private boolean gameOver;
  private List<Integer> attackingCardRows;
  private List<Integer> attackingCardCols;
  private int handSize;

  @Override
  public void startGame(List<List<Cell>> grid, List<ThreeTriosCard> deck, Player redPlayer,
                        Player bluePlayer) {
    // game started?
    if (gameStarted) {
      throw new IllegalStateException("Game has already started.");
    }

    // validate input
    if (grid == null || deck == null || redPlayer == null || bluePlayer == null) {
      throw new IllegalArgumentException("Input cannot be null");
    }

    // Count the number of card cells
    int cardCellCount = countCardCells(grid);
    handSize = (cardCellCount + 1) / 2;

    // Check if the deck has enough cards (at least N+1)
    int requiredDeckSize = cardCellCount + 1;
    if (deck.size() < requiredDeckSize) {
      throw new IllegalArgumentException("The deck must contain at least " + requiredDeckSize +
              " cards.");
    }

    // Assign values
    this.grid = grid;
    this.deck = new ArrayList<>(deck);
    this.redPlayer = redPlayer;
    this.bluePlayer = bluePlayer;
    this.colorTurn = CardColor.RED;
    this.gameStarted = true;
    this.gameOver = false;
    this.attackingCardRows = new ArrayList<>();
    this.attackingCardCols = new ArrayList<>();

    // deal cards
    dealCards(cardCellCount);
  }

  /**
   * Deals cards randomly to both players until their hands are filled.
   * Each player gets (N+1)/2 cards, where N is the number of card cells.
   * @param cardCellCount the number of card cells in the grid
   */
  private void dealCards(int cardCellCount) {
    int handSize = (cardCellCount + 1) / 2;

    Collections.shuffle(deck);

    // distribute cards to red
    for (int i = 0; i < handSize; i++) {
      if (!deck.isEmpty()) {
        redPlayer.addToHand(deck.remove(0));
      }
    }

    // distribute cards to blue
    for (int i = 0; i < handSize; i++) {
      if (!deck.isEmpty()) {
        bluePlayer.addToHand(deck.remove(0));
      }
    }
  }

  @Override
  public void drawHand() {
    if (!gameStarted || gameOver) {
      throw new IllegalStateException("Cannot draw hands: game hasn't started or is already over.");
    }

    Player currentPlayer = getCurrentPlayer();
    while (currentPlayer.getCurrentHandSize() < handSize && !deck.isEmpty()) {
      currentPlayer.addToHand(deck.remove(0));
    }

    switchTurn();
  }

  @Override
  public void playToGrid(int curPlayerHandIdx, int row, int column) {
    if (!gameStarted || gameOver) {
      throw new IllegalStateException("Cannot play to grid: game hasn't started or is already over.");
    }
    else if (row >= grid.size() || column >= grid.get(row).size()) {
      throw new IllegalArgumentException("Row or column out of bounds");
    }
    else if (!(grid.get(row).get(column) instanceof CardCell)) {
      throw new IllegalArgumentException("Row and column given is not a card cell");
    }
    else if (!grid.get(row).get(column).isEmpty()) {
      throw new IllegalStateException("CardCell to play on is not empty");
    }
    else if (playedToGrid) {
      throw new IllegalStateException("Already played to grid this turn");
    }
    else {
      if (colorTurn == CardColor.RED) {
        grid.get(row).get(column).setCard(redPlayer.playFromHand(curPlayerHandIdx));
      }
      else {
        grid.get(row).get(column).setCard(bluePlayer.playFromHand(curPlayerHandIdx));
      }
      playedToGrid = true;
    }
  }

  private int getGridRowIdx(Cell cell) {
    for (int i = 0; i < grid.size(); i++) {
      for (int j = 0; j < grid.get(i).size(); j++) {
        if (grid.get(i).get(j).equals(cell)) {
          return i;
        }
      }
    }
    return -1;
  }

  private int getGridColIdx(Cell cell) {
    for (int i = 0; i < grid.size(); i++) {
      for (int j = 0; j < grid.get(i).size(); j++) {
        if (grid.get(i).get(j).equals(cell)) {
          return j;
        }
      }
    }
    return -1;
  }

  @Override
  public void battle() {
    int attackRow;
    int attackCol;
    while (!attackingCardRows.isEmpty()) {
      attackRow = attackingCardRows.get(0);
      attackCol = attackingCardCols.get(0);
      if (attackRow - 1 >= 0) {
        if (grid.get(attackRow).get(attackCol).battleCell(grid.get(attackRow -1).get(attackCol), Direction.NORTH)) {
          grid.get(attackRow -1).get(attackCol).flipCell();
        }
      }
      if (attackRow + 1 < grid.size()) {
        if (grid.get(attackRow).get(attackCol).battleCell(grid.get(attackRow + 1).get(attackCol), Direction.SOUTH)) {
          grid.get(attackRow + 1).get(attackCol).flipCell();
        }
      }
      if (attackCol - 1 >= 0) {
        if (grid.get(attackRow).get(attackCol).battleCell(grid.get(attackRow).get(attackCol - 1), Direction.WEST)) {
          grid.get(attackRow).get(attackCol - 1).flipCell();
        }
      }
      if (attackCol + 1 < grid.size()) {
        if (grid.get(attackRow).get(attackCol).battleCell(grid.get(attackRow).get(attackCol + 1), Direction.EAST)) {
          grid.get(attackRow).get(attackCol + 1).flipCell();
        }
      }
    }
  }


  @Override
  public boolean gameOver() {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not started.");
    }

    // Check if all card cells on the grid are filled
    for (List<Cell> row : grid) {
      for (Cell cell : row) {
        if (cell instanceof CardCell && !cell.isEmpty()) {
          return false;
        }
      }
    }

    return true;
  }

  @Override
  public Player getWinner() {
    if (!gameOver) {
      throw new IllegalStateException("Cannot determine winner: game is not over.");
    }

    int redCount = redPlayer.getCurrentHandSize(); // Count red player's cards
    int blueCount = bluePlayer.getCurrentHandSize(); // Count blue player's cards

    // Count the number of cards owned on the grid
    for (List<Cell> row : grid) {
      for (Cell cell : row) {
        if (cell instanceof CardCell) {
          String cardColor = cell.toString();
          if (cardColor.equals("R")) {
              redCount++;
            } else if (cardColor.equals("B")) {
              blueCount++;
            }
          }
        }
      }

    // Determine the winner based on card counts
    if (redCount > blueCount) {
      return redPlayer; // Red player wins
    } else if (blueCount > redCount) {
      return bluePlayer; // Blue player wins
    } else {
      return null; // It's a tie
    }
  }


  /////////////////////////////////////////////////////////////////////////////////////////////////

  private int countCardCells(List<List<Cell>> grid) {
    int cardCellCount = 0;
    for (List<Cell> row : grid) {
      for (Cell cell : row) {
        if (cell instanceof CardCell) {
          cardCellCount++;
        }
      }
    }
    return cardCellCount;
  }

  private void switchTurn() {
    colorTurn = (colorTurn == CardColor.RED) ? CardColor.BLUE : CardColor.RED;
  }

  private Player getCurrentPlayer() {
    return (colorTurn == CardColor.RED) ? redPlayer : bluePlayer;
  }
}
