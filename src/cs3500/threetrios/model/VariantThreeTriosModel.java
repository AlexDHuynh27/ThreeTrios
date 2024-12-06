package cs3500.threetrios.model;

import cs3500.threetrios.model.battlerules.BattleRule;
import cs3500.threetrios.model.battlerules.StandardBattleRule;
import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.CardCell;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.cell.Hole;
import cs3500.threetrios.model.player.Player;
import cs3500.threetrios.view.ThreeTriosFeatures;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VariantThreeTriosModel implements ThreeTriosModel {
  private final BattleRule rule;
  private final Random rand;
  private List<List<Cell>> grid;
  private List<Card> deck;
  private Player redPlayer;
  private Player bluePlayer;
  private CardColor colorTurn;
  private boolean playedToGrid;
  private boolean gameStarted;
  private boolean gameOver;
  private List<Integer> attackingCardRows;
  private List<Integer> attackingCardCols;

  private final List<ThreeTriosFeatures> listeners;

  /**
   * Constructor for randomizing the shuffling of deck and actual gameplay.
   */
  public VariantThreeTriosModel(BattleRule rule) {
    this.rule = rule;
    this.rand = new Random();
    this.listeners = new ArrayList<>();
  }

  /**
   * Constructor for testing, so that deck has the same shuffled list.
   *
   * @param random random
   */
  public VariantThreeTriosModel(BattleRule rule, Random random) {
    this.rule = rule;
    this.rand = random;
    this.listeners = new ArrayList<>();
  }

  @Override
  public void startGame(List<List<Cell>> grid, List<Card> deck, Player redPlayer, Player bluePlayer) {
    if (gameStarted) {
      throw new IllegalStateException("Game has already started.");
    }

    // Validate input
    if (grid == null || deck == null || redPlayer == null || bluePlayer == null
            || deck.contains(null) || grid.contains(null)) {
      throw new IllegalArgumentException("Input cannot be null or have a null");
    }

    // Count the number of card cells
    int cardCellCount = countCardCells(grid);
    int handSize = (cardCellCount + 1) / 2;

    // Check if the deck has enough cards
    int requiredDeckSize = cardCellCount + 1;
    if (deck.size() < requiredDeckSize) {
      throw new IllegalArgumentException("The deck must contain at least " + requiredDeckSize + " cards.");
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

    // Deal cards
    dealCards(cardCellCount);
  }

  @Override
  public void playToGrid(int curPlayerHandIdx, int row, int column) {
    if (!isLegalPlay(row, column)) {
      throw new IllegalStateException("Cannot play to the specified cell: illegal move.");
    }

    Card card;
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
    return cell instanceof CardCell && cell.isEmpty() && !playedToGrid;
  }

  @Override
  public void battle() {
    this.grid =
            rule.applyRule(grid.get(attackingCardRows.get(0))
                            .get(attackingCardCols.get(0)).getCard(),
            attackingCardRows.get(0), attackingCardCols.get(0), this);

    switchTurn();
    somethingChanged();
  }

  @Override
  public void addListener(ThreeTriosFeatures listener) {
    this.listeners.add(listener);
  }

  @Override
  public void somethingChanged() {
    for (ThreeTriosFeatures listener : listeners) {
      listener.update();
    }
    for (ThreeTriosFeatures listener : listeners) {
      listener.checkTurnAndWinner();
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
  public List<Card> getHand(CardColor color) {
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
    List<List<Cell>> gridCopy = new ArrayList<>();
    for (List<Cell> rowList : grid) {
      List<Cell> rowCopy = new ArrayList<>();
      for (Cell c : rowList) {
        if (c instanceof CardCell) {
          CardCell cellCopy = new CardCell();
          Card cellCard = c.getCard();
          if (cellCard != null) {
            // Create a copy of the card
            Card cardCopy = new ThreeTriosCard(cellCard.getName(),
                cellCard.getAttack(Direction.NORTH),
                cellCard.getAttack(Direction.SOUTH),
                cellCard.getAttack(Direction.EAST),
                cellCard.getAttack(Direction.WEST));
            cardCopy.setColor(cellCard.getColor());
            cellCopy.setCard(cardCopy);
          }
          rowCopy.add(cellCopy);
        } else {
          rowCopy.add(new Hole());
        }
      }
      gridCopy.add(rowCopy);
    }
    return gridCopy;
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
      Card card = cell.getCard();
      if (card != null) {
        return card.getColor();
      }
    }
    return null;
  }

  @Override
  public int getGridSize() {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not started.");
    }
    return countCardCells(grid);
  }

  @Override
  public int howManyFlips(Card card, int row, int column) {
    validateSimulationParameters(card, row, column);
    List<List<Cell>> gridCopy = getGrid();
    placeCardInGridCopy(card, row, column, gridCopy);

    return simulateBattle(gridCopy, row, column);
  }

  private void validateSimulationParameters(Card card, int row, int column) {
    if (!gameStarted || gameOver) {
      throw new IllegalStateException("Cannot simulate battle: game hasn't started or is already" +
          " over.");
    }
    if (card == null) {
      throw new IllegalArgumentException("Card cannot be null.");
    }
    if (row < 0 || row >= grid.size() || column < 0 || column >= grid.get(row).size()) {
      throw new IllegalArgumentException("Row or column out of bounds");
    }
    Cell cell = grid.get(row).get(column);
    if (!(cell instanceof CardCell) || !cell.isEmpty()) {
      throw new IllegalArgumentException("Invalid cell to play on.");
    }
  }

  private void placeCardInGridCopy(Card card, int row, int column,
                                   List<List<Cell>> gridCopy) {
    Card cardCopy = new ThreeTriosCard(
        card.getName(),
        card.getAttack(Direction.NORTH),
        card.getAttack(Direction.SOUTH),
        card.getAttack(Direction.EAST),
        card.getAttack(Direction.WEST));
    cardCopy.setColor(card.getColor());
    gridCopy.get(row).get(column).setCard(cardCopy);
  }

  private int simulateBattle(List<List<Cell>> gridCopy, int startRow, int startCol) {
    List<int[]> attackingPositions = new ArrayList<>();
    attackingPositions.add(new int[]{startRow, startCol});
    int flipCount = 0;

    int[][] directions = {
        {-1, 0, Direction.NORTH.ordinal()},
        {1, 0, Direction.SOUTH.ordinal()},
        {0, -1, Direction.WEST.ordinal()},
        {0, 1, Direction.EAST.ordinal()}};

    while (!attackingPositions.isEmpty()) {
      int[] pos = attackingPositions.remove(0);
      int attackRow = pos[0];
      int attackCol = pos[1];
      for (int[] dir : directions) {
        int targetRow = attackRow + dir[0];
        int targetCol = attackCol + dir[1];
        Direction direction = Direction.values()[dir[2]];
        flipCount += processSimulatedBattle(gridCopy, attackingPositions, attackRow, attackCol,
            targetRow, targetCol, direction);
      }
    }
    return flipCount;
  }

  private int processSimulatedBattle(List<List<Cell>> gridCopy, List<int[]> attackingPositions,
                                     int attackRow, int attackCol, int targetRow, int targetCol,
                                     Direction direction) {
    if (targetRow >= 0 && targetRow < gridCopy.size() && targetCol >= 0
        && targetCol < gridCopy.get(targetRow).size()) {
      Cell attackingCell = gridCopy.get(attackRow).get(attackCol);
      Cell targetCell = gridCopy.get(targetRow).get(targetCol);

      if (targetCell instanceof CardCell && !targetCell.isEmpty()) {
        if (!attackingCell.toString().equals(targetCell.toString())
            && attackingCell.battleCell(targetCell, direction)) {
          targetCell.flipCell();
          attackingPositions.add(new int[]{targetRow, targetCol});
          return 1;
        }
      }
    }
    return 0;
  }


  /**
   * Shuffles this deck based on this ThreeTriosGameModel's Random object.
   */
  private void shuffle() {
    List<Card> retDeck = new ArrayList<Card>();
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

  private void processBattle(int attackRow, int attackCol, int targetRow, int targetCol,
                             Direction direction) {
    if (targetRow >= 0 && targetRow < grid.size() && targetCol >= 0
        && targetCol < grid.get(targetRow).size()) {
      if (grid.get(attackRow).get(attackCol)
          .battleCell(grid.get(targetRow).get(targetCol), direction)) {
        grid.get(targetRow).get(targetCol).flipCell();
        attackingCardRows.add(targetRow);
        attackingCardCols.add(targetCol);
      }
    }
  }

  public Cell getAdjacentCell(int row, int col, Direction direction) {
    switch (direction) {
      case NORTH:
        if (row > 0) {
          return grid.get(row - 1).get(col);
        }
        break;
      case SOUTH:
        if (row < grid.size() - 1) {
          return grid.get(row + 1).get(col);
        }
        break;
      case EAST:
        if (col < grid.get(row).size() - 1) {
          return grid.get(row).get(col + 1);
        }
        break;
      case WEST:
        if (col > 0) {
          return grid.get(row).get(col - 1);
        }
        break;
    }
    return null;
  }
}