package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.cell.CardCell;
import cs3500.threetrios.model.player.Player;

/**
 * Represents the model of ThreeTriosGame. Contains the data and logic, to ensure that the game
 * is played correctly by the rules.
 */
public class ThreeTriosGameModel implements ThreeTriosModel {
  private final Random rand;
  private List<List<Cell>> grid;
  private List<ThreeTriosCard> deck;
  private Player redPlayer;
  private Player bluePlayer;
  private CardColor colorTurn;
  private boolean playedToGrid;
  private boolean gameStarted;
  private boolean gameOver;
  private List<Integer> attackingCardRows;
  private List<Integer> attackingCardCols;

  /**
   * Constructor for randomizing the shuffling of deck and actual gameplay.
   */
  public ThreeTriosGameModel() {
    this.rand = new Random();
  }

  /**
   * Constructor for testing, so that deck has the same shuffled list.
   *
   * @param random random
   */
  public ThreeTriosGameModel(Random random) {
    this.rand = random;
  }


  @Override
  public void startGame(List<List<Cell>> grid, List<ThreeTriosCard> deck, Player redPlayer,
                        Player bluePlayer) {
    // game started?
    if (gameStarted) {
      throw new IllegalStateException("Game has already started.");
    }

    // validate input
    if (grid == null || deck == null || redPlayer == null || bluePlayer == null ||
        deck.contains(null) || grid.contains(null)) {
      throw new IllegalArgumentException("Input cannot be null or have a null");
    }

    // Count the number of card cells
    int cardCellCount = countCardCells(grid);
    int handSize = (cardCellCount + 1) / 2;

    // Check if the deck has enough cards (at least N+1)
    int requiredDeckSize = cardCellCount + 1;
    if (deck.size() < requiredDeckSize) {
      throw new IllegalArgumentException("The deck must contain at least " + requiredDeckSize +
              " cards.");
    }

    // Assign values
    this.grid = grid;
    this.deck = new ArrayList<>(deck);
    redPlayer.setColor(CardColor.RED);
    this.redPlayer = redPlayer;
    bluePlayer.setColor(CardColor.BLUE);
    this.bluePlayer = bluePlayer;
    this.colorTurn = CardColor.RED;
    this.gameStarted = true;
    this.gameOver = false;
    this.attackingCardRows = new ArrayList<>();
    this.attackingCardCols = new ArrayList<>();

    // deal cards
    dealCards(cardCellCount);
  }

  @Override
  public void playToGrid(int curPlayerHandIdx, int row, int column) {
    if (!isLegalPlay(row, column)) {
      throw new IllegalStateException("Cannot play to the specified cell: illegal move.");
    }

    ThreeTriosCard card;
    if (colorTurn == CardColor.RED) {
      card = redPlayer.playFromHand(curPlayerHandIdx);
    } else {
      card = bluePlayer.playFromHand(curPlayerHandIdx);
    }

    grid.get(row).get(column).setCard(card);
    attackingCardRows.add(row);
    attackingCardCols.add(column);
    playedToGrid = true;
  }

  @Override
  public boolean isLegalPlay(int row, int column) {
    if (!gameStarted || gameOver) {
      throw new IllegalStateException("Game hasn't started or is already over.");
    }
    if (row < 0 || row >= grid.size() || column < 0 || column >= grid.get(row).size()) {
      throw new IllegalArgumentException("Row or column out of bounds");
    }
    Cell cell = grid.get(row).get(column);
    if (!(cell instanceof CardCell)) {
      return false;
    }
    if (!cell.isEmpty()) {
      return false;
    }
    if (playedToGrid) {
      return false;
    }
    return true;
  }

  @Override
  public void battle() {
    if (!gameStarted || gameOver) {
      throw new IllegalStateException("Cannot battle: game hasn't started or is already over.");
    } else if (!playedToGrid) {
      throw new IllegalStateException("Cannot battle: haven't played to grid this turn yet");
    }

    while (!attackingCardRows.isEmpty()) {
      int attackRow = attackingCardRows.remove(0);
      int attackCol = attackingCardCols.remove(0);

      processBattle(attackRow, attackCol, attackRow - 1, attackCol, Direction.NORTH);
      processBattle(attackRow, attackCol, attackRow + 1, attackCol, Direction.SOUTH);
      processBattle(attackRow, attackCol, attackRow, attackCol - 1, Direction.WEST);
      processBattle(attackRow, attackCol, attackRow, attackCol + 1, Direction.EAST);
    }

    switchTurn();
  }


  @Override
  public boolean gameOver() {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not started.");
    }

    // Check if all card cells on the grid are filled
    for (List<Cell> row : grid) {
      for (Cell cell : row) {
        if (cell instanceof CardCell && cell.isEmpty()) {
          return false;
        }
      }
    }

    gameOver = true;
    return true;
  }

  @Override
  public Player getWinner() {
    if (!gameOver()) {
      throw new IllegalStateException("Cannot determine winner: game is not over.");
    }

    int redScore = getPlayerScore(redPlayer);
    int blueScore = getPlayerScore(bluePlayer);

    if (redScore > blueScore) {
      return redPlayer;
    } else if (blueScore > redScore) {
      return bluePlayer;
    } else {
      return null;
    }
  }

  @Override
  public List<ThreeTriosCard> getHand(CardColor color) {
    if (color == CardColor.RED) {
      return redPlayer.getHand();
    } else {
      return bluePlayer.getHand();
    }
  }

  @Override
  public CardColor getCurrentPlayerColor() {
    return colorTurn;
  }

  @Override
  public List<List<Cell>> getGrid() {
    return List.copyOf(this.grid);
  }

  @Override
  public int getPlayerScore(Player player) {
    int score = player.getCurrentHandSize();
    CardColor playerColor = player.getColor();

    for (List<Cell> row : grid) {
      for (Cell cell : row) {
        if (cell instanceof CardCell && cell.toString().equals(playerColor == CardColor.RED ?
                "R" : "B")) {
          score++;
        }
      }
    }
    return score;
  }

  @Override
  public Cell getCellAt(int row, int column) {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not started.");
    }
    if (row < 0 || row >= grid.size() || column < 0 || column >= grid.get(row).size()) {
      throw new IllegalArgumentException("Row or column out of bounds");
    }
    return grid.get(row).get(column);
  }

  @Override
  public CardColor getCardOwnerColorAt(int row, int column) {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not started.");
    }
    if (row < 0 || row >= grid.size() || column < 0 || column >= grid.get(row).size()) {
      throw new IllegalArgumentException("Row or column out of bounds");
    }
    Cell cell = grid.get(row).get(column);
    if (cell instanceof CardCell) {
      ThreeTriosCard card = cell.getCard();
      if (card != null) {
        return card.getColor();
      }
    }
    return null;
  }

  public int getGridSize() {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not started.");
    }
    return countCardCells(grid);
  }

  /**
   * Shuffles this deck based on this ThreeTriosGameModel's Random object.
   */
  private void shuffle() {
    List<ThreeTriosCard> retDeck = new ArrayList<ThreeTriosCard>();
    int loopCount = this.deck.size();
    for (int i = 0; i < loopCount; i++) {
      retDeck.add(this.deck.remove(this.rand.nextInt(deck.size())));
    }
    this.deck = retDeck;
  }

  /**
   * Deals cards randomly to both players until their hands are filled.
   * Each player gets (N+1)/2 cards, where N is the number of card cells.
   *
   * @param cardCellCount the number of card cells in the grid
   */
  private void dealCards(int cardCellCount) {
    int handSize = (cardCellCount + 1) / 2;
    shuffle();

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
    playedToGrid = false;
  }

  private void processBattle(int attackRow, int attackCol, int targetRow, int targetCol, Direction direction) {
    if (targetRow >= 0 && targetRow < grid.size() && targetCol >= 0 && targetCol < grid.get(targetRow).size()) {
      if (grid.get(attackRow).get(attackCol).battleCell(grid.get(targetRow).get(targetCol), direction)) {
        grid.get(targetRow).get(targetCol).flipCell();
        attackingCardRows.add(targetRow);
        attackingCardCols.add(targetCol);
      }
    }
  }
}
